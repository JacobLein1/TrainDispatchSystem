import org.junit.jupiter.api.Test;

public class TestTrain {
    Train train= new Train();
    @Test
    void testTrack(){
        if(train.getLine>0){
        return true;            
        }
        return false;
    }
    
}
