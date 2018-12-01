package com.robertx22.spells.aoe_projectile.FlameExplosion;

import com.robertx22.customitems.spells.aoe_projectile.ItemFlameExplosion;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellFireDamage;
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

public class SpellFlameExplosion extends BaseBoltAOE {

	public SpellFlameExplosion() {
		super();
	}

	@Override
	public boolean cast(World world, EntityPlayer caster, EnumHand hand, int ticksInUse, SpellItemData data) {

		if (!world.isRemote) {
			EntityFlameExplosion projectile = new EntityFlameExplosion(world);
			projectile.SpawnAndShoot(new EffectFlameExplosion(), new DamageData(caster, data), caster);

		}

		SoundUtils.playSoundAtPlayer(caster, SoundEvents.ENTITY_SNOWBALL_THROW, 1, 1);
		caster.swingArm(hand);
		return true;
	}

	@Override
	public String Name() {
		return "Flame Explosion";
	}

	@Override
	public EffectCalculation ScalingValue() {
		return new EffectCalculation(new SpellFireDamage().Guid(), 0.25F);
	}

	@Override
	public Elements Element() {
		return Elements.Fire;
	}

	@Override
	public Item SpellItem() {
		return ItemFlameExplosion.ITEM;
	}

	@Override
	public String GUID() {
		return "FlameExplosion";
	}

}