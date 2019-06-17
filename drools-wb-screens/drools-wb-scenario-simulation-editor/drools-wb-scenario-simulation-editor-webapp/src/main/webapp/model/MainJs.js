// var Jsonix = Jsonix;
// var SCESIM = SCESIM;

MainJs = {

    // unmarshallLocal: function (text, callback) {
    //     console.log("unmarshallLocal");
    //     // Create Jsonix context
    //     var context = new Jsonix.Context([SCESIM]);
    //
    //     // Create unmarshaller
    //     var unmarshaller = context.createUnmarshaller();
    //     var toReturn = unmarshaller.unmarshalString(text);
    //     callback(toReturn);
    // },

    // marshallLocal: function (value, callback) {
    //     console.log("marshallLocal");
    //     // Create Jsonix context
    //     var context = new Jsonix.Context([SCESIM]);
    //
    //     // Create unmarshaller
    //     var marshaller = context.createMarshaller();
    //
    //     var xmlDocument = marshaller.marshalDocument(value);
    //     var s = new XMLSerializer();
    //     var toReturn = s.serializeToString(xmlDocument);
    //     callback(toReturn);
    // },

    unmarshall: function (text, callback) {
        console.log("out unmarshall");
        // Create Jsonix context
        var context = new Jsonix.Context([SCESIM]);

        // Create unmarshaller
        var unmarshaller = context.createUnmarshaller();
        var toReturn = unmarshaller.unmarshalString(text);
        callback(toReturn);



        // unmarshallLocal(text, callback);
        // require(["mainLocal"], function (mainLocal) {
        //     console.log("inner unmarshall");
        //     mainLocal.unmarshallLocal(text, callback);
        // });
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
        // require(["mainLocal"], function (mainLocal) {
        //     console.log("inner unmarshall");
        //     mainLocal.marshallLocal(value, callback);
        // });
    }
}