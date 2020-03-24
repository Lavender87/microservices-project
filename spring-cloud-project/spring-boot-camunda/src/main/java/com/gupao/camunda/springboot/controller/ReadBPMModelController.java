package com.gupao.camunda.springboot.controller;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.*;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;
import org.camunda.bpm.model.xml.type.ModelElementType;

import java.io.File;
//import java.lang.Process;
import java.util.ArrayList;
import java.util.Collection;

public class ReadBPMModelController {


    /**
     * <?xml version="1.0" encoding="UTF-8" standalone="no"?>
     * <definitions targetNamespace="http://camunda.org/examples" xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL">
     *   <process id="process-with-one-task">
     *     <startEvent id="start">
     *       <outgoing>start-task1</outgoing>
     *     </startEvent>
     *     <userTask id="task1">
     *       <incoming>start-task1</incoming>
     *       <outgoing>task1-end</outgoing>
     *     </userTask>
     *     <endEvent id="end">
     *       <incoming>task1-end</incoming>
     *     </endEvent>
     *     <sequenceFlow id="start-task1" sourceRef="start" targetRef="task1"/>
     *     <sequenceFlow id="task1-end" sourceRef="task1" targetRef="end"/>
     *   </process>
     * </definitions>
     *
     *
     *
     */
    private void readModelFromFile(){
        // read a model from a file
        File file = new File("PATH/TO/MODEL.bpmn");
        BpmnModelInstance modelInstance = Bpmn.readModelFromFile(file);


        // read a model from a stream
//        InputStream stream = "";//[...]
//        BpmnModelInstance modelInstance = Bpmn.readModelFromStream(stream);

        // find element instance by ID
//        StartEvent start = (StartEvent) modelInstance.getModelElementById("start");
//
//        // find all elements of the type task
        ModelElementType taskType = modelInstance.getModel().getType(Task.class);
        Collection<ModelElementInstance> taskInstances = modelInstance.getModelElementsByType(taskType);


        StartEvent start = (StartEvent) modelInstance.getModelElementById("start");

        // read attributes by helper methods
        String id = start.getId();
        String name = start.getName();

        // edit attributes by helper methods
        start.setId("new-id");
        start.setName("new name");

        // read attributes by generic XML model API (with optional namespace)
        String custom1 = start.getAttributeValue("custom-attribute");
        String custom2 = start.getAttributeValueNs("custom-attribute-2", "http://camunda.org/custom");

        // edit attributes by generic XML model API (with optional namespace)
        start.setAttributeValue("custom-attribute", "new value");
        start.setAttributeValueNs("custom-attribute", "http://camunda.org/custom", "new value");


    }


    private void readModelFromFile2(){
        // read bpmn model from file
        BpmnModelInstance modelInstance = Bpmn.readModelFromFile(new File("/PATH/TO/MODEL.bpmn"));

        // find sequence flow by id
        SequenceFlow sequenceFlow = (SequenceFlow) modelInstance.getModelElementById("start-task1");

        // get the source and target element
        FlowNode source = sequenceFlow.getSource();
        FlowNode target = sequenceFlow.getTarget();

        // get all outgoing sequence flows of the source
        Collection<SequenceFlow> outgoing = source.getOutgoing();
        assert(outgoing.contains(sequenceFlow));
    }


    public Collection<FlowNode> getFlowingFlowNodes(FlowNode node) {
        Collection<FlowNode> followingFlowNodes = new ArrayList<FlowNode>();
        for (SequenceFlow sequenceFlow : node.getOutgoing()) {
            followingFlowNodes.add(sequenceFlow.getTarget());
        }
        return followingFlowNodes;
    }


    public void createWorkflow(){
        BpmnModelInstance modelInstance = Bpmn.createEmptyModel();
        Definitions definitions = modelInstance.newInstance(Definitions.class);
        definitions.setTargetNamespace("http://camunda.org/examples");
        modelInstance.setDefinitions(definitions);

        org.camunda.bpm.model.bpmn.instance.Process process = modelInstance.newInstance(Process.class);
        process.setId("process");
        definitions.addChildElement(process);

        // create an empty model
//        BpmnModelInstance modelInstance = Bpmn.createEmptyModel();
//        Definitions definitions = modelInstance.newInstance(Definitions.class);
        definitions.setTargetNamespace("http://camunda.org/examples");
        modelInstance.setDefinitions(definitions);

// create the process
//        Process process = createElement(definitions, "process-with-one-task", Process.class);

// create start event, user task and end event
        StartEvent startEvent = createElement(process, "start", StartEvent.class);
        UserTask task1 = createElement(process, "task1", UserTask.class);
        task1.setName("User Task");
        EndEvent endEvent = createElement(process, "end", EndEvent.class);

// create the connections between the elements
        createSequenceFlow(process, startEvent, task1);
        createSequenceFlow(process, task1, endEvent);

// validate and write model to file
        Bpmn.validateModel(modelInstance);
        File file = null ;//File.createTempFile("bpmn-model-api-", ".bpmn");
        Bpmn.writeModelToFile(file, modelInstance);
    }

    protected <T extends BpmnModelElementInstance> T createElement(BpmnModelElementInstance parentElement, String id, Class<T> elementClass) {
        T element = parentElement.getModelInstance().newInstance(elementClass);
        element.setAttributeValue("id", id, true);
        parentElement.addChildElement(element);
        return element;
    }

    public SequenceFlow createSequenceFlow(Process process, FlowNode from, FlowNode to) {
        String identifier = from.getId() + "-" + to.getId();
        SequenceFlow sequenceFlow = createElement(process, identifier, SequenceFlow.class);
        process.addChildElement(sequenceFlow);
        sequenceFlow.setSource(from);
        from.getOutgoing().add(sequenceFlow);
        sequenceFlow.setTarget(to);
        to.getIncoming().add(sequenceFlow);
        return sequenceFlow;
    }

    public void createBpm(){
        // create an empty model
        BpmnModelInstance modelInstance = Bpmn.createEmptyModel();
        Definitions definitions = modelInstance.newInstance(Definitions.class);
        definitions.setTargetNamespace("http://camunda.org/examples");
        modelInstance.setDefinitions(definitions);

        Process process = createElement(definitions, "process-with-one-task", Process.class);
// create elements
        StartEvent startEvent = createElement(process, "start", StartEvent.class);
        ParallelGateway fork = createElement(process, "fork", ParallelGateway.class);
        ServiceTask task1 = createElement(process, "task1", ServiceTask.class);
        task1.setName("Service Task");
        UserTask task2 = createElement(process, "task2", UserTask.class);
        task2.setName("User Task");
        ParallelGateway join = createElement(process, "join", ParallelGateway.class);
        EndEvent endEvent = createElement(process, "end", EndEvent.class);

// create flows
        createSequenceFlow(process, startEvent, fork);
        createSequenceFlow(process, fork, task1);
        createSequenceFlow(process, fork, task2);
        createSequenceFlow(process, task1, join);
        createSequenceFlow(process, task2, join);
        createSequenceFlow(process, join, endEvent);

// validate and write model to file
        Bpmn.validateModel(modelInstance);
        File file = null ;//File.createTempFile("bpmn-model-api-", ".bpmn");
        Bpmn.writeModelToFile(file, modelInstance);
    }
}
