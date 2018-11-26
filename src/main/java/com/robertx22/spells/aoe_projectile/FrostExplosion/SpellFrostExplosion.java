package com.robertx22.spells.aoe_projectile.FrostExplosion;

import com.robertx22.customitems.spells.aoe_projectile.ItemFrostExplosion;
import com.robertx22.database.stats.types.elementals.spell_damage.SpellWaterDamage;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.bases.DamageData;
import com.robertx22.spells.bases.EffectCalculation;
import com.robertx22.spells.projectile.BaseBoltAOE;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.utilityclasses.SoundUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class SpellFrostExplosion extends BaseBoltAOE {

	public SpellFrostExplosion() {
		super();
	}

	@Override
	public boolean cast(World world, EntityPlayer caster, EnumHand hand, int ticksInUse, SpellItemData data) {

		if (!world.isRemote) {
			EntityFrostExplosion projectile = new EntityFrostExplosion(world);
			projectile.SpawnAndShoot(new EffectFrostExplosion(), new DamageData(caster, data), caster);

		}

		SoundUtils.playSoundAtPlayer(caster, SoundEvents.ENTITY_SNOWBALL_THROW, 1, 1);
		caster.swingArm(hand);
		return true;
	}

	@Override
	public String Name() {
		return "Frost Explosion";
	}

	@Override
	public EffectCalculation ScalingValue() {
		return new EffectCalculation(new SpellWaterDamage().Name(), 0.25F);
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

}