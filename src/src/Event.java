
package src;

import java.time.LocalDate;

// NAME: Valerian FOUREL

// ID: 260791863

/*
Representation of a type of Event that can exist
 */
public interface Event {
  
     // we modify the interface in order to use as the "accept" function of a Visitor Design Pattern
     String getName();
     Location getLocation();
     LocalDate getDate();
     double getPrice();
     int getNumTickets();
     void accept(EventVisitor eventVisitor);



}
