package com.robertx22.spells.nova;

import com.robertx22.customitems.spells.nova.ItemPoisonNova;
import com.robertx22.database.stat_types.elementals.resist.NatureResist;
import com.robertx22.spells.bases.EffectCalculation;
import com.robertx22.uncommon.enumclasses.Elements;

import net.minecraft.item.Item;

public class SpellPoisonNova extends BaseNovaSpell {

    @Override
    public String GUID() {
	return "spell_poison_nova";
    }

    @Override
    public EffectCalculation ScalingValue() {
	return new EffectCalculation(new NatureResist().Guid(), scaling);
    }

    @Override
    public Elements Element() {
	return Elements.Nature;
    }

    @Override
    public Item SpellItem() {
	return ItemPoisonNova.ITEM;
    }

}
