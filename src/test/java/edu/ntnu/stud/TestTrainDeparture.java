package edu.ntnu.stud;

import static org.junit.Assert.assertEquals;

import edu.ntnu.stud.models.TrainDeparture;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;





class TestTrainDeparture {
    
  // Positive test for variable Track
  @Test
    void testTrack() {
    LocalTime input = LocalTime.parse("22:46");
    TrainDeparture oslo = new TrainDeparture(1, 1, "L3", "Bygdøy", input, LocalTime.of(0, 2));
    assertEquals(1, oslo.getTrainNumber());
  }

  @Test
    void testCompareToPositive() {
    LocalTime input = LocalTime.parse("22:46");
    TrainDeparture barum = new TrainDeparture(98, 2, "L2", "Hovik", input, LocalTime.of(0, 1));
      
    LocalTime input2 = LocalTime.parse("22:46");
    TrainDeparture oslo = new TrainDeparture(98, 2, "L3", "Grønland", input2, LocalTime.of(0, 0));
        
    assertEquals(0, oslo.compareTo(barum));
  }

  @Test
    void testCompareToNegative() {
    LocalTime input = LocalTime.parse("22:46");
    TrainDeparture barum = new TrainDeparture(97, 2, "L2", "Hovik", input, LocalTime.of(0, 1));
      
    LocalTime input2 = LocalTime.parse("22:46");
    TrainDeparture oslo = new TrainDeparture(98, 2, "L3", "Grønland", input2, LocalTime.of(0, 0));
    
    
    assertEquals(1, oslo.compareTo(barum));
    
  }
  /* 
  @Test 
   void testCompareToExtreme() {
    LocalTime input = LocalTime.parse("22:46");
    TrainDeparture barum = new TrainDeparture(99, 2, "L2", "Hovik", input, LocalTime.of(0, 1));
      
    assertEquals(100000, barum.getTrainNumber());

  }*/

  @Test
  void testAddDelayTime() {
    LocalTime input = LocalTime.parse("22:46");
    TrainDeparture barum = new TrainDeparture(0001, 2, "L2", "Hovik", input, LocalTime.of(0, 1));

    barum.addDelay(LocalTime.of(0, 2));
    
    assertEquals(LocalTime.of(0, 3), barum.getDelay());
  }
}
