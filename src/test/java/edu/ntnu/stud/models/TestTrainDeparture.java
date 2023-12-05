package edu.ntnu.stud.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;





class TestTrainDeparture {
  LocalTime time;
  TrainDeparture oslo;

  @BeforeEach
    void setup() {
    time = LocalTime.of(22, 46);
    oslo = new TrainDeparture(1, 1, "L3", "Bygdøy", time, LocalTime.of(0, 2));
    }
  // Positive test for variable Track
  
  @Test
    void testTrack() {
    TrainDeparture oslo = new TrainDeparture(1, 1, "L3", "Bygdøy", time, LocalTime.of(0, 2));
    assertEquals(1, oslo.getTrainNumber());
  }

  @Test
    void testCompareToPositive() {
  
    TrainDeparture barum = new TrainDeparture(98, 2, "L2", "Hovik", time, LocalTime.of(0, 1));
    
    TrainDeparture asker = new TrainDeparture(98, 2, "L3", "Grønland", time, LocalTime.of(0, 0));
        
    assertEquals(0, asker.compareTo(barum));
  }

  @Test
    void testCompareToNegative() {
    
    TrainDeparture barum = new TrainDeparture(97, 2, "L2", "Hovik", time, LocalTime.of(0, 1));
    
    TrainDeparture asker = new TrainDeparture(98, 2, "L3", "Grønland", time, LocalTime.of(0, 0));

    assertEquals(1, asker.compareTo(barum));
  }

  @Test 
  void testSetTrackPostitive() {

    oslo.setTrack(1);

    assertEquals(1, oslo.getTrack());
  }

  @Test
  void testSetTrackNoTrackGranted() {

    oslo.setTrack(-1);

    assertEquals(-1, oslo.getTrack());
  }

  @Test
  void testSetTrackNegative() {

    assertThrowsExactly(IllegalArgumentException.class, () -> {

      oslo.setTrack(-3);
    });
  }

  @Test
  void testSetTrackNegativeOverLimit() {

    assertThrowsExactly(IllegalArgumentException.class, () -> {

      oslo.setTrack(10);
    });
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
    

    oslo.addDelay(LocalTime.of(0, 2));
    
    assertEquals(LocalTime.of(0, 4), oslo.getDelay());
  }
}
