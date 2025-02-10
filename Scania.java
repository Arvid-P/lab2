package lab1;

import java.awt.*;

public class Scania extends Car implements Biltransports {

    private int angle;


    public Scania(){
        super(2, 80, Color.gray, "Scania");
    }


    private void flakUp(){

        if(getCurrentSpeed() == 0 ){
            angle++;
            angle = Math.min(angle, 70);
        }


    }

    private void flankDown() {
        if (getCurrentSpeed() == 0) {
            angle--;
            angle = Math.max(angle, 0);

        }

    }


    private int getFlankGrade(){ return angle;}

    public double speedFactor(){
        return enginePower * 0.01;
    }






}


