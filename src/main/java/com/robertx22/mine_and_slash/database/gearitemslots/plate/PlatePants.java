package com.robertx22.mine_and_slash.database.gearitemslots.plate;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.armor.BasePants;
import com.robertx22.mine_and_slash.database.stats.types.defense.Armor;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModItems;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.List;

public class PlatePants extends BasePants {
    public static GearItemSlot INSTANCE = new PlatePants();

    private PlatePants() {

    }

    @Override
    public List<StatModifier> BaseStats() {
        return Arrays.asList(
            new StatModifier(20, 60, Armor.getInstance(), StatModTypes.Flat)
        );
    }

    @Override
    public List<StatModifier> ImplicitStats() {
        return Arrays.asList();
    }

    @Override
    public List<SlotTag> getTags() {
        return Arrays.asList(SlotTag.Plate, SlotTag.Pants);
    }

    @Override
    public Item getItem() {
        return ModItems.PLATE_PANTS.get();
    }

    @Override
    public String GUID() {
        return "plate_pants";
    }

    @Override
    public PlayStyle getPlayStyle() {
        return PlayStyle.WARRIOR;
    }

    @Override
    public String locNameForLangFile() {
        return "Plate Greaves";
    }
}
