package com.robertx22.spells.projectile.firebolt;

import com.robertx22.customitems.spells.projectile.ItemFireBolt;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellFireDamage;
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

public class SpellFireBolt extends BaseBolt {

	public SpellFireBolt() {
		super();
	}

	@Override
	public boolean cast(World world, EntityPlayer caster, EnumHand hand, int ticksInUse, SpellItemData data) {

		if (!world.isRemote) {
			EntityFireBolt projectile = new EntityFireBolt(world);
			projectile.SpawnAndShoot(new EffectFireBolt(), new DamageData(caster, data), caster);
		}
		SoundUtils.playSoundAtPlayer(caster, SoundEvents.ENTITY_BLAZE_SHOOT, 1, 1);
		caster.swingArm(hand);
		return true;
	}

	@Override
	public String Name() {
		return "Fire Bolt";
	}

	@Override
	public EffectCalculation ScalingValue() {
		return new EffectCalculation(new SpellFireDamage().Guid(), 0.5F);
	}

	@Override
	public Elements Element() {
		return Elements.Fire;
	}

	@Override
	public Item SpellItem() {
		return ItemFireBolt.ITEM;
	}

	@Override
	public String GUID() {
		return "FireBolt";
	}

}