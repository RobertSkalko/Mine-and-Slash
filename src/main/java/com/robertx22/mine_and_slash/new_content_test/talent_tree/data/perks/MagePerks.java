package com.robertx22.mine_and_slash.new_content_test.talent_tree.data.perks;

import com.robertx22.mine_and_slash.new_content_test.talent_tree.Perk;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.PerkBuilder;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.data.PerkEffects;

public class MagePerks {
    public static Perk ms0;
    private static Perk ms1;

    public static void create() {

        ms0 = PerkBuilder.create("ms0")
                .pos(498, 493)
                .effect(PerkEffects.MAGIC_SHIELD_PERCENT.small())
                .connections()
                .add(StartPerks.MAGE)
                .build();

        ms1 = PerkBuilder.create("int1")
                .pos(498, 491)
                .effect(PerkEffects.MAGIC_SHIELD_PERCENT.small())
                .connections()
                .add(ms0)
                .build();

    }

}
