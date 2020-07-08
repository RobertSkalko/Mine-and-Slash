package com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;

public interface IGearPart {

    public enum Part {
        AFFIX, PRIMARY_STATS, SECONDARY_STATS, UNIQUE_STATS, OTHER;
    }

    Part getPart();

    default MinMax getMinMax(GearItemData gear) {
        return gear.getRarity()
            .getStatPercentsFor(getPart());
    }

}
