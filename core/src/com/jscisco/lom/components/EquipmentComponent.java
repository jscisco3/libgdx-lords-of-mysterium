package com.jscisco.lom.components;

import com.artemis.Component;
import com.jscisco.lom.components.model.EquipmentSlot;
import com.jscisco.lom.components.model.EquipmentType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EquipmentComponent extends Component {

    public List<EquipmentSlot> equipment = new ArrayList<>();

    public List<EquipmentSlot> getSlotsByType(EquipmentType type) {
        return equipment.stream()
                .filter(slot -> slot.getType() == type)
                .collect(Collectors.toList());
    }

}
