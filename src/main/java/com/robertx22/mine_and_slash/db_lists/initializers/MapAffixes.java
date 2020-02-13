package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.map_affixes.BaseMapAffix;
import com.robertx22.mine_and_slash.database.map_affixes.beneficial.BonusEleDmgAffix;
import com.robertx22.mine_and_slash.database.map_affixes.beneficial.BonusEleResistAffix;
import com.robertx22.mine_and_slash.database.map_affixes.beneficial.BonusHealthAffix;
import com.robertx22.mine_and_slash.database.map_affixes.beneficial.BonusLifestealAffix;
import com.robertx22.mine_and_slash.database.map_affixes.detrimental.*;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.List;

public class MapAffixes implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        List<BaseMapAffix> All = new ArrayList<>();

        List<BaseMapAffix> list = new ArrayList<BaseMapAffix>() {
            {
                {

                    add(new LessWeaponDamageMapAffix(WeaponTypes.None));

                    add(new BonusHealthAffix());
                    add(new BonusLifestealAffix());

                    add(new BonusEleDmgAffix(Elements.Nature));
                    add(new BonusEleResistAffix(Elements.Nature));
                    add(new LessEleDmgAffix(Elements.Nature));

                    add(new LessDodgeAffix());
                    add(new LessCriticalHitAffix());

                    // resources
                    add(new LessEnergyRegenAffix());
                    add(new LessManaRegenAffix());
                    add(new LessHealthRegenAffix());
                    add(new LessLifeOnHitAffix());
                    add(new LessLifestealAffix());
                    add(new LessHealthAffix());
                    add(new LessManaOnHitAffix());

                }
            }
        };

        for (BaseMapAffix affix : list) {

            if (affix instanceof IGenerated) {
                IGenerated<BaseMapAffix> gen = (IGenerated<BaseMapAffix>) affix;
                for (BaseMapAffix statmod : gen.generateAllPossibleStatVariations()) {
                    All.add(statmod);
                }
            } else {

                All.add(affix);
            }
        }

        All.forEach(x -> x.registerToSlashRegistry());

    }

}
