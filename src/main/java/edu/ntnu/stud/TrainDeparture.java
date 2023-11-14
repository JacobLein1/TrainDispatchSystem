package edu.ntnu.stud;
import java.util.Objects;
import java.time.LocalTime;

/**
 * The train dispatch table.
 * Information this class will involve, and their datatype:  
 * Departure time(LocalTime), Line(String), Train number(int)
 * Destination(String), Track(int), Delay(LocalTime)
 * Immutable variables: Line, Train number, Destination, Track 
 * Will throw error messages if wrong value
 */
public class TrainDeparture {        //Information regarding train departure
  private final int trainNumber;
  private int track;              //"Spor på norsk"
  private final String line;      //"Linje på norsk, f.ex L1"
  private final String destination;
  private LocalTime departureTime;
  private int delay;

  /**.
  *Constructor for a departure 
  */
  public TrainDeparture(int trainNumber, int track, String line, 
      String destination, LocalTime departureTime, int delay) {
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

  public int getDelay() {
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
  
  public void setDelayTime(int addedDelayTime) {              //set a delay for a train

    delay = addedDelayTime;
  }
  //method to check if trainnumber already exists??
  //compare to?? Fakk det?
  public int compareTo(TrainDeparture otherDeparture){
    return Integer.compare(this.getTrainNumber(), otherDeparture.getTrainNumber());
  }

}
