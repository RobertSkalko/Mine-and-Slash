package com.robertx22.mine_and_slash.database.requirements;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;

public class GearRequestedFor {

    public GearItemSlot forSlot;
    public GearItemData gearData;

    public GearRequestedFor(GearItemSlot slot, GearItemData data) {
        this.forSlot = slot;
        this.gearData = data;
    }

    public GearRequestedFor(GearItemData data) {
        this.forSlot = data.GetBaseGearType();
        this.gearData = data;
    }

    public GearRequestedFor(GearItemSlot slot) {
        this.forSlot = slot;
        this.gearData = new GearItemData();
    }

}
