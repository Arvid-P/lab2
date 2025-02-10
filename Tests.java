package lab1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import org.junit.jupiter.api.Test;

import java.awt.*;

// storing subclass objects
public class Tests {
    Volvo240 volvo = new Volvo240();
    Saab95 saab = new Saab95();

// Testing method getNrDoors with the cars predetermined amount of doors
    @Test
    void TestDoors() {
        assertEquals(2, saab.getNrDoors());
        assertEquals(4, volvo.getNrDoors());
    }
// Testing method getEnginePower with the cars predetermined engine power
    @Test
    void TestEngine() {
        assertEquals(125, saab.getEnginePower());
        assertEquals(100, volvo.getEnginePower());
    }
// Testing method getColor with the cars predetermined Color
    @Test
    void TestColor() {
        assertEquals(Color.red, saab.getColor());
        assertEquals(Color.black, volvo.getColor());

        saab.setColor(Color.green);
        volvo.setColor(Color.blue);

        assertEquals(Color.green, saab.getColor());
        assertEquals(Color.blue, volvo.getColor());
    }

    // Testing if the cars model is being stored in its variable
    @Test
    void TestModelName() {
        assertEquals("Saab95", saab.modelName);
        assertEquals("Volvo240", volvo.modelName);
    }
    // Testing the cars current speed if its being stored correctly
    @Test
    void TestEngineState() {
        assertEquals(0, saab.getCurrentSpeed());
        assertEquals(0, volvo.getCurrentSpeed());

        saab.startEngine();
        volvo.startEngine();

        assertEquals(0.1,  saab.getCurrentSpeed());
        assertEquals(0.1, volvo.getCurrentSpeed());

        saab.stopEngine();
        volvo.stopEngine();

        assertEquals(0, saab.getCurrentSpeed());
        assertEquals(0, volvo.getCurrentSpeed());

    }
// Testing the Saabs speed factor by calculating its value and then running the method
    @Test
    void TestSpeedFactorSaab() {
        assertEquals(1.25, saab.speedFactor());

        saab.setTurboOn();
        assertEquals(1.625, saab.speedFactor());

        saab.setTurboOff();
        assertEquals(1.25, saab.speedFactor());

    }
// Testing the Volvos speed factor by calculating its value and then running the method
    @Test
    void TestSpeedFactorVolvo() {
        assertEquals(1.25, volvo.speedFactor());
    }

// Testing if the car is turning to the right direction if we're calling turnleft() and turnright methods
    @Test
    void TestTurning() {
        saab.turnLeft();
        volvo.turnLeft();
        assertEquals("West", saab.getDirection());
        assertEquals("West", volvo.getDirection());

        saab.turnRight();
        saab.turnRight();
        volvo.turnRight();
        volvo.turnRight();
        assertEquals("East", saab.getDirection());
        assertEquals("East", volvo.getDirection());

    }
// testing the move method to see if the cars x and y position values are being adjusted correctly
    @Test
    void TestMove() {
        saab.startEngine();
        volvo.startEngine();
        saab.move();
        volvo.move();
        assertEquals(0.1, saab.getYPos());
        assertEquals(0.1, volvo.getYPos());

        saab.turnLeft();
        volvo.turnRight();
        saab.move();
        volvo.move();
        assertEquals(-0.1, saab.getXPos());
        assertEquals(0.1, volvo.getXPos());

        volvo.turnLeft();
        volvo.turnLeft();
        volvo.move();
        assertEquals(0, volvo.getXPos());

    }

// Testing the gas method to see if the cars can increase its speed without exceeding its enginepower
    @Test
    void TestGas() {
        volvo.gas(1);
        volvo.gas(-1);
        volvo.gas(2);
        saab.setTurboOn();
        saab.gas(1);
        saab.gas(-1);
        saab.gas(2);
        assertEquals(1.25, volvo.getCurrentSpeed());
        assertEquals(1.625, saab.getCurrentSpeed());

        for (int i = 0; i < 100; i++) {
            volvo.gas(1);
            saab.gas(1);
        }

        assertEquals(100, volvo.getCurrentSpeed());
        assertEquals(125, saab.getCurrentSpeed());
    }

//testing the brake method to see if cars current speed doesn't go below 0
    @Test
    void TestBrake() {
        volvo.brake(1);
        volvo.brake(-1);
        volvo.brake(2);
        saab.brake(1);
        saab.brake(-1);
        saab.brake(2);

        assertEquals(0, volvo.getCurrentSpeed());
        assertEquals(0, saab.getCurrentSpeed());

        for (int i = 0; i < 100; i++) {
            volvo.gas(1);
            saab.gas(1);
        }
        volvo.brake(1);
        saab.brake(1);

        assertEquals(98.75, volvo.getCurrentSpeed());
        assertEquals(123.75, saab.getCurrentSpeed());
    }
}
