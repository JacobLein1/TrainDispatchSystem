package edu.ntnu.stud;

import edu.ntnu.stud.Models.TrainDeparture;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import edu.ntnu.stud.Models.TrainRegister;
import edu.ntnu.stud.Utils.Print;

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
public class TestTrainRegister {
  TrainRegister testRegister = new TrainRegister();
    

  @Test
    public void testAddNewTrainDeparture() {
    
    TrainDeparture oslo = new TrainDeparture(1, 1, "L3",
         "Bygdøy", LocalTime.of(22, 30), LocalTime.of(0, 3));
    
    TrainDeparture tromso = new TrainDeparture(2, 1, "L3",
         "Bygdøy", LocalTime.of(22, 46), LocalTime.of(0, 2));

    testRegister.addNewDeparture(oslo);
    testRegister.addNewDeparture(tromso);

    assertEquals(2, testRegister.getListOfDepartures().length);
  }

  @Test
    public void testAddNewDepartureInfo() {
    TrainDeparture oslo = new TrainDeparture(1, 1, "L3",
        "Bygdøy", LocalTime.of(22, 30), LocalTime.of(0, 2));
    
    testRegister.addNewDeparture(oslo);
    
    assertEquals(1, testRegister.getListOfDepartures()[0].getTrainNumber());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNewDepartureInfo_negative() {
    LocalTime input = LocalTime.parse("22:46"); 
    TrainDeparture tromso = new TrainDeparture(1, 1, "L3",
         "Bygdøy", input, LocalTime.of(0, 2));
    TrainDeparture oslo = new TrainDeparture(1, 1, "L3",
         "Bygdøy", input, LocalTime.of(0, 2));

    testRegister.addNewDeparture(tromso);
    testRegister.addNewDeparture(oslo);

    //Fikse sjekk på at lengden på listen stemmer
  }

  @Test
  public void testRemoveDeparture() {

    TrainDeparture tromso = new TrainDeparture(1, 1, "L3",
         "Bygdøy", LocalTime.of(10, 0), LocalTime.of(0, 2));
    TrainDeparture stabekk = new TrainDeparture(2, 3, "L1",
         "Ovre Hovik", LocalTime.of(10, 0), LocalTime.of(12, 0));
    testRegister.addNewDeparture(tromso);
    testRegister.addNewDeparture(stabekk);
    testRegister.removeDeparture(1);
    assertEquals(1, testRegister.getListOfDepartures().length);
  }

  @Test
  public void testwantedDeparture() {
    TrainDeparture oslo = new TrainDeparture(1, 1, "L3",
        "Bygdøy", LocalTime.of(22, 30), LocalTime.of(0, 2));
    TrainDeparture tromso = new TrainDeparture(2, 1, "L3",
         "Bygdøy", LocalTime.of(22, 46), LocalTime.of(0, 2));
    testRegister.addNewDeparture(oslo);
    testRegister.addNewDeparture(tromso);

    assertEquals(oslo, testRegister.wantedDeparture(1));
  }
  
  @Test
  public void testWantedDepartureNotFound() {
    TrainDeparture oslo = new TrainDeparture(1, 1, "L3", "Bygdøy",
        LocalTime.of(22, 30), LocalTime.of(0, 2));
    testRegister.addNewDeparture(oslo);
    assertEquals(null, testRegister.wantedDeparture(2));
  }

  @Test
  public void testGetListOfDepartures() {
    
    TrainDeparture oslo = new TrainDeparture(1, 1, "L3", "Bygdøy",
         LocalTime.of(22, 30), LocalTime.of(0, 2));
    testRegister.addNewDeparture(oslo);
    
    TrainDeparture tromso = new TrainDeparture(2, 3, "L1", "Tromso",
         LocalTime.of(13, 00), LocalTime.of(0, 3));
    testRegister.addNewDeparture(tromso);

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
    
    testRegister.addNewDeparture(oslo);
    testRegister.addNewDeparture(stabekk);

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
    
    testRegister.addNewDeparture(haslum);
    testRegister.addNewDeparture(stabekk);
    testRegister.addNewDeparture(oslo);
    
    TrainDeparture[] expected = {oslo, stabekk, haslum};

    assertArrayEquals(expected, testRegister.sortedDepartureList());
  }
  
  @Test
  public void testDepartureToString() {

    TrainDeparture haslum = new TrainDeparture(3, 15, "L2", "Haslum",
        LocalTime.of(23, 40), LocalTime.of(0, 0));
    TrainDeparture oslo = new TrainDeparture(1, 0, "L3", "Oslo",
        LocalTime.of(22, 28), LocalTime.of(0, 1));
    TrainDeparture asker = new TrainDeparture(2, 1, "L3", "Asker",
        LocalTime.of(22, 28), LocalTime.of(0, 3));
    TrainDeparture bergen = new TrainDeparture(4, 0, "L3", "Bergen",
        LocalTime.of(22, 28), LocalTime.of(0, 0));    
    

    Print.printDepartureListStart(testRegister);

    System.out.println(haslum.departureToString());
    System.out.println(oslo.departureToString());
    System.out.println(asker.departureToString());
    System.out.println(bergen.departureToString());

    testRegister.addNewDeparture(haslum);
    testRegister.addNewDeparture(oslo);
    testRegister.addNewDeparture(asker);
    testRegister.addNewDeparture(bergen);
    
    System.out.println(testRegister.sortedDepartureList());

    String haslumExpected =
         "|      23:40          L2        3              Haslum      15           |"; 

    String osloExpected = 
        "|      22:28          L3        1                Oslo              00:01|";

    String askerExpected =
        "|      22:28          L3        2               Asker       1      00:03|";
        
    String bergenExpected = 
        "|      22:28          L3        4              Bergen                   |";

    assertEquals(haslumExpected, haslum.departureToString());
    assertEquals(osloExpected, oslo.departureToString());
    assertEquals(askerExpected, asker.departureToString());
    assertEquals(bergenExpected, bergen.departureToString());
        
      
  }
}

