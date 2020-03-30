package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.data_generation.wrappers.StatModsHolder;
import com.robertx22.mine_and_slash.database.affixes.BaseAffix;
import com.robertx22.mine_and_slash.database.mob_affixes.base.MobAffix;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.DodgeRatingFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CompletePhysDispersionFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.LifeOnHitFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.LifestealFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalAttackDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalPeneFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.ArrayList;
import java.util.List;

public class MobAffixes implements ISlashRegistryInit {

    public static MobAffix EMPTY = new MobAffix("empty", "empty", BaseAffix.Type.prefix);

    public static MobAffix COLD = new MobAffix("cold", "Cold", BaseAffix.Type.prefix).setMods(new StatModsHolder(new ElementalAttackDamageFlat(Elements.Water), new CompletePhysDispersionFlat()));
    public static MobAffix FLAMING = new MobAffix("flaming", "Flaming", BaseAffix.Type.prefix).setMods(new StatModsHolder(new ElementalAttackDamageFlat(Elements.Fire), new CompletePhysDispersionFlat()));
    public static MobAffix LIGHTNING = new MobAffix("lightning", "Lightning", BaseAffix.Type.prefix).setMods(new StatModsHolder(new ElementalAttackDamageFlat(Elements.Thunder), new CompletePhysDispersionFlat()));
    public static MobAffix VENOMOUS = new MobAffix("venom", "Venomous", BaseAffix.Type.prefix).setMods(new StatModsHolder(new ElementalAttackDamageFlat(Elements.Nature), new CompletePhysDispersionFlat()));

    public static MobAffix OF_LEECHING = new MobAffix("of_leeching", "Of Leeching", BaseAffix.Type.suffix).setMods(new StatModsHolder(new LifestealFlat().size(StatMod.Size.TRIPLE), new LifeOnHitFlat()));
    public static MobAffix OF_MOUNTAINS = new MobAffix("of_mountains", "Of Mountains", BaseAffix.Type.suffix).setMods(new StatModsHolder(new HealthFlat().size(StatMod.Size.TRIPLE)));
    public static MobAffix EVASIVE = new MobAffix("evasive", "Evasive", BaseAffix.Type.prefix).setMods(new StatModsHolder(new DodgeRatingFlat().size(StatMod.Size.TRIPLE)));
    public static MobAffix RESISTANT = new MobAffix("resistant", "Resistant", BaseAffix.Type.prefix).setMods(new StatModsHolder(new ElementalResistFlat(Elements.Elemental).size(StatMod.Size.TRIPLE)));
    public static MobAffix OF_ROCK_SKIN = new MobAffix("of_rock_skin", "Of Rock Skin", BaseAffix.Type.suffix).setMods(new StatModsHolder(new ArmorFlat().size(StatMod.Size.TRIPLE)));

    public static MobAffix OF_HARSH_WINTER = new MobAffix("of_harsh_winter", "Of Harsh Winter", BaseAffix.Type.suffix).setMods(new StatModsHolder(new ElementalResistFlat(Elements.Water), new ElementalAttackDamageFlat(Elements.Water), new ElementalPeneFlat(Elements.Water).size(StatMod.Size.DOUBLE)));
    public static MobAffix OF_RAGING_VOLCANO = new MobAffix("of_raging_volcano", "Of Raging Volcano", BaseAffix.Type.suffix).setMods(new StatModsHolder(new ElementalResistFlat(Elements.Fire), new ElementalAttackDamageFlat(Elements.Fire), new ElementalPeneFlat(Elements.Fire).size(StatMod.Size.DOUBLE)));
    public static MobAffix OF_THUNDER_STRIKES = new MobAffix("of_thunder_strikes", "Of Thunder Strikes", BaseAffix.Type.suffix).setMods(new StatModsHolder(new ElementalResistFlat(Elements.Thunder), new ElementalAttackDamageFlat(Elements.Thunder), new ElementalPeneFlat(Elements.Thunder).size(StatMod.Size.DOUBLE)));
    public static MobAffix OF_THE_COBRA = new MobAffix("of_the_cobra", "Of the Cobra", BaseAffix.Type.suffix).setMods(new StatModsHolder(new ElementalResistFlat(Elements.Nature), new ElementalAttackDamageFlat(Elements.Nature), new ElementalPeneFlat(Elements.Nature).size(StatMod.Size.DOUBLE)));

    @Override
    public void registerAll() {

        List<MobAffix> all = new ArrayList<>();

        all.add(COLD);
        all.add(FLAMING);
        all.add(LIGHTNING);
        all.add(VENOMOUS);

        all.add(OF_HARSH_WINTER);
        all.add(OF_THUNDER_STRIKES);
        all.add(OF_RAGING_VOLCANO);
        all.add(OF_THE_COBRA);

        all.add(OF_LEECHING);
        all.add(OF_ROCK_SKIN);
        all.add(RESISTANT);
        all.add(EVASIVE);
        all.add(OF_MOUNTAINS);

        all.forEach(x -> x.addToSerializables());

    }
}
