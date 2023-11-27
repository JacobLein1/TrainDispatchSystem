package edu.ntnu.stud;

import edu.ntnu.stud.Models.TrainDeparture;


import java.time.LocalTime;
import java.util.Arrays;
import java.util.Scanner;

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
    
    TrainDeparture stabekk = new TrainDeparture(1, 1, "L1",
         "Spikkestad", LocalTime.of(10, 0), LocalTime.of(0, 0));
    
    trainRegister.addNewTrainDeparture(stabekk);

    TrainDeparture rykkin = new TrainDeparture(2, 2, "L2",
         "Sandvika", LocalTime.of(10, 45), LocalTime.of(0, 0));
    trainRegister.addNewTrainDeparture(rykkin);

    TrainDeparture hovik = new TrainDeparture(3, 0, "L2",
         "Nedre Hovik", LocalTime.of(14, 0), LocalTime.of(0, 5));
    trainRegister.addNewTrainDeparture(hovik);

    TrainDeparture bergen = new TrainDeparture(4, 3, "L3",
          "Bergen", LocalTime.of(12, 0), LocalTime.of(0, 0));
    trainRegister.addNewTrainDeparture(bergen);

    TrainDeparture nesodden = new TrainDeparture(5, 0, "L3",
          "Nesodden", LocalTime.of(0, 30), LocalTime.of(0, 0));
    trainRegister.addNewTrainDeparture(nesodden);
   
    return trainRegister;
  }

  /**.
   * Starts up the menu.
   */
  public void start() {
    
    
    int choice = 0;

    while (choice != 9) {
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

      Scanner input = new Scanner(System.in);
      int choice = input.nextInt();


    }

    

  }
 

}

