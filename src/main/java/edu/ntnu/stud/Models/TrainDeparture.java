package edu.ntnu.stud.Models;

import java.time.LocalTime;

/**
 * The train dispatch table.
 * Information this class will involve, and their datatype:  
 * Departure time(LocalTime), Line(String), Train number(int)
 * Destination(String), Track(int), Delay(LocalTime)
 * Immutable variables: Line, Train number, Destination, Track 
 *
 *  <p>Will throw error messages if wrong value.
 *
 * @author Jacob Lein
 * @version 1.0
 */
public class TrainDeparture {        //Information regarding train departure
  private final int trainNumber;
  private int track;              //"Spor på norsk"
  private final String line;      //"Linje på norsk, f.ex L1"
  private final String destination;
  private LocalTime departureTime;
  private LocalTime delay;

  /**.
  *Constructor for a departure 
  */
  public TrainDeparture(int trainNumber, int track, String line, 
      String destination, LocalTime departureTime, LocalTime delay) {
    if (trainNumber < 0) {
      throw new IllegalArgumentException("Train number must be positive. ");
    }
    if (track < 0) {
      throw new IllegalArgumentException("Track must be a positive number. ");
    }
    if (line.equals(" ") || line.equals("")) {
      throw new IllegalArgumentException("Destination has to be an input. ");
    }
    if (destination.equals(" ") || destination.equals("")) {
      throw new IllegalArgumentException("Destination has to be an input. ");
    } 
    if (departureTime == null) {
      throw new IllegalArgumentException("Must add a departure time. ");
    }
    if (track == 0) {
      track = -1;
    }
    //også hvis den er tom, 0, mellomrom, String

    this.trainNumber = trainNumber;
    this.track = track;
    this.line = line;
    this.destination = destination;
    this.departureTime = departureTime;
    this.delay = delay;

  }
  
  //Getter-methods
  
  public int getTrainNumber() {
    return trainNumber;
  }

  public int getTrack() {
    return track;
  }

  public String getLine() {
    return line;
  }

  public String getDestination() {
    return destination;
  }

  public LocalTime getDepartureTime() {
    return departureTime;
  }

  public LocalTime getDelay() {
    return delay;
  }

  //Setter-methods

  public void setTrack(int newTrack) {  //Set a new track a train will arrive at
    
    track = newTrack;
  }
  
  //set a new departure time in case of a delay
  public void setDepartureTime(LocalTime newDepartureTime) {        
  
    departureTime = newDepartureTime;
  }
  
  /**
 * Adds hours and minutes to the original delay.
 *
 * @param addedDelayTime Amount of delay.
 *                      Should be in the format of {@code LocalTime}.
 *                      It represents both hours and minutes to be added.
 */
  public void addDelay(LocalTime addedDelayTime) {    //set a delay for a train

    delay = delay.plusMinutes(addedDelayTime.getMinute());
    delay = delay.plusHours(addedDelayTime.getHour());
    
  }
  //method to check if trainnumber already exists

  public int compareTo(TrainDeparture otherDeparture) {
    return Integer.compare(this.getTrainNumber(), otherDeparture.getTrainNumber());
  }

  public boolean hasDelay() {
    return this.delay != LocalTime.parse("00:00");
  } 
  /**.
   *
   * @param trainDeparture Departure that is wanted to be updated. 
   * @return The departures time after delay
   */

  public LocalTime departureTimeAfterDelay(TrainDeparture trainDeparture) {
    if (trainDeparture.hasDelay()) {

      int newHourOfDeparture = trainDeparture.departureTime
          .getHour() + trainDeparture.delay.getHour();

      int newMinuteOfDeparture = trainDeparture.departureTime
          .getMinute() + trainDeparture.delay.getMinute();
          
      return LocalTime.of(newHourOfDeparture, newMinuteOfDeparture); 
    } else {
      return trainDeparture.departureTime;
    }

  
  }
  /**.
   *
   *@return A departure returned as a string, withoout irrelevant information.
   */

  public String departureToString() {

    String result = "";

    result += String.format("|      %4s          %2s        %1o      %14s",
     departureTime.toString(), line, trainNumber, destination);

    if (track != -1) {
      result += String.format("      %2d", track);
    } else {
      result += "        ";
    }
    if (delay != LocalTime.of(00, 00)) {
      result += String.format("      %5s", delay);     
    } else {
      result += "           ";
    }
    result += "|"; 
    return result;
  }
}
