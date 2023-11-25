package edu.ntnu.stud;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.Assert.*;


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
    TrainDeparture barum = new TrainDeparture(122, 2, "L2", "Hovik", input, LocalTime.of(0, 1));
      
    LocalTime input2 = LocalTime.parse("22:46");
    TrainDeparture oslo = new TrainDeparture(122, 2, "L3", "Grønland", input2, LocalTime.of(0, 0));
        
    assertEquals(0, oslo.compareTo(barum));
  }

  @Test
    void testCompareToNegative() {
    LocalTime input = LocalTime.parse("22:46");
    TrainDeparture barum = new TrainDeparture(122, 2, "L2", "Hovik", input, LocalTime.of(0, 1));
      
    LocalTime input2 = LocalTime.parse("22:46");
    TrainDeparture oslo = new TrainDeparture(123, 2, "L3", "Grønland", input2, LocalTime.of(0, 0));
    
    
    assertEquals(1, oslo.compareTo(barum));
    
  }

  @Test 
   void testCompareToExtreme() {
    LocalTime input = LocalTime.parse("22:46");
    TrainDeparture barum = new TrainDeparture(100000, 2, "L2", "Hovik", input, LocalTime.of(0, 1));
      
    assertEquals(100000, barum.getTrainNumber());

  }
}
