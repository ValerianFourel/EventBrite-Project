package src;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.*;

// NAME: Valerian FOUREL

// ID: 260791863

/*
Controller to manage events hosted on EventBrite.
 */
public class EventManagement implements EventVisitor{
  // Event Management

    private final int choicesConcert = 7; // number of functions of each type of Events
    private final int choicesGala = 6;
    private final int choicesFestival = 1;
    private final int choicesScreening = 6;
    private final int choicesWorkshop =6;

    private List<Event> aHostedEvents = new ArrayList<Event>(); // we have all Events in this Arraylist
    private List<FilterResult> allFilter = new ArrayList<FilterResult>(); // All Filters are contained in this one
    private List<Criteria> allCriteria = new ArrayList<>(); // All Criteria object created

    private Scanner scanner = new Scanner(System.in); // Scanner to get the user input
    private ProfitSingleton profitSingleton = ProfitSingleton.getInstance(); // we use a singleton
    private FilterResult all = new FilterResult("all",aHostedEvents); // FilterResult with all events present


    /*
    Method to host a new concert event
     */
    public void addConcertEvent(){
        System.out.println("Adding a Concert"); // we use a function to signal what we are doing
        Concert concert;
        LocalDate date = getDate();
        String name = getName();

        boolean comingSoon = getComingSoon();

        if(comingSoon){ // we  verify whether the Event is coming soon
            concert = new Concert(name,date);
            aHostedEvents.add(concert);
            return;
        }
        Location location = getLocation(); // we get all of the attributes
        boolean check = checkDateAndLocation(date,location); // we verify whether the date and location
        // is legal, as specified in the requirements
        if(!check){
            System.out.println("Error Date and Location taken");
            return;
        }
        String artist = getArtist();

        double price = getPrice();
        int numOfTickets = getNumOfTickets();
        int VIPs = getNumOfVIPs();
        concert = new Concert(name,location,date,price,numOfTickets,artist,VIPs); // creating the Concert object, in a concrete class
        aHostedEvents.add(concert); // we add the event in the arraylist of all events

    }
    /*
    Method to host a new Gala event
     */
    public void addGalaEvent(){
        System.out.println("Adding a Gala");

        Gala gala;
        LocalDate date = getDate();
        String name = getName();

        boolean comingSoon = getComingSoon();

        if(comingSoon){ // we get whether the Event is all set
            gala = new Gala(name,date);
            aHostedEvents.add(gala);
            return;
        }
        Location location = getLocation();
        boolean check = checkDateAndLocation(date,location); // we verify whether the date and location
        // is legal, as specified in the requirements
        if(!check){
            System.out.println("Error Date and Location taken");
            return;
        }
        double price = getPrice();
        int numOfTickets = getNumOfTickets();
        int VIPs = getNumOfVIPs(); // we get the number of vips with a spceialized function
        gala = new Gala(name,location,date,price,numOfTickets,VIPs); // creating the object and adding it
        aHostedEvents.add(gala);



    }

    /*
    Method to host a new Screening event
     */
    public void addScreeningEvent(){
        System.out.println("Adding a Screening");

        Screening screening;
        LocalDate date = getDate();

        String name = getName();

        boolean comingSoon = getComingSoon();

        if(comingSoon){
            screening = new Screening(name,date);
            aHostedEvents.add(screening);
            return;
        }

        Location location = getLocation();
        boolean check = checkDateAndLocation(date,location); // we verify whether the date and location
        // is legal, as specified in the requirements
        if(!check){
            System.out.println("Error Date and Location taken");
            return;
        }
        double price = getPrice();
        int numOfTickets = getNumOfTickets();
        Rating rating = getRating();
        screening = new Screening(name,location,date,price,numOfTickets,rating); // creating the object
        aHostedEvents.add(screening);

    }

    private List<Workshop> getPrerequisites(){
        List<Workshop> workshops = new ArrayList<>();
        System.out.println(" If this workshop is a prerequisite then enter x");
        for(Event event: aHostedEvents){

            if(event instanceof Workshop){
                System.out.println(event.getName() +" "+event.getDate()+" "+event.getLocation()+" "+event.getPrice());
                String choice = scanner.next();
                if(choice.equals("x") || choice.equals("X")) {
                    workshops.add((Workshop) event);
                }
            }
        }
        return workshops;
    }

    /*
    Method to host a new Workshop event
     */
    public void addWorkshopEvent(){
        System.out.println("Adding a Workshop");

        Workshop workshop;
        String name = getName();
        LocalDate date = getDate();
        boolean comingSoon = getComingSoon();

        if(comingSoon){
            workshop = new Workshop(name,date);
            aHostedEvents.add(workshop);
            return;
        }
        Location location = getLocation();
        boolean check = checkDateAndLocation(date,location); // we verify whether the date and location
        // is legal, as specified in the requirements
        if(!check){
            System.out.println("Error Date and Location taken");
            return;
        }
        double price = getPrice();
        int numOfTickets = getNumOfTickets();
        System.out.println("Enter x if there are prerequsites");
        String choice = scanner.next(); // we pick the choice with the scanner
        if(choice.equals("x") || choice.equals("X")) {
            List<Workshop> workshops = getPrerequisites();
            workshop = new Workshop(name, location, date, price, numOfTickets,workshops);
        } else {
            workshop = new Workshop(name, location, date, price, numOfTickets);
        }
        aHostedEvents.add(workshop);
    }

    /*
    Return the list of hosted events on EventBrite.
    This method assumes that Events are immutable.
     */
    public ArrayList<Event> getHostedEvents(){
        return new ArrayList<Event>(aHostedEvents);
    }
      // use the filter design pattern
    public List<FilterResult> getAllFilter() {
        return allFilter;
    }
    // list of all the criteria for the design pattern
    public List<Criteria> getAllCriteria() {
        return allCriteria;
    }

    // we verify whether the Date and Location are matching
    private boolean checkDateAndLocation(LocalDate localDate, Location location){
        for(Event event: getHostedEvents()){
            if(event.getDate().equals(localDate) && event.getLocation().equals(location)){
                return false;
            }
        }
        return true;
    }
    // we get the Name of an Event
    private String getName(){
        System.out.println("Get Name: ");
        String name = scanner.next();
        return name;
    }
    // we get the Location of an event
    private Location getLocation(){

        Location location = Location.ComingSoon;
            try {
                System.out.println("Choose Location of the Event:");
                List<Location> locations = java.util.Arrays.asList(Location.values());
                for(int i = 0; i < locations.size();i++){
                    System.out.println((i+1)+" "+locations.get(i));
                }
                int index = scanner.nextInt();
                location = java.util.Arrays.asList(Location.values()).get(index - 1);
            } catch (Exception e) {
                System.out.println("Index Out of Bound");
            }

        return location;
    }
    // we get the date of the Event
    private LocalDate getDate(){
        LocalDate d1;
        while(true) {
            try {
                System.out.println("Choose Date of the Event:");
                String dInStr = scanner.next();
                 d1 = LocalDate.parse(dInStr);
                break;
            } catch (Exception e) {
                System.out.println("Format is Incorrect");
            }
        }
        return d1;

    }
    // we get the price of a ticket for the Event
    private double getPrice(){
        System.out.println("Choose Price of the Event:");
        double price = 10;
        try {
            price = scanner.nextDouble();
        }catch (Exception e){
            System.out.println("Format is wrong");
        }

        return price;
    }
    // we get the number of tickets sold for this event
    private int getNumOfTickets(){
        int numOfTickets = 100;
        try {
            System.out.println("Choose the Number of Tickets of the Event:");
            numOfTickets = scanner.nextInt();
        } catch (Exception e){
            System.out.println("Format is Incorrect");
        }
        return numOfTickets;
    }
    // we get the number of VIPs for the VIPEvent, which extends the Event
    private int getNumOfVIPs(){
        int numOfVIPS = 0;
        try {
            System.out.println("Choose the Number of VIPs of the Event:");
            numOfVIPS = scanner.nextInt();
        }catch (Exception e){
            System.out.println("Format is incorrect");
        }
        return numOfVIPS;
    }
    // we get the Artist for the Concert
    private String getArtist(){
        System.out.println("Get Artist of Event: ");
        String name =  scanner.next();
        return name;
    }
    // we get the Rating of the screening
    private Rating getRating(){
        Rating rating = Rating.Undetermined;
        try {
            System.out.println("Choose Rating of the Screening:");
            List<Rating> ratings = java.util.Arrays.asList(Rating.values());
            for(int i =0;i<ratings.size();i++){
                // we show all ratings for the user to determine which one to use
                System.out.println((i+1)+" "+ ratings.get(i));
            }
            int index = scanner.nextInt();
            rating = java.util.Arrays.asList(Rating.values()).get(index-1);
        }catch (Exception e){
            System.out.println("Format of the Screening is incorrect;");
        }
        return rating;
    }
    // we check whether the event is coming soon
    private boolean getComingSoon(){
        boolean comingSoon = false;
        System.out.println("If the Event is \'Coming Soon\', please enter X: ");
        String check = scanner.next();
        if(check.equals("x") || check.equals("X")){
            // if the options  is selected then the constructor
            // only has a localDate and name field!
            // the location is coming soon
            // the information can be update
            // in update Events
            comingSoon = true;
        }
        return comingSoon;
    }
    // we can remove the Event
    public void removeEvent(){
        System.out.println("removeEvent");
        int index = 0;
        Event event;
        System.out.println("Select Event to Delete. Choose 0 to cancel");
        for(int i = 0 ; i < aHostedEvents.size();i++){
            event = aHostedEvents.get(i);
            // we show all events that can be removed
            System.out.println((i+1) +": "+event.getClass()+" "+event.getName()+" "+event.getLocation()+" "+event.getDate());
        }
        try{
            index = scanner.nextInt();
            if(index == 0){
                return;
            }
            aHostedEvents.remove(index-1);
        } catch(Exception e){
            System.out.println("Index Selected is Out of Bound");
        }
    }

    // we can make a FilterResult Object for future use
    public void makeFilterResult(){
        System.out.println("make Filter Result");
        int index = 0;
        Event event;
        List<Event> filterResult = new ArrayList<>();
        while(true) {
            System.out.println("Select Event to filter. Choose 0 to validate choices.");
            for (int i = 0; i < aHostedEvents.size(); i++) {
                event = aHostedEvents.get(i);
                System.out.println((i + 1) + ": " + event.getClass() + " " + event.getName() + " " + event.getLocation() + " " + event.getDate());

            }
            try {
                index = scanner.nextInt();
                // if the filterResult contains all the elements we break the loop
                if (index == 0 || filterResult.size() == aHostedEvents.size()) {
                    break;
                }
                if(!(filterResult.contains(aHostedEvents.get(index-1)))){
                filterResult.add(aHostedEvents.get(index-1));
                }
            } catch (Exception e) {
                System.out.println("Index Selected is Out of Bound");
            }
        }
        String name = getName();
        FilterResult filterResult1 = new FilterResult(name, filterResult);
        allFilter.add(filterResult1);
    }
    // we set the profit rate of the Singleton
    public void setProfitSingleton(){
        // when we reset the profit rates of the singleton we reset them all
        System.out.println("Setting the profit rates of the Singleton");
        try {
            System.out.println("Set Profit rate for Concert: ");
            double concert = scanner.nextDouble(); // we get the profit rate for Concert
            System.out.println("Set Profit rate for Gala: ");
            double gala = scanner.nextDouble();
            System.out.println("Set Profit rate for Workshop: ");
            double workshop = scanner.nextDouble();
            System.out.println("Set Profit rate for Screening: ");
            double screening = scanner.nextDouble();
            profitSingleton.setProfitRate(concert, workshop, gala, screening);
        } catch (Exception e){
            System.out.println("Error in Format. ");
        }

    }
    // we select the FilterResult for the computation or Change you want
    public void selectFilterResult(){
        System.out.println("Selecting a Filter to have an operation on");
        FilterResult filterResult;
        if(allFilter.size() == 0){
            allFilter.add(all);
        }
        for(int i = 0;i< allFilter.size(); i++){
            filterResult = allFilter.get(i);
            System.out.println((i+1)+" "+filterResult.getName()+" "+filterResult.getaFilteredResult().size());
        }
        Criteria criteria;

        int selection = 0;

        while (true) {
            try {
                System.out.println("Please choose which filter to use. Choose 0 to leave");
                selection = scanner.nextInt(); // we get the index of the FilterResult wanted
                if (selection == 0) {
                    return;
                }
                filterResult = allFilter.get(selection - 1);
                break;
            } catch (Exception e) {
                System.out.println("Selection is invalid"); // we consider this selection to be invalid
                return;
            }
        }
        while(true) { // we select what to do with the Criteria
            System.out.println("now choose which function to implement: ");
            // we have different choice for this program
            System.out.println("1. buildCriteria 2. Select Criteria 3. Profit Computation 4. VIP Computation 5. Show Filter Result");
            int selectionFunc = 0;
            try {
                selectionFunc = scanner.nextInt();
            } catch (Exception e){
                System.out.println("Error in the Format"); // this is in case of an error
                return;
            }
            if(selectionFunc==0){
                return; // we consider all 5 options
            } else if(selectionFunc==1) {
                buildCriteria(filterResult);
                break;
            }else if(selectionFunc==2){
                if(allCriteria.size() == 0){
                    return;
                }
                criteria = getACriteria();
                allFilter.add(criteria.meetCriteria(filterResult));
                break;
            } else if(selectionFunc==3){
                getProfitfromFilter(filterResult); // we compute the profit from the Filter
                break;
            } else if(selectionFunc ==4){
                getVIPfromFilter(filterResult); // we compute the number of VIP of the Filter
                break;
            } else if(selectionFunc == 5){
                showFilterResult(filterResult); // We show the Events in the current Filter
                break;
            }
        }
    }

    private void showFilterResult(FilterResult filterResult){
        List<Event> events = filterResult.getaFilteredResult();
        for(Event event : events){
            System.out.println(event.getName()+" "+event.getLocation()+" "+event.getDate()+" "+event.getPrice()+" "+event.getNumTickets());
        }
    }

    private void buildCriteria(FilterResult filterResult){
        // we have the function for the choice of Criteria to be made here
        // there are 5 types of Criteria implementing the interface
        String name;
        int selection = 0;
        System.out.println("Choose the type of Criteria wanted. Choose 0 to leave");
        System.out.println("1. Criteria by Location "+ "2. Criteria by Price "+ " 3. Tailored Criteria ");
        System.out.println(" 4. AndCriteria "+ " 5. OrCriteria ");
        try {
             selection = scanner.nextInt();
        } catch (Exception e){
            System.out.println("Error of Format");
            selection = 0; // if there's an error we stop the operation
        }
        if(selection == 0){
            return;
        } else if(selection ==1){
            // we pick the Criteria
            // and then add the Criteria
            Location location = getLocation();
            CriteriaLocation criteriaLocation = new CriteriaLocation(location);
            //we must all the criteria to the previous criterias to that it is saved
            allCriteria.add(criteriaLocation);
            // we also save the result of that filtering
            allFilter.add(criteriaLocation.meetCriteria(filterResult));

        } else if(selection ==2){
            // we have here the price range criteria
            System.out.println("get min price:");
            double minPrice = getPrice();
            System.out.println("get max price:");
            double maxPrice = getPrice();
            CriteriaPrice criteriaPrice = new CriteriaPrice(minPrice,maxPrice);
            allCriteria.add(criteriaPrice);
            // criterias and FilterResult are always saved
            allFilter.add(criteriaPrice.meetCriteria(filterResult));

        }  else if(selection ==3){
            // we get the method name, which is limited to the Event type of method
            // through reflections
            // Events cannot be filtered by prerequisites, # of VIPs, or Artist
            String methods  = getMethods();
            Object o = null;
            try { // we rebuild the code so that it sends back the proper function for the tailored criteria
                if(methods.equals("getName")){
                    o = getName();

                } else if(methods.equals("getLocation")){
                    o = getLocation();

                }else if(methods.equals("accept")){
                    return;
                } else if(methods.equals("getDate")){
                    o= getDate();

                }else if(methods.equals("getNumTickets")){
                    o= getNumOfTickets();

                }else if(methods.equals("getPrice")){
                    o= getPrice();

                }
            } catch ( Exception e) {
                e.printStackTrace();
            }
            CriteriaTailored criteriaTailored;
            try {
                criteriaTailored = new CriteriaTailored(methods,o);

            }catch (Exception e){
                e.printStackTrace();
                System.out.println("Error in the Tailored Criteria");
                return;
            }
            allCriteria.add(criteriaTailored);
            // criterias and FilterResult are saved again
            allFilter.add(criteriaTailored.meetCriteria(filterResult));

        } else if(selection == 4){
            Criteria criteria1 = getACriteria();
            Criteria criteria2 = getACriteria();
            // We know consider the cases when Criterias are combined, here AND
            // we saved the Criterias for this reasons so that we can fetch precise result
            AndCriteria andCriteria = new AndCriteria(criteria1,criteria2);
            // no matter what the results are saved
            allCriteria.add(andCriteria);
            allFilter.add(andCriteria.meetCriteria(filterResult));
        } else if(selection == 5){
            Criteria criteria1 = getACriteria();
            Criteria criteria2 = getACriteria();
            // We know consider the cases when Criterias are combined, here OR
            // we saved the Criterias for this reasons so that we can fetch precise result
            OrCriteria orCriteria = new OrCriteria(criteria1,criteria2);
            // no matter what the filters and result are saved
            allFilter.add(orCriteria.meetCriteria(filterResult));
            allCriteria.add(orCriteria);

        }

    }

    // this is the method to get the methods name for the Reflection
    // and invoking
    private String getMethods(){
        String method2;
        Method[] methods = Event.class.getDeclaredMethods();
        for (int i = 0 ; i<methods.length;i++) {
            // do what you have to do with the method
            System.out.println(i +" " +methods[i].getName());
        }
        try {
            int index = scanner.nextInt();
            method2 = methods[index].getName();

    
            return method2;
        }catch (Exception e){
            System.out.println("Method Out Of Bound"); // in case of an error nothing is returned
        }
        return null;

    }

    // we fetch the Criterias here so that we can combine them in
    // OrCriterias and ANDCriterias
    private Criteria getACriteria(){
        for(int i = 0; i <allCriteria.size(); i++){
            System.out.println(i+" "+allCriteria.get(i).getClass());
        }
        System.out.println("Choose the Criteria");
        try{
            int index = scanner.nextInt();
            Criteria criteria = allCriteria.get(index);
           return criteria;
        } catch (Exception e){
            System.out.println("Index Out Of Bound");
        }
        return null; // in case of an error we return null

    }
    // we return the profit computation from the Filter
    private void getProfitfromFilter(FilterResult filterResult){
        System.out.println("The total profit of the selected filter is: "+filterResult.totalProfit());
    }
    // we return the total number of VIP from the Filter
    private void getVIPfromFilter(FilterResult filterResult){
        System.out.println("The total number of VIPs of the selected filter is: "+filterResult.totalVIP());

    }

    public void updateEvent(){
        System.out.println("updateEvent");
        int index = 0;
        Event event;
        System.out.println("Select Event to update. Choose 0 to cancel");
        for(int i = 0 ; i < aHostedEvents.size();i++){
            event = aHostedEvents.get(i);
                System.out.println((i+1) + ": " + event.getClass() + " " + event.getName() + " " + event.getLocation() + " " + event.getDate());

        }
        try{
            index = scanner.nextInt();

            if(index == 0){
                return;
            }
            System.out.println("Choose which event to change. Press 0 to return");
            aHostedEvents.get(index-1).accept(this);
        } catch(Exception e){
            // we print an error message
            System.out.println("Index Selected is Out of Bound");
        }
    }



    // those are the visit methods of the Visitor Interface as the
    // Event Management needs to consider different
    // options depending on which Event is updated
    @Override
    public void visit(Concert concert) {
        // we show the choices for the concert object in the consacred visit method
        System.out.println("1.Name: "+concert.getName() + " 2.Date: "+concert.getDate());
        System.out.println(" 3.NumTickets "+concert.getNumTickets()+" 4.Price: "+concert.getPrice()+" 5. Location: "+concert.getLocation());
        System.out.println("6.# VIP: "+concert.getVIPattendees()+ " 7.Artist: "+concert.getArtist());
        change(choicesConcert,concert);
    }

    @Override
    public void visit(Gala gala) {
        System.out.println("1. Name: "+gala.getName()+ "2.Date: "+gala.getDate()+" 3.NumTickets "+gala.getNumTickets());
        System.out.println(" 4.Price: "+gala.getPrice()+" 5. Location: "+gala.getLocation());
        System.out.println("6.# VIP: "+gala.getVIPattendees());
        change(choicesGala,gala);
    }

    @Override
    public void visit(Festival festival) {
        // for Festival we can only change the name
        System.out.println("1. Name: "+festival.getName());
        change(choicesFestival,festival);
    }

    @Override
    public void visit(Screening screening) {
        System.out.println("1. Name: "+screening.getName()+ " 2.Date: "+screening.getDate()+" 3.NumTickets "+screening.getNumTickets());
        System.out.println(" 4.Price: "+screening.getPrice()+" 5. Location: "+screening.getLocation()+ " 6. Rating: "+screening.getRating());
        change(choicesScreening,screening);
    }

    @Override
    public void visit(Workshop workshop) {
        System.out.println("1. Name: "+workshop.getName() + "2.Date: "+workshop.getDate()+" 3.NumTickets "+workshop.getNumTickets());
        System.out.println("4.Price: "+workshop.getPrice()+" 5. Location: "+workshop.getLocation()+" 6. Prerequisites");
        change(choicesWorkshop,workshop);
    }

    private void change(int choices, Event changedEvent) {
        int selection = 0;
            System.out.println("Please enter your choice");
            try {
                selection = scanner.nextInt();
            } catch (Exception e){
                System.out.println("Format Error");
                selection = 0;
                // in case of an error we cancel the change, to keep the information unsullied
            }
            if (selection == 0) {
                return;
            }

        try {
            Method method;
            // we use Reflections to invoke the proper function of a setEvent concrete class
            // we use if/else statement to get the Method to reflect
            if (selection == 1) {
                method= setEvent.class.getMethod("setName", String.class);
                String newName = getName();
                method.invoke(changedEvent,newName);
            } else if (selection == 2) {
                method=  setEvent.class.getMethod("setDate", LocalDate.class);
                LocalDate localDate = getDate();
                method.invoke(changedEvent,localDate);
            } else if (selection == 3) {
                method =  setEvent.class.getMethod("setNumTickets", int.class);
                int newNumofTicket = getNumOfTickets();
                method.invoke(changedEvent,newNumofTicket);
            } else if (selection == 4) {
                method = setEvent.class.getMethod("setPrice", double.class);
                double newPrice = getPrice();
                method.invoke(changedEvent,newPrice);
            } else if (selection == 5) {
                method = setEvent.class.getMethod("setLocation", Location.class);
                Location location =getLocation();
                method.invoke(changedEvent,location);
            } else if (selection == 6) {
                if(changedEvent instanceof Screening){ // if the selection is number 6 and it can be either for the
                    // the number of VIPs for Concerts and Galas,
                    // if it's a Screening then we must change the Rating
                    method = Screening.class.getMethod("setRating", Rating.class);
                    Rating rating = getRating(); // we get the rating before
                    // updating the rating for this Screening
                    method.invoke(changedEvent, rating);
                    if(changedEvent instanceof Workshop){
                        // we can set requisites for Workshops
                        method = Workshop.class.getMethod("setPrerequisites", List.class);
                        List<Workshop> workshops = getPrerequisites();
                        method.invoke(changedEvent, workshops);
                    }
                }else {
                    // only for VIPEvent types
                    method = VIPEvent.class.getMethod("setVIPattendees", int.class);
                    int newVIP = getNumOfVIPs();
                    method.invoke(changedEvent, newVIP);
                }
            } else if (selection == 7) {
                method = Concert.class.getMethod("setArtist", String.class);
                String artist = getArtist();
                method.invoke(changedEvent,artist);
            }
        } catch (Exception e){
            System.out.println("The Method does not exist. ");
        }

    }
    public void CreateFestival(){
        System.out.println("Creating a Festival");
        // a Festival can be added when the events of the festival have already been created
        int index = 0;
        Event event;
        List<Event> festivalEvents = new ArrayList<>();
        while(true) {
            System.out.println("Select Event to add to Festival. Choose 0 to finish");
            for (int i = 0; i < aHostedEvents.size(); i++) {
                event = aHostedEvents.get(i);
                System.out.println((i + 1) + ": " + event.getClass() + " " + event.getName() + " " + event.getLocation() + " " + event.getDate());

            }
            try {
                index = scanner.nextInt();

                if (index == 0) {
                    String name = getName();
                    Festival festival = new Festival(name, festivalEvents);
                    aHostedEvents.add(festival);
                    return;
                }
                event =  aHostedEvents.get(index-1);
                if(!(festivalEvents.contains(event))) { // we allow no duplicate
                    festivalEvents.add(event);
                }
            } catch (Exception e) {
                System.out.println("Index Selected is Out of Bound");
                return;
            }
        }
    }
}
