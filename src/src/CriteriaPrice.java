package src;

import java.util.ArrayList;
import java.util.List;

// NAME: Valerian FOUREL

// ID: 260791863

public class CriteriaPrice implements Criteria{
// Criteria Deisgn Pattern 
    // Code from:
    // https://www.tutorialspoint.com/design_pattern/filter_pattern.htm

    private double minPrice;
    private double maxPrice;

    // we use a price range in the Critera
    public CriteriaPrice( double minPrice, double maxPrice){
        this.minPrice=minPrice;
        this.maxPrice=maxPrice;
    }

    // we can change the Criteria by changing the max and min prices
    public double getMaxPrice() {
        return maxPrice;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    @Override
    public FilterResult meetCriteria(FilterResult events) {
        List<Event> eventResult = new ArrayList<Event>();

        for(Event event: events.getaFilteredResult()){
            if(event.getPrice() >= minPrice && event.getPrice() <= maxPrice){
                eventResult.add(event);
            }
        }

        FilterResult result = new FilterResult(events.getName()+minPrice+"/"+maxPrice,eventResult);
        return result;
    }
}
