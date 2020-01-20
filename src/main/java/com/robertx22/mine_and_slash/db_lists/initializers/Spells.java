package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.spells.spell_classes.SpellBonusEleBasicDmg;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.cleric.InstantHealSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.druid.RegenerateSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean_mystic.BlizzardSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean_mystic.FrostballSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.projectile.SpellAcidBolt;
import com.robertx22.mine_and_slash.database.spells.spell_classes.projectile.SpellFireBolt;
import com.robertx22.mine_and_slash.database.spells.spell_classes.projectile.SpellThunderBolt;
import com.robertx22.mine_and_slash.database.spells.spell_classes.shaman.ThunderstormSpell;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.List;

public class Spells implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        List<BaseSpell> All = new ArrayList<BaseSpell>() {
            {
                {
                    add(new FrostballSpell());
                    add(new SpellFireBolt());
                    add(new SpellAcidBolt());
                    add(new SpellThunderBolt());

                    add(new InstantHealSpell());
                    add(new RegenerateSpell());

                    add(new BlizzardSpell());
                    add(new ThunderstormSpell());

                }
            }
        };

        List<BaseSpell> generated = new ArrayList<BaseSpell>() {
            {
                {
                    add(new SpellBonusEleBasicDmg(Elements.Physical));

                }
            }
        };

        for (BaseSpell spell : generated) {
            IGenerated<BaseSpell> gen = (IGenerated<BaseSpell>) spell;
            gen.generateAllPossibleStatVariations().forEach(x -> All.add(x));
        }

        All.forEach(x -> x.registerToSlashRegistry());

    }
}
