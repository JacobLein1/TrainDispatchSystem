package edu.ntnu.stud.Models;

import java.time.LocalTime;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.stream.Collectors;

import edu.ntnu.stud.UserInterface;



/**
 * TrainRegister class 
 * Consist of the train registry, as an ArrayList of elements of the class Train.
 * 
 * <p>As well as the methods: 
 * 
 * <p>addNewTrainDeparture
 * checkDuplicate
 * wantedDeparture
 * getListOfDepartures
 * departuresToWantedDestination
 * departuresAfterTime
 * sortedDepartureList
 */
public class TrainRegister { 
  

  private ArrayList<TrainDeparture> departureList = new ArrayList<>();
  private static LocalTime clock = LocalTime.of(0, 0);
  /**.
   * Add new train departure method
   */

  public void addNewDeparture(TrainDeparture newTrainDeparture) {

    if (checkDuplicate(newTrainDeparture)) {

      throw new IllegalArgumentException("Duplicate found!");

    } else {
      departureList.add(newTrainDeparture);
    }
  }
  /**.
   *
   * @param trainNumber Train number of departure wanted to be removed
   * 
   */

  public void removeDeparture(int trainNumber) {

    departureList = departureList.stream().filter(d -> d.getTrainNumber() == trainNumber)
      .collect(Collectors.toCollection(ArrayList::new));
  }

  private boolean checkDuplicate(TrainDeparture chosenDeparture) {

    TrainDeparture[] duplicateStream = departureList.stream().filter(d ->
     d.getTrainNumber() == chosenDeparture.getTrainNumber())
    .collect(Collectors.toList()).toArray(TrainDeparture[]::new);

    if (duplicateStream.length == 0) {
      return false;
    } else {
      return true;
    }
    
  }

  /**.
  *
  *@param trainnumber the wanted trainnumber
  *
  *@return found departures of the right trainNumber
  */
  
  public TrainDeparture wantedDeparture(int trainNumber) {

    return departureList
        .stream()
        .filter(d -> d.getTrainNumber() == trainNumber)
        .findFirst()
        .orElse(null);
  }

  

  /**.
   *
   * @return all departures.
   */
  public TrainDeparture[] getListOfDepartures() {     
  
    return departureList
    .stream()
    .collect(Collectors.toList()).toArray(new TrainDeparture[0]);
  }
  
  /**.
   *
  * @param wantedDestination the wanted destination
  *
  * @return found departures to the wanted destination
  */
  public TrainDeparture[] departuresToWantedDestination(String wantedDestination) {
    return departureList
      .stream()
      .filter(d -> d.getDestination().equalsIgnoreCase(wantedDestination))
      .collect(Collectors.toList()).toArray(TrainDeparture[]::new);
     
  }
  /**.
  *
  * @param currentTime User inputs current time.
  * 
  * 
  */
  
  public void departuresAfterTime(LocalTime currentTime) {
    departureList = departureList.stream()
    .filter(d -> d.departureTimeAfterDelay(d).isAfter(currentTime))
    .collect(Collectors.toCollection(ArrayList::new));
  }
  /**.
  *
  * @return a sorted list of departures by departure time without regarding delay.
  * https://www.geeksforgeeks.org/how-to-sort-an-arraylist-of-objects-by-property-in-java/ Link
  used to make this method
  */

  public TrainDeparture[] sortedDepartureList() {
    
    return departureList.stream().sorted((d1, d2) -> 
    d1.getDepartureTime().compareTo(d2.getDepartureTime()))
    .collect(Collectors.toList()).toArray(TrainDeparture[]::new);
     
  }

  /**.
   * Method to print the trainregister sorted by departure time.
   * 
   */
  public void printSortedList() {
    
    System.out.println("\n");
    System.out.println(
        "----------------------------------" + clock + "---------------------------------");
    System.out.println("|  Departure Time    Line  Train number    Destination   Track    Delay |");
    System.out.println("|_______________________________________________________________________|");
    Arrays.stream(sortedDepartureList())
        .forEach(d -> System.out.println(d.departureToString()));
  }
}
