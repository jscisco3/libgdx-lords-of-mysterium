package com.jscisco.lom.domain.cellular_automata;

public abstract class RuleSet {

    public abstract Cell apply(Cell cell, Cell[] neighborhood);
}
