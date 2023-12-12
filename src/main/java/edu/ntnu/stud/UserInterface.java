package edu.ntnu.stud;

import edu.ntnu.stud.models.TrainDeparture;
import edu.ntnu.stud.models.TrainRegister;
import edu.ntnu.stud.utils.TimeHandler;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;


/**
 * <h2>The user interface for a train departure system.</h2>
 * <h3>Provides methods for:</h3>
 * <u>
 * <li>initializing the system</li>
 * <li>starting the menu</li>
 * <li>performing actions in the menu</li>
 * <li>validating various user inputs</li>
 * 
 * </u>
 */
public class UserInterface {
   
  private final TrainRegister trainRegister = new TrainRegister();
  private static TimeHandler clock = new TimeHandler(LocalTime.of(0, 0));
  private static final Scanner input = new Scanner(System.in);
  
  /**.
   * Initalise all test departures.
   */

  public TrainRegister init() {
    System.out.println("Initialising...");  

    
    TrainDeparture spikkestad = new TrainDeparture(1, 4, "L11",
         "Spikkestad", LocalTime.of(7, 20), LocalTime.of(0, 0));
    trainRegister.addDeparture(spikkestad);

    TrainDeparture sandvika = new TrainDeparture(02, 2, "L2",
         "Sandvika", LocalTime.of(9, 45), LocalTime.of(0, 0));
    trainRegister.addDeparture(sandvika);

    TrainDeparture nedreHovik = new TrainDeparture(03, -1, "L2",
         "Nedre Hovik", LocalTime.of(12, 10), LocalTime.of(0, 0));
    trainRegister.addDeparture(nedreHovik);

    TrainDeparture bergen = new TrainDeparture(04, 3, "L3",
          "Bergen", LocalTime.of(12, 0), LocalTime.of(0, 0));
    trainRegister.addDeparture(bergen);

    TrainDeparture nesodden = new TrainDeparture(11, 1, "L3",
          "Nesodden", LocalTime.of(0, 30), LocalTime.of(0, 4));
    trainRegister.addDeparture(nesodden);

    TrainDeparture gardemoen = new TrainDeparture(6, -1, "R1",
        "Gardemoen", LocalTime.of(15, 10), LocalTime.of(0, 0));
    trainRegister.addDeparture(gardemoen);
   
    TrainDeparture ovreHovik = new TrainDeparture(5, 2, "R12",
         "Øvre Høvik", LocalTime.of(23, 59), LocalTime.of(0, 0));
    trainRegister.addDeparture(ovreHovik);

    return trainRegister;
  }

  /**.
   * Starts the menu.
   */
  public void start() {
    
    int choice = 0;

    while (choice != 6) {
      trainRegister.departuresAfterTime(clock.getCurrentTime());
      printDepartureFormatStart();
      Arrays.stream(trainRegister.sortedDepartureList())
          .forEach(d -> System.out.println(d));
      System.out.println(
          "-------------------------------------------------------------------------");    

      System.out.println("What would you like to do? Choose an option between 1-6.");
      System.out.println("1. Add new train departure.");
      System.out.println("2. Remove departure");
      System.out.println("3. Find specific departure by train number. ");
      System.out.println("4. Find departures by destination.");
      System.out.println("5. Update clock. ");
      System.out.println("6. Exit menu. ");

      choice = menuInput(6);

      switch (choice) {
        case 1: 
          addDeparture();
        
          break;
        case 2:
          removeDeparture();
        
          break;
        case 3: 
          searchDepartures();

          break;
        case 4: 
          destinationSearch();

          break;
        case 5:
          updateClock();
          
          break;
        case 6: 
          System.out.println("Exiting...");
          break;
        default:
          System.out.println("You must enter a number between 1-6. ");
          break;
        
      } 
      
    }

    
    input.close();
  }
   
  /**.
   *
   * @param amountChoices how many choices the user will be given in a menu.
   *
   * @return user choice validated and parsed to integer.
   * 
   */

  private int menuInput(int amountChoices) {
    String userInput = input.next();
    boolean validating = true;
    int userchoice = 0;

    while (validating) {

      try {
        userchoice = Integer.parseInt(userInput);
        validating = false;
      } catch (NumberFormatException nfe) {
        System.out.println("You must enter a number. Between 1 and " + amountChoices);
      
        userInput = input.next();
      }
        
    } 
    return userchoice;
  }

  /**.
   * Method to print table header, for visuals.
   */

  private void printDepartureFormatStart() {
    
    System.out.println(
        "\n----------------------------------" 
        + clock.getCurrentTime() + "---------------------------------");
    System.out.println(
                "|  Departure Time    Line  Train number    Destination    Delay   Track |");
    System.out.println(
                "|_______________________________________________________________________|");
  }
  /**.
   * Parses and validates train number input.
   *
   * @return parsed and validated int trainNumber. 
   */

  private int getValidTrainNumber() {
    
    String trainNumberInput = input.next();

    while (!Pattern.matches("\\d{2}",
      trainNumberInput) && !Pattern.matches("\\d", trainNumberInput)) {
      System.out.println(
          "Train number must be in the right format, [00-99]. Please try again.");
      trainNumberInput = input.next();
    }
    return Integer.parseInt(trainNumberInput);
  }

  /**.
  * Parses and validates track number input.
  *
  * @return input validated and parsed to int.    
  */
  private int getValidTrackNumber() {
    
    String trackNumberInput = input.next();
    int trackNumber = 0;
    boolean running = true;

    while (running) {
      try {
        trackNumber = Integer.parseInt(trackNumberInput);
        if (trackNumber <= 9 && trackNumber > 0 || trackNumber == -1) {
          running = false;
        } else {
          System.out.println(
              "Enter a number between 1-9. If track number is not granted yet, enter -1.");
          trackNumberInput = input.next();
          
        }
      } catch (NumberFormatException nfe) {
        System.out.println(
            "Track number must be a number between 1-9. " 
            + " If track number is not granted yet, enter -1. ");
        trackNumberInput = input.next();
        
      }
      
    }

    return trackNumber;
  }
  /**.
   * Parses and validates LocalTime input.
   *
   * @return LocalTime parsed and validated.
   */

  private LocalTime getValidLocalTime() {
    
    String localTime;
    LocalTime validLocalTime = null;
    boolean running = true;


    while (running) {
      
      localTime = input.next();
            
      if (localTime.matches("\\d{2}:\\d{2}")) {
        try {
          validLocalTime = TimeHandler.stringToLocalTime(localTime);
          running = false;
        } catch (DateTimeParseException e) {
          System.out.println("Hours must be in interval [00-23]" 
                    + " and minutes must be in interval [00-59]. Please try again.");
        } 
      } else {
        System.out.println("Time must match format hh:mm. Please try again.");
      }

    }
    return validLocalTime;
  }

  /**.
  * Handles user input to add new departure.
  */
  private void addDeparture() {
    System.out.println("What is the train number of the new departure? ");

    int trainNumber = getValidTrainNumber();

    while (trainRegister.wantedDeparture(trainNumber) != null) {
      System.out.println(
          "Used train number. Please enter a new train number.");
      trainNumber = getValidTrainNumber();
    }

    System.out.println("Which track will the train be arriving at" 
        + ", if not granted yet, enter -1. ");
          
    final int trackNumber = getValidTrackNumber();
          
    System.out.println("What is the departure`s designated line? ");

    String lineInput = input.next();
    while (lineInput.length() > 3) {
      System.out.println(
          "Line must be 3 characters or shorter, f.ex L11. Please try again");
      lineInput = input.next();
    }
          
    //dummy line
    input.nextLine();

    System.out.println("What is the departure`s destination? ");

    String destination = input.nextLine();
    if (destination.length() > 14 || destination.equals("") || destination.equals(" ")) {
      System.out.println(
          "Destination name has to be an input and can not be longer than 14 characters." 
            + " Please try again. ");
      destination = input.nextLine();
    }
          
    System.out.println("When does the train depart? Enter as hh:mm");

    LocalTime validDepartureTime = getValidLocalTime();
        
    TrainDeparture trainDeparture = new TrainDeparture(trainNumber,
        trackNumber, lineInput, destination, validDepartureTime, LocalTime.of(0, 0));  
    trainRegister.addDeparture(trainDeparture);
  }

  /**.
   * Removes departure. 
   */
  private void removeDeparture() {
    System.out.println(
              "What is the train number of the departure you want to remove?");
          
    int validTrainnumber = getValidTrainNumber();
    TrainDeparture wanteDeparture = trainRegister.wantedDeparture(validTrainnumber);

    if (wanteDeparture == null) {
      System.out.println("\nCould not remove departure as departure by trainnumber " 
          + validTrainnumber + " does not exits.");            
    } else {
      trainRegister.removeDeparture(validTrainnumber);
      System.out.println("Departure with train number " + validTrainnumber + " removed.");
    }
    
  }

  /**
   * Takes input from user and grants track to departure.
   *
   * @param trainNumber train number of wanted departure.
   */
  private void grantTrack(int trainNumber) {

    TrainDeparture wantedDeparture = trainRegister.wantedDeparture(trainNumber);
    if (wantedDeparture == null) {
      System.out.println("\nCould not find departure as departure by departure number " 
          + trainNumber + " does not exist.");     
    } else {
      System.out.println(
              "What track will you give this departure? " 
              + "Enter a number between 1-9. If track is not yet granted enter -1. ");
      int trackNumber = getValidTrackNumber();
      trainRegister.wantedDeparture(trainNumber)
          .setTrack(trackNumber);
    }
  }
  /**.
   * Prints a wanted departure by train number, and gives choices to operator.
   */

  private void searchDepartures() {
    System.out.println("What is the train number? ");
          
    int trainNumber = getValidTrainNumber();

          
    TrainDeparture wantedDeparture = trainRegister
              .wantedDeparture(trainNumber);
          
    if (wantedDeparture == null) {
      System.out.println("\nDeparture not found.");          
    } else { 
      System.out.println("\nWanted departure:");
      printDepartureFormatStart();
      System.out.println(
          "-------------------------------------------------------------------------\n" 
          + wantedDeparture 
          + "\n-------------------------------------------------------------------------\n");
      
      
        
      
      int choice = 0;

      while (choice != 3) {

        System.out.println("What would you like to do to this departure? ");
        System.out.println("1. Grant departure a track. ");
        System.out.println("2. Add delay to this departure. ");   
        System.out.println("3. Return to main menu.");

        choice = menuInput(3);

        switch (choice) {
          case 1:
            grantTrack(trainNumber);
            System.out.println("\nTrack granted. ");
            break;
          case 2: 
            addDelayToDeparture(trainNumber);
            break;
          case 3:
            System.out.println("\nReturning to main menu.");
            break;
          default:
            System.out.println("\nThe number must be between 1-3. ");
            break;
        }
      
      }
      
    }
  }
  /**.
   * Takes input from user, and adds delay to a departure. 
   * 
   * <p>Will not allow ooperator to add delay if departure time will exceed midnight.
   *
   * @param trainNumber train number of wanted departure.
   * 
   *
   */

  private void addDelayToDeparture(int trainNumber) {
          
    TrainDeparture currentTrainDeparture = trainRegister.wantedDeparture((trainNumber));
    System.out.println(
          "How much of a delay do you want to add? Enter as hh:mm, " 
          + "enter 00:00 if no delay shall be added.");

    LocalTime validDelay = getValidLocalTime(); 

    //Departure time after with addition of delay.
    LocalTime newDepartureTime = currentTrainDeparture.actualDepartureTime()
          .plusHours(validDelay.getHour())
          .plusMinutes(validDelay.getMinute());

    //Checks if the departure time after delay addition, exceedes midnight/next day.
    if (newDepartureTime.isBefore(currentTrainDeparture
          .actualDepartureTime())) {
      System.out.println(
          "\nCould not add delay as departure time will exceed midnight. Please try again.\n");
      
    } else {
      if  (validDelay.equals((LocalTime.of(0, 0)))) {
        System.out.println("No delay added.\n");
      } else {
        currentTrainDeparture.addDelay(validDelay);
        System.out.println("Delay succesfully added.\n");
      }
    } 
      
  }
 
 
  /**.
   * Prints all departures to input destination.
   * Will print message if no departures to input destination is found.
   */

  private void destinationSearch() {
    System.out.println("Where do you want to go? ");
    input.nextLine(); 
      
    boolean running = true;
    String destinationInput = "";

    while (running) {
      destinationInput = input.nextLine().trim();
      TrainDeparture[] wantedDepartureList = trainRegister
          .searchByDestination(destinationInput);
         
      if (wantedDepartureList.length == 0) {
        System.out.println("No departure with the destination " 
            + destinationInput + " found, please try again.");
      } else {
        running = false;
            
      }
    }
    System.out.println("Wanted departure: ");
    printDepartureFormatStart();
          
    Arrays.stream(trainRegister.searchByDestination(destinationInput))
        .forEach(d -> System.out.println(d));
    System.out.println(
         "--------------------------------------------------------------------------");
    
        
  }
  /**.
   *Method for operator to manually update clock.
   */

  private void updateClock() {

    System.out.println("Current time is " 
        + clock.getCurrentTime() + "\nEnter new time as hh:mm - ");
    
    String time;
    boolean running = true;

    while (running) {
            
      time = input.next();
            
      if (time.matches("\\d{2}:\\d{2}")) {
        try {
          if (TimeHandler.stringToLocalTime(time).isBefore(clock.getCurrentTime())) {
            System.out.println("New time must be after current time.");     
          } else {
            clock.updateCurrentTime(TimeHandler.stringToLocalTime(time));
            running = false;
          }
        } catch (DateTimeParseException e) {
          System.out.println("Hours must be in interval [00-23]" 
              + " and minutes must be in interval [00-59]. Please try again.");
        } 
      } else {
        System.out.println("Time must match format hh:mm. Please try again.");
      }
            
    }    
  }
} 

