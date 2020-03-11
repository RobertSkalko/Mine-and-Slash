package com.robertx22.mine_and_slash.mmorpg;

import com.robertx22.mine_and_slash.mmorpg.registers.client.ItemDyeRegister;
import com.robertx22.mine_and_slash.new_content.trader.TraderTooltipEvent;
import com.robertx22.mine_and_slash.onevent.entity.*;
import com.robertx22.mine_and_slash.onevent.entity.damage.OnHurtEvent;
import com.robertx22.mine_and_slash.onevent.item.*;
import com.robertx22.mine_and_slash.onevent.my_events.CollectGearEvent;
import com.robertx22.mine_and_slash.onevent.my_events.GiveExpSub;
import com.robertx22.mine_and_slash.onevent.ontick.OnBossTick;
import com.robertx22.mine_and_slash.onevent.ontick.OnClientTick;
import com.robertx22.mine_and_slash.onevent.ontick.OnServerTick;
import com.robertx22.mine_and_slash.onevent.player.*;
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

        register(OnDungeonBlockEvents.class);
        register(GiveExpSub.class);
        register(OnDeath.class);
        register(OnPlayerClone.class);
        register(OnLogin.class);
        register(OnServerTick.class);

        register(CapSync.class);
        register(StopCastingIfInteract.class);
        register(RightClickSpell.class);

        register(OnBossTick.class);

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            register(OnKeyPress.class);
            register(OnClientTick.class);
            //register(StitchTextures.class);
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
        register(OnTargetEvent.class);
        register(TridentSpawn.class);

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {

        });

    }

    private static void registerItemEvents() {

        register(OnContainerCompatibleItem.class);
        register(OnMissingMappings.class);
        register(OnPickupInsertIntoBag.class);
        register(OnPickUpSalvage.class);

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            register(OnTooltip.class);
            register(TraderTooltipEvent.class);

            FMLJavaModLoadingContext.get()
                .getModEventBus()
                .register(ItemDyeRegister.class);

        });

    }

}
