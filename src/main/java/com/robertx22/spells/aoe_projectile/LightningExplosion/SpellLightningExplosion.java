package com.robertx22.spells.aoe_projectile.LightningExplosion;

import com.robertx22.customitems.spells.aoe_projectile.ItemLightningExplosion;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellThunderDamage;
import com.robertx22.spells.aoe_projectile.BaseAoeSpellProjectile;
import com.robertx22.spells.bases.EffectCalculation;
import com.robertx22.spells.bases.projectile.EntityElementalBolt;
import com.robertx22.uncommon.enumclasses.Elements;

import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellLightningExplosion extends BaseAoeSpellProjectile {

    public SpellLightningExplosion() {
	super();
    }

    @Override
    public String Name() {
	return "Lightning Explosion";
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