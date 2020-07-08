package com.robertx22.mine_and_slash.loot.blueprints;

import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.GearItemEnum;

public class BlueprintUtils {

    public static GearBlueprint randomGearBlueprint(int lvl, int tier) {

        GearItemEnum type = GearItemEnum.random();

        if (type == GearItemEnum.NORMAL) {
            return new GearBlueprint(lvl);
        }
        if (type == GearItemEnum.UNIQUE) {
            return new UniqueGearBlueprint(lvl, tier);
        }

        return null;

    }
}
