package test;



// NAME: Valerian FOUREL

// ID: 260791863

// we bring the units for unit testing
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

public class EventSingletonTest {


    ProfitSingleton profitSingleton;



    @BeforeEach
    public void initEach(){
      // we initialize the Singleton

        profitSingleton = ProfitSingleton.getInstance();
        profitSingleton.setProfitRate(0.6,0.5,0.3,0.1);

    }

    @Test
    void IsASingletonTest(){
      // we check the that it is a singleton
        ProfitSingleton profitSingleton1 = ProfitSingleton.getInstance();
        assertEquals(profitSingleton1.getProfitGala(),profitSingleton.getProfitGala());
        assertEquals(profitSingleton1.getProfitScreening(),profitSingleton.getProfitScreening());
        assertEquals(profitSingleton1,profitSingleton);


    }

    @Test
    void getterSingletonTest(){
        // we get the singleton
        ProfitSingleton profitSingleton1 = ProfitSingleton.getInstance();

        assertEquals(0.6,profitSingleton.getProfitConcert());
        assertEquals(0.5,profitSingleton.getProfitWorkshop());
        assertEquals(profitSingleton1.getRevenueFestival(),profitSingleton.getRevenueFestival());
    }

    @Test
    void setterSingletonTest(){
      // we check if we can set the profits rate for the Singleton
        ProfitSingleton profitSingleton1 = ProfitSingleton.getInstance();
        profitSingleton1.setProfitGala(0.8);
        assertEquals(0.8,profitSingleton.getProfitGala());
        profitSingleton1.setRevenueFestival(0);
        assertEquals(0 ,profitSingleton.getRevenueFestival());
    }



}
