package com.robertx22.mine_and_slash.database.quests.base;

import com.robertx22.mine_and_slash.blocks.bases.BaseLootCrateTileEntity;
import com.robertx22.mine_and_slash.database.quests.actions.ActionDoneData;
import com.robertx22.mine_and_slash.database.quests.actions.OpenedCrateData;
import com.robertx22.mine_and_slash.database.quests.data.QuestTaskData;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseOpenCratesQuest extends Quest {

    @Override
    public ResourceLocation icon() {
        return new ResourceLocation(Ref.MODID, "textures/gui/quest_icons/open_loot_crates.png");
    }

    @Override
    public void onAction(QuestTaskData task, ActionDoneData actionData) {

        if (actionData instanceof OpenedCrateData) {

            OpenedCrateData data = (OpenedCrateData) actionData;

            if (WorldUtils.isMapWorldClass(data.player.world)) {
                if (isCrateValid(data.crate)) {
                    task.increaseCompletition(data.getGroupCompletitionMultiplier());
                    return;
                }
            }

        }
    }

    public abstract boolean isCrateValid(BaseLootCrateTileEntity crate);

    @Override
    public List<ITextComponent> getTooltip(QuestTaskData data) {
        List<ITextComponent> tooltip = new ArrayList<>();

        tooltip.add(new StringTextComponent(
                "Open Loot Crates: " + " ( " + data.getCurrentCompletition() + " / " + data.amountRequired + " ) "));

        return tooltip;
    }
}
