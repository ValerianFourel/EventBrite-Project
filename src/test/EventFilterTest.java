package test;

// NAME: Valerian FOUREL

// ID: 260791863
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventFilterTest {
// Before Each for the rest of the test
     // we create the event objects
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
      // we initalize all of the event objects for the testingx
        List<Event> allEvents = new ArrayList<>();

        concert = new Concert("Depeche Mode",Location.BellCentre, LocalDate.of(2022,01,01), 50.0,1000,"Depeche Mode",10);
        concert2 = new Concert("BMTH in Montreal",Location.ParcJeanDrapeau,LocalDate.of(2021,10,10),13,400,"Bring Me The Horizon",20);
        workshop = new Workshop("Cherry Pie",Location.BellCentre,LocalDate.of(2021,12,20),9,25);
        workshop2 = new Workshop("Lemon Pie",Location.BellCentre,LocalDate.of(2021,12,27),9,25);
        workshop.addPrerequisites(workshop);
        // we add a prerequsiites for checking in
        gala = new Gala("Pierre invisible", Location.PlaceDesArts,LocalDate.of(2022,01,05),100,400,3);
        gala2 = new Gala("Launch of the Alliance",Location.OlympicStadium,LocalDate.of(2022,01,15),0,300,6);

        screening = new Screening("Kaamelott Livre I",Location.ParcJeanDrapeau,LocalDate.of(2021,10,10),10,200,Rating.G);
        screening2 = new Screening("Kaamelott Livre II",Location.ParcJeanDrapeau,LocalDate.of(2021,10,11),10,200,Rating.G);

        ArrayList<Event> kaamelott = new ArrayList<>();
        kaamelott.add(screening);
        kaamelott.add(screening2);


        festival1 = new Festival("Kaamelott",kaamelott);
        ArrayList<Event> type = new ArrayList<>();
        type.add(gala);
        type.add(screening);
        type.add(workshop2);
        type.add(concert);
        type.add(workshop);

        festival2 = new Festival("Type",type);
        ArrayList<Event> kaa = new ArrayList<>();
        kaa.add(festival1);
        kaamelottFilter = new FilterResult("kaamelott",kaa);

        profitSingleton = ProfitSingleton.getInstance();
        profitSingleton.setProfitRate(0.6,0.5,0.3,0.1);
        // adding all the events for the AllEvents Arraylist
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
    void CriteriaLocationTest(){
      // we verify that the criteria design pattern
        List<Event> eventOlympic = new ArrayList<>();
        eventOlympic.add(gala2);

        FilterResult filterOlympic = new FilterResult("OlympicEvent",eventOlympic);
        CriteriaLocation criteriaLocation = new CriteriaLocation(Location.OlympicStadium);

        assertEquals(filterOlympic.getaFilteredResult(),criteriaLocation.meetCriteria(filter).getaFilteredResult());


    }

    @Test
    void TailoredCriteriaTest(){
      // customized criteria design pattern
        CriteriaTailored criteriaNumofTickets = new CriteriaTailored("getNumTickets",200);
        assertEquals(festival1.getFestivalEvents(),criteriaNumofTickets.meetCriteria(filter).getaFilteredResult());
        CriteriaTailored criteriaOnePrice = new CriteriaTailored("getPrice",10.0); // we check events with an individual price
        assertEquals(festival1.getFestivalEvents(),criteriaOnePrice.meetCriteria(filter).getaFilteredResult());

    }

    @Test
    void PriceCriteriaTest(){
      // criteria with the price attribute of the event objects
        CriteriaPrice criteriaPrice = new CriteriaPrice(26,99);
        List<Event> concert1 = new ArrayList<>();
        concert1.add(concert);
        assertEquals(concert1,criteriaPrice.meetCriteria(filter).getaFilteredResult());
    }

    @Test
    void AndCriteriaTest(){
      // we verifiy that we can have either criteria
        CriteriaPrice criteriaPrice = new CriteriaPrice(0,25);
        CriteriaLocation criteriaLocation = new CriteriaLocation(Location.BellCentre);
        AndCriteria andCriteria = new AndCriteria(criteriaLocation,criteriaPrice);
        List<Event> events = new ArrayList<>();
        events.add(workshop);
        events.add(workshop2);
        assertEquals(events,andCriteria.meetCriteria(filter).getaFilteredResult());
    }

    @Test
    void OrCriteriaTest(){
      // we can have the combinations of results for either criteria A or criteria B,
        CriteriaPrice criteriaPrice = new CriteriaPrice(9,9);
        CriteriaLocation criteriaLocation = new CriteriaLocation(Location.BellCentre);
        OrCriteria orCriteria = new OrCriteria(criteriaLocation,criteriaPrice);
        List<Event> events = new ArrayList<>();
        events.add(concert);
        events.add(workshop);
        events.add(workshop2);
        assertEquals(events,orCriteria.meetCriteria(filter).getaFilteredResult());
    }
}
