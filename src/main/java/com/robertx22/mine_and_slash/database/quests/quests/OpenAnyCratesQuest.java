package com.robertx22.mine_and_slash.database.quests.quests;

import com.robertx22.mine_and_slash.blocks.bases.BaseLootCrateTileEntity;
import com.robertx22.mine_and_slash.database.quests.base.BaseOpenCratesQuest;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.text.ITextComponent;

public class OpenAnyCratesQuest extends BaseOpenCratesQuest {

    private OpenAnyCratesQuest() {
    }

    public static OpenAnyCratesQuest getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public boolean isCrateValid(BaseLootCrateTileEntity crate) {
        return true;
    }

    @Override
    public ITextComponent name() {
        return Words.OpenAnyLootCrates.locName();
    }

    @Override
    public float amountRequired() {
        return 7;
    }

    @Override
    public int Weight() {
        return super.Weight();
    }

    @Override
    public int minutes() {
        return 40;
    }

    @Override
    public String GUID() {
        return "open_any_crates";
    }

    private static class SingletonHolder {
        private static final OpenAnyCratesQuest INSTANCE = new OpenAnyCratesQuest();
    }
}
