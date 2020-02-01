package com.robertx22.mine_and_slash.database.quests.base;

import com.robertx22.mine_and_slash.database.loot_crates.bases.MapScoreEnum;
import com.robertx22.mine_and_slash.database.quests.actions.ActionDoneData;
import com.robertx22.mine_and_slash.database.quests.data.QuestSaveData;
import com.robertx22.mine_and_slash.database.quests.data.QuestTaskData;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityTypeUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public abstract class Quest implements ISlashRegistryEntry {

    public abstract void onAction(QuestTaskData task, ActionDoneData actionData);

    public abstract ResourceLocation icon();

    public abstract ITextComponent name();

    public QuestTaskData getTaskData() {

        QuestTaskData data = new QuestTaskData();

        data.amountRequired = (int) this.amountRequired();
        data.questGUID = this.GUID();

        return data;

    }

    public void onCompleted(PlayerEntity player, QuestSaveData data) {
        data.reward.score = MapScoreEnum.getScore(player);
    }

    public static boolean isValidMapMobKill(Entity mob, EntityCap.UnitData data) {
        if (data.getType() != EntityTypeUtils.EntityType.MOB) {
            return false;
        }
        return WorldUtils.isMapWorldClass(mob.world);

    }

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
        return Rarities.Gears.get(getRarityRank());
    }

    @Override
    public int Tier() {
        return 0;
    }

    public abstract int minutes();
}
