package edu.ntnu.stud;

import java.time.LocalTime;

/**
 * The train dispatch table.
 * Information this class will involve, and their datatype:  
 * Departure time(LocalTime), Line(String), Train number(int)
 * Destination(String), Track(int), Delay(LocalTime)
 * Immutable variables: Line, Train number, Destination, Track 
 * Will throw error messages if wrong value
 */
public class Train {        //Information regarding train departure
  private final int trainNumber;
  private int track; //"Spor på norsk"
  private final String line; //"Linje på norsk, f.ex L1"
  private final String destination;
  private LocalTime departureTime;
  private LocalTime delay;

  /**.
  *Constructor for a departure 
  */
  public Train(int trainNumber, int track, String line, 
      String destination, LocalTime departureTime, LocalTime delay) {
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
  
  public void setDelayTime(LocalTime addedDelayTime) {              //set a delay for a train

    delay = addedDelayTime;
  }

}
