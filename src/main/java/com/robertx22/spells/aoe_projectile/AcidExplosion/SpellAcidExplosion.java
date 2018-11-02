package com.robertx22.spells.aoe_projectile.AcidExplosion;

import com.robertx22.customitems.spells.aoe_projectile.ItemAcidExplosion;
import com.robertx22.database.stats.types.elementals.damage.NatureDamage;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.bases.DamageData;
import com.robertx22.spells.bases.EffectCalculation;
import com.robertx22.spells.projectile.BaseBoltAOE;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.utilityclasses.SoundUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class SpellAcidExplosion extends BaseBoltAOE {

	public SpellAcidExplosion() {
		super();
	}

	@Override
	public boolean cast(World world, EntityPlayer caster, EnumHand hand, int ticksInUse, SpellItemData data) {

		if (!world.isRemote) {
			EntityAcidExplosion projectile = new EntityAcidExplosion(world);
			projectile.SpawnAndShoot(new EffectAcidExplosion(), new DamageData(caster, data), caster);

		}

		SoundUtils.playSoundAtPlayer(caster, SoundEvents.ENTITY_SNOWBALL_THROW, 1, 1);
		caster.swingArm(hand);
		return true;
	}

	@Override
	public String Name() {
		return "Acid Explosion";
	}

	@Override
	public EffectCalculation ScalingValue() {
		return new EffectCalculation(new NatureDamage().Name(), 0.25F);
	}

	@Override
	public Elements Element() {
		return Elements.Nature;
	}

	@Override
	public Item SpellItem() {
		return ItemAcidExplosion.ITEM;
	}

	@Override
	public String GUID() {
		return "AcidExplosion";
	}

}