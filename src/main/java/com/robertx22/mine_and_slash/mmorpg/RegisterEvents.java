package com.robertx22.mine_and_slash.mmorpg;

import com.robertx22.mine_and_slash.mmorpg.registers.client.ItemDyeRegister;
import com.robertx22.mine_and_slash.onevent.entity.*;
import com.robertx22.mine_and_slash.onevent.entity.damage.OnHurtEvent;
import com.robertx22.mine_and_slash.onevent.item.*;
import com.robertx22.mine_and_slash.onevent.my_events.CollectGearEvent;
import com.robertx22.mine_and_slash.onevent.my_events.GiveExpSub;
import com.robertx22.mine_and_slash.onevent.ontick.OnTickLogic;
import com.robertx22.mine_and_slash.onevent.player.*;
import com.robertx22.mine_and_slash.onevent.testrender;
import com.robertx22.mine_and_slash.uncommon.capability.WeaponSpeedCap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class RegisterEvents {

    public static void register() {

        registerItemEvents();
        registerEntityEvents();
        registerPlayerEvents();
    }

    private static void register(Class theclass) {
        MinecraftForge.EVENT_BUS.register(theclass);
    }

    private static void registerPlayerEvents() {

        register(WeaponSpeedCap.class);
        register(GiveExpSub.class);
        register(OnDeath.class);
        register(OnPlayerClone.class);
        register(OnLogin.class);
        register(OnLogout.class);
        register(OnTickLogic.class);

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            register(OnKeyPress.class);

        });

    }

    private static void registerEntityEvents() {

        register(CollectGearEvent.class);
        register(OnHurtEvent.class);
        register(OnEquipChange.class);
        register(OnHealDecrease.class);
        register(OnMobDeathDrops.class);
        register(OnPotionChange.class);
        register(OnTrackEntity.class);
        register(OnMobSpawn.class);

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            register(testrender.class);
        });

    }

    private static void registerItemEvents() {

        register(OnContainerCompatibleItem.class);
        register(OnMissingMappings.class);
        register(OnPickupInsertIntoBag.class);
        register(OnPickUpSalvage.class);

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            register(OnTooltip.class);

            FMLJavaModLoadingContext.get()
                    .getModEventBus()
                    .register(ItemDyeRegister.class);

        });

    }

}
