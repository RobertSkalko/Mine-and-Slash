package com.robertx22.mine_and_slash.database.gearitemslots.bases;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import top.theillusivec4.curios.api.CuriosAPI;

public abstract class BaseCurio extends GearItemSlot {

    @Override
    public EquipmentSlotType getVanillaSlotType() {
        return null;
    }

    @Override
    public int Weight() {
        return 750;
    }

    @Override
    public final boolean isGearOfThisType(Item item) { // TODO unsure if this works
        return CuriosAPI.getCurioTags(item)
            .stream()
            .anyMatch(x -> x.toString()
                .contains(GUID()));
    }
}
