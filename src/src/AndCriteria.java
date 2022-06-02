package src;

import java.util.List;

// NAME: Valerian FOUREL

// ID: 260791863

public class AndCriteria implements Criteria{

  // Criteria Deisgn Pattern

    // Code gotten from the following link:
    // https://www.tutorialspoint.com/design_pattern/filter_pattern.htm

    private Criteria criteria;
    private Criteria otherCriteria;

    public AndCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public FilterResult meetCriteria(FilterResult events) {
        FilterResult firstCriteriaEvent = criteria.meetCriteria(events);
        FilterResult result = otherCriteria.meetCriteria(firstCriteriaEvent);
        String name = result.getName();
        result.setName("And/"+name);
        return result;
    }
}
