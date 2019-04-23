package com.robertx22.spells.projectile;

import com.robertx22.customitems.spells.projectile.ItemAcidBolt;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellNatureDamage;
import com.robertx22.spells.bases.EffectCalculation;
import com.robertx22.spells.bases.projectile.EntityElementalBolt;
import com.robertx22.uncommon.enumclasses.Elements;

import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellAcidBolt extends BaseSpellProjectile {
    static public class EntityAcidBolt extends EntityElementalBolt {

	public EntityAcidBolt(World worldIn) {

	    super(worldIn);

	}

	@Override
	public Elements element() {
	    return Elements.Nature;
	}

    }

    public SpellAcidBolt() {
	super();
    }

    @Override
    public EffectCalculation ScalingValue() {
	return new EffectCalculation(new SpellNatureDamage().Guid(), 0.5F);
    }

    @Override
    public Elements Element() {
	return Elements.Nature;
    }

    @Override
    public Item SpellItem() {
	return ItemAcidBolt.ITEM;
    }

    @Override
    public String GUID() {
	return "AcidBolt";
    }

    @Override
    public EntityElementalBolt projectile(World world) {
	return new EntityAcidBolt(world);
    }

}