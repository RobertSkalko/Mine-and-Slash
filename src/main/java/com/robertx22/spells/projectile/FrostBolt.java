package com.robertx22.spells.projectile;

import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.spells.bases.MyDamageSource;
import com.robertx22.spells.bases.projectile.EntityFrostBolt;
import com.robertx22.utilityclasses.SoundUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class FrostBolt extends BaseSpell {

	public FrostBolt() {
		super();
	}

	@Override
	public boolean cast(World world, EntityPlayer caster, EnumHand hand, int ticksInUse, SpellItemData data) {

		Vec3d look = caster.getLookVec();

		if (!world.isRemote) {
			EntityFrostBolt frostbolt = new EntityFrostBolt(world);
			frostbolt.setPosition(caster.posX + look.x, caster.posY + look.y + 1.3, caster.posZ + look.z);

			frostbolt.shoot(caster, caster.rotationPitch, caster.rotationYaw, 0.0F, 1.5F, 1.0F);

			MyDamageSource dmg = new MyDamageSource("test");

			// caster.attackEntityFrom(dmg, 1);
			// fireball.getCapability(capability, facing)

			world.spawnEntity(frostbolt);
		}

		SoundUtils.playSoundAtPlayer(caster, SoundEvents.ENTITY_BLAZE_SHOOT, 1, 1);
		caster.swingArm(hand);
		return true;
	}

	@Override
	public String Name() {
		return "Fire Bolt";
	}

	@Override
	public int Weight() {
		return this.NormalWeight;
	}

}
