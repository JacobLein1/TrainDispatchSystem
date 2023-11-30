package edu.ntnu.stud.Utils;
import java.time.LocalTime;
import java.util.Arrays;

import edu.ntnu.stud.Models.TrainDeparture;
import edu.ntnu.stud.Models.TrainRegister;

/**.
 * Class for print methods. 
 */
public class Print {
  /**.
   * Method to print the top of the departure list.
   */
  public static void printDepartureListStart(TrainRegister trainRegister) {

    TrainDeparture[] trainRegisterList = trainRegister.sortedDepartureList();
    LocalTime clock = LocalTime.of(0, 0);
    
    System.out.println("\n");
    System.out.println(
        "----------------------------------" + clock + "---------------------------------");
    System.out.println("|  Departure Time    Line  Train number    Destination   Track    Delay |");
    System.out.println("|_______________________________________________________________________|");
    
  }


}
