package com.jscisco.lom.raws;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class SpawnTableEntry {
    public String name;
    public int weight;
    public int minimumDepth;
    public int maximumDepth;
    public boolean addMapDepthToWeight = false;
}
