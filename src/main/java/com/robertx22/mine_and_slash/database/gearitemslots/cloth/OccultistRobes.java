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

public class OccultistRobes extends BaseBoots {
    public static GearItemSlot INSTANCE = new OccultistRobes();

    private OccultistRobes() {

    }

    @Override
    public List<StatModifier> BaseStats() {
        return Arrays.asList(
            new StatModifier(5, 15, MagicShield.getInstance(), ModType.FLAT)
        );
    }

    @Override
    public List<StatModifier> ImplicitStats() {
        return Arrays.asList();
    }

    @Override
    public List<SlotTag> getTags() {
        return Arrays.asList(SlotTag.Cloth, SlotTag.Chest);
    }

    @Override
    public Item getItem() {
        return ModItems.OCCULTIST_ROBES.get();
    }

    @Override
    public String GUID() {
        return "occultist_robes";
    }

    @Override
    public PlayStyle getPlayStyle() {
        return PlayStyle.INT;
    }

    @Override
    public String locNameForLangFile() {
        return "Occultist Robes";
    }
}

