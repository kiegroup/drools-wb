
package org.drools.workbench.scenariosimulation.kogito.marshaller.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Consumer;
import javax.xml.namespace.QName;
import jsinterop.base.Js;
import jsinterop.base.JsArrayLike;


/**
 * Utility class to provide generic methods used by all specific JSInterop classes
 * 
 */
public class JsUtils {


    private JsUtils() {
         //Private constructor to prevent instantiation
    }

    public static<D >void add(final JsArrayLike<D> jsArrayLike, final D element) {
        int length = jsArrayLike.getLength();
        jsArrayLike.setLength((length + 1));
        jsArrayLike.setAt(length, element);
    }

    public static<D, E extends D >void addAll(final JsArrayLike<D> jsArrayLike, E... elements) {
        for (D element: elements) {
            JsUtils.add(jsArrayLike, element);
        }
    }

    public static<D >void remove(final JsArrayLike<D> jsArrayLike, final int index) {
        int targetIndex = 0;
        for (int sourceIndex = 0; (sourceIndex<jsArrayLike.getLength()); sourceIndex ++) {
            if (sourceIndex!= index) {
                jsArrayLike.setAt(targetIndex ++, jsArrayLike.getAt(sourceIndex));
            }
        }
        jsArrayLike.setLength(targetIndex);
    }

    public static<D >List<D> toList(final JsArrayLike<D> jsArrayLike) {
        final List<D> toReturn = new ArrayList<D>();
        if (Objects.nonNull(jsArrayLike)) {
            for (int i = 0; (i<jsArrayLike.getLength()); i ++) {
                final D toAdd = Js.uncheckedCast(jsArrayLike.getAt(i));
                toReturn.add(toAdd);
            }
        }
        return toReturn;
    }

    public static<D >JsArrayLike<D> toJsArrayLike(final List<D> list) {
        final JsArrayLike<D> toReturn = getNativeArray();
        if (Objects.nonNull(list)) {
            for (int i = 0; (i<list.size()); i ++) {
                final D toAdd = Js.uncheckedCast(list.get(i));
                toReturn.setAt(toReturn.getLength(), toAdd);
            }
        }
        return toReturn;
    }

    /**
     * Extracts the <b>otherAttributes</b> property from a JavaScriptObject to a <i>regular</i> Java Map.
     * 
     * @param original
     *      <b>js object</b> to transform.
     * @return
     *     the populated <code>Map&lt;QName, String&gt;</code>
     */
    public static Map<QName, String> toAttributesMap(final Object original) {
        final Map<QName, String> toReturn = new HashMap<QName, String>();
        if (Objects.nonNull(original)) {
            toAttributesMap(toReturn, original);
        }
        return toReturn;
    }

    /**
     * Create a <code>QName</code> instance from the given <b>qNameAsString</b>, and the use it as key for a new entry on <b>destination</b> Map.
     * 
     * @param destination
     *      the <code>Map</code> to populate.
     * @param qNameAsString
     *      the <code>String</code> to transform to <code>QName</code> instance used as key.
     * @param value
     *      the <b>value</b> to be used in the new entry.
     */
    private static void putToAttributesMap(final Map<QName, String> destination, final String qNameAsString, final String value) {
        destination.put(QName.valueOf(qNameAsString), value);
    }

    /**
     * Extracts the <b>otherAttributes</b> property from a <i>regular<i> Java Map to a JavaScriptObject.
     * 
     * @param original
     *      the <code>Map&lt;QName, String&gt;</code> to transform.
     * @return
     *     the populated JavaScriptObject
     */
    public static<D >D fromAttributesMap(final Map<QName, String> original) {
        final D toReturn = getJsObject();
        original.entrySet().stream().forEach(new Consumer<Entry<QName,String>>() {


            @Override
            public void accept(Entry<QName, String> entry) {
                putToJavaScriptObject(toReturn, entry.getKey().toString(), entry.getValue());
            }

        }
        );
        return toReturn;
    }
    
     /**
     * Returns a <b>stub</b> object with <b>name</b> and <b>value</b> attributes
     * @return
     */
     public static native <D> D newWrappedInstance() /*-{
        var json = "{\"name\": \"\", \"value\": \"\"}";
        var retrieved = JSON.parse(json)
        return retrieved
    }-*/;

     /**
     * Set the <b>name</b> attribute of the given <b>wrapped</b> <code>D</code> with the <b>json</b> representation of the given <code>JSIName</code>
     * @param wrappedObject
     * @param name
     */
    public static native <D> void setNameOnWrapped(D wrappedObject, JSIName name) /*-{
        wrappedObject.name = name
    }-*/;
     /**
     * Set the <b>value</b> attribute of the given <b>wrapped</b> <code>D</code> with the <b>json</b> representation of <b>value</b> <code>E</code>
     * @param wrappedObject
     * @param value
     */
    public static native <D, E> void setValueOnWrapped(D wrappedObject, E value) /*-{
        wrappedObject.value = value
    }-*/;
     /**
     * Returns the original <code>JsArrayLike</code> or, ift the original <code>JsArrayLike</code> is <code>null</code>, a new, empty one
     * @param original
     * @param <D>
     * @return
     */
     public static native <D> JsArrayLike<D> getNativeElementsArray(final JsArrayLike<D> original) /*-{
        if(original == null) {
            return [];
        } else {
            return original;
        }
    }-*/;
     /**
     * Returns a <code>JsArrayLike</code> where each element represents the <b>unwrapped</b> object (i.e. object.value) of the original one.
     * It the original <code>JsArrayLike</code> is <code>null</code>, returns a new, empty one
     * @param original
     * @param <D>
     * @return
     */
     public static native <D> JsArrayLike<D> getUnwrappedElementsArray(final JsArrayLike<D> original) /*-{
        var toReturn = [];
        if(original != null) {
            toReturn = original.map(function (arrayItem) {
                var retrieved = arrayItem.value
                var toSet = retrieved == null ? arrayItem : retrieved
                return toSet;
            });
        }
        return toReturn;
    }-*/;

     public static native Object getUnwrappedElement(final Object original) /*-{
        var toReturn = original.value;
        var toSet = toReturn == null ? original : toReturn;
        return toSet;
    }-*/;

     public static native <D> D getWrappedElement(final Object value) /*-{
        var json = "{\"name\": \"\", \"value\": \"\"}";
        var toReturn = JSON.parse(json)
        toReturn.value =  value;
        return toReturn;
    }-*/;

     /**
     * Helper method to create a new, empty <code>JsArrayLike</code>
     * @return
     */
     public static native <D> JsArrayLike<D> getNativeArray() /*-{
        return [];
    }-*/;

     private static native void toAttributesMap(final Map<QName, String> toReturn,
                                               final Object original) /*-{
        var keys = Object.keys(original);
        for (var i = 0; i < keys.length; i++) {
            var key = keys[i];
            var value = original[key];
            @org.drools.workbench.scenariosimulation.kogito.marshaller.mapper.JsUtils::putToAttributesMap(Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;)(toReturn, key, value);
        }
    }-*/;

    /**
     * Helper method to create a new empty JavaScript object.
     * @return
     */
    private static native <D> D getJsObject() /*-{
        return {};
    }-*/;

    /**
     * Helper method to add a value to a JavaScript object at the associated key.
     */
    private static native <D, K, V> void putToJavaScriptObject(final D jso, final K key, final V value) /*-{
        jso[key] = value;
    }-*/;

    public static native String getTypeName(final Object instance) /*-{
        return instance.TYPE_NAME
    }-*/;

    public static native JSIName getJSIName(final String namespaceURI,
                                            final String localPart,
                                            final String prefix)/*-{
        var json = "{\"namespaceURI\": \"" + namespaceURI + "\", \"localPart\": \"" + localPart + "\", \"prefix\": \"" + prefix + "\", \"key\": \"{" + namespaceURI + "}" + localPart + "\", \"string\": \"{" + namespaceURI + "}" + prefix + ":" + localPart + "\"}";
        return JSON.parse(json);

}-*/;
}
