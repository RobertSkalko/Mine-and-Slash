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

        AffixBuilder.Of("of_cruelty")
            .Named("Of Cruelty")
            .Stats(new StatModifier(10, 50, new WeaponDamage(Elements.Physical), StatModTypes.LOCAL_INCREASE))
            .Req(SlotRequirement.Of(GearItemSlot.SlotFamily.Weapon))
            .BuildSuffix();

        AffixBuilder.Of("of_tyranny")
            .Named("Of Tyranny")
            .Stats(new StatModifier(0.2F, 2.5F, 0.5F, 3F, new WeaponDamage(Elements.Physical), StatModTypes.Flat))
            .Req(SlotRequirement.Of(GearItemSlot.SlotFamily.Weapon))
            .BuildSuffix();

    }
}
