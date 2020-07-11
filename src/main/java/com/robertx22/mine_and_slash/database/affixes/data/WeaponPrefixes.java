package com.robertx22.mine_and_slash.database.affixes.data;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.affixes.AffixBuilder;
import com.robertx22.mine_and_slash.database.affixes.ElementalAffixBuilder;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.types.generated.WeaponDamage;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.ModType;

import java.util.Arrays;

public class WeaponPrefixes implements ISlashRegistryInit {
    @Override
    public void registerAll() {

        ElementalAffixBuilder.start()
            .guid(x -> x.guidName + "_wep_dmg")
            .add(Elements.Fire, "Scorched")
            .add(Elements.Water, "Chilled")
            .add(Elements.Thunder, "Sparkling")
            .add(Elements.Nature, "Poisoned")
            .tier(1, x -> Arrays.asList(new StatModifier(1, 3, 2, 6, new WeaponDamage(x), ModType.FLAT)))
            .tier(2, x -> Arrays.asList(new StatModifier(1, 3, 2, 6, new WeaponDamage(x), ModType.FLAT)))
            .tier(3, x -> Arrays.asList(new StatModifier(1, 3, 2, 6, new WeaponDamage(x), ModType.FLAT)))
            .Req(SlotRequirement.Of(GearItemSlot.SlotFamily.Weapon))
            .Prefix()
            .Build();

        AffixBuilder.Normal("cruel")
            .Named("Cruel")
            .tier(1, new StatModifier(60, 75, new WeaponDamage(Elements.Physical), ModType.LOCAL_INCREASE))
            .tier(2, new StatModifier(45, 60, new WeaponDamage(Elements.Physical), ModType.LOCAL_INCREASE))
            .tier(3, new StatModifier(35, 45, new WeaponDamage(Elements.Physical), ModType.LOCAL_INCREASE))
            .tier(4, new StatModifier(20, 35, new WeaponDamage(Elements.Physical), ModType.LOCAL_INCREASE))
            .tier(5, new StatModifier(10, 20, new WeaponDamage(Elements.Physical), ModType.LOCAL_INCREASE))
            .Req(SlotRequirement.Of(GearItemSlot.SlotFamily.Weapon))
            .Prefix()
            .Build();

        AffixBuilder.Normal("tyrannical")
            .Named("Tyrannical")
            .tier(1, new StatModifier(0.3F, 2.5F, 0.5F, 3F, new WeaponDamage(Elements.Physical), ModType.FLAT))
            .tier(1, new StatModifier(0.2F, 1F, 0.3F, 2F, new WeaponDamage(Elements.Physical), ModType.FLAT))
            .tier(1, new StatModifier(0.1F, 0.5F, 0.5F, 1.2F, new WeaponDamage(Elements.Physical), ModType.FLAT))
            .Req(SlotRequirement.Of(GearItemSlot.SlotFamily.Weapon))
            .Prefix()
            .Build();

    }

}
