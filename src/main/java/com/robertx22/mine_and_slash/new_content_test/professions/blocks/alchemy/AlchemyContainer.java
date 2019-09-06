package com.robertx22.mine_and_slash.new_content_test.professions.blocks.alchemy;

import com.robertx22.mine_and_slash.blocks.gear_factory_station.TileGearFactory;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ContainerTypeRegisters;
import com.robertx22.mine_and_slash.new_content_test.professions.blocks.ProfessionContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class AlchemyContainer extends ProfessionContainer {

    public AlchemyContainer(int i, PlayerInventory playerInventory,
                            PacketBuffer packetBuffer) {
        this(i, playerInventory, new Inventory(TileGearFactory.TOTAL_SLOTS_COUNT), packetBuffer
                .readBlockPos());
    }

    public AlchemyContainer(int num, PlayerInventory invPlayer, IInventory inventory,
                            BlockPos pos) {
        super(ContainerTypeRegisters.GEAR_FACTORY, num);

        this.pos = pos;

    }

    @Override
    public SlotItemHandler slot(IItemHandler inv, int index, int x, int y) {
        return null;
    }

}
