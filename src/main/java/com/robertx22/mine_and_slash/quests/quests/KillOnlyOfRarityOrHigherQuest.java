package com.robertx22.mine_and_slash.quests.quests;

import com.robertx22.mine_and_slash.database.rarities.MobRarity;
import com.robertx22.mine_and_slash.database.rarities.mobs.EpicMob;
import com.robertx22.mine_and_slash.database.rarities.mobs.LegendaryMob;
import com.robertx22.mine_and_slash.database.rarities.mobs.MythicalMob;
import com.robertx22.mine_and_slash.quests.actions.ActionDoneData;
import com.robertx22.mine_and_slash.quests.actions.KilledMobData;
import com.robertx22.mine_and_slash.quests.base.Quest;
import com.robertx22.mine_and_slash.quests.data.QuestSaveData;
import com.robertx22.mine_and_slash.quests.data.QuestTaskData;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class KillOnlyOfRarityOrHigherQuest extends Quest {

    public static KillOnlyOfRarityOrHigherQuest EPIC = new KillOnlyOfRarityOrHigherQuest(new EpicMob(), 20, 30);
    public static KillOnlyOfRarityOrHigherQuest LEGENDARY = new KillOnlyOfRarityOrHigherQuest(new LegendaryMob(), 10, 20);
    public static KillOnlyOfRarityOrHigherQuest MYTHIC = new KillOnlyOfRarityOrHigherQuest(new MythicalMob(), 2, 3);

    private KillOnlyOfRarityOrHigherQuest(MobRarity rar, int min, int max) {
        this.rarity = rar;
        this.min = min;
        this.max = max;
    }

    MobRarity rarity;
    int min;
    int max;

    @Override
    public void onAction(QuestTaskData task, ActionDoneData actionData) {

        if (actionData instanceof KilledMobData) {

            KilledMobData mdata = (KilledMobData) actionData;

            if (Quest.isValidMapMobKill(mdata.mobkKilled, mdata.mobData) == false) {
                return;
            }

            if (mdata.mobData.getRarity() >= rarity.Rank()) {
                task.increaseCompletition(mdata.getGroupCompletitionMultiplier());
            }
        }
    }

    @Override
    public ITextComponent name() {
        return new StringTextComponent("Kill ").appendSibling(rarity.locName())
                .appendText(" mobs");
    }

    @Override
    public void onCompleted(PlayerEntity player, QuestSaveData data) {

    }

    @Override
    public float amountRequired() {
        return RandomUtils.RandomRange(min, max);
    }

    @Override
    public List<ITextComponent> getTooltip(QuestTaskData data) {
        List<ITextComponent> tooltip = new ArrayList<>();

        tooltip.add(new StringTextComponent("Kill mobs: " + " ( " + data.getCurrentCompletition() + " / " + data.amountRequired + " ) "));
        tooltip.add(new StringTextComponent("Only ").appendSibling(rarity.locName())
                .appendText(" or higher rarity Mobs count"));

        return tooltip;
    }

    @Override
    public int minutes() {
        return 45;
    }

    @Override
    public String GUID() {
        return "kill_rarity_or_higher_" + rarity.GUID();
    }
}

