package edu.ntnu.stud.utils;

import java.time.LocalTime;

/**
 * A utility class for handling time-related operations.
 */
public class TimeHandler {
    
  private LocalTime currentTime;

  /**
   * Constructs a TimeHandler object with the specified current time.
   *
   * @param currentTime the current time
   */
  public TimeHandler(LocalTime currentTime) {
    updateCurrentTime(currentTime);
  }

  /**
   * Returns the current time.
   *
   * @return the current time
   */
  public LocalTime getCurrentTime() {
    return currentTime;
  }

  /**
   * Updates the current time to the specified new current time.
   *
   * @param newCurrentTime the new current time
   */
  public void updateCurrentTime(LocalTime newCurrentTime) {
    currentTime = newCurrentTime;
  }

  /**
   * Parses the specified time string into a LocalTime object.
   *
   * @param time the time string to be parsed
   * @return the parsed LocalTime object
   * @throws IllegalArgumentException if the time string is empty
   * @throws java.time.DateTimeException if the time string is in the wrong format
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
