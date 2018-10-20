package com.robertx22.spells.projectile.firebolt;

import com.robertx22.customitems.spells.ItemFireBolt;
import com.robertx22.database.stats.types.elementals.damage.FireDamage;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.spells.bases.DamageData;
import com.robertx22.spells.bases.EffectCalculation;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.utilityclasses.SoundUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SpellFireBolt extends BaseSpell {

	public SpellFireBolt() {
		super();
	}

	@Override
	public boolean cast(World world, EntityPlayer caster, EnumHand hand, int ticksInUse, SpellItemData data) {

		Vec3d look = caster.getLookVec();

		if (!world.isRemote) {
			EntityFireBolt projectile = new EntityFireBolt(world);
			projectile.SetReady(new EffectFireBolt(), new DamageData(caster, data));
			projectile.setPosition(caster.posX + look.x, caster.posY + look.y + 1.3, caster.posZ + look.z);
			projectile.shoot(caster, caster.rotationPitch, caster.rotationYaw, 0.0F, 1.5F, 1.0F);
			world.spawnEntity(projectile);
		}
		SoundUtils.playSoundAtPlayer(caster, SoundEvents.ENTITY_BLAZE_SHOOT, 1, 1);
		caster.swingArm(hand);
		return true;
	}

	@Override
	public String Name() {
		return "Fire_Bolt";
	}

	@Override
	public int Weight() {
		return this.NormalWeight;
	}

	@Override
	public int ManaCost() {
		return 10;
	}

	@Override
	public int Cooldown() {
		return 10;
	}

	@Override
	public int BaseDamage() {
		return 2;
	}

	@Override
	public EffectCalculation ScalingDamage() {
		return new EffectCalculation(new FireDamage().Name(), 0.5F);
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