package edu.ntnu.stud.utils;

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
    if (time.equals("")) {
      throw new IllegalArgumentException("You must enter a time, try again.");
    } else {
      return LocalTime.parse(time);
    }
  }
}
