package lab2;

import static org.junit.Assert.*;
import org.junit.Test;

public class Tests {

    private Volvo240 volvo = new Volvo240();
    private Saab95 saab95 = new Saab95();
    private Scania scania = new Scania();
    private Carcarrier carcarrier = new Carcarrier();
    private Mechanic<Volvo240> volvoMechanic = new Mechanic<>(2);
    private Mechanic<Car> generalMechanic = new Mechanic<>(10);
    private Volvo240 blackVolvo = new Volvo240();
    private Saab95 whiteSaab = new Saab95();
    private Scania bigScania = new Scania();



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
        carcarrier.rampDown();
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
        carcarrier.loadCar(blackVolvo);
        carcarrier.loadCar(saab95);
        carcarrier.loadCar(scania);
        carcarrier.loadCar(bigScania);
        carcarrier.loadCar(whiteSaab);
        assertEquals(volvo, carcarrier.getLoad()[0]);
        assertEquals(blackVolvo, carcarrier.getLoad()[1]);
        assertEquals(saab95, carcarrier.getLoad()[2]);
        assertEquals(scania, carcarrier.getLoad()[3]);
        assertEquals(bigScania, carcarrier.getLoad()[4]);
    }

    @Test
    public void testUnLoadRampState() {
        carcarrier.stopEngine();
        carcarrier.loadCar(volvo);
        carcarrier.loadCar(scania);

        carcarrier.rampUp();
        carcarrier.unloadCar();
        assertEquals(volvo, carcarrier.getLoad()[0]);
        assertEquals(scania, carcarrier.getLoad()[1]);

        carcarrier.rampDown();
        carcarrier.unloadCar();
    }

    @Test
    public void testUnloadCurrentSpeed(){
        carcarrier.stopEngine();
        carcarrier.loadCar(volvo);
        carcarrier.loadCar(scania);

        carcarrier.startEngine();

        carcarrier.unloadCar();
        assertEquals(volvo, carcarrier.getLoad()[0]);
        assertEquals(scania, carcarrier.getLoad()[1]);
    }

    @Test
    public void testUnLoadCar() {
        carcarrier.loadCar(volvo);
        carcarrier.loadCar(scania);
        carcarrier.loadCar(blackVolvo);

        carcarrier.unloadCar();
        assertEquals(volvo, carcarrier.getLoad()[0]);
        assertEquals(scania, carcarrier.getLoad()[1]);
        assertEquals(null, carcarrier.getLoad()[2]);

        carcarrier.unloadCar();
        assertEquals(volvo, carcarrier.getLoad()[0]);
        assertEquals(null, carcarrier.getLoad()[1]);
        assertEquals(null, carcarrier.getLoad()[2]);

        carcarrier.unloadCar();
        assertEquals(null, carcarrier.getLoad()[0]);
        assertEquals(null, carcarrier.getLoad()[1]);
        assertEquals(null, carcarrier.getLoad()[2]);

        carcarrier.unloadCar();
    }

    @Test
    public void speedFactorTest(){
        assertEquals(0.8,carcarrier.speedFactor(),0);
        assertEquals(0.8,scania.speedFactor(),0);
    }

    @Test
    public void TestIsLoadable(){assertFalse("false", carcarrier.isLoadable());}

    @Test
    public void TestCarsOnCarrier(){
        carcarrier.stopEngine();
        carcarrier.loadCar(volvo);
        carcarrier.loadCar(scania);

        carcarrier.gas(1);
        carcarrier.move();
        assertEquals(0.8,carcarrier.getYPos(),0);

        for (int i = 0; i < 2; i++) {assertEquals(0.8,carcarrier.getLoad()[i].getYPos(),0);}

        carcarrier.turnRight();
        carcarrier.gas(1);
        carcarrier.move();
        assertEquals(1.6,carcarrier.getXPos(),0);
        for (int i = 0; i < 2; i++) {assertEquals(1.6,carcarrier.getLoad()[i].getXPos(),0);}
    }

    @Test
    public void testStartEngine(){
        carcarrier.startEngine();
        assertEquals(0.1, carcarrier.getCurrentSpeed(),0);

        carcarrier.stopEngine();
        carcarrier.rampUp();
        carcarrier.startEngine();
        assertEquals(0, carcarrier.getCurrentSpeed(),0);

    }

    @Test
    public void MechanicAddsCar(){
        for(int i = 0; i < 15; i++){
            generalMechanic.addCar(whiteSaab);
            generalMechanic.addCar(bigScania);
            volvoMechanic.addCar(blackVolvo);
            //volvoMechanic.addCar(whiteSaab);
        }

        assertEquals(10,generalMechanic.getCars().size());
        assertEquals(2 ,volvoMechanic.getCars().size());
    }


    @Test
    public void MechanicRemovesCar(){

        generalMechanic.addCar(whiteSaab);
        generalMechanic.addCar(bigScania);

        for(int i = 0; i < 10; i++){

            assertEquals(2,generalMechanic.getCars().size());

        }

        generalMechanic.removeCar(whiteSaab);

        assertEquals(1,generalMechanic.getCars().size());
        assertEquals(bigScania, generalMechanic.getCars().getFirst());



    }

}
