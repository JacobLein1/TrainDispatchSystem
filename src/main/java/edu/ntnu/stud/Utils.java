package edu.ntnu.stud;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**.
 * Useful methods needed for other classes.
 */

public class Utils {
    
  /**.
  * @param time
  * @return time parsed to LocalTime  
  */
  public LocalTime parseStringToLocalTime(String time) {    
    if (time.equals("")) {
      throw new IllegalArgumentException("You must enter a time, try again.");
    }
    return LocalTime.parse(time);
  }

}
