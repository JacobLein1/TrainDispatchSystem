package edu.ntnu.stud.Utils;

import java.time.LocalTime;

/**.
 * 
 */
public class TimeHandler {
    
  private LocalTime currentTime;

  public TimeHandler(LocalTime currentTime) {
    updateCurrentTime(currentTime);
  }

  public LocalTime getCurrentTime() {
    return currentTime;
  }

  public void updateCurrentTime(LocalTime newCurrentTime) {
    currentTime = newCurrentTime;
  }

  /**.
  *
  * @param time Time wanted to be parsed.
  * @return time parsed to LocalTime  
  */

  public static LocalTime stringToLocalTime(String time) {    
    LocalTime timeParsed = LocalTime.of(0, 0);

    if (time.equals("")) {
      throw new IllegalArgumentException("You must enter a time, try again.");
    } else {

      try {
        timeParsed = LocalTime.parse(time);
      } catch (java.time.DateTimeException dte) {
        System.out.println("Time in wrong format. ");
        throw dte;
      }
    }
    return timeParsed;
  }
}
