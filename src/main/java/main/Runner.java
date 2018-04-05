package main;

import model.Applicant;
import model.Application;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Runner {
    public static void main(String[] args) {
        KieServices kservices = KieServices.get();
        KieContainer kieContainer = kservices.getKieClasspathContainer();

        StatelessKieSession kSession = kieContainer.newStatelessKieSession();
        Applicant applicant = new Applicant( "Mr John Smith", 17 );
        Application application = new Application();
        assertTrue( application.isValid() );
        kSession.execute( Arrays.asList( new Object[] { application, applicant } ) );
        assertFalse( application.isValid() );

    }

}
