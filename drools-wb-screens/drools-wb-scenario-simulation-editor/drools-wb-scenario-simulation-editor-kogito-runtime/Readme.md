Kogito Runtime Webapp
==================================

This webapp is meant as runtime environment. It **does not provides** methods and functions (ex. file retrieval) that should be provided by running environment (ex. vs code).

Chrome testing
--------------

Some minimal tests may be done in the following way:

1. full compilation (i.e. included GWT compilation) of the runtime module
2. open the `drools-wb/drools-wb-screens/drools-wb-scenario-simulation-editor/drools-wb-scenario-simulation-editor-kogito-runtime/target/drools-wb-scenario-simulation-editor-kogito-runtime/index.html` file inside Chrome
3. inside the Chrome dev console, issue the command `window.gwtEditorBeans.get("ScenarioSimulationEditorKogitoRuntimeScreen").get().setContent("", "")`; this will create a new - empty - scesim file.
Some tricks: to avoid CORS and other policy-related issues:

1. set chrome://flags/#allow-insecure-localhost for invalid certificates error
2. start chrome from cli with the command `chrome --allow-file-access-from-files` to allow loading from file.
3. Open index.html file:///(path_to_file)/index.html and get no dev compilation error.
4. window.gwtEditorBeans.get("ScenarioSimulationEditorKogitoRuntimeScreen").get().setContent("") inside dev console
5. edit the asset
6. invoke window.gwtEditorBeans.get("ScenarioSimulationEditorKogitoRuntimeScreen").get().getContent() inside dev console and store the returned xml
7. invoke window.gwtEditorBeans.get("ScenarioSimulationEditorKogitoRuntimeScreen").get().setContent method with the stored xml

VSCODE Integration
------------------

1. clone https://github.com/kiegroup/kogito-tooling
2. create WAR of runtime webapp
3. copy **content** of the war inside kogito-tooling/packages/kie-bc-editors-unpacked/scesim
4. update kogito-tooling/packages/kie-bc-editors/src/GwtEditorRoutes.ts
    1. export const editors
    2. getRoutes - create scesimLanguageData
    3. return new Map<string, GwtLanguageData> - add scesim
5. edit kogito-tooling/packages/vscode-extension-pack-kogito-kie-editors/src/extension/extension.ts - inside new GwtEditorRoutes add
    path to scesim
6. edit kogito-tooling/packages/chrome-extension-pack-kogito-kie-editors/src/github-content-script.ts and add scesimPath
7. edit kogito-tooling/packages/online-editor/src/App.tsx and add scesimPath

See also https://medium.com/kie-foundation/building-your-own-custom-editors-with-kogito-tooling-npm-packages-c33caed6c668 for further info/help

Then, after launched VSCODE

1. Open index.html file:///(path_to_file)/index.html
and get no dev compliation error.
2. window.gwtEditorBeans.get("ScenarioSimulationEditorKogitoRuntimeScreen").get().setContent("", "") (inside console ?)
