package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.sets.Set;
import com.robertx22.mine_and_slash.database.sets.endgame_lvl.AscensionOfElement;
import com.robertx22.mine_and_slash.database.sets.endgame_lvl.RingsOfImpossibility;
import com.robertx22.mine_and_slash.database.sets.from_lvl_50.*;
import com.robertx22.mine_and_slash.database.sets.low_lvl.armors.*;
import com.robertx22.mine_and_slash.database.sets.low_lvl.elemental_jewerly.FlamingDevil;
import com.robertx22.mine_and_slash.database.sets.low_lvl.elemental_jewerly.ForestGuardian;
import com.robertx22.mine_and_slash.database.sets.low_lvl.elemental_jewerly.IceLord;
import com.robertx22.mine_and_slash.database.sets.low_lvl.elemental_jewerly.WillOfLightning;
import com.robertx22.mine_and_slash.database.sets.low_lvl.misc.Limitless;
import com.robertx22.mine_and_slash.database.sets.low_lvl.misc.TreeOfLife;
import com.robertx22.mine_and_slash.database.sets.mid_lvl.ArmorOfTheElements;
import com.robertx22.mine_and_slash.database.sets.mid_lvl.ArtifactArmor;
import com.robertx22.mine_and_slash.database.sets.mid_lvl.SpiritOfTheArcane;
import com.robertx22.mine_and_slash.database.sets.mid_lvl.TheProtector;
import com.robertx22.mine_and_slash.database.sets.uniques.TreasureFinder;
import com.robertx22.mine_and_slash.database.sets.uniques.WisdomOfTheElders;
import com.robertx22.mine_and_slash.db_lists.bases.IRandomDefault;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Sets implements IRandomDefault<Set>, ISlashRegistryInit {

    public static final Sets INTANCE = new Sets();

    @Override
    public HashMap<String, Set> All() {
        return SlashRegistry.Sets().getAll();
    }

    @Override
    public void registerAll() {

        List<Set> All = new ArrayList<>();

        List<Set> sets = new ArrayList<Set>() {
            {
                {

                    add(new AscensionOfElement(Elements.Physical));
                    add(new RoyalThiefAdornments());
                    add(new RingsOfImpossibility());

                    add(new Eliminator());
                    add(new TreasureFinder());
                    //high lvl
                    add(new MysticalOrnaments());
                    add(new ElementalEssence());
                    add(new SeersGuidance());
                    add(new GodKingsPlate());
                    add(new TheAscended());

                    // mid lvl
                    add(new ArmorOfTheElements());
                    add(new TheProtector());
                    add(new SpiritOfTheArcane());
                    add(new ArtifactArmor());

                    // low lvl
                    add(new BarbarianArmor());
                    add(new MagesRobes());
                    add(new RockmanChains());
                    add(new RangerArmor());

                    add(new IceLord());
                    add(new FlamingDevil());
                    add(new ForestGuardian());
                    add(new WillOfLightning());

                    add(new TreeOfLife());
                    add(new Limitless());
                    add(new ScholarRobes());

                    add(new WisdomOfTheElders());

                }
            }
        };

        for (Set set : sets) {
            if (set instanceof IGenerated) {
                for (Set statmod : ((IGenerated<Set>) set).generateAllPossibleStatVariations()) {
                    All.add(statmod);
                }
            } else {
                All.add(set);
            }

        }

        All.forEach(x -> SlashRegistry.getRegistry(SlashRegistryType.SET).register(x));

    }

}
