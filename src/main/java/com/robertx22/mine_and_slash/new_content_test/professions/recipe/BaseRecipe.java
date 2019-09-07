package com.robertx22.mine_and_slash.new_content_test.professions.recipe;

import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases.ProfessionTile;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;

import java.util.List;

public abstract class BaseRecipe implements ISlashRegistryEntry {

    public BaseRecipe(String guid) {
        this.guid = guid;
    }

    public String guid;

    public abstract List<BaseMaterial> getMaterials();

    public abstract BasePreviewItem getOutput(
            ProfessionTile tile); // needs the instance for some recipes that modify existing items

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.RECIPE;
    }

    @Override
    public String GUID() {
        return guid;
    }

    @Override
    public int Weight() {
        return 1000;
    }

    @Override
    public int getRarityRank() {
        return 0;
    }

    @Override
    public Rarity getRarity() {
        return Rarities.Items.get(getRarityRank());
    }

    @Override
    public int Tier() {
        return 0;
    }

}
