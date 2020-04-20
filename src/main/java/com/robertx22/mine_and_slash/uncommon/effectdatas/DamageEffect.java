package com.robertx22.mine_and_slash.uncommon.effectdatas;

import com.robertx22.mine_and_slash.api.MineAndSlashEvents;
import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.MyDamageSource;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnBasicAttackSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnDamageDoneSynergy;
import com.robertx22.mine_and_slash.database.stats.effects.defense.BlockEffect;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.onevent.entity.damage.DamageEventData;
import com.robertx22.mine_and_slash.onevent.entity.damage.DmgSourceUtils;
import com.robertx22.mine_and_slash.packets.DmgNumPacket;
import com.robertx22.mine_and_slash.potion_effects.bases.IOnBasicAttackPotion;
import com.robertx22.mine_and_slash.potion_effects.bases.IOnBasicAttackedPotion;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.*;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.HealthUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.NumberUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TeamUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class DamageEffect extends EffectData implements IArmorReducable, IPenetrable, IDamageEffect,
    IElementalResistable, IElementalPenetrable, ICrittable {

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

    public static String dmgSourceName = Ref.MODID + ".custom_damage";
    public Elements element = Elements.Physical;
    public int armorPene;
    public int elementalPene;

    public float damageMultiplier = 1;

    public float healthHealed;
    public float magicShieldRestored;
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

    public boolean isDmgAllowed() {
        return !isFullyBlocked && !isDodged;
    }

    public float getActualDamage() {
        float dmg = this.number * damageMultiplier; // this way axes can do double damage instead of doing double
        // attacks

        if (dmg <= 0) {
            return 0;
        }

        dmg = HealthUtils.DamageToMinecraftHealth(dmg, target, targetData);

        return dmg;
    }

    public float getVisibleDamage() {
        float dmg = this.number * damageMultiplier; // this way axes can do double damage instead of doing double
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

    private void activateSynergies() {

        if (this.activateSynergies) {
            if (this instanceof SpellDamageEffect) {
                SpellDamageEffect e = (SpellDamageEffect) this;

                e.spell.getAllocatedSynergies(Load.spells(e.source))
                    .forEach(x -> {
                        if (x instanceof OnDamageDoneSynergy) {
                            OnDamageDoneSynergy s = (OnDamageDoneSynergy) x;
                            s.tryActivate(e);
                        }
                    });
            } else {
                if (this.getEffectType()
                    .equals(EffectTypes.BASIC_ATTACK)) {

                    PlayerSpellCap.ISpellsCap cap = Load.spells(source);

                    cap.getAbilitiesData()
                        .getAllocatedSynergies()
                        .forEach(x -> {
                            if (x instanceof OnBasicAttackSynergy) {
                                OnBasicAttackSynergy s = (OnBasicAttackSynergy) x;
                                s.tryActivate(this);
                            }
                        });

                }

            }

        }

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

        if (this.removeKnockback || this.isFullyBlocked) {
            BlockEffect.applyKnockbackResist(target);
        }

        if (!this.isDmgAllowed()) {
            cancelDamage();
            return;
        }

        DmgByElement info = getDmgByElement();
        float dmg = info.totalDmg;

        if (getEffectType()
            .equals(EffectTypes.BASIC_ATTACK)) {
            if (weaponType != null && weaponType.isMelee) {

                if (this.source instanceof PlayerEntity) {

                    PlayerEntity player = (PlayerEntity) source;

                    // TODO SEEMS NOT NEEDED if (false && player.ticksExisted != sourceData.getLastHitTicksExisted()) { // allow sword sweep and other aoe stuff that happens at same tick

                    float cooldown = sourceData.getAttackCooldown();

                    dmg = dmg * (0.2F + cooldown * cooldown * 0.8F);

                    if (cooldown > 0.9F) {
                        if (player.getActiveItemStack()
                            .getItem() instanceof SwordItem == false) {
                            player.spawnSweepParticles();
                        }

                    }

                    if (cooldown < 0.1F || dmg <= 0) {

                        if (event != null) {
                            player.resetCooldown();
                            event.setCanceled(true);
                        }

                        return;
                    }
                }

            }
        }

        if (this.canceled) {
            cancelDamage();
            return;
        }
        DamageSource ds = null;

        if (event != null) {
            ds = event.getSource();
        }

        MyDamageSource dmgsource = new MyDamageSource(ds, dmgSourceName, this.source, element, (int) number);

        if (this.isPartiallyBlocked) {
            dmgsource.setDamageBypassesArmor();
        }

        if (isDodged) {
            cancelDamage();
            SoundUtils.playSound(target, SoundEvents.ITEM_SHIELD_BLOCK, 1, 1.5F);

        } else {

            MinecraftForge.EVENT_BUS.post(new MineAndSlashEvents.OnDmgDoneEvent(this.source, this));

            activateSynergies();

            this.sourceData.onAttackEntity(source, target);

            dmg += getEventDmg() * ModConfig.INSTANCE.Server.NON_MOD_DAMAGE_MULTI.get()
                .floatValue();

            if (event != null) {
                event.setAmount(dmg);
                event.getSource()
                    .setDamageBypassesArmor(); // this also sets it as unblockable.. AND STOPS ARMOR FROM BEING
                // DAMAGED
                event.getSource()
                    .setDamageIsAbsolute();
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

                if (source instanceof ServerPlayerEntity && info.highestDmgElement != null) {

                    ServerPlayerEntity player = (ServerPlayerEntity) source;

                    String str = NumberUtils.formatDamageNumber(this, (int) HealthUtils.vanillaHealthToActualHealth(dmg,
                        target,
                        targetData
                    ));

                    DmgNumPacket packet = new DmgNumPacket(target, info.highestDmgElement, str

                    );
                    MMORPG.sendToClient(packet, player);

                }
            }
        }

        BlockEffect.removeKnockbackResist(target);

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
