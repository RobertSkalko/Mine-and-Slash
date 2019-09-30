package com.robertx22.mine_and_slash.new_content_test.talent_tree;

import net.minecraft.item.Items;

public class TalentPoints {

    public static TalentPoint CRIT0 = TalentPointBuilder.create("crit0")
            .setPos(0, 0)
            .setRender(Items.COAL)
            .setEffect(TalentEffects.SMALL_CRIT_HIT)
            .finish()
            .build();

}
