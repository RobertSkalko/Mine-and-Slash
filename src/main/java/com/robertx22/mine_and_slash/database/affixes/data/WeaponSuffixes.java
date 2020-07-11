package com.robertx22.mine_and_slash.database.affixes.data;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.affixes.AffixBuilder;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalHit;
import com.robertx22.mine_and_slash.database.stats.types.resources.Lifesteal;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.uncommon.enumclasses.ModType;

public class WeaponSuffixes implements ISlashRegistryInit {
    @Override
    public void registerAll() {

        AffixBuilder.Normal("of_precision")
            .Named("Of Precision")
            .tier(1, new StatModifier(25F, 50F, CriticalHit.getInstance(), ModType.LOCAL_INCREASE))
            .tier(2, new StatModifier(20F, 25F, CriticalHit.getInstance(), ModType.LOCAL_INCREASE))
            .tier(3, new StatModifier(10, 20F, CriticalHit.getInstance(), ModType.LOCAL_INCREASE))
            .Req(SlotRequirement.Of(GearItemSlot.SlotFamily.Weapon))
            .Suffix()
            .Build();

        AffixBuilder.Normal("of_good_aim")
            .Named("Of Good Aim")
            .tier(1, new StatModifier(4, 8, CriticalHit.getInstance(), ModType.FLAT))
            .tier(2, new StatModifier(2, 4, CriticalHit.getInstance(), ModType.FLAT))
            .tier(3, new StatModifier(1, 2F, CriticalHit.getInstance(), ModType.FLAT))

            .Req(SlotRequirement.Of(GearItemSlot.SlotFamily.Weapon))
            .Suffix()
            .Build();

        AffixBuilder.Normal("of_vampirism")
            .Named("Of Vampirism")
            .tier(1, new StatModifier(3, 5, Lifesteal.getInstance(), ModType.FLAT))
            .tier(2, new StatModifier(2, 3, Lifesteal.getInstance(), ModType.FLAT))
            .tier(3, new StatModifier(1, 2, Lifesteal.getInstance(), ModType.FLAT))
            .Req(SlotRequirement.Of(GearItemSlot.SlotFamily.Weapon))
            .Suffix()
            .Build();

        AffixBuilder.Normal("of_brutality")
            .Named("Of Brutality")
            .tier(1, new StatModifier(15, 20, CriticalDamage.getInstance(), ModType.FLAT))
            .tier(2, new StatModifier(10, 15, CriticalDamage.getInstance(), ModType.FLAT))
            .tier(3, new StatModifier(5, 10, CriticalDamage.getInstance(), ModType.FLAT))
            .tier(4, new StatModifier(3, 5, CriticalDamage.getInstance(), ModType.FLAT))
            .Req(SlotRequirement.Of(GearItemSlot.SlotFamily.Weapon))
            .Suffix()
            .Build();

    }
}
