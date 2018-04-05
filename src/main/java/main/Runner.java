package main;

import model.Room;
import model.Sprinkler;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

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
    }

}
