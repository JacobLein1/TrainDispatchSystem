package edu.ntnu.stud.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


//test alle metoder med logikk, test konstruktøren


class TestTrainDeparture {
  LocalTime time;
  TrainDeparture oslo;
  TrainDeparture asker;

  @BeforeEach
    void setup() {
    time = LocalTime.of(22, 46);
    oslo = new TrainDeparture(1, 1, "L3", "Bygdøy", time, LocalTime.of(0, 2));
  }
  // Positive test for variable Track

  @Test 
  void invalidTrainNumberNegative() {
    assertThrows(IllegalArgumentException.class, () -> {
      TrainDeparture drammen = new TrainDeparture(-1, 1, "L3", "Helvete", time, LocalTime.of(0, 0));
    });
  }

  @Test 
  void invalidTrainNumberHighLimit() {
    assertThrows(IllegalArgumentException.class, () -> {
      TrainDeparture drammen = new TrainDeparture(1000,
           1, "L3", "Helvete", time, LocalTime.of(0, 0));
    });
  }

  @Test 
  void invalidLineHighLimit() {
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class, () -> {
        TrainDeparture drammen = new TrainDeparture(2, 1, "1234",
            "Helvete", time, LocalTime.of(0, 0));
      });
    assertEquals("Line has to be an input. With max length of 3 symbols.", exception.getMessage());
  }

  @Test 
  void invalidLineEmpty() {
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class, () -> {
        TrainDeparture drammen = new TrainDeparture(2, 1, "",
            "Helvete", time, LocalTime.of(0, 0));
      });
    assertEquals("Line has to be an input. With max length of 3 symbols.", exception.getMessage());
  }
  
  @Test 
  void invalidLineSpace() {
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class, () -> {
        TrainDeparture drammen = new TrainDeparture(2, 1, " ",
            "Helvete", time, LocalTime.of(0, 0));
      });
    assertEquals("Line has to be an input. With max length of 3 symbols.", exception.getMessage());
  }


  @Test 
    void invalidTrack() {
    IllegalArgumentException excception = assertThrows(IllegalArgumentException.class, () -> {
      TrainDeparture drammen = new TrainDeparture(2, -9, "L3", "Helvete", time, LocalTime.of(0, 0));
    });
    assertEquals("Track number must be positive.", excception.getMessage());
  }

  @Test 
    void invalidDestinationHighLimit() {
    IllegalArgumentException excception = assertThrows(IllegalArgumentException.class, () -> {
      TrainDeparture drammen = new TrainDeparture(2, -9, "L3",
           "123456789123456", time, LocalTime.of(0, 0));
    });
    assertEquals("Destination has to be an input under 14 characters. ", excception.getMessage());
  }

  @Test 
    void invalidDestinationEmpty() {
    IllegalArgumentException excception = assertThrows(IllegalArgumentException.class, () -> {
      TrainDeparture drammen = new TrainDeparture(2, -9, "L3",
           "", time, LocalTime.of(0, 0));
    });
    assertEquals("Destination has to be an input under 14 characters. ", excception.getMessage());
  }

  @Test 
    void invalidDestinationSpace() {
    IllegalArgumentException excception = assertThrows(IllegalArgumentException.class, () -> {
      TrainDeparture drammen = new TrainDeparture(2, -9, "L3",
           " ", time, LocalTime.of(0, 0));
    });
    assertEquals("Destination has to be an input under 14 characters. ", excception.getMessage());
  }

  @Test 
    void invalidDepartureTime() {
    IllegalArgumentException excception = assertThrows(IllegalArgumentException.class, () -> {
      TrainDeparture drammen = new TrainDeparture(2, -1, "L3",
           "Helvete", null, LocalTime.of(0, 0));
    });
    assertEquals("Must add a departure time. ", excception.getMessage());
  }

  @Test 
  void setTrackPostitive() {

    oslo.setTrack(1);

    assertEquals(1, oslo.getTrack());
  }

  @Test
  void setTrackNegative() {

    assertThrowsExactly(IllegalArgumentException.class, () -> {

      oslo.setTrack(-3);
    });
  }

  @Test
  void setTrackNegativeOverLimit() {

    assertThrowsExactly(IllegalArgumentException.class, () -> {

      oslo.setTrack(10);
    });
  }

  @Test
  void setTrackNoTrackGranted() {

    oslo.setTrack(-1);

    assertEquals(-1, oslo.getTrack());
  }

  @Test
  void addDelay() {
    

    oslo.addDelay(LocalTime.of(0, 2));
    
    assertEquals(LocalTime.of(0, 4), oslo.getDelay());
  }


  @Test
    void compareToPositive() {
  
    TrainDeparture barum = new TrainDeparture(98, 2, "L2", "Hovik", time, LocalTime.of(0, 1));
    
    TrainDeparture asker = new TrainDeparture(98, 2, "L3", "Grønland", time, LocalTime.of(0, 0));
        
    assertEquals(0, asker.compareTo(barum));
  }

  @Test
    void compareToNegative() {
    
    TrainDeparture barum = new TrainDeparture(97, 2, "L2", "Hovik", time, LocalTime.of(0, 1));
    
    TrainDeparture asker = new TrainDeparture(98, 2, "L3", "Grønland", time, LocalTime.of(0, 0));

    assertEquals(1, asker.compareTo(barum));
  }

  @Test
  void hasDelayTrue() {
    assertEquals(true, oslo.hasDelay());
  }

  @Test
  void hasDelayFalse() {
    TrainDeparture asker = new TrainDeparture(98, 2, "L3", "Grønland", time, LocalTime.of(0, 0));
    assertEquals(false, asker.hasDelay());
  }

  @Test
  void hasDelayAfterAddition() {
    TrainDeparture asker = new TrainDeparture(98, 2, "L3", "Grønland", time, LocalTime.of(0, 0));
    asker.addDelay(LocalTime.of(0, 1));
    assertEquals(true, asker.hasDelay());
  }

  @Test
   void actualDepartureTime() {
    TrainDeparture asker = new TrainDeparture(98, 2, "L3", "Grønland", time, LocalTime.of(0, 0));
    asker.addDelay(LocalTime.of(0, 1));
    assertEquals(LocalTime.of(22, 47), asker.actualDepartureTime());
  }

  @Test 
  void actualDepartureTimeNoAddition() {
    TrainDeparture asker = new TrainDeparture(98, 2, "L3", "Grønland", time, LocalTime.of(0, 0));
    assertEquals(LocalTime.of(22, 46), asker.actualDepartureTime());
  }
  
  @Test
  void testToString() {
    TrainDeparture asker = new TrainDeparture(98, 2, "L3", "Grønland", time, LocalTime.of(0, 0));
    assertEquals("|      22:46           L3       98           Grønland                2  |",
         asker.toString());
  }

  @Test
  void toStringDelayAdded() {
    TrainDeparture asker = new TrainDeparture(98, 2, "L3", "Grønland", time, LocalTime.of(0, 0));
    asker.addDelay(LocalTime.of(0, 1));
    assertEquals("|      22:46           L3       98           Grønland    00:01       2  |",
         asker.toString());
  }

  @Test
  void toStringNoTrackDelay() {
    TrainDeparture asker = new TrainDeparture(98, 2, "L3", "Grønland", time, LocalTime.of(0, 0));
    asker.setTrack(-1);
    assertEquals("|      22:46           L3       98           Grønland                   |",
         asker.toString());
  }
  
  @Test 
  void toStringDelayNoTrack() {
    TrainDeparture asker = new TrainDeparture(98, 2, "L3", "Grønland", time, LocalTime.of(0, 0));
    asker.setTrack(-1);
    assertEquals("|      22:46           L3       98           Grønland                   |",
         asker.toString());
  }
  /* 
  @Test 
   void testCompareToExtreme() {
    LocalTime input = LocalTime.parse("22:46");
    TrainDeparture barum = new TrainDeparture(99, 2, "L2", "Hovik", input, LocalTime.of(0, 1));
      
    assertEquals(100000, barum.getTrainNumber());

  }*/

 
}
