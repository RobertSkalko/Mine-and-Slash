package com.robertx22.effectdatas;

import com.robertx22.effectdatas.interfaces.IArmorReducable;
import com.robertx22.effectdatas.interfaces.ICrittable;
import com.robertx22.effectdatas.interfaces.IDamageEffect;
import com.robertx22.effectdatas.interfaces.IElementalPenetrable;
import com.robertx22.effectdatas.interfaces.IElementalResistable;
import com.robertx22.effectdatas.interfaces.IPenetrable;
import com.robertx22.mmorpg.Main;
import com.robertx22.mmorpg.ModConfig;
import com.robertx22.mmorpg.Ref;
import com.robertx22.network.DamageNumberPackage;
import com.robertx22.saveclasses.DamageNumberData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.spells.bases.MyDamageSource;
import com.robertx22.uncommon.datasaving.bases.Saving;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.utilityclasses.HealthUtils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class DamageEffect extends EffectData
		implements IArmorReducable, IPenetrable, IDamageEffect, IElementalResistable, IElementalPenetrable, ICrittable {

	public DamageEffect(EntityLivingBase source, EntityLivingBase target, int dmg) {
		super(source, target);

		this.Number = dmg;

	}

	public static String DmgSourceName = Ref.MODID + "_Custom_Damage";
	public Elements Element = Elements.None;
	public int ArmorPene;
	public int ElementalPene;

	@Override
	protected void activate() {

		MyDamageSource dmgsource = new MyDamageSource(DmgSourceName, this.Source, Element, (int) Number);

		float dmg = HealthUtils.DamageToMinecraftHealth(Number + 1, Target);
		Target.hurtResistantTime = 0; // this allows to add bonus damages at the same second
		Target.attackEntityFrom(dmgsource, dmg);

		if (ModConfig.GUI.RENDER_CHAT_COMBAT_LOG) {
			LogCombat();
		}

		if (ModConfig.GUI.RENDER_FLOATING_DAMAGE && (int) Number > 0) {
			NetworkRegistry.TargetPoint point = new NetworkRegistry.TargetPoint(Target.dimension, Target.posX,
					Target.posY, Target.posZ, 32);

			Main.Network.sendToAllAround(
					new DamageNumberPackage(
							Saving.ToString(new DamageNumberData(FormatDamageNumber(this), this.Element, Target))),
					point);
		}

	}

	private void LogCombat() {

		if (this.Type.equals(EffectTypes.BONUS_ATTACK)) { // don't spam chat with bonus damaages
			return;
		}

		if (this.Source instanceof EntityPlayer) {

			String s = "Dealt " + LogDamage() + " to " + this.Target.getName() + " "
					+ LogCurrentHP(this.Target, this.targetUnit);
			this.Source.sendMessage(new TextComponentString(s));

		}

		if (this.Target instanceof EntityPlayer) {

			String s = "Took " + LogDamage() + " from " + this.Source.getName() + " "
					+ LogCurrentHP(this.Target, this.targetUnit);
			this.Target.sendMessage(new TextComponentString(s));

		}

	}

	private String LogCurrentHP(EntityLivingBase entity, Unit unit) {

		String str = TextFormatting.LIGHT_PURPLE + "[" + unit.health().CurrentValue(entity, unit) + "/"
				+ (int) unit.health().Value + "]";

		return str;

	}

	public static String FormatNumber(int Number) {

		String num = "";
		if (Number > 1000) {
			int thousands = (int) (Number / 1000);

			int leftover = (int) ((Number - thousands * 1000) / 100);

			num = thousands + "." + leftover + "k";
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

		String str = num + " DMG ";

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

}
