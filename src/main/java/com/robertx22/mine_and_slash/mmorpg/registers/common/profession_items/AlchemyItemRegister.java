package com.robertx22.mine_and_slash.mmorpg.registers.common.profession_items;

import com.robertx22.mine_and_slash.items.profession.alchemy.bases.BasePotion;
import com.robertx22.mine_and_slash.items.profession.alchemy.bases.ILvlRecipeGen;
import com.robertx22.mine_and_slash.items.profession.alchemy.potion_buffs.EneRegenBuffPotion;
import com.robertx22.mine_and_slash.items.profession.alchemy.potion_buffs.HpRegenBuffPotion;
import com.robertx22.mine_and_slash.items.profession.alchemy.potion_buffs.LootBonusBuffPotion;
import com.robertx22.mine_and_slash.items.profession.alchemy.potion_buffs.ManaRegenBuffPotion;
import com.robertx22.mine_and_slash.items.profession.alchemy.single_use.*;
import com.robertx22.mine_and_slash.professions.blocks.bases.Professions;
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
        list.add(new HpRegenBuffPotion(Professions.Levels.ONE));
        list.add(new ManaRegenBuffPotion(Professions.Levels.ONE));
        list.add(new EneRegenBuffPotion(Professions.Levels.ONE));
        list.add(new LootBonusBuffPotion(Professions.Levels.ONE));

        for (ILvlRecipeGen gen : list) {
            List<BasePotion> vars = gen.generateAllPossibleStatVariations();
            vars.forEach(x -> r.register(x.setRegistryName(x.GUID())));
        }

        r.register(new LesserRecallPotionItem().setRegistryName(new LesserRecallPotionItem()
                .GUID()));
        r.register(new ResetStatsPotionItem().setRegistryName(new ResetStatsPotionItem().GUID()));
        r.register(new ResetTalentsPotionItem().setRegistryName(new ResetTalentsPotionItem()
                .GUID()));

        r.register(new AddRemoveTalentPotionItem().setRegistryName(new AddRemoveTalentPotionItem()
                .GUID()));

    }

}
