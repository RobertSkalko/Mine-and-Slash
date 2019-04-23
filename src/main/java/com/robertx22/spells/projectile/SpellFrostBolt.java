package com.robertx22.spells.projectile;

import com.robertx22.customitems.spells.projectile.ItemFrostBolt;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellWaterDamage;
import com.robertx22.spells.bases.EffectCalculation;
import com.robertx22.spells.bases.projectile.EntityElementalBolt;
import com.robertx22.uncommon.enumclasses.Elements;

import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellFrostBolt extends BaseSpellProjectile {
    static public class EntityFrostBolt extends EntityElementalBolt {

	public EntityFrostBolt(World worldIn) {

	    super(worldIn);

	}

	@Override
	public Elements element() {
	    return Elements.Water;
	}
    }

    public SpellFrostBolt() {
	super();
    }

    @Override
    public EffectCalculation ScalingValue() {
	return new EffectCalculation(new SpellWaterDamage().Guid(), 0.5F);
    }

    @Override
    public Elements Element() {
	return Elements.Water;
    }

    @Override
    public Item SpellItem() {
	return ItemFrostBolt.ITEM;
    }

    @Override
    public String GUID() {
	return "FrostBolt";
    }

    @Override
    public EntityElementalBolt projectile(World world) {
	return new EntityFrostBolt(world);
    }

}