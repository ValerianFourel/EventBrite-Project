package src;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

// NAME: Valerian FOUREL

// ID: 260791863

public class CriteriaTailored implements Criteria{
  // criteria Design Pattern
    // Code from:
    // https://www.tutorialspoint.com/design_pattern/filter_pattern.htm
    private Object o;   // o is the contrainst (similar to the price) for this tailored Criteria
    private Method method;
    // We use Reflection and a Filter Design pattern for Tailored Criteria objects
    // we use a certain method to compare from the Event interface in order to get the method
    public CriteriaTailored(String methodName, Object o){
        try {
            this.method = Event.class.getMethod(methodName);
        } catch(Exception e){
            System.out.println("Error: Name of the Method is incorrect.");
        }
        this.o=o;
    }

    // we use the methodName, in order to find it
    public CriteriaTailored(String methodName){
        try {
            this.method = Event.class.getMethod(methodName);
        } catch(Exception e){
            System.out.println("Error: Name of the Method is incorrect.");
        }
    }

    public Method getMethod() {
        return method;
    }

    // we can modify the implementation by setting the method directly, from a previously create TailoredCriteria
    public void setMethod(Method method) {
        this.method = method;
    }

    // we have getter and setter functions for the constraint
    public Object getO() {
        return o;
    }

    public void setO(Object o) {
        this.o = o;
    }

    @Override
    public FilterResult meetCriteria(FilterResult events)  {
        List<Event> eventResult = new ArrayList<Event>();
        try {
            for (Event event : events.getaFilteredResult()) {

                if (method.invoke(event).equals(o)) {
                    eventResult.add(event);
                }
            }
        }catch (Exception e){
            // we have an Exception
            System.out.println("Type of Object and return type of the method does not match.");
        }

        FilterResult result = new FilterResult(events.getName()+":"+o.toString(),eventResult);
        return result;
    }
}
