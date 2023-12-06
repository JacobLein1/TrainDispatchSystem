package edu.ntnu.stud.models;

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
  private final LocalTime departureTime;
  private LocalTime delay;

  /**.
  *Constructor for a departure 
  */
  public TrainDeparture(int trainNumber, int track, String line, 
      String destination, LocalTime departureTime, LocalTime delay) {
    if (trainNumber < 0 || trainNumber > 99) {
      throw new IllegalArgumentException("Train number must be positive. Between [0-99]");
    }
    if (line.equals(" ") || line.equals("") || line.length() > 3) {
      throw new IllegalArgumentException("Line has to be an input. With max length of 3 symbols.");
    }
    if (destination.equals(" ") || destination.equals("") || destination.length() > 14) {
      throw new IllegalArgumentException("Destination has to be an input under 14 characters. ");
    } 
    if (departureTime == null) {
      throw new IllegalArgumentException("Must add a departure time. ");
    }
    

    //også hvis den er tom, 0, mellomrom, String

    this.trainNumber = trainNumber;
    setTrack(track);
    this.line = line;
    this.destination = destination;
    this.departureTime = departureTime;
    setDelay(delay);

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
  /**.
   *
   * @param inputTrack new track for departure.
   * 
   */
  public void setTrack(int inputTrack) 
      throws IllegalArgumentException {  //Set a new track a train will arrive at
    if (inputTrack < 0 && inputTrack != -1 || inputTrack > 9) {
      throw new IllegalArgumentException("Track number must be positive.");      
    }
    track = inputTrack;
  }
  
  private void setDelay(LocalTime delay) {
    this.delay = delay;
  }
  /**
 * Adds hours and minutes to the original delay.
 *
 * @param delayTime Amount of delay.
 *                      Should be in the format of {@code LocalTime}.
 *                      It represents both hours and minutes to be added.
 */

  //Set delay function, but adds instead. 
  public void addDelay(LocalTime delayTime) {
      
    delay = delay.plusMinutes(delayTime.getMinute());
    delay = delay.plusHours(delayTime.getHour());  
    
    
  }

  //method to check if trainnumber already exists
  public int compareTo(TrainDeparture otherDeparture) {
    return Integer.compare(this.getTrainNumber(), otherDeparture.getTrainNumber());
  }

  public boolean hasDelay() {
    return getDelay() != LocalTime.parse("00:00");
  } 
  /**.
   *
   * @return The departure's time after delay is added.
   */

  public LocalTime actualDepartureTime() {
    return getDepartureTime().plusHours(getDelay().getHour()).plusMinutes(getDelay().getMinute());
  }
  /**.
   *
   *@return A departure returned as a string, withoout irrelevant information.
   */
  
  public String toString() {

    String result = "";

    result += String.format("| %10s %12s %8d %18s",
     departureTime.toString(), line, trainNumber, destination);
     
    if (hasDelay()) {
      result += String.format("%9s", delay);     
    } else {
      result += "         ";
    }
    if (track != -1) {
      result += String.format("%8d", track);
    } else {
      result += "        ";
    }
    
    result += "  |"; 
    return result;
  }
}
