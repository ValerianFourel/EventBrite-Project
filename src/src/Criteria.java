package src;

import java.util.List;

// NAME: Valerian FOUREL

// ID: 260791863

public interface Criteria {
  // Criteria Deisgn Pattern

    // Code from:
    // https://www.tutorialspoint.com/design_pattern/filter_pattern.htm
     FilterResult meetCriteria(FilterResult events);

}
