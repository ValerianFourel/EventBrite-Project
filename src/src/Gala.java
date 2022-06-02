package src;


import java.time.LocalDate;
// NAME: Valerian FOUREL

// ID: 260791863
public class Gala implements VIPEvent,setEvent {
  // attributes for Gala events with the requirements

    private String Name;
    private Location location;
    private LocalDate localDate;
    private double price;
    private int numTickets;
    private int VIPattendees;

    // we have a first constructor with all attributes
    public Gala(String name, Location location, LocalDate localDate, double price, int numTickets, int VIPattendees) {
        this.Name = name;
        this.location=location;
        this.localDate = localDate;
        this.price = price;
        this.numTickets =  numTickets;
        this.VIPattendees = VIPattendees;
    }
    // a second constructor with limited attributes
    public Gala(String name, LocalDate localDate) {
        this.Name = name;
        this.location= Location.ComingSoon;
        this.localDate = localDate;
        this.price = 0;
        this.numTickets =  1;
        this.VIPattendees = 0;
    }

    // we implement the method of the VIPEvent and setEvent interface
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
    public int getVIPattendees() {
        return VIPattendees;
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
    public void setVIPattendees(int VIPattendees) {
        this.VIPattendees = VIPattendees;
    }

    @Override
    public void setName(String name) {
        Name = name;
    }
}
