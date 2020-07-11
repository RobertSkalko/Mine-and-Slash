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

public class HealthNecklace extends BaseCurio {
    public static GearItemSlot INSTANCE = new HealthNecklace();

    private HealthNecklace() {

    }

    @Override
    public List<StatModifier> BaseStats() {
        return Arrays.asList(new StatModifier(2, 6, Health.getInstance(), ModType.FLAT));
    }

    @Override
    public List<StatModifier> ImplicitStats() {
        return Arrays.asList();
    }

    @Override
    public PlayStyle getPlayStyle() {
        return PlayStyle.NONE;
    }

    @Override
    public String GUID() {
        return "life_necklace";
    }

    @Override
    public SlotFamily family() {
        return SlotFamily.Jewelry;
    }

    @Override
    public List<SlotTag> getTags() {
        return Arrays.asList(SlotTag.Necklace);
    }

    @Override
    public Item getItem() {
        return ModItems.HEALTH_NECKLACE.get();
    }

    @Override
    public String locNameForLangFile() {
        return "Life Necklace";
    }
}
