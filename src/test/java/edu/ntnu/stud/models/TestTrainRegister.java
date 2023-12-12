package edu.ntnu.stud.models;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.time.LocalTime;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**.
 * Class to test methods for TrainRegister class, shown below:
 * 
 * <p>Testing of addNewTrainDeparture method includes testing that size of register increases, 
 * testing that the right value(departure info) is correct
 *  as well as checking that constructor throws IllegalAgrumentException for duplicate trainnumbers
 * 
 * <p>Testing that wantedDeparture method only 
 * returns the wanted departure, as well as testing that it 
 * does not return anything if no match is found.
 * 
 * <p>Testing that method getListOfDepartures returns the right list.
 * 
 * <p>Testing that method departuresToWantedDestination 
 * returns a list of departures to the wanted destionation, 
 * while excluding departures to other destinations.
 * 
 * <p>Testing that method sortedListOfDepartures 
 * returns the list of departures in the correct order.
 */
class TestTrainRegister {
  TrainRegister testRegister = new TrainRegister();
  TrainDeparture oslo;
  TrainDeparture tromso;

  @BeforeEach
  void setup() {
    oslo = new TrainDeparture(1, 1, "L3",
         "Bygdøy", LocalTime.of(22, 30), LocalTime.of(0, 3));
    tromso = new TrainDeparture(2, 1, "L3",
         "Bygdøy", LocalTime.of(22, 46), LocalTime.of(0, 2));
  }

  @Test
  void testAddNewTrainDeparture() {

    testRegister.addDeparture(oslo);
    testRegister.addDeparture(tromso);

    assertEquals(2, testRegister.getListOfDepartures().length);
  }

  @Test
    void testAddNewDepartureInfo() {
    
    testRegister.addDeparture(oslo);
    
    assertEquals(1, testRegister.getListOfDepartures()[0].getTrainNumber());
  }

  @Test
  void addDepartureLenght() {

    testRegister.addDeparture(oslo);
    assertEquals(1, testRegister.getListOfDepartures().length);
  }

  @Test()
  void testAddNewDepartureInfo_negative() {
    TrainDeparture asker = new TrainDeparture(2, 1, "L3", "Asker",
        LocalTime.of(22, 28), LocalTime.of(0, 3));
    TrainDeparture bergen = new TrainDeparture(2, 0, "L3", "Bergen",
        LocalTime.of(22, 28), LocalTime.of(0, 0));
    assertThrowsExactly(IllegalArgumentException.class, () -> {
      testRegister.addDeparture(asker);
      testRegister.addDeparture(bergen);
    });

  }

  @Test
  void testRemoveDeparture() {

    TrainDeparture stabekk = new TrainDeparture(3, 3, "L1",
         "Ovre Hovik", LocalTime.of(10, 0), LocalTime.of(12, 0));
    testRegister.addDeparture(tromso);
    testRegister.addDeparture(stabekk);
    testRegister.removeDeparture(3);
    assertEquals(1, testRegister.getListOfDepartures().length);
  }

  @Test
  void testRemoveNotFound() {

    testRegister.addDeparture(oslo);
    testRegister.removeDeparture(2);
    assertEquals(1, testRegister.getListOfDepartures().length);
  }

  @Test
  void testwantedDeparture() {
    
    testRegister.addDeparture(oslo);
    testRegister.addDeparture(tromso);

    assertEquals(oslo, testRegister.wantedDeparture(1));
  }
  
  @Test
  void testWantedDepartureNotFound() {

    testRegister.addDeparture(oslo);
    assertEquals(null, testRegister.wantedDeparture(2));
  }

  @Test
  void testGetListOfDepartures() {
    
    testRegister.addDeparture(oslo);
    
    testRegister.addDeparture(tromso);

    TrainDeparture[] expected = {oslo, tromso};

    assertArrayEquals(expected, testRegister.getListOfDepartures());
    
  }
  
  @Test
  void testSearchByDestination() {

    TrainDeparture stabekk = new TrainDeparture(2, 1, "L3", "Bygdøy",
        LocalTime.of(22, 30), LocalTime.of(0, 2));

    TrainDeparture[] expected = {oslo, stabekk};
    
    testRegister.addDeparture(oslo);
    testRegister.addDeparture(stabekk);

    assertArrayEquals(expected, testRegister.searchByDestination("Bygdøy"));
  }
  
  @Test
  void testSortedListOfDepartures() {

    TrainDeparture haslum = new TrainDeparture(4, 2, "L3", "Bygdøy",
        LocalTime.of(23, 40), LocalTime.of(0, 0));

    TrainDeparture stabekk = new TrainDeparture(3, 1, "L3", "Bygdøy",
        LocalTime.of(22, 31), LocalTime.of(0, 0));
    
    testRegister.addDeparture(haslum);
    testRegister.addDeparture(stabekk);
    testRegister.addDeparture(oslo);
    
    TrainDeparture[] expected = {oslo, stabekk, haslum};

    assertArrayEquals(expected, testRegister.sortedDepartureList());
  }
  
  @Test
  void departuresAfterTimeLength() {
    
    testRegister.addDeparture(oslo);
    testRegister.addDeparture(tromso);

    testRegister.departuresAfterTime(LocalTime.of(22, 45));
    
    assertEquals(1, testRegister.getListOfDepartures().length);
  }

  @Test
  void departuresAfterTime() {
    TrainDeparture haslum = new TrainDeparture(4, 2, "L3", "Bygdøy",
        LocalTime.of(8, 40), LocalTime.of(0, 0));
    
    testRegister.addDeparture(haslum);
    testRegister.addDeparture(oslo);
    testRegister.addDeparture(tromso);
    
    Arrays.stream(testRegister.getListOfDepartures()).forEach(System.out::println);
   
    testRegister.departuresAfterTime(LocalTime.of(22, 40));
    
    TrainDeparture[] expected = {tromso};
    
    assertArrayEquals(expected, testRegister.getListOfDepartures());
    assertEquals(expected[0], testRegister.getListOfDepartures()[0]);
  }
  
}

