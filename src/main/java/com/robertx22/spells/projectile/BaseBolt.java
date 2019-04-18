package com.robertx22.spells.projectile;

import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.spells.bases.DamageData;
import com.robertx22.spells.bases.SpellEffectDamage;
import com.robertx22.spells.bases.projectile.EntityElementalBolt;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.utilityclasses.SoundUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public abstract class BaseBolt extends BaseSpell {

    @Override
    public boolean cast(World world, EntityPlayer caster, EnumHand hand, int ticksInUse, SpellItemData data) {

	if (!world.isRemote) {
	    EntityElementalBolt projectile = this.projectile(world);
	    projectile.SpawnAndShoot(new SpellEffectDamage(this.Element()), new DamageData(caster, data), caster);

	}
	SoundUtils.playSoundAtPlayer(caster, SoundEvents.ENTITY_SPLASH_POTION_THROW, 1, 1);
	caster.swingArm(hand);
	return true;
    }

    @Override
    public int ManaCost() {
	return 10;
    }

    @Override
    public int BaseValue() {
	return 4;
    }

    @Override
    public String GetDescription(SpellItemData data) {
	return CLOC.tooltip("single_target_spell_bolt");

    }

    public abstract EntityElementalBolt projectile(World world);
}
