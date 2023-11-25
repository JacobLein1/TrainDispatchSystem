package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.Arrays;

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
    TrainDeparture barum = new TrainDeparture(2, 2, "L2",
         "Hovik", input, LocalTime.parse("00:00"));

    LocalTime input2 = LocalTime.parse("22:46");
    TrainDeparture oslo = new TrainDeparture(1, 2, "L3", 
        "Grønland", input2, LocalTime.parse("00:00"));
    TrainDeparture bergen = new TrainDeparture(12, 1, "L3",
         "Bygdøy", LocalTime.of(22, 30), LocalTime.parse("00:02"));
    TrainDeparture tromso = new TrainDeparture(3, 1, "L3",
         "Bygdøy", LocalTime.of(22, 30), LocalTime.parse("00:02"));


    trainRegister.addNewTrainDeparture(oslo);
    trainRegister.addNewTrainDeparture(bergen);
    trainRegister.addNewTrainDeparture(tromso);
    trainRegister.addNewTrainDeparture(barum);    
    TrainDeparture stabekk = new TrainDeparture(2, 1, "L3",
           "Bygdøy", LocalTime.of(22, 30), LocalTime.parse("00:02"));
    TrainDeparture[] expected = {bergen, stabekk};
    if (bergen.getDestination().equals(stabekk.getDestination())) {
      System.out.println("Riktig");     
    }

    Utils utils = new Utils();

    //System.out.println("skal kaste error");
    //utils.parseStringToLocalTime(null);
}
}
