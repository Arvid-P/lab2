package lab1;

import static org.junit.Assert.*;
import org.junit.Test;

public class Tests {

    private Volvo240 volvo = new Volvo240();
    private Saab95 saab95 = new Saab95();
    private Scania scania = new Scania();
    private Carcarrier carcarrier = new Carcarrier();

    @Test
    public void testScaniaRampAngle() {
        scania.setRampDown();
        assertEquals(0, scania.getRampAngle());
        for (int i = 0; i < 100; i++) {
            scania.setRampUp();
        }
        assertEquals(70, scania.getRampAngle());
    }
    @Test
    public void testScaniaRampSpeed() {
        scania.startEngine();
        scania.setRampUp();
        assertEquals(0, scania.getRampAngle());
        scania.setRampDown();
        assertEquals(0, scania.getRampAngle());

        scania.stopEngine();
        scania.setRampUp();
        assertEquals(1, scania.getRampAngle());
        scania.startEngine();
        assertEquals(0, scania.getCurrentSpeed(), 0);
    }


    public void testCarcarrierRampState(){


        carcarrier.stopEngine();
        carcarrier.rampUp();
        assertTrue("true", carcarrier.getRampState());





    }
}
