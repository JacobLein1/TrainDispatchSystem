package edu.ntnu.stud;

import edu.ntnu.stud.Utils.Parser;
import edu.ntnu.stud.models.TrainDeparture;
import edu.ntnu.stud.models.TrainRegister;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;


/**.
 * User interface class
 */
public class UserInterface {
   
  private final TrainRegister trainRegister = new TrainRegister();
  private LocalTime clock;
  private final Scanner input = new Scanner(System.in);
  
  /**.
   * Initalise all pre-set example departures.
   */

  public TrainRegister init() {
    System.out.println("Initialising...");  

    clock = LocalTime.of(0, 0);
    System.out.println(1);
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
   * Starts up the menu.
   */
  public void start() {
    
    int choice = 0;

    while (choice != 6) {
      trainRegister.departuresAfterTime(clock);
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
   * Method to print format above departure, for visuals.
   */

  public void printDepartureFormatStart() {
    
    System.out.println(
        "\n----------------------------------" + clock + "---------------------------------");
    System.out.println(
                "|  Departure Time    Line  Train number    Destination    Delay   Track |");
    System.out.println(
                "|_______________________________________________________________________|");
  }
  /**.
   *
   * @return parsed and validated int trainNumber. 
   */

  public int getValidTrainNumber() {
    
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
  *
  * @return input validated and parsed to int.
  *    
  */
  public int getValidTrackNumber() {
    
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
   *
   * @return LocalTime parsed and validated.
   * 
   */

  public LocalTime getValidLocalTime() {
    
    String localTime;
    LocalTime validLocalTime = null;
    boolean running = true;


    while (running) {
      
      localTime = input.next();
            
      if (localTime.matches("\\d{2}:\\d{2}")) {
        try {
          validLocalTime = Parser.stringToLocalTime(localTime);
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
  *Takes user input to add new departure.
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

    System.out.println("What is the departures destination? ");

    String destination = input.nextLine();
    if (destination.length() > 14) {
      System.out.println(
          "Destination name can not be longer than 14 characters. Please try again. ");
      destination = input.nextLine();
    }
          
    System.out.println("When does the train depart? Enter as hh:mm");

    LocalTime validDepartureTime = getValidLocalTime();
        
    TrainDeparture trainDeparture = new TrainDeparture(trainNumber,
        trackNumber, lineInput, destination, validDepartureTime, LocalTime.of(0, 0));  
    trainRegister.addDeparture(trainDeparture);
  }

  /**.
   *Method for UI to remove departure. 
   * 
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
  /**.
   *Method takes input from user and adds track to departure.
   */

  public void grantTrack(int trainNumber) {

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
   * Prints a wanted departure by train number.
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
   * Method takes input from user, and adds delay to a departure. 
   */

  private void addDelayToDeparture(int trainNumber) {
          
    TrainDeparture currentTrainDeparture = trainRegister.wantedDeparture((trainNumber));
    System.out.println(
          "How much of a delay do you want to add? Enter as hh:mm, " 
          + "enter 00:00 if no delay shall be added.");

    LocalTime validDelay = getValidLocalTime(); 


    LocalTime newDepartureTime = currentTrainDeparture.departureTimeAfterDelay()
          .plusHours(validDelay.getHour())
          .plusMinutes(validDelay.getMinute());

    //Checks if the departure time after delay addition, exceedes midnight/next day.
    if (newDepartureTime.isBefore(currentTrainDeparture
          .departureTimeAfterDelay())) {
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
   */

  private void destinationSearch() {
    System.out.println("Where do you want to go? ");
    input.nextLine(); 
      
    boolean running = true;
    String destinationInput = "";

    while (running) {
      destinationInput = input.nextLine().trim();
      TrainDeparture[] wantedDepartureList = trainRegister
          .departuresToWantedDestination(destinationInput);
         
      if (wantedDepartureList.length == 0) {
        System.out.println("No departure with the destination " 
            + destinationInput + " found, please try again.");
      } else {
        running = false;
            
      }
    }
    System.out.println("Wanted departure: ");
    printDepartureFormatStart();
          
    Arrays.stream(trainRegister.departuresToWantedDestination(destinationInput))
        .forEach(d -> System.out.println(d));
    System.out.println(
         "--------------------------------------------------------------------------");
    
        
  }
  /**.
   *Method for user to manually update clock.
   */

  private void updateClock() {

    System.out.println("Current time is " + clock + "\nEnter new time as hh:mm - ");
    
    String time;
    boolean running = true;

    while (running) {
            
      time = input.next();
            
      if (time.matches("\\d{2}:\\d{2}")) {
        try {
          if (Parser.stringToLocalTime(time).isBefore(clock)) {
            System.out.println("New time must be after current time.");     
          } else {
            clock = Parser.stringToLocalTime(time);
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
  /**.
   *
   * @param amountChoices how many choices the user will be given in a menu.
   * @return user choice parsed and validated.
   * 
   */

  public int menuInput(int amountChoices) {
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
}

