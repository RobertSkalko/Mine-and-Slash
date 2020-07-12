package com.robertx22.mine_and_slash.database.gearitemslots.offhand;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseOffHand;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.stats.types.offense.SpellDamage;
import com.robertx22.mine_and_slash.database.stats.types.resources.MagicShield;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModItems;
import com.robertx22.mine_and_slash.uncommon.enumclasses.ModType;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.List;

public class SpiritShield extends BaseOffHand {
    public static GearItemSlot INSTANCE = new SpiritShield();

    private SpiritShield() {

    }

    @Override
    public List<StatModifier> BaseStats() {
        return Arrays.asList(new StatModifier(5, 15, MagicShield.getInstance(), ModType.FLAT));
    }

    @Override
    public List<StatModifier> ImplicitStats() {
        return Arrays.asList(new StatModifier(3, 8, SpellDamage.getInstance(), ModType.FLAT));
    }

    @Override
    public PlayStyle getPlayStyle() {
        return PlayStyle.INT;
    }

    @Override
    public List<SlotTag> getTags() {
        return Arrays.asList(SlotTag.Shield, SlotTag.Cloth);
    }

    @Override
    public Item getItem() {
        return ModItems.SPIRIT_SHIELD.get();
    }

    @Override
    public String GUID() {
        return "spirit_shield";
    }

    @Override
    public String locNameForLangFile() {
        return "Spirit Shield";
    }
}
