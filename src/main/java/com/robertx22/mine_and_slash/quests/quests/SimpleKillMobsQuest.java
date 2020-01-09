package com.robertx22.mine_and_slash.quests.quests;

import com.robertx22.mine_and_slash.database.loot_crates.bases.MapScoreEnum;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.quests.base.BaseSimpleKillMobsQuest;
import com.robertx22.mine_and_slash.quests.data.QuestSaveData;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class SimpleKillMobsQuest extends BaseSimpleKillMobsQuest {

    public static SimpleKillMobsQuest INSTANCE = new SimpleKillMobsQuest();

    private SimpleKillMobsQuest() {

    }

    @Override
    public ITextComponent name() {
        return Words.KillMobs.locName();
    }

    @Override
    public ResourceLocation icon() {
        return new ResourceLocation(Ref.MODID, "textures/gui/main_hub/quest_icons/kill_mobs.png");
    }

    @Override
    public void onCompleted(PlayerEntity player, QuestSaveData data) {
        data.reward.score = MapScoreEnum.getScore(player);
    }

    @Override
    public float amountRequired() {
        return RandomUtils.RandomRange(40, 80);
    }

    @Override
    public int minutes() {
        return 25;
    }

    @Override
    public String GUID() {
        return "kill_mobs";
    }
}
