package src;

import java.util.ArrayList;
import java.util.List;

// NAME: Valerian FOUREL

// ID: 260791863

public class CriteriaLocation implements Criteria {
  // Criteria Deisgn Pattern

    // Code from:
    // https://www.tutorialspoint.com/design_pattern/filter_pattern.htm

    private Location location;


    // we use the Location as a Criteria, and it is ar argument of the constructor
    public CriteriaLocation(Location location){
        this.location=location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    // overriding the interface
    @Override
    public FilterResult meetCriteria(FilterResult events) {
        List<Event> eventResult = new ArrayList<Event>();

        for(Event event: events.getaFilteredResult()){
            if(event.getLocation().compareTo(location) ==0){
                eventResult.add(event);
            }
        }

        FilterResult result = new FilterResult(events.getName()+":"+location.toString(), eventResult);
        return result;
    }
}
