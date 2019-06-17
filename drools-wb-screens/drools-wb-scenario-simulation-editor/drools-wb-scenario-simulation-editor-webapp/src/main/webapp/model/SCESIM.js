var SCESIM_Module_Factory = function () {
  var SCESIM = {
    name: 'SCESIM',
    typeInfos: [{
        localName: 'ImportsType',
        typeName: 'importsType',
        propertyInfos: [{
            name: 'imports',
            elementName: {
              localPart: 'imports'
            },
            typeInfo: '.InnerImportsType'
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
            typeInfo: 'Float',
            attributeName: {
              localPart: 'version'
            },
            type: 'attribute'
          }]
      }, {
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
          }]
      }, {
        localName: 'ExpressionElementsType',
        typeName: 'expressionElementsType',
        propertyInfos: [{
            name: 'expressionElement',
            required: true,
            elementName: {
              localPart: 'ExpressionElement'
            },
            typeInfo: '.ExpressionElementType'
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
        localName: 'FactMappingValueType',
        propertyInfos: [{
            name: 'factIdentifier',
            required: true,
            elementName: {
              localPart: 'factIdentifier'
            },
            typeInfo: '.FactIdentifierReferenceType'
          }, {
            name: 'expressionIdentifier',
            required: true,
            elementName: {
              localPart: 'expressionIdentifier'
            },
            typeInfo: '.ExpressionIdentifierReferenceType'
          }, {
            name: 'rawValue',
            required: true,
            elementName: {
              localPart: 'rawValue'
            },
            typeInfo: 'AnyType'
          }]
      }, {
        localName: 'ScenariosType',
        typeName: 'scenariosType',
        propertyInfos: [{
            name: 'scenario',
            required: true,
            elementName: {
              localPart: 'Scenario'
            },
            typeInfo: '.ScenarioType'
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
            typeInfo: '.SimulationDescriptorType'
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
        localName: 'InnerImportsType',
        typeName: 'innerImportsType',
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
        localName: 'FactIdentifierReferenceType',
        typeName: 'factIdentifierReferenceType',
        propertyInfos: [{
            name: 'reference',
            attributeName: {
              localPart: 'reference'
            },
            type: 'attribute'
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