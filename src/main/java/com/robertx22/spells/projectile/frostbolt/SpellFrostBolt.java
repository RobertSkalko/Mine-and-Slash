package com.robertx22.spells.projectile.frostbolt;

import com.robertx22.customitems.spells.ItemFrostBolt;
import com.robertx22.database.stats.types.elementals.damage.WaterDamage;
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
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SpellFrostBolt extends BaseBolt {

	public SpellFrostBolt() {
		super();
	}

	@Override
	public boolean cast(World world, EntityPlayer caster, EnumHand hand, int ticksInUse, SpellItemData data) {

		Vec3d look = caster.getLookVec();

		if (!world.isRemote) {
			EntityFrostBolt projectile = new EntityFrostBolt(world);
			projectile.SetReady(new EffectFrostBolt(), new DamageData(caster, data));
			projectile.setPosition(caster.posX + look.x, caster.posY + look.y + 1.3, caster.posZ + look.z);
			projectile.shoot(caster, caster.rotationPitch, caster.rotationYaw, 0.0F, 1.5F, 1.0F);

			world.spawnEntity(projectile);

		}

		SoundUtils.playSoundAtPlayer(caster, SoundEvents.ENTITY_SNOWBALL_THROW, 1, 1);
		caster.swingArm(hand);
		return true;
	}

	@Override
	public String Name() {
		return "Frost_Bolt";
	}

	@Override
	public EffectCalculation ScalingDamage() {
		return new EffectCalculation(new WaterDamage().Name(), 0.5F);
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