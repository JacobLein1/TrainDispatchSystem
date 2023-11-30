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
   
  TrainRegister trainRegister = new TrainRegister();
  LocalTime clock;
  Scanner input = new Scanner(System.in);
  
  /**.
   * Method to initalise all pre-set departures.
   */

  public TrainRegister init() {
    System.out.println("Initialising...");  

    clock = LocalTime.of(0, 0);
    
    TrainDeparture spikkestad = new TrainDeparture(1, 4, "L11",
         "Spikkestad", LocalTime.of(7, 20), LocalTime.of(0, 0));
    
    trainRegister.addNewDeparture(spikkestad);

    TrainDeparture sandvika = new TrainDeparture(02, 2, "L2",
         "Sandvika", LocalTime.of(9, 45), LocalTime.of(0, 0));
    trainRegister.addNewDeparture(sandvika);

    TrainDeparture nedreHovik = new TrainDeparture(03, 0, "L2",
         "Nedre Hovik", LocalTime.of(12, 10), LocalTime.of(0, 0));
    trainRegister.addNewDeparture(nedreHovik);

    TrainDeparture bergen = new TrainDeparture(04, 3, "L3",
          "Bergen", LocalTime.of(12, 0), LocalTime.of(0, 0));
    trainRegister.addNewDeparture(bergen);

    TrainDeparture nesodden = new TrainDeparture(11, 1, "L3",
          "Nesodden", LocalTime.of(0, 30), LocalTime.of(0, 4));
    trainRegister.addNewDeparture(nesodden);

    TrainDeparture gardemoen = new TrainDeparture(6, 0, "R1",
        "Gardemoen", LocalTime.of(15, 10), LocalTime.of(0, 0));
    trainRegister.addNewDeparture(gardemoen);
   
    TrainDeparture ovreHovik = new TrainDeparture(5, 2, "R12",
         "Øvre Høvik", LocalTime.of(18, 00), LocalTime.of(0, 0));
    trainRegister.addNewDeparture(ovreHovik);

    return trainRegister;
  }


  //Start to possible solution to make prettier code, method with only function to handle user
  //inputs and give resulting output
  /* public static <T> T askUser(String question, String returnDatatype) {
    Scanner input = new Scanner(System.in);
    System.out.println(question);

    String userInput = input.nextLine();
    T result;

    switch (returnDatatype) {
      case "Int":
        result = Integer.parseInt(userInput);
        break;
      
      case "LocalTime":
      
        break;
      default:
        break;
    }
    input.close();
    }

  ///<Integer>askUser("what is that", "Int"); */

  /**.
   * Starts up the menu.
   */
  public void start() {
    
    int choice = 0;

    while (choice != 8) {
      trainRegister.departuresAfterTime(clock);

      printDepartureFormatStart();
      
      Arrays.stream(trainRegister.sortedDepartureList())
          .forEach(d -> System.out.println(d.departureToString()));
      System.out.println(
          "-------------------------------------------------------------------------");    

      System.out.println("What would you like to do? Choose an option between 1-9.");
      System.out.println("1. Add new train departure.");
      System.out.println("2. Remove departure");
      System.out.println("3. Grant departure a track. ");
      System.out.println("4. Add delay to a departure. ");
      System.out.println("5. Find specific departure by train number. ");
      System.out.println("6. Find departures by destination.");
      System.out.println("7. Update clock. ");
      System.out.println("8. Exit menu. ");
      
      String userInput = input.next();
      boolean validating = true;

      while (validating) {

        try {
          choice = Integer.parseInt(userInput);
          validating = false;
        } catch (NumberFormatException nfe) {
          System.out.println("You must enter a number. Between 1-8.");
      
          userInput = input.next();
      }
      }
      

      switch (choice) {
        case 1: {
          addDeparture();
        }
          break;
        case 2:

          removeDeparture();
        
          break;
        case 3: { 
          grantTrack();
        }
          break;
        
        case 4: {
          addDelayToDeparture();
          
        }
          break;  
        case 5: { 
          wantedDestination();

        }
          break;
        case 6: {
          destinationSearch();

        }
          break;
        case 7: { 
          updateClock();
          
        }
          break;
        case 8: 

          System.out.println("Exiting...");
          break;
        default:
          System.out.println("You must write a number between 1-8. ");
          break;
        
      } 
      
    }

    
    input.close();
  }
  /**.
   * Method to print format above departure, for visuals.
   */

  public void printDepartureFormatStart() {
    System.out.println("\n");
    System.out.println(
        "----------------------------------" + clock + "---------------------------------");
    System.out.println(
                "|  Departure Time    Line  Train number    Destination   Track    Delay |");
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

    while (!Pattern.matches("\\d", trackNumberInput) || Integer.parseInt(trackNumberInput) > 9) {
      System.out.println("Tracknumber must be between 0-9. Please try again.");
      trackNumberInput = input.next();
    }

    return Integer.parseInt(trackNumberInput);
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
        System.out.println("\nTime must match format hh:mm. Please try again.");
      }
            
    }
    return validLocalTime;
  }

  /**.
  *Takes user input to add new departure.
  */
  public void addDeparture() {
    System.out.println("What is the train number of the new departure? ");

    //Instant feed-back if train number ID is taken
    int trainNumber = getValidTrainNumber();

    while (trainRegister.wantedDeparture(trainNumber) != null) {
      System.out.println(
          "Used train number. Please enter a new train number.");
      trainNumber = getValidTrainNumber();
    }

    System.out.println("Which track will the train be arriving at" 
        + ", if not granted yet, enter 0. ");
          
    int trackNumber = getValidTrackNumber();
          
    System.out.println("What is the departure`s designated line? ");

    String lineInput = input.next();
    while (lineInput.length() > 4) {
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
    trainRegister.addNewDeparture(trainDeparture);
  }

  /**.
   *Method for UI to remove departure. 
   * 
   */
  public void removeDeparture() {
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
   * Method takes input from user, and adds delay to a departure. 
   */
  public void addDelayToDeparture() {

    System.out.println("To which departure do you wish to add a delay? Enter train number.");
    int trainNumber = getValidTrainNumber();
          
    TrainDeparture currentTrainDeparture = trainRegister.wantedDeparture((trainNumber));

    if (currentTrainDeparture == null) {
            
      System.out.println("Could not add delay as departure by trainnumber " 
                + trainNumber + " does not exits. ");
    } else {
      System.out.println("How much of a delay do you want to add? Enter as hh:mm");
      LocalTime validDelay = getValidLocalTime();
      currentTrainDeparture.addDelay(validDelay);
    }
  }
  /**.
   *Method takes input from user and adds track to departure.
   */

  public void grantTrack() {

    System.out.println(
            "What departure will you like to grant a track? Enter train number.");

    int trainNumber = getValidTrainNumber();

    System.out.println(
              "What track will you give this departure? Enter a number between 1-9.");

    int trackNumber = getValidTrackNumber();
          
    trainRegister.wantedDeparture(trainNumber)
        .setTrack(trackNumber);
  }
  /**.
   * Prints a wanted departure by train number.
   */

  public void wantedDestination() {
    System.out.println("What is the train number? ");
          
    int trainNumber = getValidTrainNumber();

          
    TrainDeparture wantedDeparture = trainRegister
              .wantedDeparture(trainNumber);
          
    if (wantedDeparture == null) {
      System.out.println("Departure not found.");          
    } else { 
      System.out.println("\nWanted departure:");
      printDepartureFormatStart();
      System.out.println(
          "-------------------------------------------------------------------------\n" 
          + wantedDeparture.departureToString() 
          + "\n-------------------------------------------------------------------------\n");
    }
  }
  /**.
   * Prints all departures to input destination.
   */

  public void destinationSearch() {
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
    printDepartureFormatStart();
          
    Arrays.stream(trainRegister.departuresToWantedDestination(destinationInput))
        .forEach(d -> System.out.println(d.departureToString()));
    System.out.println(
         "--------------------------------------------------------------------------");
        
  }
  /**.
   *Method for user to manually update clock.
   */

  public void updateClock() {

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

}

