package lab1;

import java.awt.*;

public class Scania extends Car {

    private int angle;

    public Scania(){
        super(2, 80, Color.gray, "Scania");
    }

    private void rampUp(){
        if(getCurrentSpeed() == 0 ){
            angle++;
            angle = Math.min(angle, 70);
        }
    }

    private void rampDown() {
        if (getCurrentSpeed() == 0) {
            angle--;
            angle = Math.max(angle, 0);
        }
    }

    public int getRampAngle(){ return angle;}

    public double speedFactor(){
        return enginePower * 0.01;
    }

}


