package com.jscisco.lom.raws;

/**
 * The Raw representation of an entity.
 */
public class RawItem {
    // Name of the entity (e.g. `Goblin`)
    public String name;
    // Reference to the Key in the Assets Library.
    public String glyph;
    public int weight;
    public int value;
    public String category;
    public Consumable consumable = null;
    public Equippable equippable = null;
}
