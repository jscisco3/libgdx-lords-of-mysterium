package com.jscisco.lom.components;

import com.artemis.Component;
import com.jscisco.lom.util.Position3D;

/**
 * This is used by the movement system. The direction is added to the current position to make the entity move.
 */
public class MovementComponent extends Component {

    public Position3D direction;

}
