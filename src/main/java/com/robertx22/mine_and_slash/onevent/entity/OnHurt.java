package com.robertx22.mine_and_slash.onevent.entity;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.config.mod_dmg_whitelist.ModDmgWhitelistContainer;
import com.robertx22.mine_and_slash.spells.bases.MyDamageSource;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnHurt {

    /**
     * If damage is from my source, leave the value, otherwise decrease it (this
     * makes my damage source the best one)
     *
     * @param event
     */
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void OnLivingHurt(LivingHurtEvent event) {

        if (event.getEntity().world.isRemote) {
            return;
        }

        if (event.getSource() instanceof MyDamageSource) {
            onHurt(event);
            return;
        }

        // mobs take much less damage from any source other than my mods. This is
        // required or else there's no point in getting legendary weapons if a diamond
        // sword more damage
        if (event.getSource() != null) {
            if (event.getSource().getTrueSource() instanceof LivingEntity == false) {
                if (event.getEntity() instanceof PlayerEntity == false) {
                    event.setAmount(event.getAmount() * ModConfig.INSTANCE.Server.MOB_ENVIRONMENT_DAMAGE_MULTI
                            .get()
                            .floatValue());
                    return;
                }
            } else {

                // dont decrease dmg if its from whitelist item
                LivingEntity en = (LivingEntity) event.getSource().getTrueSource();

                ModDmgWhitelistContainer.ModDmgWhitelist mod = ModDmgWhitelistContainer.getModDmgWhitelist(en
                        .getHeldItemMainhand());

                if (mod != null) {
                    event.setAmount(event.getAmount() * mod.dmgMultiplier);
                    return;
                }

                event.setAmount(event.getAmount() * ModConfig.INSTANCE.Server.NON_MOD_DAMAGE_MULTI
                        .get()
                        .floatValue());
                return;
            }

        }

    }

    public static void onHurt(LivingHurtEvent event) {

        Entity defender = event.getEntityLiving();

        if (defender instanceof PlayerEntity == false) {

            if (event.getSource() != null) {
                Entity attacker = event.getSource().getTrueSource();

                if (attacker instanceof PlayerEntity == false) {
                    UnitData data = Load.Unit(event.getEntityLiving());
                    if (data != null) {
                        data.onDamagedByNonPlayer(event.getAmount());
                    }
                }
            } else {
                UnitData data = Load.Unit(event.getEntityLiving());
                if (data != null) {
                    data.onDamagedByNonPlayer(event.getAmount());
                }
            }
        }
    }
}
