package main;

import model.Applicant;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Runner {
    public static void main(String[] args) {
        KieServices kservices = KieServices.get();
        KieContainer kieContainer = kservices.getKieClasspathContainer();

        StatelessKieSession kieSession = kieContainer.newStatelessKieSession();
        Applicant applicant = new Applicant("John Dow", 17);
        assertTrue( applicant.isValid() );
        kieSession.execute(applicant);
        assertFalse( applicant.isValid() );

    }

}
