package com.robertx22.mine_and_slash.loot.blueprints;

import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.GearItemEnum;

public class BlueprintUtils {

    public static GearBlueprint randomGearBlueprint(int tier) {

        GearItemEnum type = GearItemEnum.random();

        if (type == GearItemEnum.NORMAL) {
            return new GearBlueprint();
        }
        if (type == GearItemEnum.UNIQUE) {
            return new UniqueGearBlueprint(tier);
        }

        return null;

    }
}
