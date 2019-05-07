package com.jscisco.lom.attributes;

public class FieldOfView {

    private double[][] fov;
    private double radius;

    public FieldOfView(double radius) {
        this.radius = radius;
    }

    public void setFov(double[][] fov) {
        this.fov = fov;
    }

    public double[][] getFov() {
        return fov;
    }

    public double getRadius() {
        return radius;
    }
}
