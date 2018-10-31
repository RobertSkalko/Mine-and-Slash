package com.robertx22.spells.projectile.acidbolt;

import com.robertx22.customitems.spells.projectile.ItemAcidBolt;
import com.robertx22.database.stats.types.elementals.damage.NatureDamage;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.bases.DamageData;
import com.robertx22.spells.bases.EffectCalculation;
import com.robertx22.spells.projectile.BaseBolt;
import com.robertx22.spells.projectile.firebolt.EffectFireBolt;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.utilityclasses.SoundUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SpellAcidBolt extends BaseBolt {

	public SpellAcidBolt() {
		super();
	}

	@Override
	public boolean cast(World world, EntityPlayer caster, EnumHand hand, int ticksInUse, SpellItemData data) {

		Vec3d look = caster.getLookVec();

		if (!world.isRemote) {
			EntityAcidBolt projectile = new EntityAcidBolt(world);
			projectile.SetReady(new EffectFireBolt(), new DamageData(caster, data));
			projectile.setPosition(caster.posX + look.x, caster.posY + look.y + 1.3, caster.posZ + look.z);
			projectile.shoot(caster, caster.rotationPitch, caster.rotationYaw, 0.0F, 1.5F, 1.0F);
			world.spawnEntity(projectile);
		}
		SoundUtils.playSoundAtPlayer(caster, SoundEvents.ENTITY_SPLASH_POTION_THROW, 1, 1);
		caster.swingArm(hand);
		return true;
	}

	@Override
	public String Name() {
		return "Acid Bolt";
	}

	@Override
	public EffectCalculation ScalingValue() {
		return new EffectCalculation(new NatureDamage().Name(), 0.5F);
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

}