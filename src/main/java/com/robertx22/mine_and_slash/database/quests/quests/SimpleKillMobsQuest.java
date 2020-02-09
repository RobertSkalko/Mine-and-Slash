package com.robertx22.mine_and_slash.database.quests.quests;

import com.robertx22.mine_and_slash.database.quests.base.BaseSimpleKillMobsQuest;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
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
        return new ResourceLocation(Ref.MODID, "textures/gui/quest_icons/kill_mobs.png");
    }

    @Override
    public float amountRequired() {
        return 80;
    }

    @Override
    public int minutes() {
        return 40;
    }

    @Override
    public String GUID() {
        return "kill_mobs";
    }
}
