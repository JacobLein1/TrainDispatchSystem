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
 * Class to test methods for TrainRegister class, shown below:
 * 
 * Testing of addNewTrainDeparture method includes testing that size of register increases, 
 * testing that the right value(departure info) is correct
 *  as well as checking that constructor throws IllegalAgrumentException for duplicate trainnumbers
 * 
 * <p> Testing that wantedDeparture method only returns the wanted departure, as well as testing that it 
 * does not return anything if no match is found.
 * 
 * <p> Testing that method getListOfDepartures returns the right list.
 * 
 * <p> Testing that method departuresToWantedDestination returns a list of departures to the wanted destionation, 
 * while excluding departures to other destinations.
 * 
 * <p> Testing that method sortedListOfDepartures returns the list of departures in the correct order.
 */
public class TestTrainRegister {
  TrainRegister testRegister = new TrainRegister();
    

  @Test
    public void testAddNewTrainDeparture() {
    
    TrainDeparture oslo = new TrainDeparture(1, 1, "L3",
         "Bygdøy", LocalTime.of(22, 30), LocalTime.of(0, 3));
    
    TrainDeparture tromso = new TrainDeparture(2, 1, "L3",
         "Bygdøy", LocalTime.of(22, 46), LocalTime.of(0, 2));

    testRegister.addNewTrainDeparture(oslo);
    testRegister.addNewTrainDeparture(tromso);

    assertEquals(2, testRegister.getListOfDepartures().length);
  }

  @Test
    public void testAddNewDepartureInfo() {
    TrainDeparture oslo = new TrainDeparture(1, 1, "L3",
        "Bygdøy", LocalTime.of(22, 30), LocalTime.of(0, 2));
    
    testRegister.addNewTrainDeparture(oslo);
    
    assertEquals(1, testRegister.getListOfDepartures()[0].getTrainNumber());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNewDepartureInfo_negative() {
    LocalTime input = LocalTime.parse("22:46"); 
    TrainDeparture tromso = new TrainDeparture(1, 1, "L3",
         "Bygdøy", input, LocalTime.of(0, 2));
    TrainDeparture oslo = new TrainDeparture(1, 1, "L3", "Bygdøy", input, LocalTime.of(0, 2));

    testRegister.addNewTrainDeparture(tromso);
    testRegister.addNewTrainDeparture(oslo);

    //Fikse sjekk på at lengden på listen stemmer
  }

  @Test
  public void testwantedDeparture() {
    TrainDeparture oslo = new TrainDeparture(1, 1, "L3",
        "Bygdøy", LocalTime.of(22, 30), LocalTime.of(0, 2));
    TrainDeparture tromso = new TrainDeparture(2, 1, "L3",
         "Bygdøy", LocalTime.of(22, 46), LocalTime.of(0, 2));
        testRegister.addNewTrainDeparture(oslo);
        testRegister.addNewTrainDeparture(tromso);

    assertEquals(oslo, testRegister.wantedDeparture(1));
  }
  
  @Test
  public void testWantedDepartureNotFound() {
    TrainDeparture oslo = new TrainDeparture(1, 1, "L3", "Bygdøy",
     LocalTime.of(22, 30), LocalTime.of(0, 2));
    testRegister.addNewTrainDeparture(oslo);
    assertEquals(null, testRegister.wantedDeparture(2));
  }

  @Test
  public void testGetListOfDepartures() {
    
    TrainDeparture oslo = new TrainDeparture(1, 1, "L3", "Bygdøy", LocalTime.of(22, 30), LocalTime.of(0, 2));
    testRegister.addNewTrainDeparture(oslo);
    
    TrainDeparture tromso = new TrainDeparture(2, 3, "L1", "tromso", LocalTime.of(13, 00), LocalTime.of(0, 3));
    testRegister.addNewTrainDeparture(tromso);

    TrainDeparture[] expected = {oslo, tromso};

    assertArrayEquals(expected, testRegister.getListOfDepartures());
    
  }
  @Test
  public void testDeparturesToWantedDestination() {

    TrainDeparture oslo = new TrainDeparture(1, 1, "L3", "Bygdøy",
        LocalTime.of(22, 30), LocalTime.of(0, 2));

    TrainDeparture stabekk = new TrainDeparture(2, 1, "L3", "Bygdøy",
        LocalTime.of(22, 30), LocalTime.of(0, 2));

    TrainDeparture[] expected = {oslo, stabekk};
    
    testRegister.addNewTrainDeparture(oslo);
    testRegister.addNewTrainDeparture(stabekk);

    assertArrayEquals(expected, testRegister.departuresToWantedDestination("Bygdøy"));
  }
  
  @Test
  public void testSortedListOfDepartures() {

    TrainDeparture haslum = new TrainDeparture(3, 2, "L3", "Bygdøy",
        LocalTime.of(23, 40), LocalTime.of(0, 0));
    TrainDeparture oslo = new TrainDeparture(1, 1, "L3", "Bygdøy",
        LocalTime.of(22, 28), LocalTime.of(0, 3));

    TrainDeparture stabekk = new TrainDeparture(2, 1, "L3", "Bygdøy",
        LocalTime.of(22, 30), LocalTime.of(0, 0));
    
    testRegister.addNewTrainDeparture(haslum);
    testRegister.addNewTrainDeparture(stabekk);
    testRegister.addNewTrainDeparture(oslo);
    
    TrainDeparture[] expected = {oslo, stabekk, haslum};

    assertArrayEquals(expected, testRegister.sortedDepartureList());
  }
}

