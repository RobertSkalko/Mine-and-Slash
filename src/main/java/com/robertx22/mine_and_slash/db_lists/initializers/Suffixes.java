package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.affixes.suffixes.*;
import com.robertx22.mine_and_slash.database.affixes.suffixes.defense.*;
import com.robertx22.mine_and_slash.database.affixes.suffixes.offense.*;
import com.robertx22.mine_and_slash.database.affixes.suffixes.resource.OfManaRegen;
import com.robertx22.mine_and_slash.database.affixes.suffixes.resource.OfTheDepths;
import com.robertx22.mine_and_slash.database.affixes.suffixes.resource.OfTheSage;
import com.robertx22.mine_and_slash.database.affixes.suffixes.resource.OfVampirism;
import com.robertx22.mine_and_slash.database.affixes.suffixes.unique.OfGodhood;
import com.robertx22.mine_and_slash.database.affixes.suffixes.unique.OfTheHydra;
import com.robertx22.mine_and_slash.database.affixes.suffixes.unique.OfWeaponFlurry;
import com.robertx22.mine_and_slash.db_lists.bases.IRandomDefault;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Suffixes implements IRandomDefault<Suffix>, ISlashRegistryInit {

    public static Suffixes INSTANCE = new Suffixes();

    @Override
    public HashMap<String, Suffix> All() {
        return SlashRegistry.Suffixes().getAll();
    }

    @Override
    public void registerAll() {

        List<Suffix> allSuffixes = new ArrayList<Suffix>() {
            {
                {
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

        all.forEach(x -> SlashRegistry.getRegistry(SlashRegistryType.SUFFIX).register(x));

    }
}
