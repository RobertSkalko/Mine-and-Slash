package com.robertx22.mine_and_slash.uncommon.datasaving;

import com.robertx22.mine_and_slash.uncommon.capability.MapCap;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerCapBackupCap;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerMapCap;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class Load {

    public static boolean hasUnit(
            ICapabilityProvider provider) { // tterag said this is correct
        if (provider != null) {
            return provider.getCapability(EntityCap.Data).isPresent();
        }
        return false;
    }

    public static MapCap.IMapData mapData(ICapabilityProvider provider) {

        if (provider != null) {

            return provider.getCapability(MapCap.Data).orElse(new MapCap.DefaultImpl());

        }
        return null;
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

    public static PlayerMapCap.IPlayerMapData playerMapData(PlayerEntity provider) {

        if (provider != null) {
            return provider.getCapability(PlayerMapCap.Data)
                    .orElse(new PlayerMapCap.DefaultImpl());
        }
        return null;
    }

}
