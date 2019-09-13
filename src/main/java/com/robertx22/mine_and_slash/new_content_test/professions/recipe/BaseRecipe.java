package com.robertx22.mine_and_slash.new_content_test.professions.recipe;

import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases.ProfessionTile;
import com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases.Professions;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.item_classes.RecipeItemData;
import com.robertx22.mine_and_slash.uncommon.capability.ProfessionsCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Recipe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

import java.util.List;

public abstract class BaseRecipe implements ISlashRegistryEntry {

    public BaseRecipe(String guid) {
        this.guid = guid;
    }

    public String guid;
    public int professionLevelReq = 1;
    public int expGiven = 1;

    public BaseRecipe expGivenAtLvl1(int exp) {
        this.expGiven = MathHelper.clamp(exp * this.professionLevelReq, 1, 100000);
        return this;
    }

    public BaseRecipe levelReq(int lvl) {
        this.professionLevelReq = MathHelper.clamp(lvl, 1, 100);
        return this;
    }

    public int getCookTimeTicks() {
        return 100;
    }

    public int getLevelReq() {
        return this.professionLevelReq;
    }

    public boolean meetsLvlReq(ProfessionsCap.IProfessionsData cap) {
        return cap.getLevel(this.profession()) >= this.professionLevelReq;
    }

    public abstract Professions profession();

    public abstract List<BaseMaterial> getMaterials();

    public ItemStack getPreviewRecipeStack(ProfessionTile tile) {

        ItemStack stack = this.getOutput(tile).getPreview();

        RecipeItemData data = new RecipeItemData();
        data.guid = this.guid;

        Recipe.Save(stack, data);

        return stack;

    }

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
