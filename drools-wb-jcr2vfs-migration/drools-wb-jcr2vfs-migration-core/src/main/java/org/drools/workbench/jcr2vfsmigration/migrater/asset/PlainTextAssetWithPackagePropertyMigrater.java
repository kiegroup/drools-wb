package org.drools.workbench.jcr2vfsmigration.migrater.asset;

import java.util.Scanner;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.drools.guvnor.client.common.AssetFormats;
import org.drools.guvnor.client.rpc.Module;
import org.drools.guvnor.server.RepositoryAssetService;
import org.drools.repository.AssetItem;
import org.drools.workbench.jcr2vfsmigration.migrater.PackageImportHelper;
import org.drools.workbench.jcr2vfsmigration.migrater.util.MigrationPathManager;
import org.drools.workbench.screens.drltext.service.DRLTextEditorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uberfire.backend.server.util.Paths;
import org.uberfire.backend.vfs.Path;
import org.uberfire.io.IOService;
import org.uberfire.java.nio.base.options.CommentedOption;
import org.uberfire.java.nio.file.Files;
import org.uberfire.java.nio.file.StandardCopyOption;

@ApplicationScoped
public class PlainTextAssetWithPackagePropertyMigrater extends BaseAssetMigrater {

    protected static final Logger logger = LoggerFactory.getLogger( PlainTextAssetWithPackagePropertyMigrater.class );

    @Inject
    protected RepositoryAssetService jcrRepositoryAssetService;

    @Inject
    @Named("ioStrategy")
    private IOService ioService;

    @Inject
    protected MigrationPathManager migrationPathManager;

    @Inject
    DRLTextEditorService drlTextEditorServiceImpl;

    @Inject
    PackageImportHelper packageImportHelper;

    public Path migrate( Module jcrModule,
                         AssetItem jcrAssetItem,
                         Path previousVersionPath) {
        Path path = migrationPathManager.generatePathForAsset( jcrModule,
                                                               jcrAssetItem );
        final org.uberfire.java.nio.file.Path nioPath = Paths.convert( path );
        //The asset was renamed in this version. We move this asset first.
        if(previousVersionPath != null && !previousVersionPath.equals(path)) {
            ioService.move(Paths.convert( previousVersionPath ), nioPath, StandardCopyOption.REPLACE_EXISTING);
        }

        if ( !Files.exists( nioPath ) ) {
            ioService.createFile( nioPath );
        }

        StringBuilder sb = new StringBuilder();

        if ( AssetFormats.DRL.equals( jcrAssetItem.getFormat() ) ) {
            sb.append( "rule '" + jcrAssetItem.getName() + "'" );
            sb.append( "\n" );
            sb.append( "\n" );
        }
        sb.append( jcrAssetItem.getContent() );
        sb.append( "\n" );
        sb.append( "\n" );
        sb.append( "end" );

        String content = sb.toString();        
       
        //Support for # has been removed from Drools Expert
        if (AssetFormats.DSL.equals(jcrAssetItem.getFormat())
                || AssetFormats.DSL_TEMPLATE_RULE.equals(jcrAssetItem.getFormat())                
        		|| AssetFormats.RULE_TEMPLATE.equals(jcrAssetItem.getFormat())
        		|| AssetFormats.DRL.equals(jcrAssetItem.getFormat())
                || AssetFormats.FUNCTION.equals(jcrAssetItem.getFormat())) {
        	StringBuffer sb1 = new StringBuffer();
            Scanner scanner = new Scanner(content);
            
            while (scanner.hasNextLine()) {
            	  String line = scanner.nextLine();
            	  if(line.startsWith("#")) {
            		  sb1.append(line.replaceFirst("#", "//"));
            	  } else {
            		  sb1.append(line);
            	  }            	  
            }
            
            scanner.close();
            content = sb1.toString();
        }
        
        
        String sourceWithImport = drlTextEditorServiceImpl.assertPackageName( content,
                                                                              path );
        sourceWithImport = packageImportHelper.assertPackageImportDRL( sourceWithImport,
                                                                       path );

        ioService.write( nioPath,
                         sourceWithImport,
                         migrateMetaData(jcrModule, jcrAssetItem),
                         new CommentedOption( jcrAssetItem.getLastContributor(),
                                              null,
                                              jcrAssetItem.getCheckinComment(),
                                              jcrAssetItem.getLastModified().getTime() ) );
        
        return path;
    }

}
