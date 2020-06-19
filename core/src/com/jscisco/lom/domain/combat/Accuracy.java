package com.jscisco.lom.domain.combat;

public class Accuracy {
    int accuracy;

    private Accuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public static Accuracy of(int accuracy) {
        return new Accuracy(accuracy);
    }

    public int getAccuracy() {
        return accuracy;
    }
}
