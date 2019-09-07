package com.robertx22.mine_and_slash.new_content_test.professions.blocks;

import com.robertx22.mine_and_slash.blocks.bases.BaseTileContainer;
import com.robertx22.mine_and_slash.blocks.slots.handlerslots.RecipeSlot;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ContainerTypeRegisters;
import com.robertx22.mine_and_slash.new_content_test.professions.blocks.alchemy.AlchemyTile;
import com.robertx22.mine_and_slash.new_content_test.professions.data.Professions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.ItemStackHandler;

public class ProfessionRecipeContainer extends BaseTileContainer {

    public static int size = 9 * 6;
    public static int numRows = 6;

    Professions profession = Professions.ALCHEMY;

    public BlockPos pos;

    public ProfessionRecipeContainer(int i, PlayerInventory playerInventory,
                                     PacketBuffer packetBuffer) {
        this(i, new AlchemyTile(), packetBuffer.readBlockPos(), playerInventory);
    }

    protected ProfessionRecipeContainer(int id, ProfessionTile tile, BlockPos pos,
                                        PlayerInventory invPlayer) {
        super(size, ContainerTypeRegisters.PROFESSION_RECIPE_CONTAINER, id);
        this.profession = tile.profession;
        this.pos = pos;
        renderInventory(new ItemStackHandler(tile.recipeStacks), invPlayer);
    }

    public void renderInventory(ItemStackHandler inventory, PlayerInventory playerInv) {

        int i = (this.numRows - 4) * 18;

        for (int j = 0; j < this.numRows; ++j) {
            for (int k = 0; k < 9; ++k) {
                this.addSlot(new RecipeSlot(inventory, k + j * 9, 8 + k * 18, 18 + j * 18));
            }
        }

        for (int l = 0; l < 3; ++l) {
            for (int j1 = 0; j1 < 9; ++j1) {
                this.addSlot(new Slot(playerInv, j1 + l * 9 + 9, 8 + j1 * 18, 103 + l * 18 + i));
            }
        }

        for (int i1 = 0; i1 < 9; ++i1) {
            this.addSlot(new Slot(playerInv, i1, 8 + i1 * 18, 161 + i));
        }

    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }
}
