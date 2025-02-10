package lab1;

import java.awt.*;

public class carryCar extends Car implements NotLoadable {

    private boolean rampState;
    private Car[] load = new Car[5];

    public carryCar(){
        super(2, 80, Color.gray, "Scania");
    }

    private void rampUp(){
        if(getCurrentSpeed() == 0 ){
            rampState = true;
        }
    }

    private void rampDown() {
        if (getCurrentSpeed() == 0) {
            rampState = false;
        }
    }

    private void loadCar(Car car) {
        if  (!rampState && canLoad) {
            if ((car.getYPos() - this.getYPos()) < 5 && ((car.getXPos() - this.getXPos()) < 5)) {
                for (int i = 0; i < 5; i++) {
                    if (load[i] == null) {
                        load[i] = car;
                        break;
                    }
                }
            }
        }
    }

    private void unloadCar() {
        if  (!rampState) {
            for (int i = 4; i >= 0; i--) {
                if (load[i] != null) {
                    load[i].setXPos(this.getXPos() + 3);
                    load[i].setYPos(this.getYPos() + 3);
                    load[i] = null;
                    break;
                }
            }
        }
    }

    public double speedFactor(){
        return enginePower * 0.01;
    }


}
