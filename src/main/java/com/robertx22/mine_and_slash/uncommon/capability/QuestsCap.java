package com.robertx22.mine_and_slash.uncommon.capability;

import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.network.sync_cap.CapTypes;
import com.robertx22.mine_and_slash.network.sync_cap.SyncCapabilityToClient;
import com.robertx22.mine_and_slash.quests.actions.ActionDoneData;
import com.robertx22.mine_and_slash.quests.base.Quest;
import com.robertx22.mine_and_slash.quests.data.QuestLogData;
import com.robertx22.mine_and_slash.quests.data.QuestSaveData;
import com.robertx22.mine_and_slash.quests.quest_rewards.MapQuestReward;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseProvider;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseStorage;
import com.robertx22.mine_and_slash.uncommon.capability.bases.ICommonCapability;
import com.robertx22.mine_and_slash.uncommon.datasaving.QuestLogSaving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
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

    public interface IQuestsData extends ICommonCapability {

        void onAction(PlayerEntity player, ActionDoneData actionData);

        void syncToClient(PlayerEntity player);

        void setMapQuest(Quest quest);
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
        public CompoundNBT getNBT() {

            CompoundNBT nbt = new CompoundNBT();

            QuestLogSaving.Save(nbt, data);

            return nbt;

        }

        @Override
        public void setNBT(CompoundNBT nbt) {
            this.data = QuestLogSaving.Load(nbt);
        }

        @Override
        public void onAction(PlayerEntity player, ActionDoneData actionData) {

            List<QuestSaveData> all = data.getAllQuests();

            all.forEach(x -> x.tasks.forEach(y -> y.getQuest().onAction(y, actionData)));

            all.forEach(x -> x.setupResult(player));

        }

        @Override
        public void syncToClient(PlayerEntity player) {
            MMORPG.sendToClient(new SyncCapabilityToClient((ServerPlayerEntity) player, CapTypes.QUESTS), (ServerPlayerEntity) player);
        }

        @Override
        public void setMapQuest(Quest quest) {
            this.data.mapCompletitionQuest = new QuestSaveData();
            this.data.mapCompletitionQuest.tasks.add(quest.getTaskData());
            this.data.mapCompletitionQuest.reward.rewardGUID = MapQuestReward.INSTANCE.GUID();
        }
    }

    public static class Storage extends BaseStorage<IQuestsData> {

    }

}
