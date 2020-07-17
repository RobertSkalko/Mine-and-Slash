package com.robertx22.mine_and_slash.mmorpg;

import com.robertx22.mine_and_slash.onevent.entity.*;
import com.robertx22.mine_and_slash.onevent.entity.damage.OnHurtEvent;
import com.robertx22.mine_and_slash.onevent.item.*;
import com.robertx22.mine_and_slash.onevent.my_events.CollectGearEvent;
import com.robertx22.mine_and_slash.onevent.ontick.OnClientTick;
import com.robertx22.mine_and_slash.onevent.ontick.OnServerTick;
import com.robertx22.mine_and_slash.onevent.player.*;
import com.robertx22.mine_and_slash.onevent.world.WorldTickMinute;
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

        register(OnPlayerClone.class);
        register(OnLogin.class);
        register(OnServerTick.class);

        register(CapSync.class);
        register(StopCastingIfInteract.class);
        register(RightClickSpell.class);

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            register(OnKeyPress.class);
            register(OnClientTick.class);
        });

    }

    private static void registerEntityEvents() {

        register(OnPlayerAtkEntity.class);
        register(CollectGearEvent.class);
        register(OnHurtEvent.class);
        register(OnEquipChange.class);
        register(OnMobDeathDrops.class);
        register(OnPotionChange.class);
        register(OnTrackEntity.class);
        register(OnMobSpawn.class);
        register(OnTargetEvent.class);
        register(WorldTickMinute.class);

    }

    private static void registerItemEvents() {

        register(OnContainerCompatibleItem.class);
        register(OnMissingMappings.class);
        register(OnPickupInsertIntoBag.class);

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            register(OnTooltip.class);
            register(ItemNamesOnGround.class);
        });

    }

}
