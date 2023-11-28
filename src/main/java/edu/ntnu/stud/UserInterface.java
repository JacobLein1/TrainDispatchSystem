package edu.ntnu.stud;

import edu.ntnu.stud.Models.TrainDeparture;


import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
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
  
  /**.
   * Method to initalise all pre-set departures.
   */

  public TrainRegister init() {
    System.out.println("Initialising...");  

    clock = LocalTime.of(0, 0);
    
    TrainDeparture stabekk = new TrainDeparture(01, 1, "L1",
         "Spikkestad", LocalTime.of(10, 0), LocalTime.of(0, 0));
    
    trainRegister.addNewTrainDeparture(stabekk);

    TrainDeparture rykkin = new TrainDeparture(02, 2, "L2",
         "Sandvika", LocalTime.of(10, 45), LocalTime.of(0, 0));
    trainRegister.addNewTrainDeparture(rykkin);

    TrainDeparture hovik = new TrainDeparture(03, 0, "L2",
         "Nedre Hovik", LocalTime.of(14, 0), LocalTime.of(0, 5));
    trainRegister.addNewTrainDeparture(hovik);

    TrainDeparture bergen = new TrainDeparture(04, 3, "L3",
          "Bergen", LocalTime.of(12, 0), LocalTime.of(0, 0));
    trainRegister.addNewTrainDeparture(bergen);

    TrainDeparture nesodden = new TrainDeparture(05, 0, "L3",
          "Nesodden", LocalTime.of(0, 30), LocalTime.of(0, 0));
    trainRegister.addNewTrainDeparture(nesodden);
   
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
    
    Scanner input = new Scanner(System.in);
    int choice = 0;

    while (choice != 7) {
      trainRegister.departuresAfterTime(clock);

      System.out.println("\n");
      System.out.println(
          "----------------------------------" + clock + "---------------------------------");
      System.out.println(
          "|  Departure Time    Line  Train number    Destination   Track    Delay |");
      System.out.println(
          "|_______________________________________________________________________|");
      Arrays.stream(trainRegister.sortedDepartureList())
          .forEach(d -> System.out.println(d.departureToString()));
      System.out.println(
          "-------------------------------------------------------------------------");    

      System.out.println("What would you like to do? Choose an option between 1-9.");
      System.out.println("1. Add new train departure.");
      System.out.println("2. Grant departure a track. ");
      System.out.println("3. Add delay to a departure. ");
      System.out.println("4. Find specific departure by train number. ");
      System.out.println("5. Find departures by destination.");
      System.out.println("6. Update clock. ");
      System.out.println("7. Exit menu. ");

      choice = input.nextInt();

      switch (choice) {
        case 1:
    
          break;
        case 2: 
          System.out.println("What departure will you like to grant a track? Enter train number.");

          
          break;
          
        case 3: {

          System.out.println("To which departure do you wish to add a delay? Enter train number.");
  
          String trainNumberInput = input.next();

          //Checks if input is on format hh:mm
          while (!Pattern.matches("\\d{2}",
           trainNumberInput) && !Pattern.matches("\\d", trainNumberInput)) {
            System.out.println(
                "Train number must be in the right format, [00-99]. Please try again.");
            trainNumberInput = input.next();
          }
          
          System.out.println("How much of a delay do you want to add? Enter as hh:mm");
          
          
          boolean running = true;
          while (running) {
            String delayInput = input.next();
            
            //KILDE chatGPT om hvordan catche DateTimeParseException
            ////Checks if delay is on right format within [00-23]:[00-59], and if yes, adds delay
            if (delayInput.matches("\\d{2}:\\d{2}")) {
              try {
                
                LocalTime delayParsed = Parse.parseStringToLocalTime(delayInput);
                trainRegister.wantedDeparture(Integer
                .parseInt(trainNumberInput)).addDelayTime(delayParsed);
                running = false;
                
              } catch (DateTimeParseException e) {
                System.out.println("Time must match format hh:mm. Please try again.");
              } 
            } else {
              System.out.println("Delay must match format hh:mm. Please try again");

            }
            
            
          }    
        }

          break;  

        case 4: { 
          //Asks user what coorrelating train number is to the train number they are looking for
          System.out.println("What is the train number? ");
          //Store their input into a string
          String trainNumberInput = input.next();
          //Check that their input matches the requirements for the method, right 
          while (!Pattern.matches("\\d{2}",
           trainNumberInput) && !Pattern.matches("\\d", trainNumberInput)) {
            System.out.println(
                "Train number must be in the right format, [00-99]. Please try again.");
            trainNumberInput = input.next();
          }

          //Store the wanted departure as a variable
          TrainDeparture wantedDeparture = trainRegister
              .wantedDeparture(Integer.parseInt(trainNumberInput));
          //If departure not found, tell user
          if (wantedDeparture == null) {
            System.out.println("Departure not found.");          
          } else { //Print wanted departure in nice style
            System.out.println("\nWanted departure:");
            System.out.println(
                "-------------------------------------------------------------------------\n" + 
                wantedDeparture.departureToString() 
                + "\n-------------------------------------------------------------------------\n");
          }
        }
          break;
        case 5: 
        
          break;
        case 6: { 

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
        case 7: 

          System.out.println("Exiting...");

          break;
        default:
          System.out.println("You must write a number between 1-7. ");
          break;
      } 
      
    }

    
    input.close();
  }
 

}

