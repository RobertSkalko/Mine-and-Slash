package com.robertx22.constants;

public class Stat {

    public String name;
    public int maxValue;
    public int minValue;
    public boolean isPercent;
    public String type;
    public int maxTotal;

    public Stat(String name, int minValue, int maxValue, boolean isPrecent, String type, int maxTotal) {

        this.name = name;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.isPercent = isPrecent;
        this.type = type;
        this.maxTotal = maxTotal;

    }

}
