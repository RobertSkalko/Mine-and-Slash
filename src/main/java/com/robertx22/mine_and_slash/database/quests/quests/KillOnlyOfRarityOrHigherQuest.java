package com.robertx22.mine_and_slash.database.quests.quests;

import com.robertx22.mine_and_slash.database.quests.actions.ActionDoneData;
import com.robertx22.mine_and_slash.database.quests.actions.KilledMobData;
import com.robertx22.mine_and_slash.database.quests.base.Quest;
import com.robertx22.mine_and_slash.database.quests.data.QuestTaskData;
import com.robertx22.mine_and_slash.database.rarities.MobRarity;
import com.robertx22.mine_and_slash.database.rarities.mobs.EpicMob;
import com.robertx22.mine_and_slash.database.rarities.mobs.LegendaryMob;
import com.robertx22.mine_and_slash.database.rarities.mobs.MythicalMob;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class KillOnlyOfRarityOrHigherQuest extends Quest {

    public static KillOnlyOfRarityOrHigherQuest EPIC = new KillOnlyOfRarityOrHigherQuest(EpicMob.getInstance(), 28);
    public static KillOnlyOfRarityOrHigherQuest LEGENDARY = new KillOnlyOfRarityOrHigherQuest(
            LegendaryMob.getInstance(), 14);
    public static KillOnlyOfRarityOrHigherQuest MYTHIC = new KillOnlyOfRarityOrHigherQuest(
            MythicalMob.getInstance(), 3);

    private KillOnlyOfRarityOrHigherQuest(MobRarity rar, int max) {
        this.rarity = rar;
        this.max = max;
    }

    @Override
    public ResourceLocation icon() {
        return new ResourceLocation(Ref.MODID, "textures/gui/quest_icons/kill_mobs.png");
    }

    MobRarity rarity;
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
        return new StringTextComponent("Kill ").appendSibling(rarity.locName()).appendText(" mobs");
    }

    @Override
    public float amountRequired() {
        return max;
    }

    @Override
    public List<ITextComponent> getTooltip(QuestTaskData data) {
        List<ITextComponent> tooltip = new ArrayList<>();

        tooltip.add(new StringTextComponent(
                "Kill mobs: " + " ( " + data.getCurrentCompletition() + " / " + data.amountRequired + " ) "));
        tooltip.add(new StringTextComponent("Only ").appendSibling(rarity.locName())
                            .appendText(" or higher rarity Mobs count"));

        return tooltip;
    }

    @Override
    public int minutes() {
        return 60;
    }

    @Override
    public String GUID() {
        return "kill_rarity_or_higher_" + rarity.GUID();
    }
}

