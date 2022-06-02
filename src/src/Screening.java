package src;


import java.time.LocalDate;
// NAME: Valerian FOUREL

// ID: 260791863
public class Screening implements Event,setEvent {
    // attributes for Screening events with the requirements
    private  String Name;
    private Location location;
    private LocalDate localDate;
    private double price;
    private int numTickets;
    private Rating rating;

    // Constructor with all attibutes
    public Screening(String name, Location location, LocalDate localDate, double price, int numTickets, Rating rating) {
        this.Name = name;
        this.location=location;
        this.localDate = localDate;
        this.price = price;
        this.numTickets =  numTickets;
        this.rating=rating;
    }
    // small constructor
    public Screening(String name, LocalDate localDate) {
        this.Name = name;
        this.location= Location.ComingSoon;
        this.localDate = localDate;
        this.price = 0;
        this.numTickets =  1;
        this.rating= Rating.Undetermined;
    }
    // getter and setter for Rating
    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Rating getRating() {
        return rating;
    }


    // overriding the setEvent, and Event
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
