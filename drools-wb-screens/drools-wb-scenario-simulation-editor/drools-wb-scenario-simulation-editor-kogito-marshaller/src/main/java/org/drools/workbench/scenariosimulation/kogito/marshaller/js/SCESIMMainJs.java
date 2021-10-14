
package org.drools.workbench.scenariosimulation.kogito.marshaller.js;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import jsinterop.base.JsPropertyMap;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.callbacks.SCESIMMarshallCallback;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.callbacks.SCESIMUnmarshallCallback;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.SCESIM;


/**
 * JSInterop adapter to use for marshalling/unmarshalling.
 * 
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "SCESIMMainJs")
public class SCESIMMainJs {


    @JsOverlay
    private static JSONObject getJSONObjectMethod(String name, String typeName, String nameSpace) {
        final JSONObject toReturn = new JSONObject();
        toReturn.put("name", new JSONString(name));
        if (typeName!= null) {
            toReturn.put("typeName", new JSONString(typeName));
        }
        if (nameSpace!= null) {
            toReturn.put("nameSpace", new JSONString(nameSpace));
        }
        return toReturn;
    }

    @JsOverlay
    public static JsPropertyMap getConstructorsMap() {
        final JsPropertyMap toReturn = JsPropertyMap.of();
        final JSONObject[] toSet = new JSONObject[] {getJSONObjectMethod("JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIScenarioSimulationModelType", "SCESIM.ScenarioSimulationModelType", null), getJSONObjectMethod("JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSISimulationType", "SCESIM.SimulationType", null), getJSONObjectMethod("JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIScesimModelDescriptorType", "SCESIM.ScesimModelDescriptorType", null), getJSONObjectMethod("JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIFactMappingsType", "SCESIM.FactMappingsType", null), getJSONObjectMethod("JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIFactMappingType", "SCESIM.FactMappingType", null), getJSONObjectMethod("JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIExpressionElementsType", "SCESIM.ExpressionElementsType", null), getJSONObjectMethod("JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIExpressionElementType", "SCESIM.ExpressionElementType", null), getJSONObjectMethod("JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIExpressionIdentifierType", "SCESIM.ExpressionIdentifierType", null), getJSONObjectMethod("JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIFactIdentifierType", "SCESIM.FactIdentifierType", null), getJSONObjectMethod("JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIGenericTypes", "SCESIM.GenericTypes", null), getJSONObjectMethod("JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIScenariosType", "SCESIM.ScenariosType", null), getJSONObjectMethod("JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIScenarioType", "SCESIM.ScenarioType", null), getJSONObjectMethod("JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIFactMappingValuesType", "SCESIM.FactMappingValuesType", null), getJSONObjectMethod("JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIFactMappingValueType", "SCESIM.FactMappingValueType", null), getJSONObjectMethod("JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIRawValueType", "SCESIM.RawValueType", null), getJSONObjectMethod("JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIBackgroundType", "SCESIM.BackgroundType", null), getJSONObjectMethod("JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIBackgroundDatasType", "SCESIM.BackgroundDatasType", null), getJSONObjectMethod("JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIBackgroundDataType", "SCESIM.BackgroundDataType", null), getJSONObjectMethod("JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSISettingsType", "SCESIM.SettingsType", null), getJSONObjectMethod("JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIImportsType", "SCESIM.ImportsType", null), getJSONObjectMethod("JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIWrappedImportsType", "SCESIM.WrappedImportsType", null), getJSONObjectMethod("JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIImportType", "SCESIM.ImportType", null), getJSONObjectMethod("JsInterop__ConstructorAPI__org__drools__workbench__scenariosimulation__kogito__marshaller__mapper__JSIName", null, null)};
        toReturn.set("constructors", toSet);
        return toReturn;
    }

    @JsMethod
    public final static native void initializeJsInteropConstructors(JsPropertyMap constructorsMap);

    @JsMethod
    public final static native void unmarshall(String xmlString, String dynamicNamespace, SCESIMUnmarshallCallback sCESIMUnmarshallCallback);

    @JsMethod
    public final static native void marshall(SCESIM sCESIM, JavaScriptObject namespaces, SCESIMMarshallCallback sCESIMMarshallCallback);

}
