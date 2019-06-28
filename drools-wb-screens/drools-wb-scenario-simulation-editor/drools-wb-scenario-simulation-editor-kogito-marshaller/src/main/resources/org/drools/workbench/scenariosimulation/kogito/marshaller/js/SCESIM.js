var SCESIM_Module_Factory = function () {
  var SCESIM = {
    name: 'SCESIM',
    typeInfos: [{
        localName: 'SimulationDescriptorType',
        typeName: 'simulationDescriptorType',
        propertyInfos: [{
            name: 'factMappings',
            elementName: {
              localPart: 'factMappings'
            },
            typeInfo: '.FactMappingsType'
          }, {
            name: 'dmoSession',
            elementName: {
              localPart: 'dmoSession'
            }
          }, {
            name: 'dmnFilePath',
            elementName: {
              localPart: 'dmnFilePath'
            }
          }, {
            name: 'type',
            elementName: {
              localPart: 'type'
            }
          }, {
            name: 'fileName',
            elementName: {
              localPart: 'fileName'
            }
          }, {
            name: 'kieSession',
            elementName: {
              localPart: 'kieSession'
            }
          }, {
            name: 'kieBase',
            elementName: {
              localPart: 'kieBase'
            }
          }, {
            name: 'ruleFlowGroup',
            elementName: {
              localPart: 'ruleFlowGroup'
            }
          }, {
            name: 'dmnNamespace',
            elementName: {
              localPart: 'dmnNamespace'
            }
          }, {
            name: 'dmnName',
            elementName: {
              localPart: 'dmnName'
            }
          }, {
            name: 'skipFromBuild',
            elementName: {
              localPart: 'skipFromBuild'
            },
            typeInfo: 'Boolean'
          }]
      }, {
        localName: 'ExpressionElementsType',
        typeName: 'expressionElementsType',
        propertyInfos: [{
            name: 'expressionElement',
            required: true,
            collection: true,
            elementName: {
              localPart: 'ExpressionElement'
            },
            typeInfo: '.ExpressionElementType'
          }]
      }, {
        localName: 'ScenariosType',
        typeName: 'scenariosType',
        propertyInfos: [{
            name: 'scenario',
            minOccurs: 0,
            collection: true,
            elementName: {
              localPart: 'Scenario'
            },
            typeInfo: '.ScenarioType'
          }]
      }, {
        localName: 'FactMappingValueType',
        propertyInfos: [{
            name: 'factIdentifier',
            required: true,
            elementName: {
              localPart: 'factIdentifier'
            },
            typeInfo: '.FactIdentifierType'
          }, {
            name: 'expressionIdentifier',
            required: true,
            elementName: {
              localPart: 'expressionIdentifier'
            },
            typeInfo: '.ExpressionIdentifierReferenceType'
          }, {
            name: 'rawValue',
            elementName: {
              localPart: 'rawValue'
            },
            typeInfo: '.RawValueType'
          }]
      }, {
        localName: 'ImportType',
        propertyInfos: [{
            name: 'type',
            required: true,
            elementName: {
              localPart: 'type'
            }
          }]
      }, {
        localName: 'FactMappingValuesType',
        typeName: 'factMappingValuesType',
        propertyInfos: [{
            name: 'factMappingValue',
            minOccurs: 0,
            collection: true,
            elementName: {
              localPart: 'FactMappingValue'
            },
            typeInfo: '.FactMappingValueType'
          }]
      }, {
        localName: 'ScenarioType',
        propertyInfos: [{
            name: 'factMappingValues',
            required: true,
            elementName: {
              localPart: 'factMappingValues'
            },
            typeInfo: '.FactMappingValuesType'
          }, {
            name: 'simulationDescriptor',
            required: true,
            elementName: {
              localPart: 'simulationDescriptor'
            },
            typeInfo: '.SimulationDescriptorReferenceType'
          }]
      }, {
        localName: 'ExpressionIdentifierReferenceType',
        typeName: 'expressionIdentifierReferenceType',
        propertyInfos: [{
            name: 'reference',
            attributeName: {
              localPart: 'reference'
            },
            type: 'attribute'
          }]
      }, {
        localName: 'ImportsType',
        typeName: 'importsType',
        propertyInfos: [{
            name: 'imports',
            elementName: {
              localPart: 'imports'
            },
            typeInfo: '.WrappedImportsType'
          }]
      }, {
        localName: 'SimulationDescriptorReferenceType',
        typeName: 'simulationDescriptorReferenceType',
        propertyInfos: [{
            name: 'reference',
            attributeName: {
              localPart: 'reference'
            },
            type: 'attribute'
          }]
      }, {
        localName: 'WrappedImportsType',
        typeName: 'wrappedImportsType',
        propertyInfos: [{
            name: '_import',
            minOccurs: 0,
            collection: true,
            elementName: {
              localPart: 'Import'
            },
            typeInfo: '.ImportType'
          }]
      }, {
        localName: 'ScenarioSimulationModelType',
        propertyInfos: [{
            name: 'simulation',
            required: true,
            elementName: {
              localPart: 'simulation'
            },
            typeInfo: '.SimulationType'
          }, {
            name: 'imports',
            required: true,
            elementName: {
              localPart: 'imports'
            },
            typeInfo: '.ImportsType'
          }, {
            name: 'version',
            attributeName: {
              localPart: 'version'
            },
            type: 'attribute'
          }]
      }, {
        localName: 'ExpressionElementType',
        propertyInfos: [{
            name: 'step',
            required: true,
            elementName: {
              localPart: 'step'
            }
          }]
      }, {
        localName: 'FactMappingsType',
        typeName: 'factMappingsType',
        propertyInfos: [{
            name: 'factMapping',
            minOccurs: 0,
            collection: true,
            elementName: {
              localPart: 'FactMapping'
            },
            typeInfo: '.FactMappingType'
          }]
      }, {
        localName: 'ExpressionIdentifierType',
        typeName: 'expressionIdentifierType',
        propertyInfos: [{
            name: 'name',
            elementName: {
              localPart: 'name'
            }
          }, {
            name: 'type',
            elementName: {
              localPart: 'type'
            }
          }]
      }, {
        localName: 'SimulationType',
        typeName: 'simulationType',
        propertyInfos: [{
            name: 'simulationDescriptor',
            required: true,
            elementName: {
              localPart: 'simulationDescriptor'
            },
            typeInfo: '.SimulationDescriptorType'
          }, {
            name: 'scenarios',
            required: true,
            elementName: {
              localPart: 'scenarios'
            },
            typeInfo: '.ScenariosType'
          }]
      }, {
        localName: 'FactIdentifierType',
        typeName: 'factIdentifierType',
        propertyInfos: [{
            name: 'name',
            elementName: {
              localPart: 'name'
            }
          }, {
            name: 'className',
            elementName: {
              localPart: 'className'
            }
          }, {
            name: 'reference',
            attributeName: {
              localPart: 'reference'
            },
            type: 'attribute'
          }]
      }, {
        localName: 'RawValueType',
        typeName: 'rawValueType',
        propertyInfos: [{
            name: 'value',
            type: 'value'
          }, {
            name: 'clazz',
            attributeName: {
              localPart: 'class'
            },
            type: 'attribute'
          }]
      }, {
        localName: 'FactMappingType',
        propertyInfos: [{
            name: 'expressionElements',
            required: true,
            elementName: {
              localPart: 'expressionElements'
            },
            typeInfo: '.ExpressionElementsType'
          }, {
            name: 'expressionIdentifier',
            required: true,
            elementName: {
              localPart: 'expressionIdentifier'
            },
            typeInfo: '.ExpressionIdentifierType'
          }, {
            name: 'factIdentifier',
            required: true,
            elementName: {
              localPart: 'factIdentifier'
            },
            typeInfo: '.FactIdentifierType'
          }, {
            name: 'className',
            required: true,
            elementName: {
              localPart: 'className'
            }
          }, {
            name: 'factAlias',
            required: true,
            elementName: {
              localPart: 'factAlias'
            }
          }, {
            name: 'expressionAlias',
            elementName: {
              localPart: 'expressionAlias'
            }
          }, {
            name: 'genericTypes',
            minOccurs: 0,
            collection: true,
            elementName: {
              localPart: 'genericTypes'
            }
          }]
      }],
    elementInfos: [{
        typeInfo: '.ScenarioSimulationModelType',
        elementName: {
          localPart: 'ScenarioSimulationModel'
        }
      }]
  };
  return {
    SCESIM: SCESIM
  };
};
if (typeof define === 'function' && define.amd) {
  define([], SCESIM_Module_Factory);
}
else {
  var SCESIM_Module = SCESIM_Module_Factory();
  if (typeof module !== 'undefined' && module.exports) {
    module.exports.SCESIM = SCESIM_Module.SCESIM;
  }
  else {
    var SCESIM = SCESIM_Module.SCESIM;
  }
}