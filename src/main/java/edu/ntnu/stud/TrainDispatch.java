package edu.ntnu.stud;

import java.time.LocalTime;

/**
 * The train dispatch table.
 * Information this class will involve, and their datatype:  
 * Departure time(LocalTime), Line(String), Train number(int)
 * Destination(String), Track(int), Delay(LocalTime)
 * Immutable variables: Line, Train number, Destination, Track 
 */
public class TrainDispatch {        //Information regarding train departure
  int trainNumber;
  int track;
  String destination;
  LocalTime departureTime;
  LocalTime delay;

}
