package com.robertx22.spells.projectile;

import com.robertx22.customitems.spells.ItemFrostBolt;
import com.robertx22.database.stats.types.elementals.damage.WaterDamage;
import com.robertx22.effectdatas.DamageEffect;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.EntityElementalArrow;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.spells.bases.BaseSpellEffect;
import com.robertx22.spells.bases.DamageData;
import com.robertx22.spells.bases.EffectCalculation;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.utilityclasses.SoundUtils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SpellFrostBolt extends BaseSpell {

	public SpellFrostBolt() {
		super();
	}

	@Override
	public boolean cast(World world, EntityPlayer caster, EnumHand hand, int ticksInUse, SpellItemData data) {

		Vec3d look = caster.getLookVec();

		if (!world.isRemote) {
			EntityElementalArrow frostbolt = new EntityElementalArrow(world);
			frostbolt.setPosition(caster.posX + look.x, caster.posY + look.y + 1.3, caster.posZ + look.z);
			frostbolt.shoot(caster, caster.rotationPitch, caster.rotationYaw, 0.0F, 1.5F, 1.0F);

			// frostbolt.SetData(new DamageData(caster, new EffectFrostBolt(), data));

			// frostbolt.setPotionEffect(new ItemStack(Items.POTIONITEM));
			world.spawnEntity(frostbolt);

		}

		SoundUtils.playSoundAtPlayer(caster, SoundEvents.ENTITY_BLAZE_SHOOT, 1, 1);
		caster.swingArm(hand);
		return true;
	}

	@Override
	public String Name() {
		return "Frost_Bolt";
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
		return new EffectCalculation(new WaterDamage().Name(), 0.5F);
	}

	@Override
	public Elements Element() {
		return Elements.Water;
	}

	@Override
	public Item SpellItem() {
		return ItemFrostBolt.ITEM;
	}

	public class EffectFrostBolt extends BaseSpellEffect {

		public EffectFrostBolt() {
			super();

		}

		@Override
		public String Name() {
			return "Frost Bolt Damage";
		}

		@Override
		public void Activate(DamageData dmgdata, EntityLivingBase target) {

			DamageEffect dmg = new DamageEffect(dmgdata.caster, target,
					dmgdata.spellItem.GetDamage(dmgdata.casterUnit));

			System.out.println("Dmg is " + dmg.Number);
			dmg.Element = Elements.Water;

			dmg.Activate();

		}

	}

	@Override
	public String GUID() {
		return "FrostBolt";
	}

}