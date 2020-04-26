package com.gupao.vip.drools.edu.test;

import com.gupao.vip.drools.edu.entity.Person;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class HelloTest {

    private static KieContainer container = null;
    private KieSession statefulKieSession = null;

    @Test
    public void test() {
        KieServices kieServices = KieServices.Factory.get();
        container = kieServices.getKieClasspathContainer();
        statefulKieSession = container.newKieSession("ksession-rule");
        Person person = new Person("Kevin",5);
        statefulKieSession.insert(person);
        statefulKieSession.fireAllRules();
        Person person2 = new Person("Jack",50);
        statefulKieSession.insert(person2);
        statefulKieSession.fireAllRules();

        statefulKieSession.dispose();
    }
}
