package com.gupao.camunda.springboot.controller;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.*;

import java.io.File;

/**
 * https://docs.camunda.org/manual/7.12/user-guide/model-api/bpmn-model-api/fluent-builder-api/
 */
public class ProcessWithFluentBuilder {

    /**
     *
     *
     * process
     * start event
     * exclusive gateway
     * parallel gateway
     * script task
     * service task
     * user task
     * signal event definition
     * end event
     * subprocess
     *
     * 参照org.camunda.bpm.quickstart.CreateInvoiceProcessTest
     */

    /**
     *  create an empty model instance with a new process the method Bpmn.createProcess()
     *
     */
    public void processWithFluentBuilder(){
        BpmnModelInstance modelInstance = Bpmn.createProcess()
                .startEvent()
                .userTask()
                .endEvent()
                .done();
    }

    public void processWithFluentBuilder2(){
        BpmnModelInstance modelInstance = Bpmn.createProcess()
                .name("Example process")
                .executable()
                .startEvent()
                .userTask()
                .name("Some work to do")
                .endEvent()
                .done();
    }

    public void processWithFluentBuilder3(){
        BpmnModelInstance modelInstance = Bpmn.createProcess()
                .startEvent()
                .userTask()
                .parallelGateway()
                .scriptTask()
                .endEvent()
                .moveToLastGateway()
                .serviceTask()
                .endEvent()
                .done();
    }

    public void processWithFluentBuilder4(){
        BpmnModelInstance modelInstance = Bpmn.createProcess()
                .startEvent()
                .userTask()
                .exclusiveGateway()
                .name("What to do next?")
                .condition("Call an agent", "#{action = 'call'}")
                .scriptTask()
                .endEvent()
                .moveToLastGateway()
                .condition("Create a task", "#{action = 'task'}")
                .serviceTask()
                .endEvent()
                .done();
    }

    public void processWithFluentBuilder5(){
        BpmnModelInstance modelInstance = Bpmn.createProcess()
                .startEvent()
                .userTask()
                .parallelGateway("fork")
                .serviceTask()
                .parallelGateway("join")
                .moveToNode("fork")
                .userTask()
                .connectTo("join")
                .moveToNode("fork")
                .scriptTask()
                .connectTo("join")
                .endEvent()
                .done();
    }

    public void processWithFluentBuilder6(){
        BpmnModelInstance modelInstance = Bpmn.createProcess()
                .startEvent()
                .userTask()
                .id("question")
                .exclusiveGateway()
                .name("Everything fine?")
                .condition("yes", "#{fine}")
                .serviceTask()
                .userTask()
                .endEvent()
                .moveToLastGateway()
                .condition("no", "#{!fine}")
                .userTask()
                .connectTo("question")
                .done();
    }

    public void processWithFluentBuilder7(){
        // Directly define the subprocess
        BpmnModelInstance modelInstance = Bpmn.createProcess()
                .startEvent()
                .subProcess()
                .camundaAsync()
                .embeddedSubProcess()
                .startEvent()
                .userTask()
                .endEvent()
                .subProcessDone()
                .serviceTask()
                .endEvent()
                .done();

// Detach the subprocess building
        modelInstance = Bpmn.createProcess()
                .startEvent()
                .subProcess("subProcess")
                .serviceTask()
                .endEvent()
                .done();

        SubProcess subProcess = (SubProcess) modelInstance.getModelElementById("subProcess");
        subProcess.builder()
                .camundaAsync()
                .embeddedSubProcess()
                .startEvent()
                .userTask()
                .endEvent();
    }

    public void processWithFluentBuilder8(){
        BpmnModelInstance modelInstance = Bpmn.createProcess()
                .startEvent()
                .intermediateThrowEvent("throw")
                .signalEventDefinition("signal")
                .camundaInSourceTarget("source", "target1")
                .camundaInSourceExpressionTarget("${'sourceExpression'}", "target2")
                .camundaInAllVariables("all", true)
                .camundaInBusinessKey("aBusinessKey")
                .throwEventDefinitionDone()
                .endEvent()
                .done();
    }

    public void processWithFluentBuilder9(){
        BpmnModelInstance modelInstance = Bpmn.readModelFromFile(new File("PATH/TO/MODEL.bpmn"));
        ParallelGateway gateway = (ParallelGateway) modelInstance.getModelElementById("gateway");

        gateway.builder()
                .serviceTask()
                .name("New task")
                .endEvent();
    }

    public void processWithFluentBuilder10(){
        BpmnModelInstance modelInstance = Bpmn.readModelFromFile(new File("PATH/TO/MODEL.bpmn"));
        UserTask userTask = (UserTask) modelInstance.getModelElementById("task1");
        SequenceFlow outgoingSequenceFlow = userTask.getOutgoing().iterator().next();
        FlowNode serviceTask = outgoingSequenceFlow.getTarget();
        userTask.getOutgoing().remove(outgoingSequenceFlow);

        userTask.builder()
                .scriptTask()
                .userTask()
                .connectTo(serviceTask.getId());
    }


    /**
     * <?xml version="1.0" encoding="UTF-8" standalone="no"?>
     * <definitions xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI" xmlns:camunda="http://camunda.org/schema/1.0/bpmn" xmlns:dc="http://www.omg.org/spec/DD/20100524/DC" xmlns:di="http://www.omg.org/spec/DD/20100524/DI" id="definitions_dfb1f18e-6034-448e-abae-0eb2f41469da" targetNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL">
     *
     *   <!-- Generated BPMN Semantic Elements -->
     *   <process id="process-payments" isExecutable="true">
     *     <startEvent id="startEvent_2b0abd37-75a9-47dd-9838-63f1390d7515">
     *       <outgoing>sequenceFlow_b1eec5b5-889d-4e75-854d-59768fbdc8a2</outgoing>
     *     </startEvent>
     *     <serviceTask id="serviceTask_f4c2413f-5b26-49e8-b71c-2603e469ce09" name="Process Payment">
     *       <incoming>sequenceFlow_b1eec5b5-889d-4e75-854d-59768fbdc8a2</incoming>
     *       <outgoing>sequenceFlow_5839394a-c0c2-4a5b-aa81-9412f169cc35</outgoing>
     *     </serviceTask>
     *     <sequenceFlow id="sequenceFlow_b1eec5b5-889d-4e75-854d-59768fbdc8a2" sourceRef="startEvent_2b0abd37-75a9-47dd-9838-63f1390d7515" targetRef="serviceTask_f4c2413f-5b26-49e8-b71c-2603e469ce09"/>
     *     <endEvent id="endEvent_8087f927-a53b-4126-95fc-c057736f3b73">
     *       <incoming>sequenceFlow_5839394a-c0c2-4a5b-aa81-9412f169cc35</incoming>
     *     </endEvent>
     *     <sequenceFlow id="sequenceFlow_5839394a-c0c2-4a5b-aa81-9412f169cc35" sourceRef="serviceTask_f4c2413f-5b26-49e8-b71c-2603e469ce09" targetRef="endEvent_8087f927-a53b-4126-95fc-c057736f3b73"/>
     *   </process>
     *
     *   <!-- Generated Diagram Elements -->
     *   <bpmndi:BPMNDiagram id="BPMNDiagram_5b66dfb7-097b-4610-9681-43abb3ff97da">
     *     <bpmndi:BPMNPlane bpmnElement="process-payments" id="BPMNPlane_ad88b4cf-9d7a-4b86-8386-f8db23ff388d">
     *       <bpmndi:BPMNShape bpmnElement="startEvent_2b0abd37-75a9-47dd-9838-63f1390d7515" id="BPMNShape_d6c4e3c5-150c-43f7-adf8-1d388f466a69">
     *         <dc:Bounds height="36.0" width="36.0" x="100.0" y="100.0"/>
     *       </bpmndi:BPMNShape>
     *       <bpmndi:BPMNShape bpmnElement="serviceTask_f4c2413f-5b26-49e8-b71c-2603e469ce09" id="BPMNShape_51006773-13df-4327-a4cf-a5952c39e86a">
     *         <dc:Bounds height="80.0" width="100.0" x="186.0" y="78.0"/>
     *       </bpmndi:BPMNShape>
     *       <bpmndi:BPMNEdge bpmnElement="sequenceFlow_b1eec5b5-889d-4e75-854d-59768fbdc8a2" id="BPMNEdge_fb360594-8863-4d5d-b515-49e02a88d55d">
     *         <di:waypoint x="136.0" y="118.0"/>
     *         <di:waypoint x="186.0" y="118.0"/>
     *       </bpmndi:BPMNEdge>
     *       <bpmndi:BPMNShape bpmnElement="endEvent_8087f927-a53b-4126-95fc-c057736f3b73" id="BPMNShape_23930820-5507-45a0-8630-b5e45ee8dd4d">
     *         <dc:Bounds height="36.0" width="36.0" x="336.0" y="100.0"/>
     *       </bpmndi:BPMNShape>
     *       <bpmndi:BPMNEdge bpmnElement="sequenceFlow_5839394a-c0c2-4a5b-aa81-9412f169cc35" id="BPMNEdge_07ed502e-069f-42a0-bd1b-fed0d68efbda">
     *         <di:waypoint x="286.0" y="118.0"/>
     *         <di:waypoint x="336.0" y="118.0"/>
     *       </bpmndi:BPMNEdge>
     *     </bpmndi:BPMNPlane>
     *   </bpmndi:BPMNDiagram>
     * </definitions>
     */
    public void processWithFluentBuilder11(){
        final BpmnModelInstance myProcess = Bpmn.createExecutableProcess("process-payments")
                .startEvent()
                .serviceTask()
                .name("Process Payment")
                .endEvent()
                .done();

        System.out.println(Bpmn.convertToString(myProcess));
    }

    public void processWithFluentBuilder12(){}



}
