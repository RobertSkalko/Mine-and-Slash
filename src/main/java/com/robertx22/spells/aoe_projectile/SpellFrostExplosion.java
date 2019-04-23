package com.robertx22.spells.aoe_projectile;

import com.robertx22.customitems.spells.aoe_projectile.ItemFrostExplosion;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellWaterDamage;
import com.robertx22.spells.bases.EffectCalculation;
import com.robertx22.spells.bases.projectile.EntityElementalBolt;
import com.robertx22.spells.bases.projectile.EntityElementalBoltAOE;
import com.robertx22.uncommon.enumclasses.Elements;

import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellFrostExplosion extends BaseAoeSpellProjectile {
    static public class EntityFrostExplosion extends EntityElementalBoltAOE {

	public EntityFrostExplosion(World worldIn) {

	    super(worldIn);

	}

	@Override
	public Elements element() {
	    return Elements.Water;
	}

    }

    public SpellFrostExplosion() {
	super();
    }

    @Override
    public EffectCalculation ScalingValue() {
	return new EffectCalculation(new SpellWaterDamage().Guid(), 0.25F);
    }

    @Override
    public Elements Element() {
	return Elements.Water;
    }

    @Override
    public Item SpellItem() {
	return ItemFrostExplosion.ITEM;
    }

    @Override
    public String GUID() {
	return "FrostExplosion";
    }

    @Override
    public EntityElementalBolt projectile(World world) {
	return new EntityFrostExplosion(world);
    }

}