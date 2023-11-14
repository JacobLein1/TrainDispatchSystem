package edu.ntnu.stud;

import java.time.LocalTime;

/**
 * This is the main class for the train dispatch application.
 */
public class TrainDispatchApp {
  // TODO: Fill in the main method and any other methods you need.

  /**.
   * Main method
   * 
   */
  public static void main(String[] args) {
    UIF t1 = new UIF();
    TrainRegister trainRegister = new TrainRegister(); 
  
    t1.start();
    t1.init();
    LocalTime input = LocalTime.parse("22:46");
    TrainDeparture barum = new TrainDeparture(1, 2, "L2", "Hovik", input, 1);

    LocalTime input2 = LocalTime.parse("22:46");
    TrainDeparture oslo = new TrainDeparture(1, 2, "L3", "Grønland", input2, 0);
  
    trainRegister.addNewTrainDeparture(oslo);
    trainRegister.addNewTrainDeparture(barum);
  

  
}
}
