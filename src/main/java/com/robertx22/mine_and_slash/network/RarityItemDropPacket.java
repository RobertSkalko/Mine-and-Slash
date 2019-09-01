package com.robertx22.mine_and_slash.network;

import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.database.rarities.base.DropSoundData;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class RarityItemDropPacket {

    public int rarity;
    public BlockPos pos;

    public RarityItemDropPacket() {

    }

    public RarityItemDropPacket(int rar, BlockPos pos) {
        this.rarity = rar;
        this.pos = pos;
    }

    public static RarityItemDropPacket decode(PacketBuffer buf) {

        RarityItemDropPacket newpkt = new RarityItemDropPacket();

        newpkt.rarity = buf.readInt();
        newpkt.pos = buf.readBlockPos();

        return newpkt;

    }

    public static void encode(RarityItemDropPacket packet, PacketBuffer tag) {

        tag.writeInt(packet.rarity);
        tag.writeBlockPos(packet.pos);

    }

    public static void handle(final RarityItemDropPacket pkt,
                              Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {

                PlayerEntity player = MMORPG.proxy.getPlayerEntityFromContext(ctx);

                if (player != null) {

                    GearRarity rar = Rarities.Items.get(pkt.rarity);

                    DropSoundData data = rar.getDropSound();

                    if (data.hasSound()) {
                        player.world.playSound(player, pkt.pos, data.sound, SoundCategory.PLAYERS, data.volume, data.pitch);
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);

    }

}
