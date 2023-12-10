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
public class TestTimeHandler {
  TimeHandler clock;
  
  @BeforeEach
   void setup() {
    clock = new TimeHandler(LocalTime.of(0, 0));
  }

  @Test 
  public void constructor() {
    TimeHandler testClock = new TimeHandler(LocalTime.of(11, 0));
    assertEquals(LocalTime.of(11, 0), testClock.getCurrentTime());
  }

  @Test
    public void getCurrentTime() {
    assertEquals(LocalTime.of(0, 0), clock.getCurrentTime());
  }

  @Test 
  public void getCurrentAfterUpdate() {
    clock.updateCurrentTime(LocalTime.of(10, 0));
    assertEquals(LocalTime.of(10, 0), clock.getCurrentTime());
  }

  @Test 
  public void updateCurrentTime() {
    clock.updateCurrentTime(LocalTime.of(13, 10));
    assertEquals(LocalTime.of(13, 10), clock.getCurrentTime());
  }
  
  @Test
  public void getCurrentTime_NegativeHour() {
    assertThrowsExactly(DateTimeException.class, () -> {
      
      clock.updateCurrentTime(LocalTime.of(24, 10)); });
  }

  @Test
  public void getCurrentTime_NegativeMinute() {
    assertThrowsExactly(DateTimeException.class, () -> {

      
      clock.updateCurrentTime(LocalTime.of(22, 60)); });
  }
  
  @Test
  public void stringToLocalTime_Empty() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
        TimeHandler.stringToLocalTime(""));
    assertEquals("You must enter a time, try again.", exception.getMessage()); 
  }
  
  //https://stackoverflow.com/questions/40268446/junit-5-how-to-assert-an-exception-is-thrown
  /*assertThrowsExactly(IllegalArgumentException.class, () -> {
      TimeHandler.stringToLocalTime(""); }*/
  @Test
  public void stringToLocalTime_Negative() {
    String invalidTime = "Invalid";
    DateTimeException exception = assertThrows(DateTimeException.class, () -> 
        TimeHandler.stringToLocalTime(invalidTime)
    );
    assertEquals("Text 'Invalid' could not be parsed at index 0", exception.getMessage());
  }
  
  @Test
  public void stringToLocalTime_Valid() {
    String validTime = "10:00";
    assertEquals(LocalTime.of(10, 0), TimeHandler.stringToLocalTime(validTime));
  }
}
