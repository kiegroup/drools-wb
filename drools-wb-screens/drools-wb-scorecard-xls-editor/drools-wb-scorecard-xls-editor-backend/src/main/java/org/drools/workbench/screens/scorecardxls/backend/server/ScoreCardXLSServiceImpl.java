/*
 * Copyright 2012 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.workbench.screens.scorecardxls.backend.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.IOUtils;
import org.drools.workbench.screens.scorecardxls.service.ScoreCardXLSContent;
import org.drools.workbench.screens.scorecardxls.service.ScoreCardXLSService;
import org.guvnor.common.services.backend.config.SafeSessionInfo;
import org.guvnor.common.services.backend.exceptions.ExceptionUtilities;
import org.guvnor.common.services.backend.util.CommentedOptionFactory;
import org.guvnor.common.services.backend.validation.GenericValidator;
import org.guvnor.common.services.shared.metadata.model.Overview;
import org.guvnor.common.services.shared.validation.model.ValidationMessage;
import org.jboss.errai.bus.server.annotations.Service;
import org.jboss.errai.security.shared.service.AuthenticationService;
import org.kie.workbench.common.services.backend.service.KieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uberfire.backend.server.util.Paths;
import org.uberfire.backend.vfs.Path;
import org.uberfire.ext.editor.commons.service.CopyService;
import org.uberfire.ext.editor.commons.service.DeleteService;
import org.uberfire.ext.editor.commons.service.RenameService;
import org.uberfire.io.IOService;
import org.uberfire.java.nio.file.StandardOpenOption;
import org.uberfire.rpc.SessionInfo;
import org.uberfire.rpc.impl.SessionInfoImpl;
import org.uberfire.workbench.events.ResourceOpenedEvent;

@Service
@ApplicationScoped
// Implementation needs to implement both interfaces even though one extends the other
// otherwise the implementation discovery mechanism for the @Service annotation fails.
public class ScoreCardXLSServiceImpl
        extends KieService<ScoreCardXLSContent>
        implements ScoreCardXLSService,
                   ExtendedScoreCardXLSService {

    private static final Logger log = LoggerFactory.getLogger( ScoreCardXLSServiceImpl.class );

    private CopyService copyService;
    private DeleteService deleteService;
    private RenameService renameService;
    private Event<ResourceOpenedEvent> resourceOpenedEvent;
    private GenericValidator genericValidator;
    private CommentedOptionFactory commentedOptionFactory;
    private AuthenticationService authenticationService;

    public ScoreCardXLSServiceImpl() {
    }

    @Inject
    public ScoreCardXLSServiceImpl( @Named("ioStrategy") final IOService ioService,
                                    final CopyService copyService,
                                    final DeleteService deleteService,
                                    final RenameService renameService,
                                    final Event<ResourceOpenedEvent> resourceOpenedEvent,
                                    final GenericValidator genericValidator,
                                    final CommentedOptionFactory commentedOptionFactory,
                                    final AuthenticationService authenticationService ) {
        this.ioService = ioService;
        this.copyService = copyService;
        this.deleteService = deleteService;
        this.renameService = renameService;
        this.resourceOpenedEvent = resourceOpenedEvent;
        this.genericValidator = genericValidator;
        this.commentedOptionFactory = commentedOptionFactory;
        this.authenticationService = authenticationService;
    }

    @Override
    protected ScoreCardXLSContent constructContent( Path path,
                                                    Overview overview ) {
        final ScoreCardXLSContent content = new ScoreCardXLSContent();
        content.setOverview( overview );
        return content;
    }

    @Override
    public InputStream load( final Path path,
                             final String sessionId ) {
        try (final InputStream inputStream = ioService.newInputStream( Paths.convert( path ),
                                                                       StandardOpenOption.READ )) {
            //Signal opening to interested parties
            resourceOpenedEvent.fire( new ResourceOpenedEvent( path,
                                                               getSessionInfo( sessionId ) ) );

            return inputStream;

        } catch ( Exception e ) {
            throw ExceptionUtilities.handleException( e );
        }
    }

    @Override
    public Path create( final Path resource,
                        final InputStream content,
                        final String sessionId,
                        final String comment ) {
        return writeToFile( resource, content, sessionId, comment, true );
    }

    @Override
    public Path save( final Path resource,
                      final InputStream content,
                      final String sessionId,
                      final String comment ) {
        return writeToFile( resource, content, sessionId, comment, false );
    }

    private Path writeToFile( final Path resource,
                              final InputStream content,
                              final String sessionId,
                              final String comment,
                              boolean create ) {
        final SessionInfo sessionInfo = getSessionInfo( sessionId );

        log.info("USER: {} {} asset [{}]", sessionInfo.getIdentity().getIdentifier(), create ? "CREATING" : "UPDATING", resource.getFileName());

        final org.uberfire.java.nio.file.Path nioPath = Paths.convert( resource );
        if ( create ) {
            ioService.createFile( nioPath );
        }
        try ( OutputStream outputStream = ioService.newOutputStream( nioPath,
                                                                     commentedOptionFactory.makeCommentedOption( comment,
                                                                                                                 sessionInfo.getIdentity(),
                                                                                                                 sessionInfo ) ) ) {
            IOUtils.copy( content,
                          outputStream );
            outputStream.flush();

            return resource;

        } catch ( Exception e ) {
            throw ExceptionUtilities.handleException( e );
        }
    }

    @Override
    public void delete( final Path path,
                        final String comment ) {
        try {
            deleteService.delete( path,
                                  comment );

        } catch ( Exception e ) {
            throw ExceptionUtilities.handleException( e );
        }
    }

    @Override
    public Path rename( final Path path,
                        final String newName,
                        final String comment ) {
        try {
            return renameService.rename( path,
                                         newName,
                                         comment );

        } catch ( Exception e ) {
            throw ExceptionUtilities.handleException( e );
        }
    }

    @Override
    public Path copy( final Path path,
                      final String newName,
                      final String comment ) {
        try {
            return copyService.copy( path,
                                     newName,
                                     comment );

        } catch ( Exception e ) {
            throw ExceptionUtilities.handleException( e );
        }
    }

    @Override
    public Path copy( final Path path,
                      final String newName,
                      final Path targetDirectory,
                      final String comment ) {
        try {
            return copyService.copy( path,
                                     newName,
                                     targetDirectory,
                                     comment );

        } catch ( Exception e ) {
            throw ExceptionUtilities.handleException( e );
        }
    }

    @Override
    public List<ValidationMessage> validate( final Path path,
                                             final Path resource ) {
        try {
            return genericValidator.validate( path );

        } catch ( Exception e ) {
            throw ExceptionUtilities.handleException( e );
        }
    }

    private SessionInfo getSessionInfo( final String sessionId ) {
        return new SafeSessionInfo( new SessionInfoImpl( sessionId,
                                                         authenticationService.getUser() ) );
    }

}
