package com.robertx22.mine_and_slash.quests.base;

import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.quests.actions.ActionDoneData;
import com.robertx22.mine_and_slash.quests.data.QuestSaveData;
import com.robertx22.mine_and_slash.quests.data.QuestTaskData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public abstract class Quest implements ISlashRegistryEntry {

    public abstract void onAction(QuestTaskData task, ActionDoneData actionData);

    public abstract ITextComponent name();

    public QuestTaskData getTaskData() {

        QuestTaskData data = new QuestTaskData();

        data.amountRequired = (int) this.amountRequired();
        data.questGUID = this.GUID();

        return data;

    }

    public abstract void onCompleted(PlayerEntity player, QuestSaveData data);

    public abstract float amountRequired();

    public abstract List<ITextComponent> getTooltip(QuestTaskData data);

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.QUEST;
    }

    @Override
    public int Weight() {
        return getRarity().Weight();
    }

    @Override
    public int getRarityRank() {
        return 0;
    }

    @Override
    public Rarity getRarity() {
        return Rarities.Items.get(getRarityRank());
    }

    @Override
    public int Tier() {
        return 0;
    }

    public abstract int minutes();
}
