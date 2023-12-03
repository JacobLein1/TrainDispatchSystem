package edu.ntnu.stud.Utils;
import java.time.LocalTime;
import java.util.Arrays;

import edu.ntnu.stud.models.TrainDeparture;
import edu.ntnu.stud.models.TrainRegister;

/**.
 * Class for print methods. 
 */
public class Printer {
  
  
  /**.
   * Method to print the top of the departure list.
   */

  public static void printDepartureListStart() {
    LocalTime clock = LocalTime.of(0, 0);

    System.out.println(
        "\n----------------------------------" + clock + "---------------------------------");
    System.out.println(
              "|  Departure Time    Line  Train number    Destination    Delay   Track |");
    System.out.println(
              "|_______________________________________________________________________|");
    
  }
  

}
