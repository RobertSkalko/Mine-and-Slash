package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.Perk;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.PerkBuilder;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.data.PerkEffects;

import java.util.ArrayList;
import java.util.List;

public class Perks implements ISlashRegistryInit {

    public static List<Perk> all = new ArrayList<>();

    public final static Perk CRIT_HIT0, CRIT_HIT1, CRIT_DMG0, CRIT_DMG1, CRIT_DMG2, START0, SMALL_INT0, BIG_INT0;

    static {

        START0 = PerkBuilder.create("start0")
                .pos(0, 0)
                .effect(PerkEffects.NOTHING)
                .connections()
                .build()
                .setAsStart();

        SMALL_INT0 = PerkBuilder.create("SMALL_INT0")
                .pos(-1, 0)
                .effect(PerkEffects.INTELLIGENCE.small())
                .connections()
                .add(START0)
                .build();

        BIG_INT0 = PerkBuilder.create("BIG_INT0")
                .pos(-2, 0)
                .effect(PerkEffects.INTELLIGENCE.big())
                .connections()
                .add(SMALL_INT0)
                .build();

        CRIT_HIT0 = PerkBuilder.create("crit_hit0").pos(1, 0)

                .effect(PerkEffects.CRIT_HIT.small()).connections().add(START0).build();

        CRIT_HIT1 = PerkBuilder.create("crit_hit1")
                .pos(2, 0)
                .copy(CRIT_HIT0)
                .add(CRIT_HIT0)
                .build();

        CRIT_DMG0 = PerkBuilder.create("crit_dmg0")
                .pos(4, -4)
                .effect(PerkEffects.CRIT_DMG.small())
                .connections()
                .build();

        CRIT_DMG1 = PerkBuilder.create("crit_dmg1")
                .pos(3, -2)
                .copy(CRIT_DMG0)
                .add(CRIT_DMG0)
                .add(CRIT_HIT1)
                .build();

        CRIT_DMG2 = PerkBuilder.create("crit_dmg2")
                .pos(3, -3)
                .copy(CRIT_DMG0)
                .add(CRIT_DMG0)
                .add(CRIT_DMG1)
                .build();

    }

    @Override
    public void registerAll() {
        all.forEach(x -> x.registerToSlashRegistry());
    }
}
