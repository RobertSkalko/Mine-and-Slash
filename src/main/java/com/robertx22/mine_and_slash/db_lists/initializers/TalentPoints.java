package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.Perk;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.PerkBuilder;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.PerkEffects;
import net.minecraft.item.Items;

import java.util.ArrayList;
import java.util.List;

public class TalentPoints implements ISlashRegistryInit {

    public static List<Perk> all = new ArrayList<>();

    public final static Perk CRIT_HIT0, CRIT_HIT1, CRIT_DMG0, CRIT_DMG1, CRIT_DMG2, START0, BIG_INT0, MAJOR_INT0;

    static {

        START0 = PerkBuilder.create("start0")
                .setPos(0, 0)
                .setRender(Items.LANTERN)
                .setEffect(PerkEffects.NOTHING)
                .connections()
                .build()
                .setAsStart();

        BIG_INT0 = PerkBuilder.create("BIG_INT0")
                .setPos(-1, 0)
                .setRender(Items.LAPIS_LAZULI)
                .setEffect(PerkEffects.BIG_INT)
                .connections()
                .addConnection(START0)
                .build();

        MAJOR_INT0 = PerkBuilder.create("MAJOR_INT0")
                .setPos(-2, 0)
                .setRender(Items.LAPIS_LAZULI)
                .setEffect(PerkEffects.MAJOR_INT)
                .connections()
                .addConnection(BIG_INT0)
                .build();

        CRIT_HIT0 = PerkBuilder.create("crit_hit0")
                .setPos(1, 0)
                .setRender(Items.GLOWSTONE_DUST)
                .setEffect(PerkEffects.SMALL_CRIT_HIT)
                .connections()
                .addConnection(START0)
                .build();

        CRIT_HIT1 = PerkBuilder.create("crit_hit1")
                .setPos(2, 0)
                .copy(CRIT_HIT0)
                .addConnection(CRIT_HIT0)
                .build();

        CRIT_DMG0 = PerkBuilder.create("crit_dmg0")
                .setPos(4, -4)
                .setRender(Items.REDSTONE)
                .setEffect(PerkEffects.SMALL_CRIT_DMG)
                .connections()
                .build();

        CRIT_DMG1 = PerkBuilder.create("crit_dmg1")
                .setPos(3, -2)
                .copy(CRIT_DMG0)
                .addConnection(CRIT_DMG0)
                .addConnection(CRIT_HIT1)
                .build();

        CRIT_DMG2 = PerkBuilder.create("crit_dmg2")
                .setPos(3, -3)
                .copy(CRIT_DMG0)
                .addConnection(CRIT_DMG0)
                .addConnection(CRIT_DMG1)
                .build();

    }

    @Override
    public void registerAll() {
        all.forEach(x -> x.registerToSlashRegistry());
    }
}
