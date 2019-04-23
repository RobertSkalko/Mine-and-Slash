package com.robertx22.spells.projectile;

import com.robertx22.customitems.spells.projectile.ItemFireBolt;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellFireDamage;
import com.robertx22.spells.bases.EffectCalculation;
import com.robertx22.spells.bases.projectile.EntityElementalBolt;
import com.robertx22.uncommon.enumclasses.Elements;

import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellFireBolt extends BaseSpellProjectile {

    static public class EntityFireBolt extends EntityElementalBolt {

	public EntityFireBolt(World worldIn) {

	    super(worldIn);

	}

	@Override
	public Elements element() {
	    return Elements.Fire;
	}
    }

    public SpellFireBolt() {
	super();
    }

    @Override
    public EffectCalculation ScalingValue() {
	return new EffectCalculation(new SpellFireDamage().Guid(), 0.5F);
    }

    @Override
    public Elements Element() {
	return Elements.Fire;
    }

    @Override
    public Item SpellItem() {
	return ItemFireBolt.ITEM;
    }

    @Override
    public String GUID() {
	return "FireBolt";
    }

    @Override
    public EntityElementalBolt projectile(World world) {
	return new EntityFireBolt(world);
    }

}