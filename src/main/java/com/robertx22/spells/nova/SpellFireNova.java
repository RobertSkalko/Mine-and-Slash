package com.robertx22.spells.nova;

import com.robertx22.customitems.spells.nova.ItemFireNova;
import com.robertx22.database.stat_types.elementals.resist.FireResist;
import com.robertx22.spells.bases.EffectCalculation;
import com.robertx22.uncommon.enumclasses.Elements;

import net.minecraft.item.Item;

public class SpellFireNova extends BaseNovaSpell {

    @Override
    public String GUID() {
	return "spell_fire_nova";
    }

    @Override
    public EffectCalculation ScalingValue() {
	return new EffectCalculation(new FireResist().Guid(), scaling);
    }

    @Override
    public Elements Element() {
	return Elements.Fire;
    }

    @Override
    public Item SpellItem() {
	return ItemFireNova.ITEM;
    }

}
