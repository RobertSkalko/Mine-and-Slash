package com.robertx22.mine_and_slash.database.affixes.data;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.affixes.ElementalAffixBuilder;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.types.generated.WeaponDamage;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.ModType;

import java.util.Arrays;

public class JewelryPrefixes implements ISlashRegistryInit {
    @Override
    public void registerAll() {

        ElementalAffixBuilder.start()
            .guid(x -> x.guidName + "_wep_dmg_jewelry")
            .add(Elements.Fire, "Smoking")
            .add(Elements.Water, "Freezing")
            .add(Elements.Thunder, "Shocking")
            .add(Elements.Nature, "Dripping")
            .tier(1, x -> Arrays.asList(new StatModifier(2, 3, 2, 3, new WeaponDamage(x), ModType.FLAT)))
            .tier(2, x -> Arrays.asList(new StatModifier(1, 3, 2, 3, new WeaponDamage(x), ModType.FLAT)))
            .tier(3, x -> Arrays.asList(new StatModifier(1, 1, 2, 2, new WeaponDamage(x), ModType.FLAT)))
            .Req(SlotRequirement.Of(GearItemSlot.SlotFamily.Jewelry))
            .Weight(200)
            .Prefix()
            .Build();
    }
}
