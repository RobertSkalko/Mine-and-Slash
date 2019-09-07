package com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases;

import com.robertx22.mine_and_slash.blocks.bases.BaseTileContainer;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ContainerTypeRegisters;
import com.robertx22.mine_and_slash.new_content_test.professions.blocks.alchemy.AlchemyTile;
import com.robertx22.mine_and_slash.new_content_test.professions.data.Professions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;

public class ProfessionCraftingContainer extends BaseTileContainer {

    public int numRows = 6;

    Professions profession = Professions.ALCHEMY;

    public BlockPos pos;

    public ProfessionCraftingContainer(int i, PlayerInventory playerInventory,
                                       PacketBuffer packetBuffer) {
        this(i, new AlchemyTile(), packetBuffer.readBlockPos(), playerInventory);
    }

    protected ProfessionCraftingContainer(int id, ProfessionTile tile, BlockPos pos,
                                          PlayerInventory invPlayer) {
        super(6 * 9, ContainerTypeRegisters.PROFESSION_CRAFTING_CONTAINER, id);
        this.profession = tile.profession;
        this.pos = pos;

        renderContainerInventory(tile);
        renderPlayerInventory(invPlayer);

    }

    public void renderContainerInventory(ProfessionTile tile) {

        for (ItemStack materail : tile.materialStacks) {

        }

        for (ItemStack materail : tile.outputStacks) {

        }
    }

    public void renderPlayerInventory(PlayerInventory playerInv) {

        int i = (this.numRows - 4) * 18;

        for (int l = 0; l < 3; ++l) {
            for (int j1 = 0; j1 < 9; ++j1) {
                this.addSlot(new Slot(playerInv, j1 + l * 9 + 9, 8 + j1 * 18, ProfessionCraftingGui.y / 2 + l * 18 + i));
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
