package test;


/*
Vring in the imports for unit testing */

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventVisitorTest {


    /*
    We have the objects we want to generate
    */


    Concert concert;
    Concert concert2;
    Workshop workshop;
    Workshop workshop2;
    Gala gala;
    Gala gala2;
    Screening screening;
    Screening screening2;
    Festival festival1;
    Festival festival2;
    ProfitSingleton profitSingleton;
    FilterResult filter;
    FilterResult kaamelottFilter;
    EventVIPVisitor eventVIPVisitor = new EventVIPVisitor();
    EventProfitVisitor eventProfitVisitor = new EventProfitVisitor();


    @BeforeEach
    public void initEach(){
        List<Event> allEvents = new ArrayList<>();
        // building the event objects
        // initializing the concerts
        concert = new Concert("Depeche Mode",Location.BellCentre, LocalDate.of(2022,01,01), 50.0,1000,"Depeche Mode",10);
        concert2 = new Concert("BMTH in Montreal",Location.ParcJeanDrapeau,LocalDate.of(2021,10,10),10,400,"Bring Me The Horizon",20);
        // initializing the workshops
        workshop = new Workshop("Cherry Pie",Location.BellCentre,LocalDate.of(2021,12,20),9,25);
        workshop2 = new Workshop("Lemon Pie",Location.BellCentre,LocalDate.of(2021,12,27),9,25);
        workshop.addPrerequisites(workshop);
        //  initializing the Gala objects
        gala = new Gala("Pierre invisible", Location.PlaceDesArts,LocalDate.of(2022,01,05),100,400,3);
        gala2 = new Gala("Launch of the Alliance",Location.OlympicStadium,LocalDate.of(2022,01,15),0,300,6);

        screening = new Screening("Kaamelott Livre I",Location.ParcJeanDrapeau,LocalDate.of(2021,10,10),10,200,Rating.G);
        screening2 = new Screening("Kaamelott Livre II",Location.ParcJeanDrapeau,LocalDate.of(2021,10,11),10,200,Rating.G);
        // creating the screenings
        // creating an Arraylist of Events
        ArrayList<Event> kaamelott = new ArrayList<>();
        kaamelott.add(screening);
        kaamelott.add(screening2);


        festival1 = new Festival("Kaamelott",kaamelott);
        ArrayList<Event> type = new ArrayList<>();
        // we create an array list for the types of events
        type.add(gala);
        type.add(screening);
        type.add(workshop2);
        type.add(concert);
        type.add(workshop);

        festival2 = new Festival("Type",type);
        ArrayList<Event> kaa = new ArrayList<>();
        kaa.add(festival1);
        kaamelottFilter = new FilterResult("kaamelott",kaa);
        // we use a singleton design pattern

        profitSingleton = ProfitSingleton.getInstance();
        profitSingleton.setProfitRate(0.6,0.5,0.3,0.1);

        allEvents.add(concert);
        allEvents.add(concert2);
        allEvents.add(gala);
        allEvents.add(gala2);
        allEvents.add(screening);
        allEvents.add(screening2);
        allEvents.add(workshop);
        allEvents.add(workshop2);
        filter = new FilterResult("allEvents",allEvents);

    }

    @Test
    void VisitorTest(){
      // we test the visitor design pattern
        double galaProfit = 0.3*400*100;
        eventProfitVisitor.visit(gala);
        // checking if the profit rate is correct
        assertEquals(galaProfit,eventProfitVisitor.getProfitCounter());
        eventProfitVisitor.resetProfitCounter();
        assertEquals(0,eventProfitVisitor.getProfitCounter());
    }

    @Test
    void TotalProfitTest(){
        // checking the profits from festivals
        double festival1profit = 0.1*0.8*200*10 + 0.1*0.8*200*10;
        assertEquals(Math.floor(festival1profit),kaamelottFilter.totalProfit());

    }

    @Test
    void TotalVIPTest(){
        // we check the total number of VIPS
        int totalVIP = 39;
        assertEquals(totalVIP, filter.totalVIP());
    }


}
