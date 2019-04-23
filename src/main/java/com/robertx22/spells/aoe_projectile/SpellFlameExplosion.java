package com.robertx22.spells.aoe_projectile;

import com.robertx22.customitems.spells.aoe_projectile.ItemFlameExplosion;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellFireDamage;
import com.robertx22.spells.bases.EffectCalculation;
import com.robertx22.spells.bases.projectile.EntityElementalBolt;
import com.robertx22.spells.bases.projectile.EntityElementalBoltAOE;
import com.robertx22.uncommon.enumclasses.Elements;

import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellFlameExplosion extends BaseAoeSpellProjectile {
    static public class EntityFlameExplosion extends EntityElementalBoltAOE {

	public EntityFlameExplosion(World worldIn) {

	    super(worldIn);

	}

	@Override
	public Elements element() {
	    return Elements.Fire;
	}

    }

    public SpellFlameExplosion() {
	super();
    }

    @Override
    public EffectCalculation ScalingValue() {
	return new EffectCalculation(new SpellFireDamage().Guid(), 0.25F);
    }

    @Override
    public Elements Element() {
	return Elements.Fire;
    }

    @Override
    public Item SpellItem() {
	return ItemFlameExplosion.ITEM;
    }

    @Override
    public String GUID() {
	return "FlameExplosion";
    }

    @Override
    public EntityElementalBolt projectile(World world) {
	return new EntityFlameExplosion(world);
    }

}