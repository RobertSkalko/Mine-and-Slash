package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.packets.*;
import com.robertx22.mine_and_slash.packets.allocation.abilities.TryAllocateAbilityPointPacket;
import com.robertx22.mine_and_slash.packets.allocation.abilities.TryRemoveAbilityPointPacket;
import com.robertx22.mine_and_slash.packets.allocation.schools.TryAddSchoolPointPacket;
import com.robertx22.mine_and_slash.packets.allocation.schools.TryRemoveSchoolPointPacket;
import com.robertx22.mine_and_slash.packets.allocation.stats.SpendStatPointPacket;
import com.robertx22.mine_and_slash.packets.allocation.talents.TryAllocateTalentPacket;
import com.robertx22.mine_and_slash.packets.allocation.talents.TryRemoveTalentPacket;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacket;
import com.robertx22.mine_and_slash.packets.spells.CastSpellPacket;
import com.robertx22.mine_and_slash.packets.spells.ChangeSpellHotbarPacket;
import com.robertx22.mine_and_slash.packets.spells.HotbarSetupPacket;
import com.robertx22.mine_and_slash.packets.spells.WeaponRightClickSpellPacket;
import com.robertx22.mine_and_slash.packets.sync_cap.RequestSyncCapToClient;
import com.robertx22.mine_and_slash.packets.sync_cap.SyncCapabilityToClient;
import com.robertx22.mine_and_slash.packets.trader.BuyTraderItemPacket;
import com.robertx22.mine_and_slash.packets.trader.TraderPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class PacketRegister {

    static int index = 0;

    // bit shorter
    private static <MSG> void reg(Class<MSG> messageType, BiConsumer<MSG, PacketBuffer> encoder,
                                  Function<PacketBuffer, MSG> decoder,
                                  BiConsumer<MSG, Supplier<NetworkEvent.Context>> messageConsumer) {

        MMORPG.Network.registerMessage(index++, messageType, encoder, decoder, messageConsumer);

    }

    public static void register() {

        reg(EntityUnitPacket.class, EntityUnitPacket::encode, EntityUnitPacket::decode, EntityUnitPacket::handle);

        reg(DmgNumPacket.class, DmgNumPacket::encode, DmgNumPacket::decode, DmgNumPacket::handle);

        reg(NoEnergyPacket.class, NoEnergyPacket::encode, NoEnergyPacket::decode, NoEnergyPacket::handle);

        reg(RequestTilePacket.class, RequestTilePacket::encode, RequestTilePacket::decode, RequestTilePacket::handle);

        reg(MasterBagPacket.class, MasterBagPacket::encode, MasterBagPacket::decode, MasterBagPacket::handle);

        reg(SyncCapabilityToClient.class, SyncCapabilityToClient::encode, SyncCapabilityToClient::decode,
            SyncCapabilityToClient::handle
        );

        reg(RequestSyncCapToClient.class, RequestSyncCapToClient::encode, RequestSyncCapToClient::decode,
            RequestSyncCapToClient::handle
        );

        reg(SpendStatPointPacket.class, SpendStatPointPacket::encode, SpendStatPointPacket::decode,
            SpendStatPointPacket::handle
        );

        reg(OpenGuiPacket.class, OpenGuiPacket::encode, OpenGuiPacket::decode, OpenGuiPacket::handle);

        reg(TryAllocateTalentPacket.class, TryAllocateTalentPacket::encode, TryAllocateTalentPacket::decode,
            TryAllocateTalentPacket::handle
        );

        reg(TryRemoveTalentPacket.class, TryRemoveTalentPacket::encode, TryRemoveTalentPacket::decode,
            TryRemoveTalentPacket::handle
        );

        reg(EfficientMobUnitPacket.class, EfficientMobUnitPacket::encode, EfficientMobUnitPacket::decode,
            EfficientMobUnitPacket::handle
        );

        reg(SyncConfigToClientPacket.class, SyncConfigToClientPacket::encode, SyncConfigToClientPacket::decode,
            SyncConfigToClientPacket::handle
        );

        reg(CastSpellPacket.class, CastSpellPacket::encode, CastSpellPacket::decode, CastSpellPacket::handle);

        reg(ChangeSpellHotbarPacket.class, ChangeSpellHotbarPacket::encode, ChangeSpellHotbarPacket::decode,
            ChangeSpellHotbarPacket::handle
        );

        reg(HotbarSetupPacket.class, HotbarSetupPacket::encode, HotbarSetupPacket::decode, HotbarSetupPacket::handle);

        reg(ParticlePacket.class, ParticlePacket::encode, ParticlePacket::decode, ParticlePacket::handle);

        reg(RegistryPacket.class, RegistryPacket::encode, RegistryPacket::decode, RegistryPacket::handle);

        reg(
            OnLoginClientPacket.class, OnLoginClientPacket::encode, OnLoginClientPacket::decode,
            OnLoginClientPacket::handle
        );

        reg(ScrabblePacket.class, ScrabblePacket::encode, ScrabblePacket::decode, ScrabblePacket::handle);

        reg(WeaponRightClickSpellPacket.class, WeaponRightClickSpellPacket::encode, WeaponRightClickSpellPacket::decode, WeaponRightClickSpellPacket::handle);

        reg(TraderPacket.class, TraderPacket::encode, TraderPacket::decode, TraderPacket::handle);

        reg(BuyTraderItemPacket.class, BuyTraderItemPacket::encode, BuyTraderItemPacket::decode, BuyTraderItemPacket::handle);

        reg(RarityPacket.class, RarityPacket::encode, RarityPacket::decode, RarityPacket::handle);

        reg(TryAllocateAbilityPointPacket.class, TryAllocateAbilityPointPacket::encode, TryAllocateAbilityPointPacket::decode, TryAllocateAbilityPointPacket::handle);

        reg(TryRemoveAbilityPointPacket.class, TryRemoveAbilityPointPacket::encode, TryRemoveAbilityPointPacket::decode, TryRemoveAbilityPointPacket::handle);

        reg(TryRemoveSchoolPointPacket.class, TryRemoveSchoolPointPacket::encode, TryRemoveSchoolPointPacket::decode, TryRemoveSchoolPointPacket::handle);

        reg(TryAddSchoolPointPacket.class, TryAddSchoolPointPacket::encode, TryAddSchoolPointPacket::decode, TryAddSchoolPointPacket::handle);

    }

}


