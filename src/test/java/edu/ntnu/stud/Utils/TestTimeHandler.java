package edu.ntnu.stud.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.time.DateTimeException;
import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**.
 * Test class for TimeHandler class
 */
class TestTimeHandler {
  TimeHandler clock;
  
  @BeforeEach
   void setup() {
    clock = new TimeHandler(LocalTime.of(0, 0));
  }

  @Test 
    void constructor() {
    TimeHandler testClock = new TimeHandler(LocalTime.of(11, 0));
    assertEquals(LocalTime.of(11, 0), testClock.getCurrentTime());
  }

  @Test
   void getCurrentTime() {
    assertEquals(LocalTime.of(0, 0), clock.getCurrentTime());
  }

  @Test 
   void getCurrentAfterUpdate() {
    clock.updateCurrentTime(LocalTime.of(10, 0));
    assertEquals(LocalTime.of(10, 0), clock.getCurrentTime());
  }

  @Test 
  void updateCurrentTime() {
    clock.updateCurrentTime(LocalTime.of(13, 10));
    assertEquals(LocalTime.of(13, 10), clock.getCurrentTime());
  }
  
  @Test
    void getCurrentTime_NegativeHour() {
    assertThrowsExactly(DateTimeException.class, () -> {
      
      clock.updateCurrentTime(LocalTime.of(24, 10)); });
  }

  @Test
   void getCurrentTime_NegativeMinute() {
    assertThrowsExactly(DateTimeException.class, () -> {
      
      clock.updateCurrentTime(LocalTime.of(22, 60)); });
  }
  
  @Test
    void stringToLocalTime_Empty() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
        TimeHandler.stringToLocalTime(""));
    assertEquals("You must enter a time, try again.", exception.getMessage()); 
  }
  
  @Test
   void stringToLocalTime_Negative() {
    String invalidTime = "Invalid";
    DateTimeException exception = assertThrows(DateTimeException.class, () -> 
        TimeHandler.stringToLocalTime(invalidTime)
    );
    assertEquals("Text 'Invalid' could not be parsed at index 0", exception.getMessage());
  }
  
  @Test
   void stringToLocalTime_Valid() {
    String validTime = "10:00";
    assertEquals(LocalTime.of(10, 0), TimeHandler.stringToLocalTime(validTime));
  }
}
