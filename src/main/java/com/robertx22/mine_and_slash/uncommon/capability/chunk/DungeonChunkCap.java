package com.robertx22.mine_and_slash.uncommon.capability.chunk;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseProvider;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseStorage;
import com.robertx22.mine_and_slash.uncommon.capability.bases.ICommonCap;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class DungeonChunkCap {

    public static final ResourceLocation RESOURCE = new ResourceLocation(Ref.MODID, "dungeon_chunk");

    @CapabilityInject(IDungeonChunkData.class)
    public static final Capability<IDungeonChunkData> Data = null;

    public interface IDungeonChunkData extends ICommonCap {

        boolean isDoneProcessing();

        void setDone();
    }

    @Mod.EventBusSubscriber
    public static class EventHandler {

        @SubscribeEvent
        public static void onEntityConstruct(AttachCapabilitiesEvent<Chunk> event) {
            event.addCapability(RESOURCE, new Provider());
        }

    }

    public static class Provider extends BaseProvider<IDungeonChunkData> {

        @Override
        public IDungeonChunkData defaultImpl() {
            return new DefaultImpl();
        }

        @Override
        public Capability<IDungeonChunkData> dataInstance() {
            return Data;
        }
    }

    public static class DefaultImpl implements IDungeonChunkData {

        boolean isDone = false;

        @Override
        public CompoundNBT saveToNBT() {

            CompoundNBT nbt = new CompoundNBT();

            nbt.putBoolean("done", isDone);

            return nbt;

        }

        @Override
        public void loadFromNBT(CompoundNBT nbt) {
            this.isDone = nbt.getBoolean("done");
        }

        @Override
        public boolean isDoneProcessing() {
            return isDone;
        }

        @Override
        public void setDone() {
            this.isDone = true;
        }
    }

    public static class Storage extends BaseStorage<IDungeonChunkData> {

    }

}
