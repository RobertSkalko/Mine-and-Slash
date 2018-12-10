package com.robertx22.spells.self;

import com.robertx22.customitems.spells.self.ItemInstantHeal;
import com.robertx22.database.stat_types.resources.Health;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.spells.bases.EffectCalculation;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.utilityclasses.SoundUtils;
import com.robertx22.uncommon.utilityclasses.WizardryUtilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class SpellInstantHeal extends BaseSpell {

    @Override
    public String GUID() {
	return "instant_heal";
    }

    @Override
    public String Name() {
	return "Instant Heal";
    }

    @Override
    public int ManaCost() {
	return 20;
    }

    @Override
    public int BaseValue() {
	return 5;
    }

    @Override
    public EffectCalculation ScalingValue() {
	return new EffectCalculation(Health.GUID, 0.3F);

    }

    @Override
    public Elements Element() {
	return Elements.None;
    }

    @Override
    public Item SpellItem() {
	return ItemInstantHeal.ITEM;
    }

    @Override
    public String GetDescription(SpellItemData data) {
	return CLOC.tooltip("spell_instant_heal");
    }

    @Override
    public boolean cast(World world, EntityPlayer caster, EnumHand hand, int ticksInUse, SpellItemData data) {

	try {
	    UnitData unit = Load.Unit(caster);

	    unit.heal(caster, data.GetDamage(unit.getUnit()));

	    if (world.isRemote) {
		for (int i = 0; i < 10; i++) {
		    double d0 = (double) ((float) caster.posX + world.rand.nextFloat() * 2 - 1.0F);
		    // Apparently the client side spawns the particles 1 block higher than it
		    // should... hence the -
		    // 0.5F.
		    double d1 = (double) ((float) WizardryUtilities.getPlayerEyesPos(caster) - 0.5F
			    + world.rand.nextFloat());
		    double d2 = (double) ((float) caster.posZ + world.rand.nextFloat() * 2 - 1.0F);

		    world.spawnParticle(EnumParticleTypes.HEART, d0, d1, d2, 0, 48 + world.rand.nextInt(12), 1.0f);

		}
		SoundUtils.playSoundAtPlayer(caster, SoundEvents.ENTITY_GENERIC_DRINK, 1, 1);

	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return true;
    }

}
