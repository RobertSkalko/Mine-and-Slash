package com.robertx22.mine_and_slash.quests.base;

import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.quests.actions.ActionDoneData;
import com.robertx22.mine_and_slash.quests.actions.KilledMobData;
import com.robertx22.mine_and_slash.quests.data.QuestTaskData;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityTypeUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseCollectMobRarityKillQuest extends Quest {

    @Override
    public void onAction(QuestTaskData task, ActionDoneData actionData) {

        if (actionData instanceof KilledMobData) {

            KilledMobData mdata = (KilledMobData) actionData;

            if (mdata.mobData.getType() != EntityTypeUtils.EntityType.MOB) {
                return;
            }

            float amount = Rarities.Mobs.get(mdata.mobData.getRarity()).LootMultiplier();

            task.increaseCompletition(mdata.getGroupCompletitionMultiplier() * amount);

        }
    }

    @Override
    public List<ITextComponent> getTooltip(QuestTaskData data) {
        List<ITextComponent> tooltip = new ArrayList<>();

        tooltip.add(new StringTextComponent("Kill mobs, collect points: " + " ( " + data.getCurrentCompletition() + " / " + data.amountRequired + " ) "));
        tooltip.add(new StringTextComponent("Higher rarity mobs give more points"));

        return tooltip;
    }
}

