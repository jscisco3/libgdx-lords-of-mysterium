package com.jscisco.lom.entity;

import java.util.ArrayList;
import java.util.List;

public class AbstractStat {

    private int baseValue = 0;
    private List<StatBonus> bonuses = new ArrayList<>();

    public AbstractStat() {
    }

    public AbstractStat(int baseValue) {
        this.baseValue = baseValue;
    }

    public int value() {
        return baseValue + getBonusValue();
    }

    public int getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(int baseValue) {
        this.baseValue = baseValue;
    }

    public int getBonusValue() {
        return bonuses.stream()
                .map(StatBonus::getValue)
                .reduce(0, Integer::sum);
    }

    public void applyBonus(StatBonus bonus) {
        this.bonuses.add(bonus);
    }

    public void removeBonus(StatBonus bonus) {
        this.bonuses.remove(bonus);
    }
}
