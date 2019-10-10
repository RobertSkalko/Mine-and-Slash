package com.robertx22.mine_and_slash.items.profession.alchemy.bases;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.db_lists.CreativeTabs;
import com.robertx22.mine_and_slash.professions.blocks.bases.Professions;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import net.minecraft.item.Item;

public abstract class BasePotion extends Item implements IGUID, IAutoLocName, IHasRecipe, ILvlRecipeGen {

    public Professions.Levels level = Professions.Levels.ONE;

    public BasePotion(Professions.Levels lvl) {
        super(new Properties().maxStackSize(64).group(CreativeTabs.Alchemy));
        this.level = lvl;
    }
}
