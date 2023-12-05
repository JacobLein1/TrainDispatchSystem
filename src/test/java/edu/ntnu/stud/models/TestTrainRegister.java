package edu.ntnu.stud.models;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import edu.ntnu.stud.Utils.Printer;
import java.time.LocalTime;
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

  @Test
  void testAddNewTrainDeparture() {
    
    TrainDeparture oslo = new TrainDeparture(1, 1, "L3",
         "Bygdøy", LocalTime.of(22, 30), LocalTime.of(0, 3));
    
    TrainDeparture tromso = new TrainDeparture(2, 1, "L3",
         "Bygdøy", LocalTime.of(22, 46), LocalTime.of(0, 2));

    testRegister.addDeparture(oslo);
    testRegister.addDeparture(tromso);

    assertEquals(2, testRegister.getListOfDepartures().length);
  }

  @Test
    void testAddNewDepartureInfo() {
    TrainDeparture oslo = new TrainDeparture(1, 1, "L3",
        "Bygdøy", LocalTime.of(22, 30), LocalTime.of(0, 2));
    
    testRegister.addDeparture(oslo);
    
    assertEquals(1, testRegister.getListOfDepartures()[0].getTrainNumber());
  }

  @Test()
  void testAddNewDepartureInfo_negative() {
    LocalTime input = LocalTime.parse("22:46"); 
    TrainDeparture tromso = new TrainDeparture(1, 1, "L3",
         "Bygdøy", input, LocalTime.of(0, 2));
    TrainDeparture oslo = new TrainDeparture(1, 1, "L3",
         "Bygdøy", input, LocalTime.of(0, 2));

    assertThrowsExactly(IllegalArgumentException.class, () -> {
      testRegister.addDeparture(tromso);
      testRegister.addDeparture(oslo);
    });

    //Fikse sjekk på at lengden på listen stemmer
  }

  @Test
  void testRemoveDeparture() {

    TrainDeparture tromso = new TrainDeparture(1, 1, "L3",
         "Bygdøy", LocalTime.of(10, 0), LocalTime.of(0, 2));
    TrainDeparture stabekk = new TrainDeparture(2, 3, "L1",
         "Ovre Hovik", LocalTime.of(10, 0), LocalTime.of(12, 0));
    testRegister.addDeparture(tromso);
    testRegister.addDeparture(stabekk);
    testRegister.removeDeparture(1);
    assertEquals(1, testRegister.getListOfDepartures().length);
  }

  @Test
  void testwantedDeparture() {
    TrainDeparture oslo = new TrainDeparture(1, 1, "L3",
        "Bygdøy", LocalTime.of(22, 30), LocalTime.of(0, 2));
    TrainDeparture tromso = new TrainDeparture(2, 1, "L3",
         "Bygdøy", LocalTime.of(22, 46), LocalTime.of(0, 2));
    testRegister.addDeparture(oslo);
    testRegister.addDeparture(tromso);

    assertEquals(oslo, testRegister.wantedDeparture(1));
  }
  
  @Test
  void testWantedDepartureNotFound() {
    TrainDeparture oslo = new TrainDeparture(1, 1, "L3", "Bygdøy",
        LocalTime.of(22, 30), LocalTime.of(0, 2));
    testRegister.addDeparture(oslo);
    assertEquals(null, testRegister.wantedDeparture(2));
  }

  @Test
  void testGetListOfDepartures() {
    
    TrainDeparture oslo = new TrainDeparture(1, 1, "L3", "Bygdøy",
         LocalTime.of(22, 30), LocalTime.of(0, 2));
    testRegister.addDeparture(oslo);
    
    TrainDeparture tromso = new TrainDeparture(2, 3, "L1", "Tromso",
         LocalTime.of(13, 00), LocalTime.of(0, 3));
    testRegister.addDeparture(tromso);

    TrainDeparture[] expected = {oslo, tromso};

    assertArrayEquals(expected, testRegister.getListOfDepartures());
    
  }
  
  @Test
  void testDeparturesToWantedDestination() {

    TrainDeparture oslo = new TrainDeparture(1, 1, "L3", "Bygdøy",
        LocalTime.of(22, 30), LocalTime.of(0, 2));

    TrainDeparture stabekk = new TrainDeparture(2, 1, "L3", "Bygdøy",
        LocalTime.of(22, 30), LocalTime.of(0, 2));

    TrainDeparture[] expected = {oslo, stabekk};
    
    testRegister.addDeparture(oslo);
    testRegister.addDeparture(stabekk);

    assertArrayEquals(expected, testRegister.departuresToWantedDestination("Bygdøy"));
  }
  
  @Test
  void testSortedListOfDepartures() {

    TrainDeparture haslum = new TrainDeparture(3, 2, "L3", "Bygdøy",
        LocalTime.of(23, 40), LocalTime.of(0, 0));
    TrainDeparture oslo = new TrainDeparture(1, 1, "L3", "Bygdøy",
        LocalTime.of(22, 28), LocalTime.of(0, 3));

    TrainDeparture stabekk = new TrainDeparture(2, 1, "L3", "Bygdøy",
        LocalTime.of(22, 30), LocalTime.of(0, 0));
    
    testRegister.addDeparture(haslum);
    testRegister.addDeparture(stabekk);
    testRegister.addDeparture(oslo);
    
    TrainDeparture[] expected = {oslo, stabekk, haslum};

    assertArrayEquals(expected, testRegister.sortedDepartureList());
  }
  
  @Test
  void testDepartureToString() {

    TrainDeparture haslum = new TrainDeparture(3, 9, "L2", "Haslum",
        LocalTime.of(23, 40), LocalTime.of(0, 0));
    TrainDeparture oslo = new TrainDeparture(1, 0, "L3", "Oslo",
        LocalTime.of(22, 28), LocalTime.of(0, 1));
    TrainDeparture asker = new TrainDeparture(2, 1, "L3", "Asker",
        LocalTime.of(22, 28), LocalTime.of(0, 3));
    TrainDeparture bergen = new TrainDeparture(4, 0, "L3", "Bergen",
        LocalTime.of(22, 28), LocalTime.of(0, 0));    
    
    testRegister.addDeparture(haslum);
    testRegister.addDeparture(oslo);
    testRegister.addDeparture(asker);
    testRegister.addDeparture(bergen);   

    Printer.printDepartureListStart();

    System.out.println(haslum);
    System.out.println(oslo);
    System.out.println(asker);
    System.out.println(bergen);

  
    
    testRegister.printSortedList();

    String haslumExpected =
        "|      23:40           L2        3             Haslum                9  |"; 
    assertEquals(haslumExpected, haslum.toString());

    String osloExpected = 
        "|      22:28           L3        1               Oslo    00:01       0  |";
    assertEquals(osloExpected, oslo.toString());

    String askerExpected =
        "|      22:28           L3        2              Asker    00:03       1  |";
    assertEquals(askerExpected, asker.toString());

    String bergenExpected = 
        "|      22:28           L3        4             Bergen                0  |";
    assertEquals(bergenExpected, bergen.toString());
    
    
    
    
        
      
  }
}

