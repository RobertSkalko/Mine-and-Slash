package com.robertx22.mine_and_slash.onevent.entity;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.config.mod_dmg_whitelist.ModDmgWhitelistContainer;
import com.robertx22.mine_and_slash.database.spells.bases.MyDamageSource;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnMeleeAttack {

    // AttackEntityEvent doesnt work with things like bow..

    // damageevent could work, maybe dmg event for players but livingattack event for mobs?

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onMobMeleeAttack(LivingAttackEvent event) {

        LivingEntity target = event.getEntityLiving();

        if (target.world.isRemote) {
            return;
        }

        if (event.getSource() != null) {

            if (isForbiddenAttack(event)) {
                return;
            }

            if (event.getSource() instanceof MyDamageSource || event.getSource()
                    .getDamageType()
                    .equals(DamageEffect.dmgSourceName)) {
                return;
            }

            if (event.getSource().getTrueSource() instanceof LivingEntity) {
                LivingEntity source = (LivingEntity) event.getSource().getTrueSource();

                onAttack(true, source, target, event.getAmount());
            }

        }

    }

    public static void onAttack(boolean useCooldown, LivingEntity source,
                                LivingEntity target, float amount) {

        try {

            if (target.isAlive() == false) {
                return; // stops attacking dead mobs
            }

            UnitData sourceData = Load.Unit(source);

            if (sourceData == null) {
                return;
            }

            GearItemData weapondata = sourceData.getWeaponData(source);

            if (useCooldown) {
                if (ModConfig.INSTANCE.Server.USE_ATTACK_COOLDOWN.get()) {
                    if (sourceData.attackCooldownAllowsAttack(amount, source, target, weapondata) == false) {
                        return;
                    }
                }
            }

            UnitData targetData = Load.Unit(target);

            if (targetData == null) {
                return;
            }

            targetData.recalculateStats(target);
            sourceData.recalculateStats(source);

            if (source instanceof PlayerEntity) {

                if (weapondata == null) {
                    ItemStack weapon = source.getHeldItemMainhand();
                    ModDmgWhitelistContainer.ModDmgWhitelist mod = ModDmgWhitelistContainer
                            .getModDmgWhitelist(weapon);
                    if (mod != null) {
                        return;
                    }
                }

                if (sourceData.isWeapon(weapondata)) {
                    if (sourceData.tryUseWeapon(weapondata, source)) {
                        sourceData.attackWithWeapon(source.getHeldItemMainhand(), weapondata, source, target, targetData);
                    }

                } else {
                    sourceData.unarmedAttack(source, target, targetData);
                }

            } else { // if its a mob

                sourceData.mobBasicAttack(source, target, sourceData, targetData, amount);

            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public static boolean isForbiddenAttack(LivingAttackEvent event) {

        if (event.getSource().getTrueSource() instanceof LivingEntity) {
            LivingEntity en = (LivingEntity) event.getSource().getTrueSource();
            DamageSource source = event.getSource();

            Item item = en.getHeldItem(en.getActiveHand()).getItem();

            if (item instanceof BowItem || item instanceof CrossbowItem) {
                return !source.isProjectile();
            } else {
                return source instanceof IndirectEntityDamageSource;
            }
        }

        return false;
    }

}
