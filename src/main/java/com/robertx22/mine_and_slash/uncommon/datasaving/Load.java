package com.robertx22.mine_and_slash.uncommon.datasaving;

import com.robertx22.mine_and_slash.uncommon.capability.entity.BossCap;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.capability.player.*;
import com.robertx22.mine_and_slash.uncommon.capability.server_wide.PlayerCapBackupCap;
import com.robertx22.mine_and_slash.uncommon.capability.world.WorldMapCap;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;

public class Load {

    public static boolean hasUnit(ICapabilityProvider provider) { // tterag said this is correct
        if (provider != null) {
            return provider.getCapability(EntityCap.Data)
                .isPresent();
        }
        return false;
    }

    public static boolean isBoss(ICapabilityProvider provider) { // tterag said this is correct
        if (provider != null) {
            if (provider.getCapability(BossCap.Data)
                .isPresent()) {
                return provider.getCapability(BossCap.Data)
                    .orElse(new BossCap.DefaultImpl())
                    .isBoss();
            }
        }
        return false;
    }

    public static PlayerStatsPointsCap.IPlayerStatPointsData statPoints(PlayerEntity provider) {

        if (provider != null) {

            return provider.getCapability(PlayerStatsPointsCap.Data)
                .orElse(new PlayerStatsPointsCap.DefaultImpl());

        }
        return null;
    }

    @Nonnull
    public static WorldMapCap.IWorldMapData world(World provider) {

        LazyOptional<WorldMapCap.IWorldMapData> mapData = provider.getCapability(WorldMapCap.Data);

        return mapData.orElse(new WorldMapCap.DefaultImpl());

    }

    @Nonnull
    public static PlayerTalentsCap.IPlayerTalentsData talents(PlayerEntity provider) {

        return provider.getCapability(PlayerTalentsCap.Data)
            .orElse(new PlayerTalentsCap.DefaultImpl());
    }

    @Nonnull
    public static PlayerSpellCap.ISpellsCap spells(PlayerEntity provider) {

        return provider.getCapability(PlayerSpellCap.Data)
            .orElse(new PlayerSpellCap.DefaultImpl());
    }

    public static PlayerCapBackupCap.IPlayerCapBackupData playersCapBackup(World world) {

        if (world != null) {

            return world.getCapability(PlayerCapBackupCap.Data)
                .orElse(new PlayerCapBackupCap.DefaultImpl());

        }
        return null;
    }

    public static UnitData Unit(ICapabilityProvider provider) {

        if (provider != null) {

            return provider.getCapability(EntityCap.Data)
                .orElse(new EntityCap.DefaultImpl());

        }
        return null;
    }

    public static PlayerLastThrownItemCap.ILastThrownItem lastThrown(PlayerEntity provider) {

        if (provider != null) {

            return provider.getCapability(PlayerLastThrownItemCap.Data)
                .orElse(new PlayerLastThrownItemCap.DefaultImpl());

        }
        return null;
    }

    public static BossCap.IBossData boss(ICapabilityProvider provider) {

        if (provider != null) {

            return provider.getCapability(BossCap.Data)
                .orElse(new BossCap.DefaultImpl());

        }
        return null;
    }

    public static PlayerMapCap.IPlayerMapData playerMapData(PlayerEntity provider) {

        if (provider != null) {
            return provider.getCapability(PlayerMapCap.Data)
                .orElse(new PlayerMapCap.DefaultImpl());
        }
        return null;
    }

}
