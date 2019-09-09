package com.robertx22.mine_and_slash.network;

import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases.ProfessionTile;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.BaseRecipe;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ProfessionRecipePacket {

    public BlockPos pos;
    public String recipeGUID;

    public ProfessionRecipePacket() {

    }

    public ProfessionRecipePacket(BlockPos pos, BaseRecipe recipe) {
        this.pos = pos;
        this.recipeGUID = recipe.GUID();

    }

    public ProfessionRecipePacket(BlockPos pos) {
        this.pos = pos;
        this.recipeGUID = "";

    }

    public static ProfessionRecipePacket decode(PacketBuffer buf) {

        ProfessionRecipePacket newpkt = new ProfessionRecipePacket();

        newpkt.pos = buf.readBlockPos();
        newpkt.recipeGUID = buf.readString();

        return newpkt;

    }

    public static void encode(ProfessionRecipePacket packet, PacketBuffer tag) {

        tag.writeBlockPos(packet.pos);
        tag.writeString(packet.recipeGUID);

    }

    public static void handle(final ProfessionRecipePacket pkt,
                              Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {

                ServerPlayerEntity player = ctx.get().getSender();

                if (player != null) {
                    TileEntity tile = player.world.getTileEntity(pkt.pos);

                    if (tile instanceof ProfessionTile) {

                        BaseRecipe recipe = pkt.recipeGUID.isEmpty() ? null : SlashRegistry
                                .Recipes()
                                .get(pkt.recipeGUID);

                        ProfessionTile prof = (ProfessionTile) tile;
                        prof.currentRecipe = recipe;

                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);

    }

}
