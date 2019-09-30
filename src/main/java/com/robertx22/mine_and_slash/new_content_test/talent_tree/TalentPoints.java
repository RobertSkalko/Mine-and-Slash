package com.robertx22.mine_and_slash.new_content_test.talent_tree;

import net.minecraft.item.Items;

public class TalentPoints {

    public static TalentPoint CRIT_HIT0;
    public static TalentPoint CRIT_HIT1;

    public static TalentPoint CRIT_DMG0;
    public static TalentPoint CRIT_DMG1;

    static {
        create();
    }

    public static void create() {

        CRIT_HIT0 = TalentPointBuilder.create("crit_hit0")
                .setPos(0, 0)
                .setRender(Items.GLOWSTONE_DUST)
                .setEffect(TalentEffects.SMALL_CRIT_HIT)
                .finish()
                .build();

        CRIT_HIT1 = TalentPointBuilder.create("crit_hit1")
                .setPos(1, 0)
                .copy(CRIT_HIT0)
                .addConnection(CRIT_HIT0)
                .build();

        CRIT_DMG0 = TalentPointBuilder.create("crit_dmg0")
                .setPos(5, 1)
                .setRender(Items.REDSTONE)
                .setEffect(TalentEffects.SMALL_CRIT_DMG)
                .finish()
                .build();

        CRIT_DMG1 = TalentPointBuilder.create("crit_dmg1")
                .setPos(5, 2)
                .copy(CRIT_DMG0)
                .addConnection(CRIT_DMG0)
                .build();

    }

}
