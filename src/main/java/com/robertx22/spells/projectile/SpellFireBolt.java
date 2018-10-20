package com.robertx22.spells.projectile;

import com.robertx22.customitems.spells.ItemFireBolt;
import com.robertx22.database.stats.types.elementals.damage.FireDamage;
import com.robertx22.effectdatas.DamageEffect;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.spells.bases.BaseSpellEffect;
import com.robertx22.spells.bases.DamageData;
import com.robertx22.spells.bases.EffectCalculation;
import com.robertx22.spells.bases.IEntityDamageSource;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.utilityclasses.SoundUtils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
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
			projectile.setPosition(caster.posX + look.x, caster.posY + look.y + 1.3, caster.posZ + look.z);

			projectile.accelerationX = look.x * 0.1;
			projectile.accelerationY = look.y * 0.1;
			projectile.accelerationZ = look.z * 0.1;

			projectile.SetData(new DamageData(caster, new EffectFireBolt(), data));

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

	public class EntityFireBolt extends EntitySmallFireball implements IEntityDamageSource {
		public EntityFireBolt(World par1World) {
			super(par1World);
		}

		DamageData data = null;

		@Override
		protected void onImpact(RayTraceResult result) {
			if (result.entityHit != null && result.entityHit instanceof EntityLivingBase) {

				if (data != null) {

					data.effect.Activate(data, (EntityLivingBase) result.entityHit);

				} else {
					System.out.println("doesnt work!");
				}
			}

			if (!this.world.isRemote) {
				this.world.setEntityState(this, (byte) 3);
				this.setDead();
			}
		}

		@Override
		public void SetData(DamageData data) {
			this.data = data;

		}

		@Override
		public DamageData GetData() {
			return data;
		}
	}

	public class EffectFireBolt extends BaseSpellEffect {

		public EffectFireBolt() {
			super();

		}

		@Override
		public String Name() {
			return "Fire Bolt Damage";
		}

		@Override
		public void Activate(DamageData dmgdata, EntityLivingBase target) {

			DamageEffect dmg = new DamageEffect(dmgdata.caster, target,
					dmgdata.spellItem.GetDamage(dmgdata.casterUnit));

			dmg.Element = Elements.Fire;

			dmg.Activate();

		}
	}

	@Override
	public String GUID() {
		return "FireBolt";
	}

}