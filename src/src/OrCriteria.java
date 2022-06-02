package src;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
// NAME: Valerian FOUREL

// ID: 260791863
public class OrCriteria implements Criteria{

    // Code gotten from the following link:
    // https://www.tutorialspoint.com/design_pattern/filter_pattern.htm


    private Criteria criteria;
    private Criteria otherCriteria;

    public OrCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public FilterResult meetCriteria(FilterResult events) {
        FilterResult firstCriteriaItems = criteria.meetCriteria(events);
        FilterResult otherCriteriaItems = otherCriteria.meetCriteria(events);
        for (Event event : otherCriteriaItems.getaFilteredResult()) {
            if(!firstCriteriaItems.getaFilteredResult().contains(event)){
                firstCriteriaItems.addAFilteredResult(event);
            }
        }
        String nameOther = otherCriteriaItems.getName();
        String firstOther = firstCriteriaItems.getName();
        String newName = firstOther+"/Or/"+nameOther;
        firstCriteriaItems.setName(newName);
        return firstCriteriaItems;
    }
}
