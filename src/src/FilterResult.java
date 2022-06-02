package src;


import java.util.ArrayList;
import java.util.List;

// NAME: Valerian FOUREL

// ID: 260791863
public class FilterResult {
  // Filter Design Pattern 
    private List<Event> aFilteredResult = new ArrayList<Event>();
    private EventVIPVisitor eventVIPVisitor = new EventVIPVisitor();
    private EventProfitVisitor eventProfitVisitor = new EventProfitVisitor();
    private String name; // we have the name of the FilterResult to differentiate them

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FilterResult(String name,List<Event> aFilteredResult){
        this.name = name;
        this.aFilteredResult = aFilteredResult;
    }

    public List<Event> getaFilteredResult(){
        return aFilteredResult;
    }

    public void addAFilteredResult(Event event) {
        this.aFilteredResult.add(event);
    }

    public void removeAFilteredResult(Event event) {
        this.aFilteredResult.remove(event);
    }

    public void removeAFilteredResult(int index) {
        this.aFilteredResult.remove(index);
    }
    // we can modify the events that are considered by a FilterResult


    // we compute the totalProfit expected
    public double totalProfit(){
        double totalProfit = 0;
        eventProfitVisitor.resetProfitCounter();
        for(Event event : aFilteredResult){
           event.accept(eventProfitVisitor);
        }
        totalProfit = eventProfitVisitor.getProfitCounter();
        return totalProfit;
    }

    // we compute the total number of VIP
    public int totalVIP(){
        int totalVIP = 0;
        eventProfitVisitor.resetProfitCounter();
        for(Event event : aFilteredResult){
            event.accept(eventVIPVisitor);
        }
        totalVIP = eventVIPVisitor.getVIPCounter();


        return totalVIP;

    }

}
