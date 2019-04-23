package com.robertx22.spells.nova;

import com.robertx22.customitems.spells.nova.ItemThunderNova;
import com.robertx22.database.stat_types.elementals.resist.ThunderResist;
import com.robertx22.spells.bases.EffectCalculation;
import com.robertx22.uncommon.enumclasses.Elements;

import net.minecraft.item.Item;

public class SpellThunderNova extends BaseNovaSpell {

    @Override
    public String GUID() {
	return "spell_thunder_nova";
    }

    @Override
    public EffectCalculation ScalingValue() {
	return new EffectCalculation(new ThunderResist().Guid(), scaling);
    }

    @Override
    public Elements Element() {
	return Elements.Thunder;
    }

    @Override
    public Item SpellItem() {
	return ItemThunderNova.ITEM;
    }

}
