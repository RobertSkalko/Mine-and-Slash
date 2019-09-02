package com.robertx22.mine_and_slash.uncommon.effectdatas;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.spells.bases.MyDamageSource;
import com.robertx22.mine_and_slash.database.stats.stat_effects.defense.BlockEffect;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.network.DmgNumPacket;
import com.robertx22.mine_and_slash.onevent.entity.damage.DmgSourceUtils;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.*;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.HealthUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.NumberUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.HashMap;
import java.util.Map.Entry;

public class DamageEffect extends EffectData implements IArmorReducable, IPenetrable, IDamageEffect, IElementalResistable, IElementalPenetrable, ICrittable {

    public DamageEffect(LivingHurtEvent event, LivingEntity source, LivingEntity target,
                        int dmg, UnitData sourceData, UnitData targetData,
                        EffectTypes effectType, WeaponTypes weptype) {
        super(source, target, sourceData, targetData);

        this.setEffectType(effectType, weptype);
        this.number = dmg;
        this.event = event;
    }

    LivingHurtEvent event;

    private HashMap<Elements, Integer> bonusElementDamageMap = new HashMap();

    public Elements getHighestBonusElementalDamageElement() {

        int highest = 0;
        Elements ele = null;
        for (Entry<Elements, Integer> entry : bonusElementDamageMap.entrySet()) {
            if (entry.getValue() > highest) {
                ele = entry.getKey();
                highest = entry.getValue();
            }
        }
        return ele;

    }

    public void addBonusEleDmgDivideByMulti(Elements element, float dmg) {
        addBonusEleDmg(element, dmg / damageMultiplier);
    }

    public void addBonusEleDmg(Elements element, float dmg) {
        if (bonusElementDamageMap.containsKey(element)) {
            bonusElementDamageMap.put(element, (int) (bonusElementDamageMap.get(element) + dmg));

        } else {
            bonusElementDamageMap.put(element, (int) dmg);
        }
    }

    public static String dmgSourceName = Ref.MODID + ".custom_damage";
    public Elements element = Elements.Physical;
    public int armorPene;
    public int elementalPene;

    public float damageMultiplier = 1;

    public float healthHealed;
    public float manaRestored;
    public boolean isFullyBlocked = false;
    public boolean isPartiallyBlocked = false;
    public boolean isDodged = false;

    public boolean isBlocked() {

        if (isFullyBlocked || isPartiallyBlocked) {
            return true;
        }
        return false;
    }

    public float getActualDamage() {
        float dmg = this.number * damageMultiplier; // this way axes can do double damage instead of doing double attacks
        dmg = HealthUtils.DamageToMinecraftHealth(dmg + 1, target, targetData);
        return dmg;
    }

    public float getVisibleDamage() {
        float dmg = this.number * damageMultiplier; // this way axes can do double damage instead of doing double attacks
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

        if (target.getHealth() <= 0F || !target.isAlive()) {
            return;
        }

        if (this.canceled) {
            return;
        }

        MyDamageSource dmgsource = new MyDamageSource(dmgSourceName, this.source, element, (int) number);

        if (this.isPartiallyBlocked) {
            dmgsource.setDamageBypassesArmor();
        }

        if (this.isFullyBlocked == false) {

            this.sourceData.onAttackEntity(source, target);

            DmgByElement info = getDmgByElement();

            float dmg = info.totalDmg;
            dmg += getEventDmg() * ModConfig.INSTANCE.Server.NON_MOD_DAMAGE_MULTI.get()
                    .floatValue();

            if (event != null) {
                event.setAmount(dmg);
                event.getSource()
                        .setDamageBypassesArmor(); // this also sets it as unblockable..
                event.getSource().setDamageIsAbsolute();
                DmgSourceUtils.markSourceAsMine(event.getSource());

            } else {
                int hurtResistantTime = target.hurtResistantTime;
                target.hurtResistantTime = 0;
                target.attackEntityFrom(dmgsource, dmg);
                target.hurtResistantTime = hurtResistantTime;

            }

            Heal();
            RestoreMana();

            if (dmg > 0 && source instanceof ServerPlayerEntity && info.highestDmgElement != null) {

                ServerPlayerEntity player = (ServerPlayerEntity) source;
                DmgNumPacket packet = new DmgNumPacket(target, info.highestDmgElement, NumberUtils
                        .formatDamageNumber(this, (int) HealthUtils.vanillaHealthToActualHealth(dmg, target, targetData)));
                MMORPG.sendToClient(packet, player);

            }
        }

        BlockEffect.removeKnockbackResist(target);

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
            sourceData.heal(new HealData(source, sourceData, healed));
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

    private DmgByElement getDmgByElement() {
        DmgByElement info = new DmgByElement();

        for (Entry<Elements, Integer> entry : bonusElementDamageMap.entrySet()) {
            if (entry.getValue() > 0) {
                DamageEffect bonus = new DamageEffect(null, source, target, entry.getValue(), this.sourceData, this.targetData, EffectTypes.BONUS_ATTACK, this.weaponType);
                bonus.element = entry.getKey();
                bonus.damageMultiplier = this.damageMultiplier;

                info.addDmg(bonus.getActualDamage(), bonus.element);

            }
        }
        info.addDmg(this.getActualDamage(), this.element);

        return info;

    }

    @Override
    public LivingEntity Source() {
        return source;
    }

    @Override
    public LivingEntity Target() {
        return target;
    }

    @Override
    public float Number() {
        return number;
    }

    @Override
    public Elements GetElement() {
        return element;
    }

    @Override
    public void SetArmorPenetration(int val) {
        this.armorPene = val;

    }

    @Override
    public void addElementalPenetration(int val) {
        this.elementalPene += val;
    }

    @Override
    public int GetArmorPenetration() {
        return this.armorPene;
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
        return this.elementalPene;
    }

}
