package com.jscisco.lom.content;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents the base definition of an entity. It is used to manage basic content and is used
 * when new entities are generated at run time
 */
@NoArgsConstructor
@Getter
@Setter
public class EntityDefinition {
    String name;
    /**
     * This string corresponds to an AI type, such as Wander or HunterSeeker
     */
    String ai; // TODO: Make smarter later
    /**
     * The glyph is a string that let's us look up the correct texture in the atlas.
     */
    String glyph;
    /******
     * TODO:
     *  Attributes
     *  Spawn table
     *  Loot table?
     *****/
}
