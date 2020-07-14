package com.robertx22.mine_and_slash.database.gearitemslots.plate;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.armor.BasePants;
import com.robertx22.mine_and_slash.database.stats.types.defense.Armor;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModItems;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.StatRequirement;
import com.robertx22.mine_and_slash.uncommon.enumclasses.ModType;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.List;

public class IronLegguards extends BasePants {
    public static GearItemSlot INSTANCE = new IronLegguards();

    private IronLegguards() {

    }

    @Override
    public List<StatModifier> BaseStats() {
        return Arrays.asList(
            new StatModifier(20, 60, Armor.getInstance(), ModType.FLAT)
        );
    }

    @Override
    public StatRequirement getStatRequirements() {
        return new StatRequirement().strength(0.5F);
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
        return PlayStyle.STR;
    }

    @Override
    public String locNameForLangFile() {
        return "Iron Legguards";
    }
}
