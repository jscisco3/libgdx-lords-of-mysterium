package com.jscisco.lom.domain;

/**
 * Graphic representation of something
 */
public class Glyph extends ValueObject<String> {

    private Glyph(String glyph) {
        this.value = glyph;
    }

    public static Glyph of(String glyph) {
        return new Glyph(glyph);
    }

}
