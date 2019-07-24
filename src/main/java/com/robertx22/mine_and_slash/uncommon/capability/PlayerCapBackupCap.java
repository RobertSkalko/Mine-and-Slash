package com.robertx22.mine_and_slash.uncommon.capability;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.PlayersCapBackup;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseProvider;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseStorage;
import com.robertx22.mine_and_slash.uncommon.capability.bases.ICommonCapability;
import com.robertx22.mine_and_slash.uncommon.datasaving.PlayersCapBackupSaving;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlayerCapBackupCap {

    public static final ResourceLocation RESOURCE = new ResourceLocation(Ref.MODID, "player_cap_backup_data");

    @CapabilityInject(IPlayerCapBackupData.class)
    public static final Capability<IPlayerCapBackupData> Data = null;

    public interface IPlayerCapBackupData extends ICommonCapability {

        PlayersCapBackup getBackup();

    }

    @Mod.EventBusSubscriber
    public static class EventHandler {

        @SubscribeEvent
        public static void onWorld(AttachCapabilitiesEvent<World> event) {
            if (event.getObject().getDimension().getType() == DimensionType.OVERWORLD) {
                event.addCapability(RESOURCE, new Provider());
            }
        }

    }

    public static class Provider extends BaseProvider<IPlayerCapBackupData> {

        @Override
        public IPlayerCapBackupData defaultImpl() {
            return new DefaultImpl();
        }

        @Override
        public Capability<IPlayerCapBackupData> dataInstance() {
            return Data;
        }
    }

    public static class DefaultImpl implements IPlayerCapBackupData {

        PlayersCapBackup backup = new PlayersCapBackup();

        @Override
        public CompoundNBT getNBT() {
            CompoundNBT nbt = new CompoundNBT();

            if (backup != null) {
                PlayersCapBackupSaving.Save(nbt, backup);
            }

            return nbt;

        }

        @Override
        public void setNBT(CompoundNBT value) {

            backup = PlayersCapBackupSaving.Load(value);

        }

        @Override
        public PlayersCapBackup getBackup() {
            return this.backup;
        }
    }

    public static class Storage extends BaseStorage<IPlayerCapBackupData> {

    }

}
