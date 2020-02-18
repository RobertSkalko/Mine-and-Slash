package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.spells.spell_classes.arcanist.MagicMissileSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.cleric.DivineShieldSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.cleric.InstantHealSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.cleric.RighteousFurySpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.druid.*;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ember_mage.BlazingInfernoSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ember_mage.FireballSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ember_mage.MagmaFlowerSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ember_mage.VolcanoSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean_mystic.*;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ranger.ArrowBarrageSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ranger.ImbueSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ranger.RecoilShotSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ranger.TripleShotSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.shaman.LightningTotemSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.shaman.ThunderDashSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.shaman.ThunderspearSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.shaman.ThunderstormSpell;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;

import java.util.ArrayList;
import java.util.List;

public class Spells implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        List<BaseSpell> All = new ArrayList<BaseSpell>() {
            {
                {

                    add(MagicMissileSpell.getInstance());

                    add(FrostballSpell.getInstance());
                    add(BlizzardSpell.getInstance());
                    add(WhirlpoolSpell.getInstance());
                    add(HeartOfIceSpell.getInstance());
                    add(GeyserSpell.getInstance());

                    add(InstantHealSpell.getInstance());
                    add(RighteousFurySpell.getInstance());
                    add(DivineShieldSpell.getInstance());

                    add(RegenerateSpell.getInstance());
                    add(GorgonsGazeSpell.getInstance());
                    add(ThornArmorSpell.getInstance());
                    add(ThornBushSpell.getInstance());
                    add(PoisonedWeaponsSpell.getInstance());

                    add(ThunderstormSpell.getInstance());
                    add(ThunderspearSpell.getInstance());
                    add(ThunderDashSpell.getInstance());
                    add(LightningTotemSpell.getInstance());

                    add(BlazingInfernoSpell.getInstance());
                    add(FireballSpell.getInstance());
                    add(VolcanoSpell.getInstance());
                    add(MagmaFlowerSpell.getInstance());

                    add(ArrowBarrageSpell.getInstance());
                    add(RecoilShotSpell.getInstance());
                    add(TripleShotSpell.getInstance());
                    add(ImbueSpell.getInstance());

                }
            }

        };

        All.forEach(x -> x.registerToSlashRegistry());

    }
}
