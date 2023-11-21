package edu.ntnu.stud;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collector;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

/**.
 * Class to test methods for TrainRegister class
 */
public class TestTrainRegister {
  TrainRegister testRegister = new TrainRegister();
    

  @Test
    public void testAddNewTrainDeparture() {
    
    TrainDeparture oslo = new TrainDeparture(1, 1, "L3", "Bygdøy", LocalTime.of(22, 30), 2);
    
    testRegister.addNewTrainDeparture(oslo);

    assertEquals(1, testRegister.getListOfDepartures().length);
  }

  @Test
    public void testAddNewDepartureInfo() {
    TrainDeparture oslo = new TrainDeparture(1, 1, "L3", "Bygdøy", LocalTime.of(22, 30), 2);

    testRegister.addNewTrainDeparture(oslo);
    
    assertEquals(1, testRegister.getListOfDepartures()[0].getTrainNumber());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNewDepartureInfo_negative() {
    LocalTime input = LocalTime.parse("22:46"); 
    TrainDeparture tromso = new TrainDeparture(1, 1, "L3", "Bygdøy", input, 2);
    TrainDeparture oslo = new TrainDeparture(1, 1, "L3", "Bygdøy", input, 2);

    testRegister.addNewTrainDeparture(tromso);
    testRegister.addNewTrainDeparture(oslo);
  }

  @Test
  public void testwantedDeparture() {
    TrainDeparture oslo = new TrainDeparture(1, 1, "L3", "Bygdøy", LocalTime.of(22, 30), 2);
    testRegister.addNewTrainDeparture(oslo);

    assertEquals(oslo, testRegister.wantedDeparture(1));
  }
  @Test
  public void testWantedDepartureNotFound() {
    TrainDeparture oslo = new TrainDeparture(1, 1, "L3", "Bygdøy", LocalTime.of(22, 30), 2);
    testRegister.addNewTrainDeparture(oslo);
    assertEquals(null, testRegister.wantedDeparture(2));
  }

  @Test
  public void testGetListOfDepartures() {
    
    TrainDeparture oslo = new TrainDeparture(1, 1, "L3", "Bygdøy", LocalTime.of(22, 30), 2);
    testRegister.addNewTrainDeparture(oslo);
    
    TrainDeparture tromso = new TrainDeparture(2, 3, "L1", "tromso", LocalTime.of(13, 00), 0);
    testRegister.addNewTrainDeparture(tromso);

    TrainDeparture[] expected = {oslo, tromso};

    assertArrayEquals(expected, testRegister.getListOfDepartures());
    
  }
  @Test
  public void testDeparturesToWantedDestination() {

    TrainDeparture oslo = new TrainDeparture(1, 1, "L3", "Bygdøy", LocalTime.of(22, 30), 2);

    TrainDeparture stabekk = new TrainDeparture(2, 1, "L3", "Bygdøy", LocalTime.of(22, 30), 2);

    TrainDeparture tromso = new TrainDeparture(3, 1, "L3", "Oslo", LocalTime.of(22, 30), 2);

    TrainDeparture[] expected = {oslo, stabekk};
    
    assertArrayEquals(expected, testRegister.departuresToWantedDestination("Bygdøy"));
  }
  
}
