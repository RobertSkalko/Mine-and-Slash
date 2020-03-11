package com.robertx22.mine_and_slash.gui.trader;

import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.packets.TraderPacket;
import com.robertx22.mine_and_slash.uncommon.datasaving.TraderSaving;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public class TraderEntity extends VillagerEntity implements IEntityAdditionalSpawnData {

    public TraderEntity(World world) {
        super(EntityRegister.TRADER, world);
        init();
    }

    public TraderData data = new TraderData();

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
    public void tick() {

        super.tick();

        if (!data.generated) {
            data.generated = true;

            data.generateMerchandise(new LootInfo(world, getPosition()));

        }

    }

    @Override
    public void readAdditional(CompoundNBT nbt) {
        super.readAdditional(nbt);

        this.data = TraderSaving.Load(nbt);

        if (data == null) {
            data = new TraderData();
        }
    }

    @Override
    public void writeAdditional(CompoundNBT nbt) {
        super.writeAdditional(nbt);

        TraderSaving.Save(nbt, data);

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

        if (!world.isRemote) {
            MMORPG.sendToClient(new TraderPacket(data), (ServerPlayerEntity) player);
        }

        return true;
    }
}
