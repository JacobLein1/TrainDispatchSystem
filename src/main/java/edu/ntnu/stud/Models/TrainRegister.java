package edu.ntnu.stud.models;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.stream.Collectors;




/**
 * <h2>TrainRegister class</h2> 
 * Consist of the train registry, as an ArrayList of elements of the class Train.
 * 
 * <h3>As well as the methods:</h3> 
 * <ul>
 * <li>addTrainDeparture</li>
 * <li>checkDuplicate</li>
 * <li>wantedDeparture</li>
 * <li>getListOfDepartures</li>
 * <li>departuresToWantedDestination</li>
 * <li>departuresAfterTime</li>
 * <li>sortedDepartureList</li>
 * </ul>
 *
 * @version 0.2
 */
public class TrainRegister { 
  

  private ArrayList<TrainDeparture> departureList = new ArrayList<>();
  /**.
   *
   * @param newDeparture new departure to be added.
   */

  public void addDeparture(TrainDeparture newDeparture) {

    if (checkDuplicate(newDeparture)) {

      throw new IllegalArgumentException("Duplicate found!");

    } else {
      departureList.add(newDeparture);
    }
  }
  /**.
   *
   * @param trainNumber Train number of departure wanted to be removed.
   * 
   */

  public void removeDeparture(int trainNumber) {

    departureList = departureList.stream().filter(d -> d.getTrainNumber() != trainNumber)
      .collect(Collectors.toCollection(ArrayList::new));
  }

  private boolean checkDuplicate(TrainDeparture chosenDeparture) {

    TrainDeparture[] duplicateStream = departureList.stream().filter(d ->
     d.getTrainNumber() == chosenDeparture.getTrainNumber())
    .collect(Collectors.toList()).toArray(TrainDeparture[]::new);

    return duplicateStream.length > 0;
    
  }

  /**.
  *
  *@param trainNumber the wanted trainnumber.
  *
  *@return found departure of the right trainNumber, null if none found.
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
  public TrainDeparture[] searchByDestination(String wantedDestination) {
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
    .filter(d -> d.actualDepartureTime().compareTo(currentTime) >= 0)
    .collect(Collectors.toCollection(ArrayList::new));
  }
  /**.
  *
  * @return a sorted list of departures by departure time without regarding delay.
  */

  public TrainDeparture[] sortedDepartureList() {
    
    return departureList.stream().sorted((d1, d2) -> 
    d1.getDepartureTime().compareTo(d2.getDepartureTime()))
    .collect(Collectors.toList()).toArray(TrainDeparture[]::new);
  }

}
