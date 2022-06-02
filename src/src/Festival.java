package src;


import java.time.LocalDate;
import java.util.List;

// NAME: Valerian FOUREL

// ID: 260791863

public class Festival implements Event{
  // attributes for Festival events with the requirements

    // we have our values
    private String Name;
    private double price;
    private int numTickets;
    private Location location;
    private LocalDate localDate;
    final private List<Event> festivalEvents; // once set the list of events cannot be modified
    private ProfitSingleton profitSingleton = ProfitSingleton.getInstance();
    private double subsidy = profitSingleton.getRevenueFestival();

    public Festival(String name, List<Event> festivalEvents) {
        assert festivalEvents != null; // we assert its not null
        this.Name = name;
        this.festivalEvents = festivalEvents;
        updateFestival();
    }

    public void updateFestival(){ // we set the internal values of the festival
        setPrice();
        setNumTickets();
        setDate();
        setLocation();
    }

    public List<Event> getFestivalEvents() {
        return festivalEvents;
    }

    // we implement the functions of the Event interface
    @Override
    public String getName() {
        return Name;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public LocalDate getDate() {
        return localDate;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getNumTickets() {
        return numTickets;
    }

    // accept functions of the Visitor Design Pattern for the Event management class
    @Override
    public void accept(EventVisitor eventVisitor) {
         eventVisitor.visit(this);
    }

    // we determine the location of the festival depending of the location of the events composing the festival
    public void setLocation() {
        Location location = festivalEvents.get(0).getLocation();
        this.location=location;

        for(Event event: festivalEvents){
            if(event.getLocation().compareTo(Location.ComingSoon) == 0){
                this.location = Location.ComingSoon;     // if not all festival events are set the event is a Coming Soon type of event

                return;
            }
            if(!location.equals(event.getLocation())){
                this.location = Location.Multiple;
            }
        }
    }

    // we set the date by determining the date of the start off event
    public void setDate() {
        LocalDate localdate = festivalEvents.get(0).getDate();
        for(Event event : festivalEvents){
            if(event.getDate().compareTo(localdate) < 0){
                localdate = event.getDate();
        }
    }
    this.localDate = localdate;
    }
    // we set the price of the ticket by adding the price of all events and then multiplying by the 80% subsidy
    public void setPrice() {
        price = 0;
        for(Event event: festivalEvents){
            price += event.getPrice();
        }
        this.price = price*subsidy;
    }

    // we set the numofTickets by determining the smallest  amount of tickets, that can be sold for a single event
    public void setNumTickets() {
        int numTickets = this.festivalEvents.get(0).getNumTickets();
        for(Event event: festivalEvents){
            if(event.getNumTickets() <= numTickets){
                numTickets = event.getNumTickets();
            }
        }
        this.numTickets = numTickets;
    }

    public void setName(String name) {
        Name = name;
    }
}
