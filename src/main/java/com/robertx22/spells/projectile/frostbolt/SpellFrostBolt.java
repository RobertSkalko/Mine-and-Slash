package com.robertx22.spells.projectile.frostbolt;

import com.robertx22.customitems.spells.projectile.ItemFrostBolt;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellWaterDamage;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.bases.DamageData;
import com.robertx22.spells.bases.EffectCalculation;
import com.robertx22.spells.projectile.BaseBolt;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.utilityclasses.SoundUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class SpellFrostBolt extends BaseBolt {

	public SpellFrostBolt() {
		super();
	}

	@Override
	public boolean cast(World world, EntityPlayer caster, EnumHand hand, int ticksInUse, SpellItemData data) {

		if (!world.isRemote) {
			EntityFrostBolt projectile = new EntityFrostBolt(world);
			projectile.SpawnAndShoot(new EffectFrostBolt(), new DamageData(caster, data), caster);

		}

		SoundUtils.playSoundAtPlayer(caster, SoundEvents.ENTITY_SNOWBALL_THROW, 1, 1);
		caster.swingArm(hand);
		return true;
	}

	@Override
	public String Name() {
		return "Frost Bolt";
	}

	@Override
	public EffectCalculation ScalingValue() {
		return new EffectCalculation(new SpellWaterDamage().Name(), 0.5F);
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

}