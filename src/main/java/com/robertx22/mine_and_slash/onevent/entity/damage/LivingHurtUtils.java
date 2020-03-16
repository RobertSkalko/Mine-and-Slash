package com.robertx22.mine_and_slash.onevent.entity.damage;

import com.robertx22.mine_and_slash.a_libraries.curios.MyCurioUtils;
import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.config.mod_dmg_whitelist.ModDmgWhitelistContainer;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.MyDamageSource;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.BossCap;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.ArrayList;
import java.util.List;

public class LivingHurtUtils {

    public static void onBossHurt(LivingEntity en) {
        en.getCapability(BossCap.Data)
            .ifPresent(x -> x.onHealthChanged(en, x));
    }

    public static void stopMobInWallDamageInMaps(LivingHurtEvent event) {
        try {
            if (event.getSource()
                .equals(DamageSource.IN_WALL)) {
                if (event.getEntityLiving() instanceof PlayerEntity == false) {
                    if (WorldUtils.isMapWorldClass(event.getEntityLiving().world)) {
                        event.setCanceled(true);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void damageCurioItems(LivingEntity en) {

        if (en instanceof PlayerEntity) {

            PlayerEntity player = (PlayerEntity) en;

            List<ItemStack> curios = MyCurioUtils.getAllSlots(player);

            curios.forEach(x -> x.damageItem(1, player, (entity) -> {
                entity.sendBreakAnimation(EquipmentSlotType.MAINHAND);
            }));

        }
    }

    public static void damageArmorItems(LivingEntity en) {

        List<ItemStack> stacks = new ArrayList<>();

        en.getArmorInventoryList()
            .forEach(x -> stacks.add(x));
        stacks.add(en.getHeldItemOffhand());

        stacks.forEach(x -> x.damageItem(1, en, (entity) -> {
            entity.sendBreakAnimation(EquipmentSlotType.MAINHAND);
        }));

    }

    public static void onAttack(LivingHurtEvent event) {

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

            if (event.getSource()
                .getTrueSource() instanceof LivingEntity) {
                LivingEntity source = (LivingEntity) event.getSource()
                    .getTrueSource();

                onAttack(source, target, event.getAmount(), event);
            }

        }

    }

    public static void onAttack(LivingEntity source, LivingEntity target, float amount, LivingHurtEvent event) {

        try {

            if (target.isAlive() == false) {
                return; // stops attacking dead mobs
            }

            UnitData sourceData = Load.Unit(source);

            if (sourceData == null) {
                return;
            }

            GearItemData weapondata = sourceData.getWeaponData(source);

            UnitData targetData = Load.Unit(target);

            if (targetData == null) {
                return;
            }

            targetData.tryRecalculateStats(target);
            sourceData.tryRecalculateStats(source);

            if (source instanceof PlayerEntity) {

                if (weapondata == null) {
                    ItemStack weapon = source.getHeldItemMainhand();
                    ModDmgWhitelistContainer.ModDmgWhitelist mod = ModDmgWhitelistContainer.getModDmgWhitelist(weapon);
                    if (mod != null) {
                        return;
                    }
                }

                if (sourceData.isWeapon(weapondata)) {
                    if (sourceData.tryUseWeapon(weapondata, source)) {
                        sourceData.attackWithWeapon(
                            event, source.getHeldItemMainhand(), weapondata, source, target, targetData);
                    }

                } else {
                    sourceData.unarmedAttack(event, source, target, targetData);
                }

            } else { // if its a mob
                sourceData.mobBasicAttack(event, source, target, sourceData, targetData, amount);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static boolean isForbiddenAttack(LivingHurtEvent event) {

        if (event.getSource()
            .getTrueSource() instanceof LivingEntity) {
            LivingEntity en = (LivingEntity) event.getSource()
                .getTrueSource();
            DamageSource source = event.getSource();

            Item item = en.getHeldItemMainhand()
                .getItem();

            if (item instanceof BowItem || item instanceof CrossbowItem) {
                if (source.damageType.contains("arrow")) {
                    return false;
                }
                if (source.damageType.contains("bolt")) {
                    return false;
                }
                if (source.damageType.contains("ammo")) {
                    return false;
                }
                if (source.damageType.contains("bullet")) {
                    return false;
                }
                if (source.damageType.contains("firework")) {
                    return false;
                }
                if (source.damageType.contains("dart")) {
                    return false;
                }
                if (source.damageType.contains("missile")) {
                    return false;
                }
                return true;

            } else {
                if (source instanceof IndirectEntityDamageSource) {
                    IndirectEntityDamageSource indi = (IndirectEntityDamageSource) source;

                    if (indi.getImmediateSource() instanceof TridentEntity) {
                        return false;
                    }

                    return true;
                }
            }
        }

        return false;
    }

    /**
     * If damage is from my source, leave the value, otherwise decrease it (this
     * makes my damage source the best one)
     *
     * @param event
     */
    public static void modifyDamage(LivingHurtEvent event) {
        if (event.getEntity().world.isRemote) {
            return;
        }
        if (event.getSource() == null) {
            return;
        }

        if (DmgSourceUtils.isMyDmgSource(event.getSource())) {
            DmgSourceUtils.removeSourceMarker(event.getSource());
            LivingHurtUtils.damageArmorItems(event.getEntityLiving());
            return;
        }

        // mobs take much less damage from any source other than my mods. This is
        // required or else there's no point in getting legendary weapons if a diamond
        // sword more damage

        if (isEnviromentalDmg(event.getSource())) {
            if (event.getEntity() instanceof PlayerEntity == false) {
                event.setAmount(
                    event.getAmount() * ModConfig.INSTANCE.Server.MOB_ENVIRONMENT_DAMAGE_MULTI.get()
                        .floatValue());
                return;
            }
        } else {

            // dont decrease dmg if its from whitelist item
            LivingEntity en = (LivingEntity) event.getSource()
                .getTrueSource();

            ModDmgWhitelistContainer.ModDmgWhitelist mod = ModDmgWhitelistContainer.getModDmgWhitelist(
                en.getHeldItemMainhand());

            if (mod != null) {
                event.setAmount(event.getAmount() * mod.dmgMultiplier);
                return;
            }

            event.setAmount(event.getAmount() * ModConfig.INSTANCE.Server.NON_MOD_DAMAGE_MULTI.get()
                .floatValue());

            return;
        }
    }

    public static boolean isEnviromentalDmg(DamageSource source) {
        return source.getTrueSource() instanceof LivingEntity == false;
    }

    public static void onHurtRecordNonPlayerDmg(LivingHurtEvent event) {

        LivingEntity defender = event.getEntityLiving();

        UnitData data = Load.Unit(defender);

        if (event.getSource() != null && event.getSource()
            .getTrueSource() instanceof LivingEntity) {
            LivingEntity attacker = (LivingEntity) event.getSource()
                .getTrueSource();
            data.onDamagedBy(attacker, event.getAmount(), defender);

        } else {
            data.onDamagedBy(null, event.getAmount(), defender);
        }

    }

}
