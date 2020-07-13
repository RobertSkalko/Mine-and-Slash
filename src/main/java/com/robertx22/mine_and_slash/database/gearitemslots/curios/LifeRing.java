package com.robertx22.mine_and_slash.database.gearitemslots.curios;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseCurio;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.stats.types.resources.Health;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModItems;
import com.robertx22.mine_and_slash.uncommon.enumclasses.ModType;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.List;

public class LifeRing extends BaseCurio {

    public static GearItemSlot INSTANCE = new LifeRing();

    private LifeRing() {

    }

    @Override
    public PlayStyle getPlayStyle() {
        return PlayStyle.NONE;
    }

    @Override
    public String GUID() {
        return "life_ring";
    }

    @Override
    public List<StatModifier> ImplicitStats() {
        return Arrays.asList();
    }

    @Override
    public List<StatModifier> BaseStats() {
        return Arrays.asList(new StatModifier(1, 5, Health.getInstance(), ModType.FLAT));
    }

    @Override
    public Item getItem() {
        return ModItems.LIFE_RING.get();
    }

    @Override
    public int Weight() {
        return super.Weight() * 2;
    }

    @Override
    public SlotFamily family() {
        return SlotFamily.Jewelry;
    }

    @Override
    public List<SlotTag> getTags() {
        return Arrays.asList(SlotTag.Ring);
    }

    @Override
    public String locNameForLangFile() {
        return "Life Ring";
    }
}
