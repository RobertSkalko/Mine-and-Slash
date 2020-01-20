package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.network.*;
import com.robertx22.mine_and_slash.network.sync_cap.RequestSyncCapToClient;
import com.robertx22.mine_and_slash.network.sync_cap.SyncCapabilityToClient;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class PacketRegister {

    static int index = 0;

    // bit shorter
    private static <MSG> void reg(Class<MSG> messageType,
                                  BiConsumer<MSG, PacketBuffer> encoder,
                                  Function<PacketBuffer, MSG> decoder,
                                  BiConsumer<MSG, Supplier<NetworkEvent.Context>> messageConsumer) {

        MMORPG.Network.registerMessage(index++, messageType, encoder, decoder, messageConsumer);

    }

    public static void register() {

        reg(EntityUnitPacket.class, EntityUnitPacket::encode, EntityUnitPacket::decode, EntityUnitPacket::handle);

        reg(DmgNumPacket.class, DmgNumPacket::encode, DmgNumPacket::decode, DmgNumPacket::handle);

        reg(NoEnergyPacket.class, NoEnergyPacket::encode, NoEnergyPacket::decode, NoEnergyPacket::handle);

        reg(ParticleGenPacket.class, ParticleGenPacket::encode, ParticleGenPacket::decode, ParticleGenPacket::handle);

        reg(RequestTilePacket.class, RequestTilePacket::encode, RequestTilePacket::decode, RequestTilePacket::handle);

        reg(MasterBagPacket.class, MasterBagPacket::encode, MasterBagPacket::decode, MasterBagPacket::handle);

        reg(RarityItemDropPacket.class, RarityItemDropPacket::encode, RarityItemDropPacket::decode, RarityItemDropPacket::handle);

        reg(ProfessionRecipePacket.class, ProfessionRecipePacket::encode, ProfessionRecipePacket::decode, ProfessionRecipePacket::handle);

        reg(SyncCapabilityToClient.class, SyncCapabilityToClient::encode, SyncCapabilityToClient::decode, SyncCapabilityToClient::handle);

        reg(RequestSyncCapToClient.class, RequestSyncCapToClient::encode, RequestSyncCapToClient::decode, RequestSyncCapToClient::handle);

        reg(SpendStatPointPacket.class, SpendStatPointPacket::encode, SpendStatPointPacket::decode, SpendStatPointPacket::handle);

        reg(OpenPickStatsGuiPacket.class, OpenPickStatsGuiPacket::encode, OpenPickStatsGuiPacket::decode, OpenPickStatsGuiPacket::handle);

        reg(AllocateTalentPacket.class, AllocateTalentPacket::encode, AllocateTalentPacket::decode, AllocateTalentPacket::handle);

        reg(OpenTalentsGuiPacket.class, OpenTalentsGuiPacket::encode, OpenTalentsGuiPacket::decode, OpenTalentsGuiPacket::handle);

        reg(TryRemoveTalentPacket.class, TryRemoveTalentPacket::encode, TryRemoveTalentPacket::decode, TryRemoveTalentPacket::handle);

        reg(UseMapTicketPacket.class, UseMapTicketPacket::encode, UseMapTicketPacket::decode, UseMapTicketPacket::handle);

        reg(EfficientMobUnitPacket.class, EfficientMobUnitPacket::encode, EfficientMobUnitPacket::decode, EfficientMobUnitPacket::handle);

        reg(SyncConfigToClientPacket.class, SyncConfigToClientPacket::encode, SyncConfigToClientPacket::decode, SyncConfigToClientPacket::handle);

        reg(CastSpellPacket.class, CastSpellPacket::encode, CastSpellPacket::decode, CastSpellPacket::handle);

    }

}


