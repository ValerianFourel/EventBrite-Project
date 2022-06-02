package test;

// NAME: Valerian FOUREL

// ID: 260791863
/*

All imports
*/
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

public class EventRequirementTest {

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
      // we are generating the events
        List<Event> allEvents = new ArrayList<>();

        concert = new Concert("Depeche Mode",Location.BellCentre, LocalDate.of(2022,01,01), 50.0,1000,"Depeche Mode",10);
        concert2 = new Concert("BMTH in Montreal",Location.ParcJeanDrapeau,LocalDate.of(2021,10,10),10,400,"Bring Me The Horizon",20);
        workshop = new Workshop("Cherry Pie",Location.BellCentre,LocalDate.of(2021,12,20),9,25);
        workshop2 = new Workshop("Lemon Pie",Location.BellCentre,LocalDate.of(2021,12,27),9,25);
        workshop.addPrerequisites(workshop);
        // building the galas
        gala = new Gala("Pierre invisible", Location.PlaceDesArts,LocalDate.of(2022,01,05),100,400,3);
        gala2 = new Gala("Launch of the Alliance",Location.OlympicStadium,LocalDate.of(2022,01,15),0,300,6);

        screening = new Screening("Kaamelott Livre I",Location.ParcJeanDrapeau,LocalDate.of(2021,10,10),10,200,Rating.G);
        screening2 = new Screening("Kaamelott Livre II",Location.ParcJeanDrapeau,LocalDate.of(2021,10,11),10,200,Rating.G);

        ArrayList<Event> kaamelott = new ArrayList<>();
          // creating the events
        kaamelott.add(screening);
        kaamelott.add(screening2);


        festival1 = new Festival("Kaamelott",kaamelott);
        ArrayList<Event> type = new ArrayList<>();
        // adding an event object for all types of event
        type.add(gala);
        type.add(screening);
        type.add(workshop2);
        type.add(concert);
        type.add(workshop);
        // testing with a new festival
        festival2 = new Festival("Type",type);
        ArrayList<Event> kaa = new ArrayList<>();
        kaa.add(festival1);
        kaamelottFilter = new FilterResult("aa",kaa);

        profitSingleton = ProfitSingleton.getInstance();
        profitSingleton.setProfitRate(0.6,0.5,0.3,0.1);
        //setting profits rate
        allEvents.add(concert);
        allEvents.add(concert2);
        allEvents.add(gala);
        allEvents.add(gala2);
        allEvents.add(screening);
        allEvents.add(screening2);
        // adding all of the events
        allEvents.add(workshop);
        allEvents.add(workshop2);
        filter = new FilterResult("all",allEvents);

    }


    @Test
    void festivalLocationTest(){
      // testing the requirements of the festivals
        assertEquals(Location.Multiple,festival2.getLocation());
        assertEquals(Location.ParcJeanDrapeau,festival1.getLocation());
        //check the number of tickets
        assertEquals(200, festival1.getNumTickets());
        assertEquals(25, festival2.getNumTickets());
    }

    @Test
    void ComingSoonTest(){
        // we have a special constructor to set the event as Coming Soon
        Workshop workshop1 = new Workshop("First",LocalDate.of(2022,12,11));
        Screening screening1 = new Screening("Second",LocalDate.of(2022,11,11));
        Concert concert1 = new Concert("Third",LocalDate.of(2022,04,04));
        Gala gala1 = new Gala("Fourth",LocalDate.of(2022,10,10));
        // testing the location of the Coming Soon
        assertEquals(Location.ComingSoon,gala1.getLocation());
        assertEquals(Location.ComingSoon,concert1.getLocation());
        assertEquals(Location.ComingSoon,screening1.getLocation());
        assertEquals(Location.ComingSoon,workshop1.getLocation());

    }

}
