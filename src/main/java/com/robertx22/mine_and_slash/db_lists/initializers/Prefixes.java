package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.affixes.AffixBuilder;
import com.robertx22.mine_and_slash.database.affixes.BaseAffix;
import com.robertx22.mine_and_slash.database.affixes.ElementalAffixBuilder;
import com.robertx22.mine_and_slash.database.affixes.ElementalAffixTemplate;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.types.generated.WeaponDamage;
import com.robertx22.mine_and_slash.db_lists.bases.IRandomDefault;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

import java.util.Arrays;
import java.util.List;

public class Prefixes implements IRandomDefault<BaseAffix>, ISlashRegistryInit {

    @Override
    public void registerAll() {

        ElementalAffixBuilder.start()
            .guid(x -> x.guidName + "wep_dmg");

        AffixBuilder.Elemental(new ElementalAffixTemplate() {
            @Override
            public String GUID(Elements element) {
                return element.guidName + "wep_dmg";
            }

            @Override
            public List<StatModifier> Stats(Elements element) {
                return Arrays.asList(new StatModifier(1, 3, 2, 6, new WeaponDamage(element), StatModTypes.Flat));
            }

            @Override
            public String FireName() {
                return "Scorched";
            }

            @Override
            public String WaterName() {
                return "Chilled";
            }

            @Override
            public String NatureName() {
                return "Poisoned";
            }

            @Override
            public String ThunderName() {
                return "Sparkling";
            }
        })
            .Req(SlotRequirement.Of(GearItemSlot.SlotFamily.Weapon))
            .Prefix()
            .Build();

    }

    public static final Prefixes INSTANCE = new Prefixes();

    @Override
    public List<BaseAffix> All() {
        return SlashRegistry.Affixes()
            .getWrapped()
            .ofAffixType(BaseAffix.Type.prefix).list;
    }

}
