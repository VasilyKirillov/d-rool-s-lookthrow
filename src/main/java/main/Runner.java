package main;

import model.Fire;
import model.Room;
import model.Sprinkler;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import java.util.HashMap;
import java.util.Map;

public class Runner {
    public static void main(String[] args) {
        KieServices kieServices = KieServices.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();

        String[] names = new String[]{"kitchen", "bedroom", "office", "livingroom"};
        Map<String, Room> name2room = new HashMap<>();
        for( String name: names ){
            Room room = new Room( name );
            name2room.put( name, room );
            kieSession.insert( room );
            Sprinkler sprinkler = new Sprinkler( room );
            kieSession.insert( sprinkler );

        }



        kieSession.fireAllRules();
        Fire kitchenFire = new Fire( name2room.get( "kitchen" ) );
        Fire officeFire = new Fire( name2room.get( "office" ) );
        FactHandle kitchenFireHandle = kieSession.insert( kitchenFire );
        FactHandle officeFireHandle = kieSession.insert( officeFire );
        kieSession.fireAllRules();



        kieSession.delete( kitchenFireHandle );
        kieSession.delete( officeFireHandle );
        kieSession.fireAllRules();
    }

}
