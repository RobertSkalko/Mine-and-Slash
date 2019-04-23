package com.robertx22.spells.projectile;

import com.robertx22.customitems.spells.projectile.ItemThunderBolt;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellThunderDamage;
import com.robertx22.spells.bases.EffectCalculation;
import com.robertx22.spells.bases.projectile.EntityElementalBolt;
import com.robertx22.uncommon.enumclasses.Elements;

import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellThunderBolt extends BaseSpellProjectile {
    static public class EntityThunderBolt extends EntityElementalBolt {

	public EntityThunderBolt(World worldIn) {

	    super(worldIn);

	}

	@Override
	public Elements element() {
	    return Elements.Thunder;
	}
    }

    public SpellThunderBolt() {
	super();
    }

    @Override
    public EffectCalculation ScalingValue() {
	return new EffectCalculation(new SpellThunderDamage().Guid(), 0.5F);
    }

    @Override
    public Elements Element() {
	return Elements.Thunder;
    }

    @Override
    public Item SpellItem() {
	return ItemThunderBolt.ITEM;
    }

    @Override
    public String GUID() {
	return "ThunderBolt";
    }

    @Override
    public EntityElementalBolt projectile(World world) {
	return new EntityThunderBolt(world);
    }

}