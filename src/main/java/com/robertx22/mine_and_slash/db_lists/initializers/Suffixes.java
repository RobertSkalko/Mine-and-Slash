package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.affixes.AffixBuilder;
import com.robertx22.mine_and_slash.database.affixes.BaseAffix;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalHit;
import com.robertx22.mine_and_slash.database.stats.types.resources.Lifesteal;
import com.robertx22.mine_and_slash.db_lists.bases.IRandomDefault;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.enumclasses.ModType;

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

        AffixBuilder.Normal("of_precision")
            .Named("Of Precision")
            .Stats(new StatModifier(10F, 50F, CriticalHit.getInstance(), ModType.LOCAL_INCREASE))
            .Req(SlotRequirement.Of(GearItemSlot.SlotFamily.Weapon))
            .Suffix()
            .Build();

        AffixBuilder.Normal("of_good_aim")
            .Named("Of Good Aim")
            .Stats(new StatModifier(1, 6F, CriticalHit.getInstance(), ModType.FLAT))
            .Req(SlotRequirement.Of(GearItemSlot.SlotFamily.Weapon))
            .Suffix()
            .Build();

        AffixBuilder.Normal("of_vampirism")
            .Named("Of Vampirism")
            .Stats(new StatModifier(1, 3, Lifesteal.getInstance(), ModType.FLAT))
            .Req(SlotRequirement.Of(GearItemSlot.SlotFamily.Weapon))
            .Suffix()
            .Build();

        AffixBuilder.Normal("of_brutality")
            .Named("Of Brutality")
            .Stats(new StatModifier(4, 20, CriticalDamage.getInstance(), ModType.FLAT))
            .Req(SlotRequirement.Of(GearItemSlot.SlotFamily.Weapon))
            .Suffix()
            .Build();

    }
}
