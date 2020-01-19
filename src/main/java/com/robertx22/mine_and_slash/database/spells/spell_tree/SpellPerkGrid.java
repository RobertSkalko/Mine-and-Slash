package com.robertx22.mine_and_slash.database.spells.spell_tree;

import com.robertx22.mine_and_slash.database.talent_tree.PerkBuilder;
import com.robertx22.mine_and_slash.database.talent_tree.csv_parser.PerkGrid;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;

import java.util.List;

public class SpellPerkGrid extends PerkGrid<SpellPerkGridPoint> {

    public SpellPerkGrid(String str) {
        super(str);
    }

    public SpellPerkGridPoint newGridPoint(int x, int y, String s) {
        return new SpellPerkGridPoint(x, y, s);
    }

    @Override
    public void createAndRegisterAll() {

        for (List<SpellPerkGridPoint> list : grid) {
            for (SpellPerkGridPoint point : list) {

                if (point.isTalent()) {

                    String id = point.getEffectID();

                    if (!SlashRegistry.SpellPerkEffects().isRegistered(id)) {
                        id = id.toLowerCase();
                        if (!SlashRegistry.SpellPerkEffects().isRegistered(id)) {
                            id = id.toUpperCase();
                        }
                    }

                    SpellPerkEffect effect = null;

                    if (SlashRegistry.SpellPerkEffects().isRegistered(id)) {
                        effect = SlashRegistry.SpellPerkEffects().get(id);
                    }
                    if (effect == null) {

                        System.out.println(point.getID() + " is a broken talent.");
                    }

                    SpellPerk perk = (SpellPerk) PerkBuilder.createSpell(point.getID())
                            .pos(point.x, point.y)
                            .effect(effect)
                            .connections()
                            .build();

                    perk.registerToSlashRegistry();

                }
            }

        }

    }

}
