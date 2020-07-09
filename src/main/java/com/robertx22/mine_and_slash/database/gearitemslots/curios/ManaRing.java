package com.robertx22.mine_and_slash.database.gearitemslots.curios;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseCurio;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.stats.types.resources.Mana;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModItems;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.List;

public class ManaRing extends BaseCurio {

    public static GearItemSlot INSTANCE = new ManaRing();

    private ManaRing() {

    }

    @Override
    public PlayStyle getPlayStyle() {
        return PlayStyle.NONE;
    }

    @Override
    public String GUID() {
        return "occult_ring";
    }

    @Override
    public List<StatModifier> ImplicitStats() {
        return Arrays.asList();
    }

    @Override
    public List<StatModifier> BaseStats() {
        return Arrays.asList(new StatModifier(5, 15, Mana.getInstance(), StatModTypes.Flat));
    }

    @Override
    public Item getItem() {
        return ModItems.MANA_RING.get();
    }

    @Override
    public int Weight() {
        return super.Weight() * 2;
    }

    @Override
    public SlotFamily slotTypeFamily() {
        return SlotFamily.Jewelry;
    }

    @Override
    public List<SlotTag> getTags() {
        return null;
    }

    @Override
    public String locNameForLangFile() {
        return "Occult Ring";
    }
}
