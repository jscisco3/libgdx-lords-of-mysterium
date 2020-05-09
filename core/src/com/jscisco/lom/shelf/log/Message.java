package com.jscisco.lom.shelf.log;

import java.util.ArrayList;
import java.util.List;

public class Message {
    private List<MessageElement> elements = new ArrayList<>();

    public Message() {
    }

    public Message withElement(MessageElement element) {
        this.elements.add(element);
        return this;
    }

    public List<MessageElement> getElements() {
        return elements;
    }
}
