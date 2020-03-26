package com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases;

public interface IGearPart {

    public enum Part {
        AFFIX, PRIMARY_STATS, SECONDARY_STATS, UNIQUE_STATS, OTHER;
    }

    Part getPart();

}
