Maven SDM
=========

The drools-wb-webapp/pom.xml file has been modified to allow hot-reload (so-called SuperDev Mode) of included client code when launched directly from maven.</p>
This would allow to develop independently from any given ide/plugin.</p>
Currently *managed* code are the client submodules of **drools-wb-screens** and **org.kie.workbench.widgets/kie-wb-common-ui**.</p>
The *trick* used is to just add the actual sources directories as additional *sources* using the **org.codehaus.mojo/build-helper-maven-plugin**.</p>
For the **drools-wb-screens** it has been possible to set a relative directory, while for the **org.kie.workbench.widgets/kie-wb-common-ui** module it ios necessary to use an absolute path (since it may change between different machines).
A specific property **path.to.kie.wb.common** has been used to set the root path - this should be set on a per-machine base inside the user' own settings.xml.
The jars included as additional sources should be removed from the **dependencies** and **compileSourcesArtifacts** lists

