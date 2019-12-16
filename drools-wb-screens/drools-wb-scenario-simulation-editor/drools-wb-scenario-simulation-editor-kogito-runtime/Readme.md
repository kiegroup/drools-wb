Kogito Runtime Webapp
==================================

This webapp is meant as runtime environment. It **does not provides** methods and functions (ex. file retrieval) that should be provided by running environment (ex. vs code).

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


1-) Open index.html file:///(path_to_file)/index.html
and get no dev compliation error.
2-) gwtEditorBeans.get("ScenarioSimulationEditor").get().setContent("") (inside console ?)


    
