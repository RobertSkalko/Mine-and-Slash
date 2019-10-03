package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.TalentEffects;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.TalentPoint;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.TalentPointBuilder;
import net.minecraft.item.Items;

import java.util.ArrayList;
import java.util.List;

public class TalentPoints implements ISlashRegistryInit {

    public static List<TalentPoint> all = new ArrayList<>();

    public final static TalentPoint CRIT_HIT0, CRIT_HIT1, CRIT_DMG0, CRIT_DMG1, START0;

    static {

        START0 = TalentPointBuilder.create("start0")
                .setPos(0, 0)
                .setRender(Items.LANTERN)
                .setEffect(TalentEffects.NOTHING)
                .connections()
                .build()
                .setAsStart();

        CRIT_HIT0 = TalentPointBuilder.create("crit_hit0")
                .setPos(1, 0)
                .setRender(Items.GLOWSTONE_DUST)
                .setEffect(TalentEffects.SMALL_CRIT_HIT)
                .connections()
                .addConnection(START0)
                .build();

        CRIT_HIT1 = TalentPointBuilder.create("crit_hit1")
                .setPos(2, 0)
                .copy(CRIT_HIT0)
                .addConnection(CRIT_HIT0)
                .build();

        CRIT_DMG0 = TalentPointBuilder.create("crit_dmg0")
                .setPos(4, -4)
                .setRender(Items.REDSTONE)
                .setEffect(TalentEffects.SMALL_CRIT_DMG)
                .connections()
                .addConnection(CRIT_HIT0)
                .build();

        CRIT_DMG1 = TalentPointBuilder.create("crit_dmg1")
                .setPos(3, -2)
                .copy(CRIT_DMG0)
                .addConnection(CRIT_DMG0)
                .build();

    }

    @Override
    public void registerAll() {
        all.forEach(x -> x.registerToSlashRegistry());
    }
}
