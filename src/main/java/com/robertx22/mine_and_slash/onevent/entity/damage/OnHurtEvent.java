package com.robertx22.mine_and_slash.onevent.entity.damage;

import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

//  seems atk speed isnt actually atk speed, but some weird dmg modification?
public class OnHurtEvent {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onHurtEvent(LivingHurtEvent event) {

        if (event.getEntity().world.isRemote) {
            return;
        }

        LivingHurtUtils.onAttack(event);
        LivingHurtUtils.modifyDamage(event);
        LivingHurtUtils.onHurtRecordNonPlayerDmg(event);
        LivingHurtUtils.damageCurioItems(event.getEntityLiving());
    }

}
