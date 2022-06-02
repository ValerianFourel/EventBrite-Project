package src;

// NAME: Valerian FOUREL

// ID: 260791863

public class ProfitSingleton  {
    // we use here a singleton as used in class
    // gotten from the module classes
    private static double profitConcert = 1;
    private static double profitWorkshop = 1;
    private static double profitGala = 1;
    private static double profitScreening = 1 ;
    private static double profitFestival = 0.8;

    private static ProfitSingleton profitSingleton = new ProfitSingleton();

    // private constructor so there can be only one of those objects
    private ProfitSingleton(){}

    public static ProfitSingleton getInstance(){
        return profitSingleton;
    }
    // we set all the rate
    public void setProfitRate(double profitConcert,double profitWorkshop, double profitGala, double profitScreening){
        this.profitConcert = profitConcert;
        this.profitWorkshop = profitWorkshop;
        this.profitGala = profitGala;
        this.profitScreening = profitScreening;
    }

    // setter and getter method for all individuals profit and revenue rate
    public void setProfitConcert(double profitConcert){
        this.profitConcert = profitConcert;
    }
    public void setProfitWorkshop(double profitWorkshop){
        this.profitWorkshop = profitWorkshop;
    }
    public void setProfitGala(double profitGala){
        this.profitGala = profitGala;
    }
    public void setProfitScreening(double profitScreening){
        this.profitScreening = profitScreening;
    }
    public void setRevenueFestival(double profitFestival){this.profitFestival = profitFestival;}

    public double getProfitConcert() {
        return profitConcert;
    }

    public  double getProfitWorkshop() {
        return profitWorkshop;
    }

    public double getProfitGala() {
        return profitGala;
    }

    public double getProfitScreening() {
        return profitScreening;
    }

    public double getRevenueFestival() {
        return profitFestival;
    }
}
