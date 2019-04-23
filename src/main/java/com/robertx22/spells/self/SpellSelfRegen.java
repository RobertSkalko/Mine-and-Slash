package com.robertx22.spells.self;

import com.robertx22.customitems.spells.self.ItemSelfRegen;
import com.robertx22.database.stat_types.resources.HealthRegen;
import com.robertx22.effectdatas.SpellBuffEffect;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.bases.EffectCalculation;
import com.robertx22.spells.bases.SpellBuffCheck;
import com.robertx22.spells.potion_effects.all.HealthRegenPotion;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.utilityclasses.SoundUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class SpellSelfRegen extends BaseSpellHeal {

    @Override
    public String GUID() {
	return "spell_self_regen";
    }

    @Override
    public int ManaCost() {
	return 25;
    }

    @Override
    public int BaseValue() {
	return 5;
    }

    @Override
    public EffectCalculation ScalingValue() {
	return new EffectCalculation(HealthRegen.GUID, 0.5F);

    }

    @Override
    public Item SpellItem() {
	return ItemSelfRegen.ITEM;
    }

    @Override
    public String GetDescription(SpellItemData data) {
	return CLOC.tooltip("spell_self_regen");
    }

    @Override
    public boolean cast(World world, EntityPlayer caster, EnumHand hand, int ticksInUse, SpellItemData data) {
	try {

	    if (!world.isRemote) {

		SoundUtils.playSoundAtPlayer(caster, SoundEvents.ENTITY_GENERIC_DRINK, 1, 1);

		UnitData unit = Load.Unit(caster);

		int healed = (int) (data.GetBaseValue()
			+ data.GetScalingValue() * unit.getUnit().healthData().Value / 100);

		caster.addPotionEffect(new PotionEffect(HealthRegenPotion.INSTANCE, 400, healed));

		// spell buffs
		SpellBuffCheck check = new SpellBuffCheck(this.Type());
		SpellBuffEffect spelleffect = new SpellBuffEffect(caster, check);
		spelleffect.Activate();
		checkSpellBuffs(caster, check);
		//

	    } else {

		spawnHealParticles(caster, 10);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return true;
    }

}
