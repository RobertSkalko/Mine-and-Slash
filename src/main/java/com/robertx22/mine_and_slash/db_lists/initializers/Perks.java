package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.Perk;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.csv_parser.TalentParser;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.data.PerkEffects;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.data.StartPerkEffects;

import java.util.ArrayList;
import java.util.List;

public class Perks implements ISlashRegistryInit {

    public static List<Perk> all = new ArrayList<>();

    @Override
    public void registerAll() {

        PerkEffects.create();

        StartPerkEffects.create();

        PerkEffects.createCombined();

        TalentParser.parse();

        SlashRegistry.Perks()
                .getList()
                .stream()
                .filter(x -> x.effect == StartPerkEffects.GUARDIAN || x.effect == StartPerkEffects.MAGE || x.effect == StartPerkEffects.THIEF || x.effect == StartPerkEffects.WARRIOR)
                .forEach(x -> x.setAsStart());












        /*
        StartPerks.create();

        MagePerks.create();

         */

        /*
        WarriorPerks.create();
        ThiefPerks.create();
        GuardianPerks.create();

         */

        //all.forEach(x -> x.registerToSlashRegistry()); no need cus i register from the parser
    }
}
