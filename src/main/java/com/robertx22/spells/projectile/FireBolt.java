package com.robertx22.spells.projectile;

import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.spells.bases.MyDamageSource;
import com.robertx22.spells.bases.MyFireBolt;
import com.robertx22.utilityclasses.SoundUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class FireBolt extends BaseSpell {

	public FireBolt() {
		super();
	}

	@Override
	public boolean cast(World world, EntityPlayer caster, EnumHand hand, int ticksInUse) {

		Vec3d look = caster.getLookVec();

		if (!world.isRemote) {
			MyFireBolt fireball = new MyFireBolt(world);
			fireball.setPosition(caster.posX + look.x, caster.posY + look.y + 1.3, caster.posZ + look.z);
			fireball.accelerationX = look.x * 0.1;
			fireball.accelerationY = look.y * 0.1;
			fireball.accelerationZ = look.z * 0.1;

			MyDamageSource dmg = new MyDamageSource("test");

			caster.attackEntityFrom(dmg, 1);
			// fireball.getCapability(capability, facing)

			world.spawnEntity(fireball);
		}

		SoundUtils.playSoundAtPlayer(caster, SoundEvents.ENTITY_BLAZE_SHOOT, 1, 1);
		caster.swingArm(hand);
		return true;
	}

	@Override
	public String Name() {
		return "Fire Bolt";
	}

}
