package com.robertx22.spells.nova;

import java.util.List;

import com.robertx22.ColoredRedstone;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.spells.bases.DamageData;
import com.robertx22.spells.bases.SpellEffectDamage;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.utilityclasses.SoundUtils;
import com.robertx22.uncommon.utilityclasses.WizardryUtilities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public abstract class BaseNovaSpell extends BaseSpell {

    public float scaling = 0.3F;
    public double radius = 4;

    @Override
    public SpellType Type() {
	return SpellType.Aoe_Damage_Nova;
    }

    @Override
    public int ManaCost() {
	return 15;
    }

    @Override
    public int BaseValue() {
	return 5;
    }

    @Override
    public String GetDescription(SpellItemData data) {
	return CLOC.tooltip("aoe_spell_nova");

    }

    @Override
    public boolean cast(World world, EntityPlayer caster, EnumHand hand, int ticksInUse, SpellItemData data) {

	if (!world.isRemote) {

	    ColoredRedstone.SpawnNovaRedstone(this.Element(), caster, radius, 200);

	    List<EntityLivingBase> list = WizardryUtilities.getEntitiesWithinRadius(radius, caster,
		    EntityLivingBase.class);

	    for (int i = 0; i < list.size(); ++i) {
		EntityLivingBase entity1 = list.get(i);

		if (entity1.hasCapability(EntityData.Data, null) && entity1 != caster) {

		    entity1.playSound(SoundEvents.ENCHANT_THORNS_HIT, 0.7F, 0);

		    SpellEffectDamage effect = new SpellEffectDamage(this.Element());
		    effect.Activate(new DamageData(caster, data), entity1);

		}

	    }

	}

	SoundUtils.playSoundAtPlayer(caster, SoundEvents.EVOCATION_ILLAGER_CAST_SPELL, 1, 1);
	caster.swingArm(hand);
	return true;
    }

}
