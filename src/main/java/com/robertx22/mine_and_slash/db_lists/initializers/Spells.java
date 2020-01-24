package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.cleric.InstantHealSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.cleric.RighteousFurySpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.druid.GorgonsGazeSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.druid.RegenerateSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ember_mage.BlazingInfernoSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ember_mage.FireballSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean_mystic.BlizzardSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean_mystic.FrostballSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean_mystic.WhirpoolSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.shaman.LightningTotemSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.shaman.ThunderDashSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.shaman.ThunderspearSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.shaman.ThunderstormSpell;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;

import java.util.ArrayList;
import java.util.List;

public class Spells implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        List<BaseSpell> All = new ArrayList<BaseSpell>() {
            {
                {
                    add(new FrostballSpell());
                    add(new BlizzardSpell());
                    add(new WhirpoolSpell());

                    add(new InstantHealSpell());
                    add(new RighteousFurySpell());

                    add(new RegenerateSpell());
                    add(new GorgonsGazeSpell());

                    add(new ThunderstormSpell());
                    add(new ThunderspearSpell());
                    add(new ThunderDashSpell());

                    add(new BlazingInfernoSpell());
                    add(new FireballSpell());

                    add(new LightningTotemSpell());
                }
            }

        };

        All.forEach(x -> x.registerToSlashRegistry());

    }
}
