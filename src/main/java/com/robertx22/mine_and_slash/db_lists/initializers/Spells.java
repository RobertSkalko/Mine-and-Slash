package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.spells.SpellBonusEleBasicDmg;
import com.robertx22.mine_and_slash.spells.aoe_bomb_proj.SpellAcidBomb;
import com.robertx22.mine_and_slash.spells.aoe_bomb_proj.SpellFireBomb;
import com.robertx22.mine_and_slash.spells.aoe_bomb_proj.SpellIceBomb;
import com.robertx22.mine_and_slash.spells.aoe_bomb_proj.SpellThunderBomb;
import com.robertx22.mine_and_slash.spells.aoe_projectile.SpellAcidExplosion;
import com.robertx22.mine_and_slash.spells.aoe_projectile.SpellFlameExplosion;
import com.robertx22.mine_and_slash.spells.aoe_projectile.SpellFrostExplosion;
import com.robertx22.mine_and_slash.spells.aoe_projectile.SpellLightningExplosion;
import com.robertx22.mine_and_slash.spells.bases.BaseSpell;
import com.robertx22.mine_and_slash.spells.nova.SpellFireNova;
import com.robertx22.mine_and_slash.spells.nova.SpellFrostNova;
import com.robertx22.mine_and_slash.spells.nova.SpellPoisonNova;
import com.robertx22.mine_and_slash.spells.nova.SpellThunderNova;
import com.robertx22.mine_and_slash.spells.projectile.SpellAcidBolt;
import com.robertx22.mine_and_slash.spells.projectile.SpellFireBolt;
import com.robertx22.mine_and_slash.spells.projectile.SpellFrostBolt;
import com.robertx22.mine_and_slash.spells.projectile.SpellThunderBolt;
import com.robertx22.mine_and_slash.spells.self.SpellInstantHeal;
import com.robertx22.mine_and_slash.spells.self.SpellSelfRegen;
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
                    add(new SpellAcidBomb());
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

        All.forEach(x -> SlashRegistry.getRegistry(SlashRegistryType.SPELL).register(x));

    }
}
