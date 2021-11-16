package com.jscisco.lom.content;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents the base definition of any given item. It is primarily used for
 * managing content. It may be used in the following way:
 *      Managing content of items
 *      Generation of items in gameplay
 */
@NoArgsConstructor
@Getter
@Setter
public class ItemDefinition {
    String name; // The base name of the item
    String glyph; // This is used to grab the asset from the texture atlas
    String itemType; // TODO: Is this necessary?

    // Potential includes
//    String rarity; // Possibly int? How to represent where items can fall? Some loot table maybe instead
    // String material; // Do all items have a material? Maybe yes
    //
}
