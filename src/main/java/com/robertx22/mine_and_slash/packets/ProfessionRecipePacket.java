package com.robertx22.mine_and_slash.packets;

import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.professions.blocks.bases.ProfessionContainer;
import com.robertx22.mine_and_slash.professions.blocks.bases.ProfessionTile;
import com.robertx22.mine_and_slash.professions.recipe.BaseRecipe;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
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

    public static ProfessionRecipePacket decode(PacketBuffer buf) {

        ProfessionRecipePacket newpkt = new ProfessionRecipePacket();

        newpkt.pos = buf.readBlockPos();
        newpkt.recipeGUID = buf.readString(50);

        return newpkt;

    }

    public static void encode(ProfessionRecipePacket packet, PacketBuffer tag) {

        tag.writeBlockPos(packet.pos);
        tag.writeString(packet.recipeGUID);

    }

    public static void handle(final ProfessionRecipePacket pkt, Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {

                ServerPlayerEntity player = ctx.get().getSender();

                if (player != null) {
                    TileEntity tile = player.world.getTileEntity(pkt.pos);

                    if (tile instanceof ProfessionTile) {

                        BaseRecipe recipe = pkt.recipeGUID.isEmpty() ? null : SlashRegistry.Recipes()
                                .get(pkt.recipeGUID);

                        ProfessionTile prof = (ProfessionTile) tile;
                        prof.currentRecipe = recipe;

                        Container container = player.openContainer;

                        if (container instanceof ProfessionContainer) {
                            ProfessionContainer profcont = (ProfessionContainer) container;

                            profcont.onRecipeChoosen(recipe);

                        }

                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);

    }

}
