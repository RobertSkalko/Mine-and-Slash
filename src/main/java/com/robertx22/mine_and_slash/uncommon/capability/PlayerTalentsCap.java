package com.robertx22.mine_and_slash.uncommon.capability;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.database.talent_tree.Perk;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryContainer;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.packets.sync_cap.PlayerCaps;
import com.robertx22.mine_and_slash.saveclasses.talents.PlayerTalentsData;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseProvider;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseStorage;
import com.robertx22.mine_and_slash.uncommon.capability.bases.ICommonPlayerCap;
import com.robertx22.mine_and_slash.uncommon.capability.bases.IPerkCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.base.LoadSave;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlayerTalentsCap {

    public static final ResourceLocation RESOURCE = new ResourceLocation(Ref.MODID, "player_talents");

    private static final String LOC = "PLAYER_TALENTS_DATA";

    @CapabilityInject(IPlayerTalentsData.class)
    public static final Capability<IPlayerTalentsData> Data = null;

    public abstract static class IPlayerTalentsData extends IPerkCap<Perk, PlayerTalentsData> implements ICommonPlayerCap {

    }

    @Mod.EventBusSubscriber
    public static class EventHandler {
        @SubscribeEvent
        public static void onEntityConstruct(AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof PlayerEntity) {
                event.addCapability(RESOURCE, new Provider());
            }
        }

    }

    public static class Provider extends BaseProvider<IPlayerTalentsData> {

        @Override
        public IPlayerTalentsData defaultImpl() {
            return new DefaultImpl();
        }

        @Override
        public Capability<IPlayerTalentsData> dataInstance() {
            return Data;
        }
    }

    public static class DefaultImpl extends IPlayerTalentsData {

        PlayerTalentsData data = new PlayerTalentsData();

        @Override
        public CompoundNBT saveToNBT() {
            CompoundNBT nbt = new CompoundNBT();
            LoadSave.Save(data, nbt, LOC);
            return nbt;
        }

        @Override
        public void loadFromNBT(CompoundNBT nbt) {
            this.data = LoadSave.Load(PlayerTalentsData.class, new PlayerTalentsData(), nbt, LOC);

            if (data == null) {
                data = new PlayerTalentsData();
            }
        }

        @Override
        public PlayerCaps getCapType() {
            return PlayerCaps.TALENTS;
        }

        public int getAllowedPoints(EntityCap.UnitData data) {
            return (int) ((float) data.getLevel() * ModConfig.INSTANCE.Server.TALENT_POINTS_PER_LEVEL.get());
        }

        @Override
        public void applyStats(EntityCap.UnitData data, PlayerEntity player) {
            this.data.applyStats(data);
        }

        @Override
        public PlayerTalentsData getPerksData() {
            return data;
        }

        @Override
        public SlashRegistryContainer getContainer() {
            return SlashRegistry.Perks();
        }

    }

    public static class Storage extends BaseStorage<IPlayerTalentsData> {

    }

}
