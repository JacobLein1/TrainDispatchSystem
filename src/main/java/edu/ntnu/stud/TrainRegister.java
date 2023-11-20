package edu.ntnu.stud;
import java.util.ArrayList;
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

    for (int i = 0; i < departureList.size(); i++) {

      if (chosenDeparture.compareTo(departureList.get(i)) == 0) {
            
        return true;
      }
    }
    return false;
  }

  /**.
   * .collect(Collectors.toCollection(ArrayList::new));
   *@param trainNumber the wanted trainNumber
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
   * @return all departures 
   * 
   */
  public TrainDeparture[] getListOfDepartures() {     
  
    return departureList
    .stream()
    .collect(Collectors.toList()).toArray(new TrainDeparture[0]);
  }
  
  /**.
   * @param wantedDestination the wanted destination
   * @return found departures to the wanted destination
   */
  public TrainDeparture[] departuresToWantedDestination(String wantedDestination){
    
    return departureList
      .stream()
      .filter(d -> d.getDestination().equals(wantedDestination))
      .collect(Collectors.toList()).toArray(TrainDeparture[]::new); 
     
  }

}
