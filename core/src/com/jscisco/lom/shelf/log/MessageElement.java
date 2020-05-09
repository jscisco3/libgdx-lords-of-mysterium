package com.jscisco.lom.shelf.log;

import com.badlogic.gdx.graphics.Color;

public class MessageElement {
    private String text;
    private Color color;

    public MessageElement(String text, Color color) {
        this.text = text;
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public Color getColor() {
        return color;
    }

    public String toString() {
        return this.getText();
    }

}
