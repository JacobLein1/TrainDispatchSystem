package edu.ntnu.stud.Utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import edu.ntnu.stud.models.TrainDeparture;

/**.
 * Useful methods needed for other classes.
 */

public class Parser {
    
  /**.
   *
  * @param time Time wanted to be parsed.
  * @return time parsed to LocalTime  
  */
  public static LocalTime stringToLocalTime(String time) {    
    if (time.equals("")) {
      throw new IllegalArgumentException("You must enter a time, try again.");
    } else {
      return LocalTime.parse(time);
    }
  }
  /**
   *
   * @param input String wanted to be parsed.
   * @return input parsed to integer.
   * 
   */

  public static int stringToInt(String input) {
    boolean validating = true;
    int result = 0;
    
    while (validating) {
      try {
        result = Integer.parseInt(input);
        validating = false;
      } catch (NumberFormatException nfe) {
        System.out.println("You must enter a number. Between 1-8.");
    }
    }
    return result;
  }
}
