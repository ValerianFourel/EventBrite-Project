package src;


// NAME: Valerian FOUREL

// ID: 260791863

public class EventVIPVisitor  implements EventVisitor{
  // visitor design pattern

    // we use a counter of the VIP
    private int VIPCounter = 0;
    // getter and restting of the VIP count
    public int getVIPCounter() {
        return VIPCounter;
    }

    public void resetVIPCounter() {
        this.VIPCounter = 0;
    }

    private void updateVIPCounter(int VIPCount){
        this.VIPCounter +=VIPCount;
    }
    // override visit functions
    @Override
    public void visit(Concert concert) {
         updateVIPCounter(concert.getVIPattendees());
    }

    @Override
    public void visit(Gala gala) {
        updateVIPCounter(gala.getVIPattendees());
    }

    @Override
    public void visit(Festival festival) {
        for(Event event : festival.getFestivalEvents()){
            event.accept(this);
        }
    }

    @Override
    public void visit(Screening screening) {
    }

    @Override
    public void visit(Workshop workshop) {
    }
}
