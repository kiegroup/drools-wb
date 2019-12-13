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
    
