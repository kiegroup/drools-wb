package org.drools.workbench.jcr2vfsmigration.migrater.asset;

import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.drools.guvnor.client.rpc.DiscussionRecord;
import org.drools.guvnor.client.rpc.Module;
import org.drools.guvnor.server.RepositoryAssetService;
import org.drools.guvnor.server.util.Discussion;
import org.drools.repository.AssetItem;
import org.drools.repository.CategoryItem;
import org.drools.workbench.jcr2vfsmigration.migrater.util.MigrationPathManager;
import org.guvnor.common.services.shared.metadata.MetadataService;
import org.guvnor.common.services.shared.metadata.model.Metadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uberfire.backend.server.util.Paths;
import org.uberfire.backend.vfs.Path;

import com.google.gwt.user.client.rpc.SerializationException;

@ApplicationScoped
public class BaseAssetMigrater {

    protected static final Logger logger = LoggerFactory.getLogger( BaseAssetMigrater.class );

    @Inject
    protected RepositoryAssetService jcrRepositoryAssetService;

    @Inject
    private Paths paths;

    @Inject
    protected MigrationPathManager migrationPathManager;

    @Inject
    protected MetadataService metadataService;
    
    public Map<String, Object> migrateMetaData(Module jcrModule, AssetItem jcrAssetItem)  {
/*        System.out.format("    Metadata: Asset [%s] with format [%s] is being migrated... \n",
        		jcrAssetItem.getName(), jcrAssetItem.getFormat());       */
        
        //avoid using RepositoryAssetService as it calls assets' content handler
        Metadata metadata = new Metadata();        
        
        List<DiscussionRecord> discussions = new Discussion().fromString( jcrAssetItem.getStringProperty( Discussion.DISCUSSION_PROPERTY_KEY ) );
        
        if(discussions.size() != 0) {
            //final org.kie.commons.java.nio.file.Path nioPath = paths.convert( path );
            for(DiscussionRecord discussion: discussions) {
                metadata.addDiscussion( new org.guvnor.common.services.shared.metadata.model.DiscussionRecord( discussion.timestamp, discussion.author, discussion.note ) );
            }
        }
        
        //System.out.format("    Metadata: setDescription... \n" + jcrAssetItem.getDescription());

        metadata.setDescription(jcrAssetItem.getDescription());
        metadata.setSubject(jcrAssetItem.getSubject());
        metadata.setExternalRelation(jcrAssetItem.getExternalRelation());
        metadata.setExternalSource(jcrAssetItem.getExternalSource());
        List<CategoryItem> jcrCategories = jcrAssetItem.getCategories();
        for(CategoryItem c : jcrCategories) {
            //System.out.format("    Metadata: addCategory... \n" + c.getFullPath());
            metadata.addCategory(c.getFullPath());        	
        }
        
        Path path = migrationPathManager.generatePathForAsset(jcrModule, jcrAssetItem);
        return metadataService.setUpAttributes(path, metadata);
                
        //System.out.format("    Metadata migration done.\n");
    }


}
