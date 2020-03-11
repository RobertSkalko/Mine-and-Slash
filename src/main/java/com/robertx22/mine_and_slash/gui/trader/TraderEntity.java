package com.robertx22.mine_and_slash.gui.trader;

import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.List;

public class TraderEntity extends VillagerEntity implements IEntityAdditionalSpawnData {

    public TraderEntity(World world) {
        super(EntityRegister.TRADER, world);
        init();
    }

    public TraderEntity(EntityType type, World world) {
        super(type, world);
        init();
    }

    public TraderEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.TRADER, world);
        init();
    }

    public void init() {
        this.ageUp(100000, false);
        this.addGrowth(10000);
    }

    @Override
    public void readAdditional(CompoundNBT nbt) {
        super.readAdditional(nbt);
    }

    @Override
    public void writeAdditional(CompoundNBT nbt) {
        super.writeAdditional(nbt);
    }

    @Override
    protected void registerData() {
        super.registerData();
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void writeSpawnData(PacketBuffer buf) {
        CompoundNBT nbt = new CompoundNBT();
        writeAdditional(nbt);
        buf.writeCompoundTag(nbt);
    }

    @Override
    public void readSpawnData(PacketBuffer buf) {
        CompoundNBT nbt = buf.readCompoundTag();
        this.readAdditional(nbt);
    }

    @Override
    public boolean processInteract(PlayerEntity player, Hand hand) {
        // TODO TESTING

        boolean bool = this.isChild();

        List<ItemStack> stacks = SlashRegistry.LootCrates()
            .random()
            .generateItems(new LootInfo(player.world, player.getPosition()));

        Minecraft.getInstance()
            .displayGuiScreen(new TraderScreen(stacks));

        return true;
    }
}
