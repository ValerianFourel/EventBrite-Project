package src;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
// NAME: Valerian FOUREL

// ID: 260791863
public class Workshop implements Event,setEvent{

    private  String Name;
    private Location location;
    private LocalDate localDate;
    private double price;
    private int numTickets;
    List<Workshop> prerequisites = new ArrayList<>();

    // constructor with all attributes

    public Workshop(String name, Location location, LocalDate localDate, double price, int numTickets,  List<Workshop> prerequisites) {
        this.Name = name;
        this.location=location;
        this.localDate = localDate;
        this.price = price;
        this.numTickets =  numTickets;
        this.prerequisites = prerequisites;
    }

    public Workshop(String name, Location location, LocalDate localDate, double price, int numTickets) {
        this.Name = name;
        this.location=location;
        this.localDate = localDate;
        this.price = price;
        this.numTickets =  numTickets;
    }
    // constructor with only the name and localDate
    public Workshop(String name, LocalDate localDate) {
        this.Name = name;
        this.location=Location.ComingSoon;
        this.localDate = localDate;
        this.price = 0;
        this.numTickets =  1;
    }
    // getter and setter method for prerequisite
    public void addPrerequisites(Workshop event){
        this.prerequisites.add(event);
    }

    public void removePrerequisites(Workshop event){
        this.prerequisites.remove(event);
    }

    public void removePrerequisites(int index){
        this.prerequisites.remove(index);
    }

    public List<Workshop> getPrerequisites(){
        return prerequisites;
    }

    public void setPrerequisites(List<Workshop> prerequisites) {
        this.prerequisites = prerequisites;
    }

    // Overriding the Event , setEvent
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



    @Override
    public void accept(EventVisitor eventVisitor) {
         eventVisitor.visit(this);
    }
    @Override
    public void setLocation(Location location) {
        this.location = location;
    }
    @Override
    public void setDate(LocalDate localDate) {
        this.localDate = localDate;
    }
    @Override
    public void setPrice(double price) {
        this.price =price;
    }
    @Override
    public void setNumTickets(int numTickets) {
        this.numTickets = numTickets;
    }
    @Override
    public void setName(String name) {
        Name = name;
    }
}

