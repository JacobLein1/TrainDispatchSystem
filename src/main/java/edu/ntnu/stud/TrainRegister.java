package edu.ntnu.stud;
import java.util.ArrayList;
import java.util.stream.Collectors;


/**
 * TrainRegister class 
 * Will consist of the train registry, as an ArrayList of elements of the class Train.
 */
public class TrainRegister {
  private ArrayList<TrainDeparture> trainRegister = new ArrayList<>();

  /**.
   * Add new train departure method
   */
  public void addNewTrainDeparture(TrainDeparture newTrainDeparture) {

    if (checkDuplicate(newTrainDeparture)) {

      throw new IllegalArgumentException("Duplicate found!");

    } else {

      trainRegister.add(newTrainDeparture);

      System.out.println("New departure added");
    }

  }

  private boolean checkDuplicate(TrainDeparture chosenDeparture) {

    for (int i = 0; i < trainRegister.size(); i++) {

      if (chosenDeparture.compareTo(trainRegister.get(i)) == 0) {
            
        return true;
      }
    }
    return false;
  }

  /**.
   * 
   */

  public TrainDeparture wantedDeparture(int trainNumber) {
    return trainRegister
        .stream()
        .filter(d -> d.getTrainNumber() == trainNumber)
        .findFirst()
        .orElse(null);
    //.collect(Collectors.toCollection(ArrayList::new));

    }
    
}
