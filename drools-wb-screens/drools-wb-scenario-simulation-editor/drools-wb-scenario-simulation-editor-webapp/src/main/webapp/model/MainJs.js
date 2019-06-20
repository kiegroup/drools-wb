
MainJs = {


    unmarshall: function (text, callback) {
        console.log("out unmarshall");
        // Create Jsonix context
        var context = new Jsonix.Context([SCESIM]);

        // Create unmarshaller
        var unmarshaller = context.createUnmarshaller();
        var toReturn = unmarshaller.unmarshalString(text);
        callback(toReturn);
    },

    marshall: function (value, callback) {
        console.log("outer marshall");
        var context = new Jsonix.Context([SCESIM]);

        // Create unmarshaller
        var marshaller = context.createMarshaller();

        var xmlDocument = marshaller.marshalDocument(value);
        var s = new XMLSerializer();
        var toReturn = s.serializeToString(xmlDocument);
        callback(toReturn);
    },

    unmarshallObject: function (source)  {
        console.log("unmarshallObject");
        // Create Jsonix context
        var context = new Jsonix.Context([SCESIM]);

        // Create unmarshaller
        var unmarshaller = context.createUnmarshaller();
        var toReturn = unmarshaller.unmarshalString(source);
        return toReturn;
    }
}