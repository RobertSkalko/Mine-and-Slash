package com.robertx22.spells.projectile.acidbolt;

import com.robertx22.customitems.spells.projectile.ItemAcidBolt;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellNatureDamage;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.bases.DamageData;
import com.robertx22.spells.bases.EffectCalculation;
import com.robertx22.spells.projectile.BaseBolt;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.utilityclasses.SoundUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class SpellAcidBolt extends BaseBolt {

	public SpellAcidBolt() {
		super();
	}

	@Override
	public boolean cast(World world, EntityPlayer caster, EnumHand hand, int ticksInUse, SpellItemData data) {

		if (!world.isRemote) {
			EntityAcidBolt projectile = new EntityAcidBolt(world);
			projectile.SpawnAndShoot(new EffectAcidBolt(), new DamageData(caster, data), caster);

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
		return new EffectCalculation(new SpellNatureDamage().Name(), 0.5F);
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