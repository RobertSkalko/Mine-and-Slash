package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.data_generation.wrappers.StatModsHolder;
import com.robertx22.mine_and_slash.database.affixes.BaseAffix;
import com.robertx22.mine_and_slash.database.mob_affixes.base.MobAffix;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CompletePhysDispersionFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalAttackDamageFlat;
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

    @Override
    public void registerAll() {

        List<MobAffix> all = new ArrayList<>();

        all.add(COLD);
        all.add(FLAMING);
        all.add(LIGHTNING);
        all.add(VENOMOUS);

        all.forEach(x -> x.addToSerializables());

    }
}
