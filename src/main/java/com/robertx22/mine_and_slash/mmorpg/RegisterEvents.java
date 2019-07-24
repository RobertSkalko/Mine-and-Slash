package com.robertx22.mine_and_slash.mmorpg;

import com.robertx22.mine_and_slash.onevent.entity.*;
import com.robertx22.mine_and_slash.onevent.item.*;
import com.robertx22.mine_and_slash.onevent.ontick.OnTickLogic;
import com.robertx22.mine_and_slash.onevent.player.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;

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

        register(GiveExpSub.class);
        register(OnDeathInMap.class);
        register(OnPlayerClone.class);
        register(OnLogin.class);
        register(OnLogout.class);
        register(OnTickLogic.class);

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            register(OnKeyPress.class);

        });

    }

    private static void registerEntityEvents() {

        register(OnHurt.class);
        register(OnMeleeAttack.class);
        register(OnEquipChange.class);
        register(OnHealDecrease.class);
        register(OnMobDeathDrops.class);
        register(OnPotionChange.class);
        register(OnTrackEntity.class);
        register(OnMobSpawn.class);

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {

        });

    }

    private static void registerItemEvents() {

        register(OnContainerCompatibleItem.class);
        register(OnLeftClickHearthstone.class);
        register(OnMissingMappings.class);
        register(OnPickupInsertIntoBag.class);
        register(OnPickUpSalvage.class);

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            register(OnTooltip.class);
        });

    }

}
