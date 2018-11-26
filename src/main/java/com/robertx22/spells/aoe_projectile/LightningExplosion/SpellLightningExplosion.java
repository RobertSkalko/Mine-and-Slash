package com.robertx22.spells.aoe_projectile.LightningExplosion;

import com.robertx22.customitems.spells.aoe_projectile.ItemLightningExplosion;
import com.robertx22.database.stats.types.elementals.spell_damage.SpellThunderDamage;
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

public class SpellLightningExplosion extends BaseBoltAOE {

	public SpellLightningExplosion() {
		super();
	}

	@Override
	public boolean cast(World world, EntityPlayer caster, EnumHand hand, int ticksInUse, SpellItemData data) {

		if (!world.isRemote) {
			EntityLightningExplosion projectile = new EntityLightningExplosion(world);
			projectile.SpawnAndShoot(new EffectLightningExplosion(), new DamageData(caster, data), caster);

		}

		SoundUtils.playSoundAtPlayer(caster, SoundEvents.ENTITY_SNOWBALL_THROW, 1, 1);
		caster.swingArm(hand);
		return true;
	}

	@Override
	public String Name() {
		return "Lightning Explosion";
	}

	@Override
	public EffectCalculation ScalingValue() {
		return new EffectCalculation(new SpellThunderDamage().Name(), 0.25F);
	}

	@Override
	public Elements Element() {
		return Elements.Thunder;
	}

	@Override
	public Item SpellItem() {
		return ItemLightningExplosion.ITEM;
	}

	@Override
	public String GUID() {
		return "LightningExplosion";
	}

}