package com.robertx22.spells.aoe_projectile;

import com.robertx22.customitems.spells.aoe_projectile.ItemAcidExplosion;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellNatureDamage;
import com.robertx22.spells.bases.EffectCalculation;
import com.robertx22.spells.bases.projectile.EntityElementalBolt;
import com.robertx22.spells.bases.projectile.EntityElementalBoltAOE;
import com.robertx22.uncommon.enumclasses.Elements;

import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellAcidExplosion extends BaseAoeSpellProjectile {

    static public class EntityAcidExplosion extends EntityElementalBoltAOE {

	public EntityAcidExplosion(World worldIn) {

	    super(worldIn);

	}

	@Override
	public Elements element() {
	    return Elements.Nature;
	}

    }

    public SpellAcidExplosion() {
	super();
    }

    @Override
    public EffectCalculation ScalingValue() {
	return new EffectCalculation(new SpellNatureDamage().Guid(), 0.25F);
    }

    @Override
    public Elements Element() {
	return Elements.Nature;
    }

    @Override
    public Item SpellItem() {
	return ItemAcidExplosion.ITEM;
    }

    @Override
    public String GUID() {
	return "AcidExplosion";
    }

    @Override
    public EntityElementalBolt projectile(World world) {
	return new EntityAcidExplosion(world);
    }

}