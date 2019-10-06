package com.robertx22.mine_and_slash.new_content_test.talent_tree.data.perks;

import com.robertx22.mine_and_slash.new_content_test.talent_tree.Perk;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.PerkBuilder;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.data.PerkEffects;

public class MagePerks {
    public static Perk ms0, ms1, mana0, mana1, ms_mana0, int0, int1, staff0, staff1, staff_ele0;

    static final String ID = "mage:";

    public static void create() {

        ms0 = PerkBuilder.create(ID + "ms0")
                .pos(498, 493)
                .effect(PerkEffects.MAGIC_SHIELD_PERCENT.small())
                .connections()
                .add(StartPerks.MAGE)
                .build();

        ms1 = PerkBuilder.create(ID + "ms1")
                .pos(498, 491)
                .effect(PerkEffects.MAGIC_SHIELD_PERCENT.small())
                .connections()
                .add(ms0)
                .build();

        mana0 = PerkBuilder.create(ID + "mana0")
                .pos(502, 493)
                .effect(PerkEffects.MANA_PERCENT.small())
                .connections()
                .add(StartPerks.MAGE)
                .build();

        mana1 = PerkBuilder.create(ID + "mana1")
                .pos(502, 491)
                .effect(PerkEffects.MANA_PERCENT.small())
                .connections()
                .add(mana0)
                .build();

        ms_mana0 = PerkBuilder.create(ID + "ms_mana0")
                .pos(500, 489)
                .effect(PerkEffects.MANA_PERC_PLUS_MAGIC_SHIELD_PERCENT.big())
                .connections()
                .add(mana1)
                .add(ms1)
                .build();

        int0 = PerkBuilder.create(ID + "int0")
                .pos(495, 488)
                .effect(PerkEffects.INTELLIGENCE.small())
                .connections()
                .add(ms1)
                .build();

        int1 = PerkBuilder.create(ID + "int1")
                .pos(490, 488)
                .effect(PerkEffects.INTELLIGENCE.small())
                .connections()
                .add(int0)
                .build();

        staff0 = PerkBuilder.create(ID + "staff0")
                .pos(492, 490)
                .effect(PerkEffects.STAFF_DMG_PERCENT.small())
                .connections()
                .add(int1)
                .build();

        staff1 = PerkBuilder.create(ID + "staff1")
                .pos(488, 490)
                .effect(PerkEffects.STAFF_DMG_PERCENT.small())
                .connections()
                .add(int1)
                .build();

        staff_ele0 = PerkBuilder.create(ID + "staff_ele0")
                .pos(490, 492)
                .effect(PerkEffects.STAFF_ELE_DMG_PERCENT.big())
                .connections()
                .add(staff0)
                .add(staff1)
                .build();

    }

}
