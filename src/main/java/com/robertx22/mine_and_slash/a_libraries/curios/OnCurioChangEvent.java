package com.robertx22.mine_and_slash.a_libraries.curios;

import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.event.LivingCurioChangeEvent;

@Mod.EventBusSubscriber
public class OnCurioChangEvent {

    @SubscribeEvent
    public static void onEquipCurioChange(LivingCurioChangeEvent event) {

        LivingEntity entity = event.getEntityLiving();
        if (entity != null) {
            EntityCap.UnitData data = Load.Unit(entity);
            if (data != null) {
                data.setEquipsChanged(true);
                data.recalculateStats(entity);

                if (entity instanceof ServerPlayerEntity) {
                    data.syncToClient((ServerPlayerEntity) entity);
                }
            }
        }
    }

}
