package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.network.*;

public class PacketRegister {
    public static void register() {
        int index = 0;

        MMORPG.Network.registerMessage(index++, EntityUnitPacket.class, EntityUnitPacket::encode, EntityUnitPacket::decode, EntityUnitPacket::handle);

        MMORPG.Network.registerMessage(index++, DmgNumPacket.class, DmgNumPacket::encode, DmgNumPacket::decode, DmgNumPacket::handle);

        MMORPG.Network.registerMessage(index++, NoEnergyPacket.class, NoEnergyPacket::encode, NoEnergyPacket::decode, NoEnergyPacket::handle);

        MMORPG.Network.registerMessage(index++, ParticleGenPacket.class, ParticleGenPacket::encode, ParticleGenPacket::decode, ParticleGenPacket::handle);

        MMORPG.Network.registerMessage(index++, PlayerUnitPacket.class, PlayerUnitPacket::encode, PlayerUnitPacket::decode, PlayerUnitPacket::handle);

        MMORPG.Network.registerMessage(index++, RequestTilePacket.class, RequestTilePacket::encode, RequestTilePacket::decode, RequestTilePacket::handle);

        MMORPG.Network.registerMessage(index++, MasterBagPacket.class, MasterBagPacket::encode, MasterBagPacket::decode, MasterBagPacket::handle);

    }
}


