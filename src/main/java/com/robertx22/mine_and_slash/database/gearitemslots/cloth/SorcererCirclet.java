package com.robertx22.mine_and_slash.database.gearitemslots.cloth;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.armor.BaseBoots;
import com.robertx22.mine_and_slash.database.stats.types.offense.SpellDamage;
import com.robertx22.mine_and_slash.database.stats.types.resources.MagicShield;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModItems;
import com.robertx22.mine_and_slash.uncommon.enumclasses.ModType;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.List;

public class SorcererCirclet extends BaseBoots {
    public static GearItemSlot INSTANCE = new SorcererCirclet();

    private SorcererCirclet() {

    }

    @Override
    public List<StatModifier> BaseStats() {
        return Arrays.asList(
            new StatModifier(3, 8, MagicShield.getInstance(), ModType.FLAT)
        );
    }

    @Override
    public List<StatModifier> ImplicitStats() {
        return Arrays.asList(new StatModifier(3, 8, SpellDamage.getInstance(), ModType.FLAT));
    }

    @Override
    public List<SlotTag> getTags() {
        return Arrays.asList(SlotTag.Cloth, SlotTag.Helmet);
    }

    @Override
    public Item getItem() {
        return ModItems.SORCERER_CIRCLET.get();
    }

    @Override
    public String GUID() {
        return "sorcerer_circlet";
    }

    @Override
    public PlayStyle getPlayStyle() {
        return PlayStyle.INT;
    }

    @Override
    public String locNameForLangFile() {
        return "Sorcerer Circlet";
    }
}

