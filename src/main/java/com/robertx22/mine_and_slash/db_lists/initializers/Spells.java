package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.cleric.DivineShieldSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.cleric.DivineTribulationSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.cleric.InstantHealSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.cleric.RighteousFurySpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.druid.*;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ember_mage.BlazingInfernoSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ember_mage.FireballSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ember_mage.MagmaFlowerSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ember_mage.VolcanoSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean_mystic.*;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ranger.*;
import com.robertx22.mine_and_slash.database.spells.spell_classes.rogue.BlinkStrikeSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.rogue.StealthSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.rogue.TripleSlashSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.shaman.*;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;

import java.util.ArrayList;
import java.util.List;

public class Spells implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        List<BaseSpell> All = new ArrayList<BaseSpell>() {
            {
                {

                    add(FrostballSpell.getInstance());
                    add(BlizzardSpell.getInstance());
                    add(WhirlpoolSpell.getInstance());
                    add(HeartOfIceSpell.getInstance());
                    add(TidalWaveSpell.getInstance());

                    add(InstantHealSpell.getInstance());
                    add(RighteousFurySpell.getInstance());
                    add(DivineShieldSpell.getInstance());
                    add(DivineTribulationSpell.getInstance());

                    add(RegenerateSpell.getInstance());
                    add(GorgonsGazeSpell.getInstance());
                    add(ThornArmorSpell.getInstance());
                    add(ThornBushSpell.getInstance());
                    add(PoisonedWeaponsSpell.getInstance());
                    add(PoisonBallSpell.getInstance());
                    add(new SummonSpidersSpell());

                    add(ThunderstormSpell.getInstance());
                    add(ThunderspearSpell.getInstance());
                    add(ThunderDashSpell.getInstance());
                    add(LightningTotemSpell.getInstance());
                    add(new SpiritWolfSpell());

                    add(BlazingInfernoSpell.getInstance());
                    add(FireballSpell.getInstance());
                    add(VolcanoSpell.getInstance());
                    add(MagmaFlowerSpell.getInstance());

                    add(ArrowBarrageSpell.getInstance());
                    add(RecoilShotSpell.getInstance());
                    add(TripleShotSpell.getInstance());
                    add(ImbueSpell.getInstance());
                    add(ArrowStormSpell.getInstance());

                    add(StealthSpell.getInstance());
                    add(BlinkStrikeSpell.getInstance());
                    add(TripleSlashSpell.getInstance());

                }
            }

        };

        All.forEach(x -> x.registerToSlashRegistry());

    }
}
