package com.robertx22.spells.self;

import java.util.ArrayList;

import com.robertx22.effectdatas.interfaces.IBuffableSpell.SpellBuffType;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.spells.bases.SpellBuffCheck;
import com.robertx22.spells.potion_effects.all.AoeRegenPotion;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.utilityclasses.WizardryUtilities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;

public abstract class BaseSpellHeal extends BaseSpell {

    @Override
    public SpellType Type() {
	return SpellType.Self_Heal;
    }

    @Override
    public Elements Element() {
	return Elements.None;
    }

    public int Weight() {
	return super.Weight() * 3;
    }

    public void checkZephyrSpeedBoost(EntityPlayer caster, SpellBuffCheck buffable) {

	if (buffable.getBuff().equals(SpellBuffType.Zephyr_Speed_Boost)) {
	    caster.addPotionEffect(new PotionEffect(MobEffects.SPEED, 200));
	}

    }

    public void checkPurityRemoveNegativeEffect(EntityPlayer caster, SpellBuffCheck buffable) {

	if (buffable.getBuff().equals(SpellBuffType.Purity_Remove_Negative_Effects)) {
	    for (PotionEffect pot : new ArrayList<PotionEffect>(caster.getActivePotionEffects())) {
		if (pot.getPotion().isBadEffect()) {
		    caster.removePotionEffect(pot.getPotion());
		    break;
		}
	    }
	}

    }

    public void checkAddLightBuff(EntityPlayer caster, SpellBuffCheck buffable) {
	if (buffable.getBuff().equals(SpellBuffType.Light_Aoe_Regen)) {
	    caster.addPotionEffect(new PotionEffect(AoeRegenPotion.INSTANCE, 200));
	}

    }

    public void checkSpellBuffs(EntityPlayer caster, SpellBuffCheck buffable) {
	checkZephyrSpeedBoost(caster, buffable);
	checkAddLightBuff(caster, buffable);
	checkPurityRemoveNegativeEffect(caster, buffable);
    }

    public static void spawnHealParticles(EntityLivingBase en, int amount) {
	for (int i = 0; i < amount; i++) {
	    double d0 = (double) ((float) en.posX + en.world.rand.nextFloat() * 2 - 1.0F);
	    // Apparently the client side spawns the particles 1 block higher than it
	    // should... hence the -
	    // 0.5F.
	    double d1 = (double) ((float) WizardryUtilities.getPlayerEyesPos(en) - 0.5F + en.world.rand.nextFloat());
	    double d2 = (double) ((float) en.posZ + en.world.rand.nextFloat() * 2 - 1.0F);

	    en.world.spawnParticle(EnumParticleTypes.HEART, d0, d1, d2, 0, 48 + en.world.rand.nextInt(12), 1.0f);
	}
    }

    public static void spawnParticles(EnumParticleTypes particle, EntityLivingBase en, int amount) {
	for (int i = 0; i < amount; i++) {
	    double d0 = (double) ((float) en.posX + en.world.rand.nextFloat() * 2 - 1.0F);
	    // Apparently the client side spawns the particles 1 block higher than it
	    // should... hence the -
	    // 0.5F.
	    double d1 = (double) ((float) WizardryUtilities.getPlayerEyesPos(en) - 0.5F + en.world.rand.nextFloat());
	    double d2 = (double) ((float) en.posZ + en.world.rand.nextFloat() * 2 - 1.0F);

	    en.world.spawnParticle(particle, d0, d1, d2, 0, 48 + en.world.rand.nextInt(12), 1.0f);
	}
    }
}
