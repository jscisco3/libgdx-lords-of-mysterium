package com.jscisco.lom.entity;

public class AbstractStat {

    private int baseValue = 0;
    private int bonusValue = 0;

    public AbstractStat() {
    }

    public AbstractStat(int baseValue) {
        this.baseValue = baseValue;
    }

    public int getValue() {
        return baseValue + bonusValue;
    }

    public int getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(int baseValue) {
        this.baseValue = baseValue;
    }

    public int getBonusValue() {
        return bonusValue;
    }

    public void setBonusValue(int bonusValue) {
        this.bonusValue = bonusValue;
    }
}
