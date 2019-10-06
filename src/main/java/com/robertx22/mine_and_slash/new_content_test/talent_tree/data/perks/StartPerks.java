package com.robertx22.mine_and_slash.new_content_test.talent_tree.data.perks;

import com.robertx22.mine_and_slash.new_content_test.talent_tree.Perk;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.PerkBuilder;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.data.StartPerkEffects;

public class StartPerks {
    public static Perk MAGE, WARRIOR, THIEF, GUARDIAN;

    public static void create() {

        MAGE = PerkBuilder.create("mage")
                .pos(500, 494)
                .effect(StartPerkEffects.MAGE)
                .connections()
                .build()
                .setAsStart();

        GUARDIAN = PerkBuilder.create("guardian")
                .pos(494, 500)
                .effect(StartPerkEffects.GUARDIAN)
                .connections()
                .build()
                .setAsStart();

        THIEF = PerkBuilder.create("thief")
                .pos(506, 500)
                .effect(StartPerkEffects.THIEF)
                .connections()
                .build()
                .setAsStart();

        WARRIOR = PerkBuilder.create("warrior")
                .pos(500, 506)
                .effect(StartPerkEffects.WARRIOR)
                .connections()
                .build()
                .setAsStart();
    }
}