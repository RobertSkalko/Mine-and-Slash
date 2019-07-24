package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.affixes.prefixes.defense.*;
import com.robertx22.mine_and_slash.database.affixes.prefixes.defense.element.EleShieldPrefix;
import com.robertx22.mine_and_slash.database.affixes.prefixes.misc.*;
import com.robertx22.mine_and_slash.database.affixes.prefixes.offense.*;
import com.robertx22.mine_and_slash.database.affixes.prefixes.offense.damage_percents.*;
import com.robertx22.mine_and_slash.database.affixes.prefixes.resource.Energetic;
import com.robertx22.mine_and_slash.database.affixes.prefixes.resource.LifeStealing;
import com.robertx22.mine_and_slash.database.affixes.prefixes.resource.Tenacious;
import com.robertx22.mine_and_slash.database.affixes.prefixes.resource.Wise;
import com.robertx22.mine_and_slash.database.affixes.prefixes.resource.rare_resource.BraveHeart;
import com.robertx22.mine_and_slash.database.affixes.prefixes.resource.rare_resource.DeepMind;
import com.robertx22.mine_and_slash.database.affixes.prefixes.resource.rare_resource.InnerSpirit;
import com.robertx22.mine_and_slash.database.affixes.prefixes.uniques.Heros;
import com.robertx22.mine_and_slash.database.affixes.prefixes.uniques.MagesGamble;
import com.robertx22.mine_and_slash.db_lists.bases.IRandomDefault;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.spells.projectile.SpellAcidBolt;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Prefixes implements IRandomDefault<Prefix>, ISlashRegistryInit {

    @Override
    public void registerAll() {

        List<Prefix> allPrefixes = new ArrayList<Prefix>() {
            {
                {

                    add(new Heros());
                    add(new Defenders());

                    add(new Looters());
                    add(new Archaeologists());
                    add(new TreasureSeeker());

                    add(new Wise());
                    add(new Tenacious());

                    add(new Gatekeepers());

                    add(new TouchOfMagic());
                    add(new Arcanists());
                    add(new Magical());

                    add(new ThirstOfAcid());
                    add(new ThirstOfFrost());
                    add(new ThirstOfFlame());
                    add(new ThirstOfLightning());

                    add(new HardHitting());
                    add(new LifeStealing());
                    add(new HeavenlyStrikes());

                    add(new Flaming());
                    add(new Frosty());
                    add(new Thorny());
                    add(new Thundering());

                    add(new Hardened());
                    add(new Evasive());
                    add(new HeavenlySkin());

                    add(new BraveHeart());
                    add(new DeepMind());
                    add(new InnerSpirit());

                    add(new Energetic());
                    add(new Tough());

                }
            }
        };

        List<Prefix> all = new ArrayList<>();

        List<IGenerated<Prefix>> allGenerated = new ArrayList<IGenerated<Prefix>>() {
            {
                {
                    add(new Heretics(Elements.Physical));
                    add(new EleShieldPrefix(Elements.Physical));
                    add(new ElementImbued(Elements.Physical));
                    add(new ElementThornsMastery(Elements.Physical));
                    add(new MagesGamble(new SpellAcidBolt()));

                }
            }
        };

        List<Prefix> list = new ArrayList<Prefix>();
        list.addAll(allPrefixes);

        for (Prefix s : list) {
            all.add(s);
        }

        for (IGenerated<Prefix> gen : allGenerated) {
            for (Prefix statmod : gen.generateAllPossibleStatVariations()) {
                all.add(statmod);
            }
        }

        all.forEach(x -> SlashRegistry.getRegistry(SlashRegistryType.PREFIX).register(x));

    }

    public static final Prefixes INSTANCE = new Prefixes();

    @Override
    public HashMap<String, Prefix> All() {
        return SlashRegistry.Prefixes().getAll();
    }

}
