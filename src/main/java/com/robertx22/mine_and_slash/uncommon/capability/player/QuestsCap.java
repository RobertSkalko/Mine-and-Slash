package com.robertx22.mine_and_slash.uncommon.capability.player;

import com.robertx22.mine_and_slash.database.quests.actions.ActionDoneData;
import com.robertx22.mine_and_slash.database.quests.base.Quest;
import com.robertx22.mine_and_slash.database.quests.data.QuestLogData;
import com.robertx22.mine_and_slash.database.quests.data.QuestSaveData;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.packets.sync_cap.PlayerCaps;
import com.robertx22.mine_and_slash.saveclasses.item_classes.MapItemData;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseProvider;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseStorage;
import com.robertx22.mine_and_slash.uncommon.capability.bases.ICommonPlayerCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.QuestLogSaving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber
public class QuestsCap {

    public static final ResourceLocation RESOURCE = new ResourceLocation(Ref.MODID, "quests");

    @CapabilityInject(IQuestsData.class)
    public static final Capability<IQuestsData> Data = null;

    public interface IQuestsData extends ICommonPlayerCap {

        void onAction(PlayerEntity player, ActionDoneData actionData);

        void setMapQuest(Quest quest, MapItemData map);

        QuestLogData getData();

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

    public static class Provider extends BaseProvider<IQuestsData> {

        @Override
        public IQuestsData defaultImpl() {
            return new DefaultImpl();
        }

        @Override
        public Capability<IQuestsData> dataInstance() {
            return Data;
        }
    }

    public static class DefaultImpl implements IQuestsData {

        QuestLogData data = new QuestLogData();

        @Override
        public CompoundNBT saveToNBT() {

            CompoundNBT nbt = new CompoundNBT();

            QuestLogSaving.Save(nbt, data);

            return nbt;

        }

        @Override
        public void loadFromNBT(CompoundNBT nbt) {
            this.data = QuestLogSaving.Load(nbt);
        }

        @Override
        public void onAction(PlayerEntity player, ActionDoneData actionData) {

            List<QuestSaveData> all = data.getAllQuests();

            all.forEach(x -> x.tasks.forEach(y -> y.getQuest()
                .onAction(y, actionData)));

            all.forEach(x -> x.setupResult(player));

        }

        @Override
        public void setMapQuest(Quest quest, MapItemData map) {
        }

        @Override
        public QuestLogData getData() {
            return this.data;
        }

        @Override
        public PlayerCaps getCapType() {
            return PlayerCaps.QUESTS;
        }
    }

    public static class Storage extends BaseStorage<IQuestsData> {

    }

}
