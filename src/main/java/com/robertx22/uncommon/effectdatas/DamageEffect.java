package com.robertx22.uncommon.effectdatas;

import java.util.HashMap;
import java.util.Map.Entry;

import com.robertx22.config.ModConfig;
import com.robertx22.database.stat_types.defense.BlockStrength;
import com.robertx22.mmorpg.Main;
import com.robertx22.mmorpg.Ref;
import com.robertx22.network.DamageNumberPackage;
import com.robertx22.onevent.combat.damage.DmgSourceUtils;
import com.robertx22.saveclasses.Unit;
import com.robertx22.spells.bases.MyDamageSource;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.effectdatas.interfaces.IArmorReducable;
import com.robertx22.uncommon.effectdatas.interfaces.ICrittable;
import com.robertx22.uncommon.effectdatas.interfaces.IDamageEffect;
import com.robertx22.uncommon.effectdatas.interfaces.IElementalPenetrable;
import com.robertx22.uncommon.effectdatas.interfaces.IElementalResistable;
import com.robertx22.uncommon.effectdatas.interfaces.IPenetrable;
import com.robertx22.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.utilityclasses.HealthUtils;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class DamageEffect extends EffectData
		implements IArmorReducable, IPenetrable, IDamageEffect, IElementalResistable, IElementalPenetrable, ICrittable {

	public DamageEffect(LivingHurtEvent event, EntityLivingBase source, EntityLivingBase target, int dmg, UnitData sourceData, UnitData targetData,
            EffectTypes effectType, WeaponTypes weptype) {
		super(source, target, sourceData, targetData);

		this.setEffectType(effectType, weaponType);
		this.Number = dmg;
		this.event = event;
	}

	LivingHurtEvent event;

	public HashMap<Elements, Integer> BonusElementDamageMap = new HashMap();

	public static String DmgSourceName = Ref.MODID + ".custom_damage";
	public Elements Element = Elements.None;
	public int ArmorPene;
	public int ElementalPene;
	
	public float damageMultiplier = 1;

	public float healthHealed;
	public float manaRestored;
	public boolean isFullyBlocked = false;
	public boolean isPartiallyBlocked = false;
	public boolean isDodged = false;

	private boolean canBlockDamageSource(EntityLivingBase target, DamageSource damageSourceIn) {
		if (!damageSourceIn.isUnblockable() && target.isActiveItemStackBlocking()) {
			Vec3d vec3d = damageSourceIn.getDamageLocation();

			if (vec3d != null) {
				Vec3d vec3d1 = target.getLook(1.0F);
				Vec3d vec3d2 = vec3d.subtractReverse(new Vec3d(target.posX, target.posY, target.posZ)).normalize();
				vec3d2 = new Vec3d(vec3d2.x, 0.0D, vec3d2.z);

				if (vec3d2.dotProduct(vec3d1) < 0.0D) {
					return true;
				}
			}
		}

		return false;
	}

	public float getActualDamage() {
		float dmg = this.Number * damageMultiplier; // this way axes can do double damage instead of doing double
													// attacks
		dmg = HealthUtils.DamageToMinecraftHealth(dmg + 1, Target, targetData);
		return dmg;
	}

	public float getVisibleDamage() {
		float dmg = this.Number * damageMultiplier; // this way axes can do double damage instead of doing double
													// attacks
		return dmg;
	}

	public float getEventDmg() {
		if (event != null) {
			return event.getAmount();
		} else {
			return 0;
		}

	}

	@Override
	protected void activate() {

		 if (Target.getHealth() <= 0F || !Target.isEntityAlive()) {
	            return;
	        }

	        if (this.canceled) {
	            return;
	        }

	        MyDamageSource dmgsource = new MyDamageSource(DmgSourceName, this.Source, Element, (int) Number);

	        if (this.isPartiallyBlocked) {
	            dmgsource.setDamageBypassesArmor();
	        }


		if (this.isFullyBlocked == false) {
			
			this.sourceData.onAttackEntity(Source, Target);

			DmgByElement info = getDmgByElement();

			float dmg1 = info.totalDmg;
			dmg1 += getEventDmg() * ModConfig.Server.NON_MOD_DAMAGE_MULTI;

			if (event != null) {
				event.setAmount(dmg1);
				event.getSource().setDamageBypassesArmor(); // this also sets it as unblockable.. AND STOPS ARMOR FROM
															// BEING DAMAGED
				event.getSource().setDamageIsAbsolute();
				DmgSourceUtils.markSourceAsMine(event.getSource());

			} else {
				int hurtResistantTime = Target.hurtResistantTime;
				Target.hurtResistantTime = 0;
				Target.attackEntityFrom(dmgsource, dmg1);
				Target.hurtResistantTime = hurtResistantTime;

			}
		}

	}

	private void RestoreMana() {
		int restored = (int) manaRestored;
		if (restored > 0) {
			this.sourceData.restoreMana(restored);
		}
	}

	private void Heal() {
		int healed = (int) healthHealed;
		if (healed > 0) {
			sourceData.heal(Source, healed);
		}
	}
	
	public DamageEffect setMultiplier(float multi) {
        this.damageMultiplier = multi;
        return this;
    }
	
	static class DmgByElement {

        public HashMap<Elements, Float> dmgmap = new HashMap<>();
        public Elements highestDmgElement;
        public float highestDmgValue;
        public float totalDmg = 0;

        public void addDmg(float dmg, Elements element) {

            Elements ele = element;

            if (ele == null) {
                ele = Elements.Physical;
            }

            float total = (dmgmap.getOrDefault(element, 0F) + dmg);

            dmgmap.put(ele, total);

            totalDmg += dmg;

            if (total > highestDmgValue) {
                highestDmgElement = ele;
                highestDmgValue = total;
            }

        }

    }

	private void addBonusElementDamage() {
		for (Entry<Elements, Integer> entry : BonusElementDamageMap.entrySet()) {
			if (entry.getValue() > 0) {
				DamageEffect bonus = new DamageEffect(null, Source, Target, entry.getValue(), this.sourceData, this.targetData, EffectTypes.BONUS_ATTACK, this.weaponType);
				bonus.setEffectType(EffectTypes.BONUS_ATTACK, this.weaponType);
				bonus.Element = entry.getKey();
				bonus.Activate();
			}
		}
	}

	private void LogCombat() {

		if (this.getEffectType().equals(EffectTypes.BONUS_ATTACK)) { // don't spam chat with bonus damaages
			return;
		}

		if (this.Source instanceof EntityPlayer) {

			String s = CLOC.word("dealt") + LogDamage() + CLOC.word("to") + " " + this.Target.getName() + " "
					+ LogCurrentHP(this.Target, this.targetUnit);
			this.Source.sendMessage(new TextComponentString(s));

		}

		if (this.Target instanceof EntityPlayer) {

			String s = CLOC.word("took") + LogDamage() + CLOC.word("from") + " " + this.Source.getName() + " "
					+ LogCurrentHP(this.Target, this.targetUnit);
			this.Target.sendMessage(new TextComponentString(s));

		}

	}

	private String LogCurrentHP(EntityLivingBase entity, Unit unit) {

		String str = TextFormatting.LIGHT_PURPLE + "[" + unit.health().CurrentValue(entity, unit) + "/"
				+ (int) unit.healthData().Value + "]";

		return str;

	}

	public static String FormatNumber(int Number) {

		String num = "";
		if (Number > 10000000) {
			int tenmillions = (int) (Number / 10000000);

			int leftover = (int) ((Number - tenmillions * 10000000) / 1000000);

			num = tenmillions + "." + leftover + "k";
		} else {
			num = Number + "";
		}

		return num;
	}

	public static String FormatDamageNumber(DamageEffect data) {
		String num = FormatNumber((int) data.Number);

		if (data.crit) {
			num += "!";

		}

		return num;
	}

	private String LogDamage() {

		String num = FormatDamageNumber(this);

		String str = " " + num + " " + CLOC.word("damage") + " ";

		if (Element == null || Element.equals(Elements.None)) {
			str = TextFormatting.GRAY + str;
		} else {
			if (Element.equals(Elements.Fire)) {
				str = TextFormatting.RED + str;
			}
			if (Element.equals(Elements.Water)) {
				str = TextFormatting.BLUE + str;
			}
			if (Element.equals(Elements.Thunder)) {
				str = TextFormatting.YELLOW + str;
			}
			if (Element.equals(Elements.Nature)) {
				str = TextFormatting.GREEN + str;
			}
		}

		return str;

	}
	
	private DmgByElement getDmgByElement() {
        DmgByElement info = new DmgByElement();

        for (Entry<Elements, Integer> entry : BonusElementDamageMap.entrySet()) {
            if (entry.getValue() > 0) {
            	DamageEffect bonus = new DamageEffect(null, Source, Target, entry.getValue(), this.sourceData, this.targetData, EffectTypes.BONUS_ATTACK, this.weaponType);
                bonus.Element = entry.getKey();
                bonus.damageMultiplier = this.damageMultiplier;

                info.addDmg(bonus.getActualDamage(), bonus.Element);

            }
        }
        info.addDmg(this.getActualDamage(), this.Element);

        return info;

    }

	@Override
	public EntityLivingBase Source() {
		return Source;
	}

	@Override
	public EntityLivingBase Target() {
		return Target;
	}

	@Override
	public float Number() {
		return Number;
	}

	@Override
	public Elements GetElement() {
		return Element;
	}

	@Override
	public void SetArmorPenetration(int val) {
		this.ArmorPene = val;

	}

	@Override
	public void SetElementalPenetration(int val) {
		this.ElementalPene = val;
	}

	@Override
	public int GetArmorPenetration() {
		return this.ArmorPene;
	}

	public boolean crit = false;

	@Override
	public void SetCrit(boolean bool) {
		crit = bool;

	}

	@Override
	public boolean GetCrit() {
		return crit;
	}

	@Override
	public int GetElementalPenetration() {
		return this.ElementalPene;
	}

	public boolean isBlocked() {

        if (isFullyBlocked || isPartiallyBlocked) {
            return true;
        }
        return false;
    }

}
