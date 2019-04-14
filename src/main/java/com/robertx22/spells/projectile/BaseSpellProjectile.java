package com.robertx22.spells.projectile;

import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.bases.DamageData;
import com.robertx22.spells.bases.SpellEffectDamage;
import com.robertx22.spells.bases.projectile.EntityElementalBolt;
import com.robertx22.uncommon.utilityclasses.SoundUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public abstract class BaseSpellProjectile extends BaseBolt {

    public BaseSpellProjectile() {
	super();
    }

    @Override
    public SpellType Type() {
	return SpellType.Single_Target_Projectile;
    }

    @Override
    public boolean cast(World world, EntityPlayer caster, EnumHand hand, int ticksInUse, SpellItemData data) {

	if (!world.isRemote) {

	    EntityElementalBolt projectile = this.projectile(world);
	    projectile.SpawnAndShoot(new SpellEffectDamage(this.Element()), new DamageData(caster, data), caster);

	}

	SoundUtils.playSoundAtPlayer(caster, SoundEvents.ENTITY_SNOWBALL_THROW, 1, 1);
	caster.swingArm(hand);
	return true;
    }

}