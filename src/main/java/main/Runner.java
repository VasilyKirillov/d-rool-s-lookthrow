package main;


import model.Person;
import org.kie.api.KieServices;
import org.kie.api.command.Command;

import org.kie.api.command.KieCommands;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;


import java.util.ArrayList;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Runner {
    public static void main(String[] args) {
        KieServices kieServices = KieServices.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();
        KieCommands kieCommands = kieServices.getCommands();
//doesn't work batch command
        List<Command> cmds = new ArrayList<Command>();
        cmds.add( kieCommands.newInsert( new Person( "Mr John Smith" ), "mrSmith", true, null ) );
        cmds.add( kieCommands.newInsert( new Person( "Mr John Doe" ), "mrDoe", true, null ) );
        BatchExecutionResults results = kieSession.execute( kieCommands.newBatchExecution( cmds ) );
        assertEquals( new Person( "Mr John Smith" ), results.getValue( "mrSmith" ) );

    }

}
