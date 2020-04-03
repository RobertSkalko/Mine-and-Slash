package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.affixes.BaseAffix;
import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.affixes.suffixes.*;
import com.robertx22.mine_and_slash.database.affixes.suffixes.curses.OfLessCoreStat;
import com.robertx22.mine_and_slash.database.affixes.suffixes.curses.OfMortality;
import com.robertx22.mine_and_slash.database.affixes.suffixes.defense.*;
import com.robertx22.mine_and_slash.database.affixes.suffixes.mixed.OfTheFallen;
import com.robertx22.mine_and_slash.database.affixes.suffixes.offense.*;
import com.robertx22.mine_and_slash.database.affixes.suffixes.resource.*;
import com.robertx22.mine_and_slash.database.affixes.suffixes.thon_reward.OfGrowth;
import com.robertx22.mine_and_slash.database.affixes.suffixes.thon_reward.OfTheCactus;
import com.robertx22.mine_and_slash.database.affixes.suffixes.unique.OfGodhood;
import com.robertx22.mine_and_slash.database.affixes.suffixes.unique.OfTheHydra;
import com.robertx22.mine_and_slash.database.affixes.suffixes.unique.OfWeaponFlurry;
import com.robertx22.mine_and_slash.db_lists.bases.IRandomDefault;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.List;

public class Suffixes implements IRandomDefault<BaseAffix>, ISlashRegistryInit {

    public static Suffixes INSTANCE = new Suffixes();

    @Override
    public List<BaseAffix> All() {
        return SlashRegistry.Affixes()
            .getWrapped()
            .ofAffixType(BaseAffix.Type.suffix).list;
    }

    @Override
    public void registerAll() {

        List<Suffix> allSuffixes = new ArrayList<Suffix>() {
            {
                {
                    add(new OfMagicShield());

                    add(new OfTheHydra());
                    add(new OfGiants());
                    add(new OfBehemoths());

                    add(new OfBalance());
                    add(new OfGuidance());
                    add(new OfSwiftness());

                    add(new OfGodhood());
                    add(new OfCriticalHits());
                    add(new OfCriticalDamage());
                    add(new OfCriticalUnity());
                    add(new OfVampirism());
                    add(new OfForce());
                    add(new OfRockPiercing());

                    add(new OfRockSkin());
                    add(new OfElementResist());
                    add(new OfImmortality());
                    add(new OfHiddenSense());
                    add(new OfTheDepths());

                    add(new OfVitality());
                    add(new OfManaRegen());
                    add(new OfTheSage());
                    add(new OfMagicStealing());

                    add(OfLessCoreStat.DEX);
                    add(OfLessCoreStat.STA);
                    add(OfLessCoreStat.INT);
                    add(OfLessCoreStat.WIS);
                    add(OfLessCoreStat.VIT);
                    add(OfLessCoreStat.STR);

                    add(new OfTheFallen());

                    add(new OfMortality());

                    add(new OfGrowth());
                    add(new OfTheCactus());

                }
            }
        };

        List<Suffix> all = new ArrayList<>();

        List<IGenerated<Suffix>> allGenerated = new ArrayList<IGenerated<Suffix>>() {
            {
                {
                    add(new OfEleStorms(Elements.Physical));
                    add(new OfDissonance(Elements.Physical));
                    add(new OfMajorAffinity(Elements.Physical));
                    add(new OfWeaponFlurry(WeaponTypes.None));

                }
            }
        };

        List<Suffix> list = new ArrayList<Suffix>();
        list.addAll(allSuffixes);

        for (Suffix s : list) {
            all.add(s);
        }

        for (IGenerated<Suffix> gen : allGenerated) {
            for (Suffix statmod : gen.generateAllPossibleStatVariations()) {
                all.add(statmod);
            }
        }

        all.forEach(x -> x.addToSerializables());

    }
}
