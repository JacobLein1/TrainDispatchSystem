package edu.ntnu.stud;

import java.time.LocalTime;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collector;
import java.util.stream.Collectors;


/**
 * TrainRegister class 
 * Will consist of the train registry, as an ArrayList of elements of the class Train.
 */
public class TrainRegister {
  private final ArrayList<TrainDeparture> departureList = new ArrayList<>();

  /**.
   * Add new train departure method
   */
  public void addNewTrainDeparture(TrainDeparture newTrainDeparture) {

    if (checkDuplicate(newTrainDeparture)) {

      throw new IllegalArgumentException("Duplicate found!");

    } else {

      departureList.add(newTrainDeparture);

      System.out.println("New departure added");
    }

  }

  private boolean checkDuplicate(TrainDeparture chosenDeparture) {

  departureList.stream().filter(d -> d.getTrainNumber() == chosenDeparture.getTrainNumber()).collect(Collectors.toList()).toArray(TrainDeparture[]::new);
    for (int i = 0; i < departureList.size(); i++) {

      if (chosenDeparture.compareTo(departureList.get(i)) == 0) {
            
        return true;
      }
    }
    return false;
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
  * @param wantedDestination the wanted destination
  *
  * @return found departures to the wanted destination
  */
  public TrainDeparture[] departuresToWantedDestination(String wantedDestination){
    return departureList
      .stream()
      .filter(d -> d.getDestination().equals(wantedDestination))
      .collect(Collectors.toList()).toArray(TrainDeparture[]::new);
     
  }
  /**.
  * 
  * @param currentTime User inputs current time.
  * 
  * @return The traindepartures after a certain time.
  */
  
  public TrainDeparture[] departuresAfterTime(LocalTime currentTime) {
    return departureList
    .stream()
    .filter(d -> d.departureTimeAfterDelay(d).isAfter(currentTime))
    .toArray(TrainDeparture[]::new);
  }
  /**.
  * 
  * @return a sorted list of departures by departure time without regarding delay.
  * https://www.geeksforgeeks.org/how-to-sort-an-arraylist-of-objects-by-property-in-java/ Link used to make this method
  */
  public TrainDeparture[] sortedDepartureList() {
    
    return departureList.stream().sorted((d1, d2) -> 
    d1.getDepartureTime().compareTo(d2.getDepartureTime()))
    .collect(Collectors.toList()).toArray(TrainDeparture[]::new);
     
  }
}
