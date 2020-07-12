package com.robertx22.mine_and_slash.database.affixes.data;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.affixes.ElementalAffixBuilder;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalResist;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.ModType;

import java.util.Arrays;

public class NonWeaponSuffixes implements ISlashRegistryInit {
    @Override
    public void registerAll() {

        ElementalAffixBuilder.start()
            .guid(x -> x.guidName + "_res")
            .add(Elements.Fire, "Of the Drake")
            .add(Elements.Water, "Of the Yeti")
            .add(Elements.Thunder, "Of the Tempest")
            .add(Elements.Nature, "Of the Snake")
            .tier(1, x -> Arrays.asList(new StatModifier(25, 30, new ElementalResist(x), ModType.FLAT)))
            .tier(2, x -> Arrays.asList(new StatModifier(20, 25, new ElementalResist(x), ModType.FLAT)))
            .tier(3, x -> Arrays.asList(new StatModifier(12, 20, new ElementalResist(x), ModType.FLAT)))
            .tier(4, x -> Arrays.asList(new StatModifier(6, 12, new ElementalResist(x), ModType.FLAT)))
            .tier(5, x -> Arrays.asList(new StatModifier(3, 6, new ElementalResist(x), ModType.FLAT)))
            .Req(SlotRequirement.everythingBesides(GearItemSlot.SlotFamily.Weapon))
            .Suffix()
            .Build();
    }
}