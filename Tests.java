package lab1;

import static org.junit.Assert.*;
import org.junit.Test;

public class Tests {

    private Volvo240 volvo = new Volvo240();
    private Saab95 saab95 = new Saab95();
    private Scania scania = new Scania();
    private Carcarrier carcarrier = new Carcarrier();
    private Mechanic<Volvo240> volvoMechanic = new Mechanic<>(2);
    private Mechanic<Car> generalMechanic = new Mechanic<>(10);
    private Volvo240 black = new Volvo240();
    private Saab95 white = new Saab95();


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

    @Test
    public void testCarcarrierRampState() {
        carcarrier.stopEngine();
        carcarrier.rampUp();
        assertTrue("true", carcarrier.getRampState());
        carcarrier.rampDown();
        assertFalse("false", carcarrier.getRampState());
        carcarrier.startEngine();
        carcarrier.rampUp();
        assertFalse("false", carcarrier.getRampState());
    }

    @Test
    public void testLoadRampState() {
        carcarrier.rampUp();
        carcarrier.loadCar(volvo);
        for (int i = 0; i < 5; i++) {
            assertEquals(null, carcarrier.getLoad()[i]);
        }
    }

    @Test
    public void testLoadLoadable() {
        carcarrier.loadCar(carcarrier);
        for (int i = 0; i < 5; i++) {
            assertEquals(null, carcarrier.getLoad()[i]);
        }
    }

    @Test
    public void testLoadSpeed() {
        carcarrier.startEngine();
        carcarrier.loadCar(volvo);
        for (int i = 0; i < 5; i++) {
            assertEquals(null, carcarrier.getLoad()[i]);
        }
    }

    @Test
    public void testLoadXYPos() {
        carcarrier.setYPos(100);
        carcarrier.loadCar(volvo);
        carcarrier.setYPos(0);
        for (int i = 0; i < 5; i++) {
            assertEquals(null, carcarrier.getLoad()[i]);
        }

        carcarrier.setYPos(0);
        carcarrier.setXPos(100);
        carcarrier.loadCar(volvo);

        for (int i = 0; i < 5; i++) {
            assertEquals(null, carcarrier.getLoad()[i]);
        }
    }

    @Test
    public void testLoadCar() {
        carcarrier.loadCar(volvo);
        assertEquals(volvo, carcarrier.getLoad()[0]);
    }

    @Test
    public void testUnLoadRampState() {

        carcarrier.stopEngine();
        carcarrier.loadCar(volvo);
        carcarrier.loadCar(scania);


        boolean volvoLoaded = false;
        boolean scaniaLoaded = false;

        for (int i = 0; i < carcarrier.getLoad().length; i++) {

            if (carcarrier.getLoad()[i] == volvo){volvoLoaded = true;}
            if (carcarrier.getLoad()[i] == scania){scaniaLoaded = true; }

        }

        assertTrue(volvoLoaded);
        assertTrue(scaniaLoaded);

        carcarrier.unloadCar();


        int count = 0;
        for (int i = 0; i < carcarrier.getLoad().length; i++) {

            if (carcarrier.getLoad()[i] != null){
                count++;
            }
        }
        assertEquals(1,count);
    }


    @Test
    public void speedFactorTest(){assertEqual(8,carcarrier.speedFactor());}

    @Test
    public void TestIsLoadable(){assertFalse("false", carcarrier.isLoadable());}

    @Test
    public void TestCarsOnCarrier(){

        carcarrier.stopEngine();
        carcarrier.loadCar(volvo);
        carcarrier.loadCar(scania);

        carcarrier.gas(1);
        carcarrier.move();
        assertEqual(0.8,carcarrier.getYPos());

        for (int i = 0; i < 2; i++) {assertEqual(0.8,carcarrier.getLoad()[i].getYPos());}

        carcarrier.turnRight();
        carcarrier.move();
        carcarrier.gas(1);
        assertEqual(1.6,carcarrier.getXPos());
        for (int i = 0; i < 2; i++) {assertEqual(1.6,carcarrier.getLoad()[i].getxPos());}



    }

    @Test
    public void testStartEngine(){

        carcarrier.startEngine();
        assertEqual(0.1, carcarrier.getCurrentSpeed());

        carcarrier.stopEngine();
        carcarrier.rampUp();
        carcarrier.startEngine();
        assertEqual(0, carcarrier.getCurrentSpeed());

    }

    @Test
    public void MechanicAddsCar(){

        for(i = 0; i < 15; i++){
            volvoMechanic.addCar(black);
            //volvoMechanic.addCar(white);

        }

        assertEqual(10 ,volvoMechanic.getCars().size());




    }





}
