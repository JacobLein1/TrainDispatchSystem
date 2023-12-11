package edu.ntnu.stud.models;

import java.time.LocalTime;

/**
 * <h2>TrainDeparture class</h2>
 * This class will include the following fields: 
 * <ul> 
 * <li>Departure time(LocalTime)</li>
 * <li>Line(String) </li>
 * <li>Train number(int)</li>
 * <li>Destination(String)</li>
 * <li>Track(int), Delay(LocalTime)</li>
 * </ul>
 * 
 * <li>Immutable variables: Line, Train number, Destination, Track <li>
 *
 *  <p>Will throw error messages if invalid value.
 *
 * @author Jacob Lein
 * @version 0.1
 * @see TrainRegister
 */
public class TrainDeparture {        //Information regarding train departure
  private final int trainNumber;
  private int track;              //"Spor på norsk"
  private final String line;      //"Linje på norsk, f.ex L1"
  private final String destination;
  private final LocalTime departureTime;
  private LocalTime delay;

  /**.
  *<h2>Constructor for a departure</h2>
  * Variables limited to the following values:
  *<ul>
  *<li>Train number: 0-99</li>
  *<li>Track: 1-9 (-1 if not given)</li>
  *<li>Line: 1-3 characters</li>
  *<li>Destination: 1-14 characters</li>
  *<li>Departure time: LocalTime limits</li>
  *<li>Delay: LocalTime limits</li>
  *</ul>
  */
  public TrainDeparture(int trainNumber, int track, String line, 
      String destination, LocalTime departureTime, LocalTime delay) {
    if (trainNumber < 0 || trainNumber > 99) {
      throw new IllegalArgumentException(
        "Train number must be positive whole number. Between [0-99]");
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
 *
 */
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
