package src;

import java.time.LocalDate;

// NAME: Valerian FOUREL

// ID: 260791863

public class Concert implements VIPEvent, setEvent{

  // Concert Event object 
    // We implement 2 separate interface
    private String Name;
    private Location location;
    private LocalDate localDate;
    private double price;
    private int numTickets;
    private String Artist;
    private int VIPattendees;


    // Constructor
    public Concert(String name, Location location, LocalDate localDate, double price, int numTickets, String Artist,int VIPattendees) {
        this.Name = name;
        this.location=location;
        this.localDate = localDate;
        this.price = price;
        this.numTickets =  numTickets;
        this.Artist = Artist;
        this.VIPattendees = VIPattendees;
    }
    //Coming Soon Constructor
    public Concert(String name, LocalDate localDate){
        this.Name = name;
        this.location= Location.ComingSoon;
        this.localDate = localDate;
        this.price = 0;
        this.numTickets =  1;
        this.Artist = "John Doe" ; //
        this.VIPattendees = 0;
    }
    //setter and getter for the Artist
    public void setArtist(String Artist){
        this.Artist=Artist;
    }

    public String getArtist() {
        return Artist;
    }

    //Overriding the setEvent and VIPEvent interfaces
    @Override
    public void setName(String aName) {
        this.Name = aName;
    }

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
    public int getVIPattendees() {
        return VIPattendees;
    }

    @Override
    public void setVIPattendees(int VIPattendees) {
        this.VIPattendees = VIPattendees;
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


}
