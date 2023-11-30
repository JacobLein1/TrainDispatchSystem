package edu.ntnu.stud.Utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import edu.ntnu.stud.Models.TrainDeparture;

/**.
 * Useful methods needed for other classes.
 */

public class Parse {
    
  /**.
   *
  * @param time Time wanted to be parsed.
  * @return time parsed to LocalTime  
  */
  public static LocalTime parseStringToLocalTime(String time) {    
    if (time.equals("")) {
      throw new IllegalArgumentException("You must enter a time, try again.");
    } else {
      return LocalTime.parse(time);
    }
  }

  
}
