package com.robertx22.mine_and_slash.new_content_test.talent_tree.data.perks;

import com.robertx22.mine_and_slash.new_content_test.talent_tree.Perk;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.PerkBuilder;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.data.PerkEffects;

public class MagePerks {
    public static Perk ms0, ms1, mana0, mana1, ms_mana0, int0, int1, staff0, staff1, staff_ele0;
    public static Perk wand0, wand1, wand_ele0, wis0, wis1, mana_regen0, ms_regen0, ms2, ms_regen1, ms_regen2, ms3, ms4;
    public static Perk intwis0, wis2, int2, int3, bigms0;

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

        wis0 = PerkBuilder.create(ID + "wis0")
                .pos(505, 488)
                .effect(PerkEffects.WISDOM.small())
                .connections()
                .add(mana1)
                .build();

        wis1 = PerkBuilder.create(ID + "wis1")
                .pos(510, 488)
                .effect(PerkEffects.WISDOM.small())
                .connections()
                .add(wis0)
                .build();

        wand0 = PerkBuilder.create(ID + "wand0")
                .pos(508, 490)
                .effect(PerkEffects.WAND_DMG_PERCENT.small())
                .connections()
                .add(wis1)
                .build();

        wand1 = PerkBuilder.create(ID + "wand1")
                .pos(512, 490)
                .effect(PerkEffects.WAND_DMG_PERCENT.small())
                .connections()
                .add(wis1)
                .build();

        wand_ele0 = PerkBuilder.create(ID + "wand_ele0")
                .pos(510, 492)
                .effect(PerkEffects.WAND_ELE_DMG_PERCENT.big())
                .connections()
                .add(wand0)
                .add(wand1)
                .build();

        ms_regen0 = PerkBuilder.create(ID + "ms_regen0")
                .pos(498, 485)
                .effect(PerkEffects.MAGIC_SHIELD_REGEN_PERCENT.small())
                .connections()
                .add(int0)
                .build();

        mana_regen0 = PerkBuilder.create(ID + "mana_regen0")
                .pos(502, 485)
                .effect(PerkEffects.MANA_REGEN_PERCENT.small())
                .connections()
                .add(wis0)
                .build();

        intwis0 = PerkBuilder.create(ID + "intwis0")
                .pos(500, 483)
                .effect(PerkEffects.INT_WIS.big())
                .connections()
                .add(mana_regen0)
                .add(ms_regen0)
                .build();

        wis2 = PerkBuilder.create(ID + "wis2")
                .pos(500, 479)
                .effect(PerkEffects.WISDOM.small())
                .connections()
                .add(intwis0)
                .build();

        ms2 = PerkBuilder.create(ID + "ms2")
                .pos(496, 479)
                .effect(PerkEffects.MAGIC_SHIELD_PERCENT.small())
                .connections()
                .add(wis2)
                .build();

        ms_regen1 = PerkBuilder.create(ID + "ms_regen1")
                .pos(494, 481)
                .effect(PerkEffects.MAGIC_SHIELD_REGEN_PERCENT.small())
                .connections()
                .add(ms2)
                .build();

        ms_regen2 = PerkBuilder.create(ID + "ms_regen2")
                .pos(492, 481)
                .effect(PerkEffects.MAGIC_SHIELD_REGEN_PERCENT.small())
                .connections()
                .add(ms_regen1)
                .build();

        ms3 = PerkBuilder.create(ID + "ms3")
                .pos(494, 477)
                .effect(PerkEffects.MAGIC_SHIELD_PERCENT.small())
                .connections()
                .add(ms2)
                .build();

        ms4 = PerkBuilder.create(ID + "ms4")
                .pos(492, 477)
                .effect(PerkEffects.MAGIC_SHIELD_PERCENT.small())
                .connections()
                .add(ms3)
                .build();

        int2 = PerkBuilder.create(ID + "int2")
                .pos(490, 479)
                .effect(PerkEffects.INTELLIGENCE.small())
                .connections()
                .add(ms4)
                .add(ms_regen2)
                .build();

        int3 = PerkBuilder.create(ID + "int3")
                .pos(487, 479)
                .effect(PerkEffects.INTELLIGENCE.small())
                .connections()
                .add(int2)
                .build();

        bigms0 = PerkBuilder.create(ID + "bigms0")
                .pos(484, 479)
                .effect(PerkEffects.MAGIC_SHIELD_PERCENT.big())
                .connections()
                .add(int3)
                .build();

    }

}
    /*


        TEMPLATE = PerkBuilder.create(ID + "GUID")
                .pos()
                .effect(PerkEffects.WISDOM.small())
                .connections()
                .add()
                .build();



     */


