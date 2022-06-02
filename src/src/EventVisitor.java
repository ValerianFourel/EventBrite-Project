package src;


// NAME: Valerian FOUREL

// ID: 260791863
public interface EventVisitor {
     // code gotten from:
    // https://www.tutorialspoint.com/design_pattern/visitor_pattern.html
     // visitor design pattern interface
     void visit(Concert concert);
     void visit(Gala gala);
     void visit(Festival festival);
     void visit(Screening screening);
     void visit(Workshop workshop);

}
