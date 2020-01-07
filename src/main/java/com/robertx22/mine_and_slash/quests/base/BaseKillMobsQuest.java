package com.robertx22.mine_and_slash.quests.base;

import com.robertx22.mine_and_slash.quests.actions.ActionDoneData;
import com.robertx22.mine_and_slash.quests.actions.KilledMobData;
import com.robertx22.mine_and_slash.quests.data.QuestTaskData;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseKillMobsQuest extends Quest {

    @Override
    public void onAction(ActionDoneData actionData) {

        if (actionData instanceof KilledMobData) {

        }

    }

    @Override
    public List<ITextComponent> getTooltip(QuestTaskData data) {
        List<ITextComponent> tooltip = new ArrayList<>();

        tooltip.add(new StringTextComponent("Kill mobs" + " ( " + (int) data.currentCompletition + " / " + (int) data.amountRequired + " ) "));

        return tooltip;
    }
}
