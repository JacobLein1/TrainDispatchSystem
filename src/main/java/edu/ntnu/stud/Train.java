package edu.ntnu.stud;

import java.time.LocalTime;

/**
 * The train dispatch table.
 * Information this class will involve, and their datatype:  
 * Departure time(LocalTime), Line(String), Train number(int)
 * Destination(String), Track(int), Delay(LocalTime)
 * Immutable variables: Line, Train number, Destination, Track 
 */
public class Train {        //Information regarding train departure
  final int trainNumber;
  int track; //"Spor på norsk"
  final String line; //"Linje på norsk, f.ex L1"
  final String destination;
  LocalTime departureTime;
  LocalTime delay;

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
  
  public int get_trainNumber() {
    return trainNumber;
  }

  public int get_track() {
    return track;
  }

  public String get_line() {
    return line;
  }

  public String get_destination() {
    return destination;
  }

  public LocalTime get_departureTime() {
    return departureTime;
  }

  public LocalTime get_delay() {
    return delay;
  }

  //Setter-methods

  public void set_track(int newTrack) {  //Set a new track a train will arrive at
    
    track = newTrack;
  }
  
  //set a new departure time in case of a delay
  public void set_departureTime(LocalTime newDepartureTime) {        
    
    departureTime = newDepartureTime;
  }
  
  public void set_delayTime(LocalTime addedDelayTime) {              //set a delay for a train
    delay = addedDelayTime;
  }

}
