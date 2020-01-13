package com.robertx22.mine_and_slash.database.quests.base;

import com.robertx22.mine_and_slash.database.quests.actions.ActionDoneData;
import com.robertx22.mine_and_slash.database.quests.actions.KilledMobData;
import com.robertx22.mine_and_slash.database.quests.data.QuestTaskData;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseSimpleKillMobsQuest extends Quest {

    @Override
    public void onAction(QuestTaskData task, ActionDoneData actionData) {

        if (actionData instanceof KilledMobData) {

            KilledMobData mdata = (KilledMobData) actionData;

            if (Quest.isValidMapMobKill(mdata.mobkKilled, mdata.mobData) == false) {
                return;
            }

            task.increaseCompletition(mdata.getGroupCompletitionMultiplier());

        }
    }

    @Override
    public List<ITextComponent> getTooltip(QuestTaskData data) {
        List<ITextComponent> tooltip = new ArrayList<>();

        tooltip.add(new StringTextComponent("Kill mobs" + " ( " + data.getCurrentCompletition() + " / " + data.amountRequired + " ) "));

        return tooltip;
    }
}
