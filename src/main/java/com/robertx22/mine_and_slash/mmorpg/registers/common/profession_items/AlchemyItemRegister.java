package com.robertx22.mine_and_slash.mmorpg.registers.common.profession_items;

import com.robertx22.mine_and_slash.items.consumables.bases.BaseConsumabletem;
import com.robertx22.mine_and_slash.items.profession.alchemy.single_use.HealthPotionItem;
import com.robertx22.mine_and_slash.items.profession.alchemy.single_use.ILvlRecipeGen;
import com.robertx22.mine_and_slash.new_content_test.professions.data.Professions;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

public class AlchemyItemRegister {

    public static void register(RegistryEvent.Register<Item> event) {

        IForgeRegistry<Item> r = event.getRegistry();
        List<ILvlRecipeGen> list = new ArrayList<>();

        list.add(new HealthPotionItem(Professions.Levels.ONE));

        for (ILvlRecipeGen gen : list) {
            List<BaseConsumabletem> vars = gen.generateAllPossibleStatVariations();
            vars.forEach(x -> r.register(x.setRegistryName(x.GUID())));

        }

    }

}
