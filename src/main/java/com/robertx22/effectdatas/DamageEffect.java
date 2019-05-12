package com.robertx22.effectdatas;

import java.util.HashMap;
import java.util.Map.Entry;

import com.robertx22.database.stat_types.defense.BlockStrength;
import com.robertx22.effectdatas.interfaces.IArmorReducable;
import com.robertx22.effectdatas.interfaces.ICrittable;
import com.robertx22.effectdatas.interfaces.IDamageEffect;
import com.robertx22.effectdatas.interfaces.IElementalPenetrable;
import com.robertx22.effectdatas.interfaces.IElementalResistable;
import com.robertx22.effectdatas.interfaces.IPenetrable;
import com.robertx22.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mmorpg.Main;
import com.robertx22.mmorpg.Ref;
import com.robertx22.mmorpg.config.ModConfig;
import com.robertx22.network.DamageNumberPackage;
import com.robertx22.saveclasses.Unit;
import com.robertx22.spells.bases.MyDamageSource;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.utilityclasses.HealthUtils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class DamageEffect extends EffectData
	implements IArmorReducable, IPenetrable, IDamageEffect, IElementalResistable, IElementalPenetrable, ICrittable {

    public DamageEffect(EntityLivingBase source, EntityLivingBase target, int dmg) {
	super(source, target);

	this.Number = dmg;

    }

    public DamageEffect(EntityLivingBase source, EntityLivingBase target, int dmg, UnitData sourceData,
	    UnitData targetData, EffectTypes effectType, WeaponTypes weptype) {
	super(source, target, sourceData, targetData);

	this.setEffectType(effectType, weptype);
	this.Number = dmg;
    }

    public HashMap<Elements, Integer> BonusElementDamageMap = new HashMap();

    public static String DmgSourceName = Ref.MODID + ".custom_damage";
    public Elements Element = Elements.None;
    public int ArmorPene;
    public int ElementalPene;

    public float healthHealed;
    public float manaRestored;

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

    @Override
    protected void activate() {

	boolean fullyblocked = false;

	MyDamageSource dmgsource = new MyDamageSource(DmgSourceName, this.Source, Element, (int) Number);
	float dmg = HealthUtils.DamageToMinecraftHealth(Number + 1, Target);

	if (canBlockDamageSource(Target, dmgsource)) {

	    float blockval = targetUnit.MyStats.get(BlockStrength.GUID).Value;

	    float afterblock = Number - blockval;

	    if (afterblock < 0) {
		fullyblocked = true;
	    } else {
		dmgsource = new MyDamageSource(DmgSourceName, this.Source, Element, (int) afterblock);
	    }

	    dmgsource.setDamageBypassesArmor();

	} else {

	}

	if (fullyblocked == false) {
	    Target.hurtResistantTime = 0; // this allows to add bonus damages at the same second
	    Target.attackEntityFrom(dmgsource, dmg);

	    addBonusElementDamage();
	    Heal();
	    RestoreMana();

	    if (ModConfig.Client.RENDER_CHAT_COMBAT_LOG) {
		LogCombat();
	    }

	    if ((int) Number > 0 && Source instanceof EntityPlayerMP) {

		Main.Network.sendTo(new DamageNumberPackage(Target, this.Element, FormatDamageNumber(this)),
			(EntityPlayerMP) Source);
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

    private void addBonusElementDamage() {
	for (Entry<Elements, Integer> entry : BonusElementDamageMap.entrySet()) {
	    if (entry.getValue() > 0) {
		DamageEffect bonus = new DamageEffect(Source, Target, entry.getValue());
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
