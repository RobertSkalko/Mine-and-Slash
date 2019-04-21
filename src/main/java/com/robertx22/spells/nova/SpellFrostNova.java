package com.robertx22.spells.nova;

import com.robertx22.customitems.spells.nova.ItemFrostNova;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellWaterDamage;
import com.robertx22.spells.bases.EffectCalculation;
import com.robertx22.uncommon.enumclasses.Elements;

import net.minecraft.item.Item;

public class SpellFrostNova extends BaseNovaSpell {

    @Override
    public String GUID() {
	return "spell_frost_nova";
    }

    @Override
    public String Name() {
	return "Frost Nova";
    }

    @Override
    public EffectCalculation ScalingValue() {
	return new EffectCalculation(new SpellWaterDamage().Guid(), scaling);
    }

    @Override
    public Elements Element() {
	return Elements.Water;
    }

    @Override
    public Item SpellItem() {
	return ItemFrostNova.ITEM;
    }

}
