package com.robertx22.mine_and_slash.uncommon.effectdatas;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.MyDamageSource;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.onevent.entity.damage.DamageEventData;
import com.robertx22.mine_and_slash.onevent.entity.damage.DmgSourceUtils;
import com.robertx22.mine_and_slash.packets.DmgNumPacket;
import com.robertx22.mine_and_slash.potion_effects.bases.IOnBasicAttackPotion;
import com.robertx22.mine_and_slash.potion_effects.bases.IOnBasicAttackedPotion;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.saveclasses.unit.ResourcesData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.*;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.NumberUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TeamUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class DamageEffect extends EffectData implements IArmorReducable, IPenetrable, IDamageEffect,
    IElementalResistable, IElementalPenetrable, ICrittable {
    public static String ARROW_DMG_MULTI_TAG = Ref.MODID + ":dmg_multi";

    public DamageEffect(LivingHurtEvent event, LivingEntity source, LivingEntity target, int dmg, UnitData sourceData,
                        UnitData targetData, EffectTypes effectType, WeaponTypes weptype) {
        super(source, target, sourceData, targetData);

        this.setEffectType(effectType, weptype);
        this.number = dmg;
        this.event = event;
    }

    public DamageEffect(DamageEventData data, int dmg, EffectTypes effectType, WeaponTypes weptype) {
        super(data.source, data.target, data.sourceData, data.targetData);

        this.setEffectType(effectType, weptype);
        this.number = dmg;
        this.event = data.event;
    }

    public DamageEffect(LivingHurtEvent event, LivingEntity source, LivingEntity target, int dmg,
                        EffectTypes effectType, WeaponTypes weptype) {
        super(source, target, Load.Unit(source), Load.Unit(target));

        this.setEffectType(effectType, weptype);
        this.number = dmg;
        this.event = event;
    }

    LivingHurtEvent event;

    private HashMap<Elements, Integer> bonusElementDamageMap = new HashMap();

    public boolean isElemental() {
        return this.element != null && this.element != Elements.Physical;
    }

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

    public float percentIncrease = 0;

    public static String dmgSourceName = Ref.MODID + ".custom_damage";
    public Elements element = Elements.Physical;
    public int armorPene;
    public int elementalPene;

    public float damageMultiplier = 1;

    public float healthHealed;
    public float magicShieldRestored;
    public float manaRestored;
    public boolean isDodged = false;

    public boolean isDmgAllowed() {
        return !isDodged;
    }

    public float getActualDamage() {
        float dmg = this.number * damageMultiplier;

        if (dmg <= 0) {
            return 0;
        }

        float percentMulti = 1 + this.percentIncrease / 100;

        dmg = modifyByAttackSpeedIfMelee(dmg);

        dmg = modifyIfArrowDamage(dmg);

        return dmg * percentMulti;
    }

    private float modifyByAttackSpeedIfMelee(float dmg) {

        if (this.weaponType.isMelee) {
            float cool = 1;
            if (this.source instanceof PlayerEntity) {

                GearItemData gear = Gear.Load(source.getHeldItemMainhand());

                float atkpersec = gear.GetBaseGearType()
                    .getAttacksPerSecondCalculated(sourceData);

                float secWaited = (float) (source.ticksExisted - source.getLastAttackedEntityTime()) / 20F;

                if (secWaited > atkpersec) {
                    secWaited = atkpersec;
                }

                cool = secWaited / atkpersec;

                dmg *= cool;

            }
        }

        return dmg;

    }

    private float modifyIfArrowDamage(float dmg) {
        if (event != null && event.getSource() != null) {
            if (event.getSource()
                .getImmediateSource() instanceof AbstractArrowEntity) {

                CompoundNBT arrownbt = event.getSource()
                    .getImmediateSource()
                    .getPersistentData();

                if (!arrownbt.contains(ARROW_DMG_MULTI_TAG)) {
                    System.out.println("Arrow didn't save charge tag? Means the mixin broke.");
                }

                float arrowmulti = arrownbt
                    .getFloat(ARROW_DMG_MULTI_TAG);

                dmg *= arrowmulti;
                // multiply dmg by saved charge value
            }
        }

        return dmg;

    }

    public float getVisibleDamage() {
        float dmg = this.number * damageMultiplier;
        return dmg;
    }

    public float getEventDmg() {
        if (event != null) {
            return event.getAmount();
        } else {
            return 0;
        }

    }

    boolean removeKnockback = false;

    public void removeKnockback() {
        removeKnockback = true;
    }

    public boolean areBothPlayers() {
        if (source instanceof ServerPlayerEntity && target instanceof ServerPlayerEntity) {
            return true;
        }
        return false;
    }

    public void cancelDamage() {
        this.canceled = true;
        if (event != null) {
            event.setAmount(0);
            event.setCanceled(true);
        }
        return;
    }

    @Override
    protected void activate() {

        if (target.getHealth() <= 0F || !target.isAlive()) {
            return;
        }

        if (areBothPlayers()) {
            if (TeamUtils.areOnSameTeam((ServerPlayerEntity) source, (ServerPlayerEntity) target)) {
                return;
            }
        } else {
            if (this instanceof SpellDamageEffect) {
                if (target instanceof TameableEntity) {
                    if (source instanceof PlayerEntity) {
                        TameableEntity tame = (TameableEntity) target;
                        if (tame.isOwner(source)) {
                            cancelDamage();
                            return;
                        }
                    }
                }
            }
        }

        if (!this.isDmgAllowed()) {
            cancelDamage();
            return;
        }

        DmgByElement info = getDmgByElement();
        float dmg = info.totalDmg;

        if (this.canceled) {
            cancelDamage();
            return;
        }
        DamageSource ds = null;

        if (event != null) {
            ds = event.getSource();
        }

        MyDamageSource dmgsource = new MyDamageSource(ds, dmgSourceName, this.source, element, (int) number);

        if (isDodged) {
            cancelDamage();
            SoundUtils.playSound(target, SoundEvents.ITEM_SHIELD_BLOCK, 1, 1.5F);

        } else {

            this.sourceData.onAttackEntity(source, target);

            if (event != null) {
                event.setAmount(dmg);
                DmgSourceUtils.markSourceAsMine(event.getSource());

            } else {
                int hurtResistantTime = target.hurtResistantTime;
                target.hurtResistantTime = 0;
                target.attackEntityFrom(dmgsource, dmg);
                target.hurtResistantTime = hurtResistantTime;
            }

            Heal();
            RestoreMana();
            restoreMagicShield();

            if (dmg > 0) {

                onEventPotions();

                if (source instanceof ServerPlayerEntity) {

                    info.dmgmap.entrySet()
                        .forEach(x -> {
                            if (x.getValue() > 0) {
                                ServerPlayerEntity player = (ServerPlayerEntity) source;

                                String str = NumberUtils.formatDamageNumber(this, x.getValue()
                                    .intValue());
                                DmgNumPacket packet = new DmgNumPacket(target, x.getKey(), str);
                                MMORPG.sendToClient(packet, player);
                            }
                        });

                }
            }
        }

    }

    private void onEventPotions() {

        if (this.getEffectType() == EffectTypes.BASIC_ATTACK) {
            List<EffectInstance> onAttacks = source.getActivePotionEffects()
                .stream()
                .filter(x -> x.getPotion() instanceof IOnBasicAttackPotion)
                .collect(Collectors.toList());

            onAttacks.forEach(x -> ((IOnBasicAttackPotion) x.getPotion()).OnBasicAttack(source, target));

            List<EffectInstance> onAttackeds = target.getActivePotionEffects()
                .stream()
                .filter(x -> x.getPotion() instanceof IOnBasicAttackedPotion)
                .collect(Collectors.toList());

            onAttackeds.forEach(x -> ((IOnBasicAttackedPotion) x.getPotion()).onBasicAttacked(x, source, target));

        }
    }

    public void RestoreMana() {
        int restored = (int) manaRestored;
        if (restored > 0) {

            sourceData.getResources()
                .modify(new ResourcesData.Context(sourceData, source, ResourcesData.Type.MANA, restored,
                    ResourcesData.Use.RESTORE
                ));

        }
    }

    public void Heal() {
        int healed = (int) healthHealed;
        if (healed > 0) {
            sourceData.getResources()
                .modify(new ResourcesData.Context(sourceData, source, ResourcesData.Type.HEALTH, healed,
                    ResourcesData.Use.RESTORE
                ));
        }
    }

    public void restoreMagicShield() {
        int healed = (int) magicShieldRestored;
        if (healed > 0) {
            sourceData.getResources()
                .modify(new ResourcesData.Context(sourceData, source, ResourcesData.Type.MAGIC_SHIELD, healed,
                    ResourcesData.Use.RESTORE
                ));
        }
    }

    public DamageEffect setMultiplier(float multi) {
        this.damageMultiplier = multi;
        return this;
    }

    public static class DmgByElement {

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

    public DmgByElement getDmgByElement() {
        DmgByElement info = new DmgByElement();

        for (Entry<Elements, Integer> entry : bonusElementDamageMap.entrySet()) {
            if (entry.getValue() > 0) {
                DamageEffect bonus = new DamageEffect(null, source, target, entry.getValue(), this.sourceData,
                    this.targetData, EffectTypes.BONUS_ATTACK, this.weaponType
                );
                bonus.element = entry.getKey();
                bonus.damageMultiplier = this.damageMultiplier;
                bonus.calculateEffects();
                float dmg = bonus.getActualDamage();

                info.addDmg(dmg, bonus.element);

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
    public void setCrit(boolean bool) {
        crit = bool;
    }

    @Override
    public boolean isCriticalHit() {
        return crit;
    }

    @Override
    public int GetElementalPenetration() {
        return this.elementalPene;
    }

}
