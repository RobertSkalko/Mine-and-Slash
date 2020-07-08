package com.robertx22.mine_and_slash.uncommon.datasaving;

import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerMapCap;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerStatsPointsCap;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerTalentsCap;
import com.robertx22.mine_and_slash.uncommon.capability.server_wide.PlayerCapBackupCap;
import com.robertx22.mine_and_slash.uncommon.capability.world.AntiMobFarmCap;
import com.robertx22.mine_and_slash.uncommon.capability.world.WorldMapCap;
import net.minecraft.entity.LivingEntity;
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
    public static PlayerSpellCap.ISpellsCap spells(LivingEntity provider) {

        if (provider instanceof PlayerEntity) {
            return provider.getCapability(PlayerSpellCap.Data)
                .orElse(new PlayerSpellCap.DefaultImpl());
        } else {
            return new PlayerSpellCap.DefaultImpl();

        }
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

    public static AntiMobFarmCap.IAntiMobFarmData antiMobFarm(World provider) {

        if (provider != null) {
            return provider.getCapability(AntiMobFarmCap.Data)
                .orElse(new AntiMobFarmCap.DefaultImpl());
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
