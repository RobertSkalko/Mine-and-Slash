package com.robertx22.spells.self;

import com.robertx22.customitems.spells.self.ItemSelfRegen;
import com.robertx22.database.stat_types.resources.Health;
import com.robertx22.effectdatas.SpellBuffEffect;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.bases.EffectCalculation;
import com.robertx22.spells.bases.SpellBuffCheck;
import com.robertx22.spells.potion_effects.all.RegenPotion;
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
    public String Name() {
	return "Regenerate";
    }

    @Override
    public int ManaCost() {
	return 30;
    }

    @Override
    public int BaseValue() {
	return 0;
    }

    @Override
    public EffectCalculation ScalingValue() {
	return new EffectCalculation(Health.GUID, 0.05F);

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

		UnitData unit = Load.Unit(caster);
		// unit.heal(caster, data.GetDamage(unit.getUnit()));
		SoundUtils.playSoundAtPlayer(caster, SoundEvents.ENTITY_GENERIC_DRINK, 1, 1);

		caster.addPotionEffect(
			new PotionEffect(RegenPotion.INSTANCE, 400, (int) (data.GetScalingValue() * 100)));

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
