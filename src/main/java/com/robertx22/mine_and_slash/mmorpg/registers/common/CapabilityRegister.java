package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.uncommon.capability.*;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityRegister {
    public static void register() {

        CapabilityManager.INSTANCE.register(EntityCap.UnitData.class, new EntityCap.Storage(), EntityCap.DefaultImpl::new);

        CapabilityManager.INSTANCE.register(PlayerMapCap.IPlayerMapData.class, new PlayerMapCap.Storage(), PlayerMapCap.DefaultImpl::new);

        CapabilityManager.INSTANCE.register(MapCap.IMapData.class, new MapCap.Storage(), MapCap.DefaultImpl::new);

        CapabilityManager.INSTANCE.register(MasterLootBagCap.IMasterLootBagData.class, new MasterLootBagCap.Storage(), MasterLootBagCap.DefaultImpl::new);

        CapabilityManager.INSTANCE.register(PlayerCapBackupCap.IPlayerCapBackupData.class, new PlayerCapBackupCap.Storage(), PlayerCapBackupCap.DefaultImpl::new);

    }
}
