package src;

// NAME: Valerian FOUREL

// ID: 260791863
public class EventProfitVisitor implements EventVisitor{

    // we get our data with the singleton, the Settings of our Event Management
    ProfitSingleton singleton = ProfitSingleton.getInstance();

    // we count the profit passing through this visitor object
    private double profitCounter = 0;
    // we need those values, to determine whether we are in a Festival object
    private boolean isFestival = false;
    private int numOfTicketsFestival = 0;
    // we have the Profit Counter getter method
    public double getProfitCounter() {
        return profitCounter;
    }

    // we can reset the Profit Counter
    public void resetProfitCounter() {
        this.profitCounter = 0;
        this.isFestival = false;
        this.numOfTicketsFestival = 0;
    }

    // we update the Profit Counter herr
    private void updateProfitCounter(int numOfTickets,double price, double revenueShare){
        double profit = 0;
        if(this.isFestival) { // we differentiate the computation of the profits depending on whther we are in a Festival
            profit = numOfTickets * price * revenueShare * (numOfTicketsFestival/numOfTickets) * singleton.getRevenueFestival();
            profit += numOfTickets * price * revenueShare * ((numOfTickets - numOfTicketsFestival)/numOfTickets);
            profitCounter += profit;
        }else{
            profit = numOfTickets * price * revenueShare;
            profitCounter += profit;

        }
    }

    // we have the visit functions here to compute the potential profits
    @Override
    public void visit(Concert concert) {
         updateProfitCounter(concert.getNumTickets(), concert.getPrice() , singleton.getProfitConcert());
    }

    @Override
    public void visit(Gala gala) {
         updateProfitCounter(gala.getNumTickets() ,gala.getPrice() , singleton.getProfitGala());
    }

    @Override
    public void visit(Festival festival) {
        this.isFestival = true;
        this.numOfTicketsFestival = festival.getNumTickets();
        // we set those number for the computation of the profits of the festival
        for(Event event : festival.getFestivalEvents()){
            event.accept(this);
        }
        this.isFestival = false;
        this.numOfTicketsFestival = 0;

    }

    @Override
    public void visit(Screening screening) {
        updateProfitCounter(screening.getNumTickets() , screening.getPrice() , singleton.getProfitScreening());
    }

    @Override
    public void visit(Workshop workshop) {
        updateProfitCounter(workshop.getNumTickets() , workshop.getPrice() , singleton.getProfitWorkshop());
    }
}
