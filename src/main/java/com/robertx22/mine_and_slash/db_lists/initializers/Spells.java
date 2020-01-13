package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.spells.spell_classes.SpellBonusEleBasicDmg;
import com.robertx22.mine_and_slash.database.spells.spell_classes.aoe_bomb_proj.SpellAcidBomb;
import com.robertx22.mine_and_slash.database.spells.spell_classes.aoe_bomb_proj.SpellFireBomb;
import com.robertx22.mine_and_slash.database.spells.spell_classes.aoe_bomb_proj.SpellIceBomb;
import com.robertx22.mine_and_slash.database.spells.spell_classes.aoe_bomb_proj.SpellThunderBomb;
import com.robertx22.mine_and_slash.database.spells.spell_classes.aoe_projectile.SpellAcidExplosion;
import com.robertx22.mine_and_slash.database.spells.spell_classes.aoe_projectile.SpellFlameExplosion;
import com.robertx22.mine_and_slash.database.spells.spell_classes.aoe_projectile.SpellFrostExplosion;
import com.robertx22.mine_and_slash.database.spells.spell_classes.aoe_projectile.SpellLightningExplosion;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.nova.SpellFireNova;
import com.robertx22.mine_and_slash.database.spells.spell_classes.nova.SpellFrostNova;
import com.robertx22.mine_and_slash.database.spells.spell_classes.nova.SpellPoisonNova;
import com.robertx22.mine_and_slash.database.spells.spell_classes.nova.SpellThunderNova;
import com.robertx22.mine_and_slash.database.spells.spell_classes.projectile.SpellAcidBolt;
import com.robertx22.mine_and_slash.database.spells.spell_classes.projectile.SpellFireBolt;
import com.robertx22.mine_and_slash.database.spells.spell_classes.projectile.SpellFrostBolt;
import com.robertx22.mine_and_slash.database.spells.spell_classes.projectile.SpellThunderBolt;
import com.robertx22.mine_and_slash.database.spells.spell_classes.self.SpellInstantHeal;
import com.robertx22.mine_and_slash.database.spells.spell_classes.self.SpellSelfRegen;
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
                    add(new SpellFrostBolt());
                    add(new SpellFireBolt());
                    add(new SpellAcidBolt());
                    add(new SpellThunderBolt());

                    add(new SpellFrostExplosion());
                    add(new SpellFlameExplosion());
                    add(new SpellLightningExplosion());
                    add(new SpellAcidExplosion());

                    add(new SpellInstantHeal());
                    add(new SpellSelfRegen());

                    add(new SpellFireBomb());
                    add(new SpellThunderBomb());
                    add(SpellAcidBomb.getInstance());
                    add(new SpellIceBomb());

                    add(new SpellFrostNova());
                    add(new SpellFireNova());
                    add(new SpellThunderNova());
                    add(new SpellPoisonNova());

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
