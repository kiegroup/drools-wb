package org.drools.workbench.jcr2vfsmigration.migrater.util;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.drools.guvnor.client.common.AssetFormats;
import org.drools.guvnor.client.rpc.Asset;
import org.drools.guvnor.client.rpc.Module;
import org.drools.repository.AssetItem;
import org.uberfire.backend.server.util.Paths;
import org.uberfire.backend.vfs.Path;
import org.uberfire.backend.vfs.PathFactory;
import org.uberfire.java.nio.file.FileSystem;

/**
 * Generates a Path for every object that needs to be migrated.
 * Guarantees uniqueness. Supports look ups.
 */
@ApplicationScoped
public class MigrationPathManager {

    @Inject
    @Named("migrationFS")
    private FileSystem fs;


    // Generate methods

    public Path generateRootPath() {

        final org.uberfire.java.nio.file.Path _path = fs.getPath( "/" );

        return Paths.convert( _path );

        //final Path path = PathFactory.newPath( paths.convert( _path.getFileSystem() ), _path.getFileName().toString(), _path.toUri().toString() );
        //return path;
    }
    
    public Path generatePathForModule( String jcrModuleName ) {
        final org.uberfire.java.nio.file.Path modulePath = fs.getPath( "/" + escapePathEntry( jcrModuleName ) );

        final Path path = PathFactory.newPath( Paths.convert( modulePath.getFileSystem() ), modulePath.getFileName().toString(), modulePath.toUri().toString() );

        return path;
    }

    public Path generatePathForAsset( Module jcrModule,
                                      Asset jcrAsset,
                                      boolean hasDSL) {
        final org.uberfire.java.nio.file.Path modulePath = fs.getPath( "/" + escapePathEntry( jcrModule.getName() ) );
        
        //final org.uberfire.java.nio.file.Path directory = getPomDirectoryPath(pathToPom);
        org.uberfire.java.nio.file.Path assetPath = null;
        if(AssetFormats.BUSINESS_RULE.equals(jcrAsset.getFormat()) && !hasDSL) {
            assetPath = modulePath.resolve("src/main/resources/" + jcrAsset.getName() + ".rdrl");
        } else if (AssetFormats.BUSINESS_RULE.equals(jcrAsset.getFormat()) && hasDSL) {
            assetPath = modulePath.resolve("src/main/resources/" + jcrAsset.getName() + ".rdslr");
        } else {
            assetPath = modulePath.resolve("src/main/resources/" + jcrAsset.getName() + "." + jcrAsset.getFormat());           
        }
        
        //final org.uberfire.java.nio.file.Path _path = fs.getPath( "/" + escapePathEntry( jcrModule.getName() ) + "/" + escapePathEntry( jcrAsset.getName() ) + "." + jcrAsset.getFormat() );

        final Path path = PathFactory.newPath( Paths.convert( assetPath.getFileSystem() ), assetPath.getFileName().toString(), assetPath.toUri().toString() );

        return path;
    }
    
    public Path generatePathForGlobal(Module jcrModule) {
        final org.uberfire.java.nio.file.Path modulePath = fs.getPath("/"
                + escapePathEntry(jcrModule.getName()));

        org.uberfire.java.nio.file.Path assetPath = modulePath.resolve("src/main/resources/" + "globals.gdrl");

        final Path path = PathFactory.newPath(Paths.convert(assetPath
                .getFileSystem()), assetPath.getFileName().toString(),
                assetPath.toUri().toString());

        return path;
    }

    public Path generatePathForAsset(Module jcrModule, Asset jcrAsset) {        
        return  generatePathForAsset( jcrModule, jcrAsset, false );
    } 

    public Path generatePathForAsset(Module jcrModule, AssetItem jcrAssetItem, boolean hasDSL) {
        final org.uberfire.java.nio.file.Path modulePath = fs.getPath("/" + escapePathEntry(jcrModule.getName()));

        org.uberfire.java.nio.file.Path assetPath = null;
        if (AssetFormats.BUSINESS_RULE.equals(jcrAssetItem.getFormat()) && !hasDSL) {
            assetPath = modulePath.resolve("src/main/resources/" + jcrAssetItem.getName() + ".rdrl");
        } else if (AssetFormats.BUSINESS_RULE.equals(jcrAssetItem.getFormat()) && hasDSL) {
            assetPath = modulePath.resolve("src/main/resources/" + jcrAssetItem.getName() + ".rdslr");
        } else if (AssetFormats.FUNCTION.equals(jcrAssetItem.getFormat())) {
            assetPath = modulePath.resolve("src/main/resources/" + jcrAssetItem.getName() + ".drl");
        } else if (AssetFormats.TEST_SCENARIO.equals(jcrAssetItem.getFormat())) {
            assetPath = modulePath.resolve("src/test/resources/" + jcrAssetItem.getName() + "." + jcrAssetItem.getFormat());
        } else {
            assetPath = modulePath.resolve("src/main/resources/" + jcrAssetItem.getName() + "." + jcrAssetItem.getFormat());
        }

        final Path path = PathFactory.newPath(Paths.convert(assetPath.getFileSystem()), assetPath.getFileName().toString(), assetPath.toUri().toString());

        return path;
    }
    
    public Path generatePathForAsset(Module jcrModule, AssetItem jcrAssetItem) {        
        return generatePathForAsset(jcrModule, jcrAssetItem, false);
    }
    
    private org.uberfire.java.nio.file.Path getPomDirectoryPath(final Path pathToPomXML) {
        return Paths.convert(pathToPomXML).getParent();
    }
    
    // Helper methods

    public String escapePathEntry( String pathEntry ) {
        // VFS doesn't support /'s in the path entries
        pathEntry = pathEntry.replaceAll( "/", " slash " );
        // TODO Once porcelli has a list of all illegal and escaped characters in PathEntry, deal with them here
        return pathEntry;
    }
}
