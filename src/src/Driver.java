package src;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// NAME: Valerian FOUREL

// ID: 260791863

public class Driver {
  // Driver code for the simulation of EventBrite

    public static void main(String[] args) throws NoSuchMethodException {


        Scanner scanner = new Scanner(System.in);
         EventManagement eventManagement = new EventManagement();

         // WE CONSIDER THAT THE "UX/UI" is built for Bob, so
        // cancel choices are with 0
        // and choices start  with 1
        // except when the cancel option is unavailable

        /**
        Bob can use 10 functions of the EventManagement class
         We use the scanner to gather the information.
         - addConcertEvent()
         - addGalaEvent()
         - addScreeningEvent()
         - addWorkshopEvent()
         - CreateFestival()  ---> CAVEAT: You need Events already present before creating a festival
         - removeEvent()
         - makeFilterResult() --> we choose the Events to put in a Filter
         - setProfitSingleton() --> we set the profitRate
         - selectFilterResult() --> we select which filter to use
         - updateEvent()
         */

        // we use those 10 functions below
       List<Method> methods = new ArrayList<>();
       methods.add(EventManagement.class.getMethod("addConcertEvent"));
       methods.add(EventManagement.class.getMethod("addGalaEvent"));
        methods.add(EventManagement.class.getMethod("addScreeningEvent"));
        methods.add(EventManagement.class.getMethod("addWorkshopEvent"));
        methods.add(EventManagement.class.getMethod("CreateFestival"));
        // we can find the expected profit of a set of Events by
        // assembling a list of Event in makeFilterResult
        // we then choose selectFilterResult, pick a FilterResult
        // and enter 3 to get the computation
        methods.add(EventManagement.class.getMethod("removeEvent"));
        methods.add(EventManagement.class.getMethod("makeFilterResult")); // --> we choose the Events to put in a Filter
        methods.add(EventManagement.class.getMethod("setProfitSingleton")); // we set the profit rates in the Singleton
        methods.add(EventManagement.class.getMethod("selectFilterResult")); // --> we select which filter to use
        methods.add(EventManagement.class.getMethod("updateEvent"));

        // we add al those methods and offer a menu for the user
        int index;
        while(true) {
            System.out.println("Select the function you want to use. Choose 0 to quit");
            for (int i = 0; i < methods.size(); i++) {
                System.out.println((i+1)+" "+methods.get(i).getName());
            }
            try{
                index = scanner.nextInt();
                if(index == 0){
                    break;
                }
                methods.get(index-1).invoke(eventManagement);
            }catch (Exception e){
                System.out.println("Error");
            }

        }



    }
}
