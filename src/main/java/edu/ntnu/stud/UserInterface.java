package edu.ntnu.stud;

import edu.ntnu.stud.Models.TrainDeparture;


import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import edu.ntnu.stud.Utils.Parse;
import edu.ntnu.stud.Models.TrainRegister;
import edu.ntnu.stud.Utils.Print;


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
    
    TrainDeparture stabekk = new TrainDeparture(01, 1, "L1",
         "Spikkestad", LocalTime.of(10, 0), LocalTime.of(0, 0));
    
    trainRegister.addNewDeparture(stabekk);

    TrainDeparture rykkin = new TrainDeparture(02, 2, "L2",
         "Sandvika", LocalTime.of(10, 45), LocalTime.of(0, 0));
    trainRegister.addNewDeparture(rykkin);

    TrainDeparture hovik = new TrainDeparture(03, 0, "L2",
         "Nedre Hovik", LocalTime.of(14, 0), LocalTime.of(0, 5));
    trainRegister.addNewDeparture(hovik);

    TrainDeparture bergen = new TrainDeparture(04, 3, "L3",
          "Bergen", LocalTime.of(12, 0), LocalTime.of(0, 0));
    trainRegister.addNewDeparture(bergen);

    TrainDeparture nesodden = new TrainDeparture(11, 0, "L3",
          "Nesodden", LocalTime.of(0, 30), LocalTime.of(0, 0));
    trainRegister.addNewDeparture(nesodden);
    System.out.println(nesodden.getTrainNumber());

    TrainDeparture gardemoen = new TrainDeparture(6, 7, "R1",
        "Gardemoen", LocalTime.of(15, 10), LocalTime.of(0, 0));
    trainRegister.addNewDeparture(gardemoen);
   
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
      try {
        choice = Integer.parseInt(userInput);
      } catch (InputMismatchException e) {
        System.out.println("Choice must be a number between 1-7. Please try again.");
      }
      

      switch (choice) {
        case 1: {
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
                "Line must be shorter 3 characters or shorter, f.ex L1. Please try again");
            lineInput = input.next();
          }
          
          //dummy line
          input.nextLine();

          System.out.println("What is the departures destination? ");

          String destination = input.nextLine();
          
          System.out.println("When does the train depart? Enter as hh:mm");

          LocalTime validDepartureTime = getValidLocalTime();
        
          TrainDeparture trainDeparture = new TrainDeparture(trainNumber,
               trackNumber, lineInput, destination, validDepartureTime, LocalTime.of(0, 0));  
          trainRegister.addNewDeparture(trainDeparture);
        }
          break;
        case 2:
          System.out.println(
              "What is the train number of the departure you want to remove?");
          
          int validTrainnumber = getValidTrainNumber();
          TrainDeparture wanteDeparture = trainRegister.wantedDeparture(validTrainnumber);

          if (wanteDeparture == null) {
            System.out.println("\"Could not remove departure as departure by trainnumber" 
                          + validTrainnumber + " does not exits.");            
          }
          trainRegister.removeDeparture(validTrainnumber);

          break;
        case 3: { 
          System.out.println("What departure will you like to grant a track? Enter train number.");

          int trainNumber = getValidTrainNumber();

          System.out.println(
              "What track will you give this departure? Enter a number between 1-9.");

          int trackNumber = getValidTrackNumber();
          
          trainRegister.wantedDeparture(trainNumber)
              .setTrack(trackNumber);
        }
          break;
          
        case 4: {

          System.out.println("To which departure do you wish to add a delay? Enter train number.");

          //Checks if input is on format hh:mm
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

          break;  

        case 5: { 
          System.out.println("What is the train number? ");
           
          int trainNumber = getValidTrainNumber();

          //Store the wanted departure as a variable
          TrainDeparture wantedDeparture = trainRegister
              .wantedDeparture(trainNumber);
          //If departure not found, tell user
          if (wantedDeparture == null) {
            System.out.println("Departure not found.");          
          } else { //Print wanted departure in nice style
            System.out.println("\nWanted departure:");
            System.out.println(
                "-------------------------------------------------------------------------\n" 
                + wantedDeparture.departureToString() 
                + "\n-------------------------------------------------------------------------\n");
          }
        }
          break;
        case 6: {
          System.out.println("Where do you want to go? ");
          input.nextLine(); 
      
        //while (!Pattern.matches("\\%s", destination)) {
          //System.out.println("Destination is written with only letters. Please try again.");
          //destination = input.nextLine();
        //}
          boolean running = true;
          String destinationInput = "";

          while (running) {
            destinationInput = input.nextLine().trim();
            TrainDeparture[] wantedDepartureList = trainRegister
              .departuresToWantedDestination(destinationInput);
         
            if (wantedDepartureList.length == 0) {
              System.out.println("No departure by the name of " 
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
          break;
        case 7: { 

          System.out.println("Current time is " + clock + "\nEnter new time as hh:mm - ");
        
          String time;
          boolean running = true;
          while (running) {
            
            time = input.next();
            
            if (time.matches("\\d{2}:\\d{2}")) {
              try {
                if (Parse.parseStringToLocalTime(time).isBefore(clock)) {
                  System.out.println("New time must be after current time.");     
                } else {
                  clock = Parse.parseStringToLocalTime(time);
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
   *
   * @param userInput input wanted parsed and validated.
   * @return parsed and validated int trainNumber.
   * 
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

  /**
   *
   * @param userInput String input wanted to be parsed and validated.
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
          validLocalTime = Parse.parseStringToLocalTime(localTime);
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
 

}

