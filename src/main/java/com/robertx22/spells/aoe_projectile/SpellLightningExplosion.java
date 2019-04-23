package com.robertx22.spells.aoe_projectile;

import com.robertx22.customitems.spells.aoe_projectile.ItemLightningExplosion;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellThunderDamage;
import com.robertx22.spells.bases.EffectCalculation;
import com.robertx22.spells.bases.projectile.EntityElementalBolt;
import com.robertx22.spells.bases.projectile.EntityElementalBoltAOE;
import com.robertx22.uncommon.enumclasses.Elements;

import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellLightningExplosion extends BaseAoeSpellProjectile {
    static public class EntityLightningExplosion extends EntityElementalBoltAOE {

	public EntityLightningExplosion(World worldIn) {

	    super(worldIn);

	}

	@Override
	public Elements element() {
	    return Elements.Thunder;
	}

    }

    public SpellLightningExplosion() {
	super();
    }

    @Override
    public EffectCalculation ScalingValue() {
	return new EffectCalculation(new SpellThunderDamage().Guid(), 0.25F);
    }

    @Override
    public Elements Element() {
	return Elements.Thunder;
    }

    @Override
    public Item SpellItem() {
	return ItemLightningExplosion.ITEM;
    }

    @Override
    public String GUID() {
	return "LightningExplosion";
    }

    @Override
    public EntityElementalBolt projectile(World world) {
	return new EntityLightningExplosion(world);
    }

}