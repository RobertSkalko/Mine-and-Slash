package com.robertx22.mine_and_slash.mmorpg;

import com.robertx22.mine_and_slash.items.profession.alchemy.bases.IHasRecipe;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

public class RegisterProfessionRecipesFromItems {

    public static void register() {
        for (Item item : ForgeRegistries.ITEMS) {
            if (item instanceof IHasRecipe) {
                IHasRecipe has = (IHasRecipe) item;
                if (has.getRecipe() != null) {
                    has.getRecipe()
                        .registerToSlashRegistry();
                }
            }
        }
    }
}
