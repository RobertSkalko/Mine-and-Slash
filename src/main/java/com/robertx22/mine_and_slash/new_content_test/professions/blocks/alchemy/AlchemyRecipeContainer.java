package com.robertx22.mine_and_slash.new_content_test.professions.blocks.alchemy;

import com.robertx22.mine_and_slash.blocks.slots.handlerslots.RecipeSlot;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ContainerTypeRegisters;
import com.robertx22.mine_and_slash.new_content_test.professions.blocks.ProfessionRecipeContainer;
import com.robertx22.mine_and_slash.new_content_test.professions.blocks.ProfessionTile;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class AlchemyRecipeContainer extends ProfessionRecipeContainer {

    public AlchemyRecipeContainer(int i, PlayerInventory playerInventory,
                                  PacketBuffer packetBuffer) {
        this(i, playerInventory, new AlchemyTile(), packetBuffer.readBlockPos());
    }

    public AlchemyRecipeContainer(int num, PlayerInventory invPlayer, ProfessionTile tile,
                                  BlockPos pos) {
        super(ContainerTypeRegisters.ALCHEMY_STATION, num);

        this.pos = pos;

        renderInventory(new ItemStackHandler(tile.recipeStacks), invPlayer);

    }

    @Override
    public SlotItemHandler slot(IItemHandler inv, int index, int x, int y) {
        return new RecipeSlot(inv, index, x, y);
    }

}
