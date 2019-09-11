package com.robertx22.mine_and_slash.mmorpg.registers.common.profession_items;

import com.robertx22.mine_and_slash.items.profession.alchemy.bases.BaseInstantPotion;
import com.robertx22.mine_and_slash.items.profession.alchemy.bases.ILvlRecipeGen;
import com.robertx22.mine_and_slash.items.profession.alchemy.single_use.InstantEnergyPotionItem;
import com.robertx22.mine_and_slash.items.profession.alchemy.single_use.InstantHealthPotionItem;
import com.robertx22.mine_and_slash.items.profession.alchemy.single_use.InstantManaPotionItem;
import com.robertx22.mine_and_slash.new_content_test.professions.data.Professions;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;
import java.util.List;

public class AlchemyItemRegister {

    @ObjectHolder("mmorpg:alchemy/instant/health/potion_lvl_100")
    public static final Item LVL_100_HP_POT = null;

    public static void register(RegistryEvent.Register<Item> event) {

        IForgeRegistry<Item> r = event.getRegistry();
        List<ILvlRecipeGen> list = new ArrayList<>();

        list.add(new InstantHealthPotionItem(Professions.Levels.ONE));
        list.add(new InstantManaPotionItem(Professions.Levels.ONE));
        list.add(new InstantEnergyPotionItem(Professions.Levels.ONE));

        for (ILvlRecipeGen gen : list) {
            List<BaseInstantPotion> vars = gen.generateAllPossibleStatVariations();
            vars.forEach(x -> r.register(x.setRegistryName(x.GUID())));

        }

    }

}
