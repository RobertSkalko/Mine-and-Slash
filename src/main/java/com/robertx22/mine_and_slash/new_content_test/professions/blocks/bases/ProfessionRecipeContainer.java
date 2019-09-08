package com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases;

import com.robertx22.mine_and_slash.blocks.bases.BaseTileContainer;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ContainerTypeRegisters;
import com.robertx22.mine_and_slash.new_content_test.professions.blocks.alchemy.AlchemyTile;
import com.robertx22.mine_and_slash.new_content_test.professions.data.Professions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;

public class ProfessionRecipeContainer extends BaseTileContainer {

    public static int size = 6 * 9;

    Professions profession = Professions.ALCHEMY;

    public BlockPos pos;

    public ProfessionRecipeContainer(int i, PlayerInventory playerInventory,
                                     PacketBuffer packetBuffer) {
        this(i, new AlchemyTile(), packetBuffer.readBlockPos(), playerInventory);
    }

    protected ProfessionRecipeContainer(int id, ProfessionTile tile, BlockPos pos,
                                        PlayerInventory invPlayer) {
        super(6 * 9, ContainerTypeRegisters.PROFESSION_RECIPE_CONTAINER, id);
        this.profession = tile.profession;
        this.pos = pos;

    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }
}
