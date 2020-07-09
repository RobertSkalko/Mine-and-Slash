package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.affixes.AffixBuilder;
import com.robertx22.mine_and_slash.database.affixes.BaseAffix;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.types.generated.WeaponDamage;
import com.robertx22.mine_and_slash.db_lists.bases.IRandomDefault;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

import java.util.List;

public class Prefixes implements IRandomDefault<BaseAffix>, ISlashRegistryInit {

    @Override
    public void registerAll() {

        AffixBuilder.Of("chilled")
            .Named("Chilled")
            .Stats(new StatModifier(1, 3, 2, 6, new WeaponDamage(Elements.Water), StatModTypes.Flat))
            .Req(SlotRequirement.Of(GearItemSlot.SlotFamily.Weapon))
            .BuildPrefix();

        AffixBuilder.Of("scorched")
            .Named("Scorched")
            .Stats(new StatModifier(1, 3, 2, 6, new WeaponDamage(Elements.Fire), StatModTypes.Flat))
            .Req(SlotRequirement.Of(GearItemSlot.SlotFamily.Weapon))
            .BuildPrefix();

    }

    public static final Prefixes INSTANCE = new Prefixes();

    @Override
    public List<BaseAffix> All() {
        return SlashRegistry.Affixes()
            .getWrapped()
            .ofAffixType(BaseAffix.Type.prefix).list;
    }

}
