package edu.ntnu.stud;
import org.junit.jupiter.api.Test;
import org.junit.Assert.assertEqual;
import org.junit.Assert.assertFalse;
import org.junit.Assert.assertTrue;

public class TestTrain {
    
    
    
    @Test
    void testTrack(){
    LocalTime input = LocalTime.parse("22:46");
    TrainDeparture oslo = new TrainDeparture(1,1,"L3","Bygdøy", input,2);
    AssertEqual()
    }
    
}
