package com.robertx22.mine_and_slash.database.gearitemslots.cloth;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.armor.BaseBoots;
import com.robertx22.mine_and_slash.database.stats.types.resources.MagicShield;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModItems;
import com.robertx22.mine_and_slash.uncommon.enumclasses.ModType;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.List;

public class SilkPants extends BaseBoots {
    public static GearItemSlot INSTANCE = new SilkPants();

    private SilkPants() {

    }

    @Override
    public List<StatModifier> BaseStats() {
        return Arrays.asList(
            new StatModifier(4, 12, MagicShield.getInstance(), ModType.FLAT)
        );
    }

    @Override
    public List<StatModifier> ImplicitStats() {
        return Arrays.asList();
    }

    @Override
    public List<SlotTag> getTags() {
        return Arrays.asList(SlotTag.Cloth, SlotTag.Pants);
    }

    @Override
    public Item getItem() {
        return ModItems.SILK_PANTS.get();
    }

    @Override
    public String GUID() {
        return "silk_pants";
    }

    @Override
    public PlayStyle getPlayStyle() {
        return PlayStyle.INT;
    }

    @Override
    public String locNameForLangFile() {
        return "Silk Pants";
    }
}

