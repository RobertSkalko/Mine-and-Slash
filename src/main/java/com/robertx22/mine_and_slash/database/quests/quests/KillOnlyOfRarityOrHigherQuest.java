package com.robertx22.mine_and_slash.database.quests.quests;

import com.robertx22.mine_and_slash.config.base.RarityWeight;
import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.database.quests.actions.ActionDoneData;
import com.robertx22.mine_and_slash.database.quests.actions.KilledMobData;
import com.robertx22.mine_and_slash.database.quests.base.Quest;
import com.robertx22.mine_and_slash.database.quests.data.QuestTaskData;
import com.robertx22.mine_and_slash.database.rarities.MobRarity;
import com.robertx22.mine_and_slash.database.rarities.mobs.EpicMob;
import com.robertx22.mine_and_slash.database.rarities.mobs.LegendaryMob;
import com.robertx22.mine_and_slash.database.rarities.mobs.MythicalMob;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class KillOnlyOfRarityOrHigherQuest extends Quest {

    public static KillOnlyOfRarityOrHigherQuest EPIC = new KillOnlyOfRarityOrHigherQuest(EpicMob.getInstance(), 1000);
    public static KillOnlyOfRarityOrHigherQuest LEGENDARY = new KillOnlyOfRarityOrHigherQuest(
            LegendaryMob.getInstance(), 500);
    public static KillOnlyOfRarityOrHigherQuest MYTHIC = new KillOnlyOfRarityOrHigherQuest(MythicalMob.getInstance(),
                                                                                           250
    );

    private KillOnlyOfRarityOrHigherQuest(MobRarity rar, int weight) {
        this.rarity = rar;
        this.weight = weight;
    }

    @Override
    public ResourceLocation icon() {
        return new ResourceLocation(Ref.MODID, "textures/gui/quest_icons/kill_mobs.png");
    }

    MobRarity rarity;
    int weight;

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
        try {
            RarityWeight c = ModConfig.INSTANCE.RarityWeightConfig.MOBS;

            float others = 0;

            float searchingFor = 0;

            if (rarity.Rank() <= IRarity.Common) {
                searchingFor += c.COMMON_WEIGHT.get();
            } else {
                others += c.COMMON_WEIGHT.get();
            }
            if (rarity.Rank() <= IRarity.Uncommon) {
                searchingFor += c.UNCOMMON_WEIGHT.get();
            } else {
                others += c.UNCOMMON_WEIGHT.get();
            }
            if (rarity.Rank() <= IRarity.Rare) {
                searchingFor += c.RARE_WEIGHT.get();
            } else {
                others += c.RARE_WEIGHT.get();
            }
            if (rarity.Rank() <= IRarity.Epic) {
                searchingFor += c.EPIC_WEIGHT.get();
            } else {
                others += c.EPIC_WEIGHT.get();
            }
            if (rarity.Rank() <= IRarity.Legendary) {
                searchingFor += c.LEGENDARY_WEIGHT.get();
            } else {
                others += c.LEGENDARY_WEIGHT.get();
            }
            if (rarity.Rank() <= IRarity.Mythic) {
                searchingFor += c.MYTHICAL_WEIGHT.get();
            } else {
                others += c.MYTHICAL_WEIGHT.get();
            }

            float perc = searchingFor / others;

            return (int) (300 * perc);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }

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
    public int Weight() {
        return weight;
    }

    @Override
    public int minutes() {
        return 60;
    }

    @Override
    public String GUID() {
        return "kill_rarity_or_higher_" + rarity.GUID().toLowerCase();
    }
}

