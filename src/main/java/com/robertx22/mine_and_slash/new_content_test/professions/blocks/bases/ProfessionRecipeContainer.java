package com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases;

import com.robertx22.mine_and_slash.blocks.bases.BaseTileContainer;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ContainerTypeRegisters;
import com.robertx22.mine_and_slash.new_content_test.professions.blocks.alchemy.AlchemyTile;
import com.robertx22.mine_and_slash.new_content_test.professions.data.Professions;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.BaseMaterial;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ProfessionRecipeContainer extends BaseTileContainer {

    public static int size = 6 * 9;

    ProfessionTile tile;
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
        this.tile = tile;

        renderPlayerInventory(invPlayer);
        renderContainerInventory(tile);
    }

    public void renderContainerInventory(ProfessionTile tile) {

        int addX = 142 + 44;
        int addY = ProfessionRecipeGui.y / 2 + 9;

        ItemStackHandler handler = new ItemStackHandler(tile.outputStacks.size());

        for (int i = 0; i < tile.outputStacks.size(); i++) {
            handler.setStackInSlot(i, tile.outputStacks.get(i));
            this.addSlot(new SlotItemHandler(handler, i, addX, addY));
            addX += 18;

        }

        addX = 142 + 44;
        addY -= 52; // for mat slots

        handler = new ItemStackHandler(tile.materialStacks.size());
        for (int i = 0; i < tile.materialStacks.size(); i++) {
            handler.setStackInSlot(i, tile.materialStacks.get(i));
            this.addSlot(new SlotItemHandler(handler, i, addX, addY));
            addX += 18;

        }

    }

    public void renderPlayerInventory(PlayerInventory playerInv) {

        int addX = 142;
        int addY = ProfessionRecipeGui.y / 2 + 35;

        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 9; ++column) {
                int index = column + row * 9 + 9;
                int x = 8 + column * 18 + addX;
                int y = row * 18 + addY;
                this.addSlot(new Slot(playerInv, index, x, y));
            }
        }

        for (int index = 0; index < 9; ++index) {
            this.addSlot(new Slot(playerInv, index, 8 + index * 18 + addX, addY + 3 * 18 + 4));
        }

    }

    private void gatherMaterialsFromInventory(int num) {

        for (int i = 3; i < 39; ++i) {
            ItemStack itemstack = this.inventorySlots.get(i).getStack();
            if (!itemstack.isEmpty()) {

                if (this.tile.currentRecipe.getMaterials().size() > num) {

                    BaseMaterial mat = this.tile.currentRecipe.getMaterials().get(num);

                    if (mat.isStackValidMaterial(itemstack)) {

                    }

                }
            }

        }

    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }
}
