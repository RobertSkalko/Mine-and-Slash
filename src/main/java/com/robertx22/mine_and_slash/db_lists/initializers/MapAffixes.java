package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.map_affixes.BaseMapAffix;
import com.robertx22.mine_and_slash.database.map_affixes.beneficial.BonusHealthAffix;
import com.robertx22.mine_and_slash.database.map_affixes.beneficial.BonusLifestealAffix;
import com.robertx22.mine_and_slash.database.map_affixes.beneficial.ele_dmg.BonusFireDamageAffix;
import com.robertx22.mine_and_slash.database.map_affixes.beneficial.ele_dmg.BonusNatureDamageAffix;
import com.robertx22.mine_and_slash.database.map_affixes.beneficial.ele_dmg.BonusThunderDamageAffix;
import com.robertx22.mine_and_slash.database.map_affixes.beneficial.ele_dmg.BonusWaterDamageAffix;
import com.robertx22.mine_and_slash.database.map_affixes.beneficial.ele_res.BonusFireResistAffix;
import com.robertx22.mine_and_slash.database.map_affixes.beneficial.ele_res.BonusNatureResistAffix;
import com.robertx22.mine_and_slash.database.map_affixes.beneficial.ele_res.BonusThunderResistAffix;
import com.robertx22.mine_and_slash.database.map_affixes.beneficial.ele_res.BonusWaterResistAffix;
import com.robertx22.mine_and_slash.database.map_affixes.detrimental.*;
import com.robertx22.mine_and_slash.database.map_affixes.detrimental.ele.LessAllFireDmgAffix;
import com.robertx22.mine_and_slash.database.map_affixes.detrimental.ele.LessAllNatureDmgAffix;
import com.robertx22.mine_and_slash.database.map_affixes.detrimental.ele.LessAllThunderDmgAffix;
import com.robertx22.mine_and_slash.database.map_affixes.detrimental.ele.LessAllWaterDmgAffix;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
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

                    add(new BonusFireDamageAffix());
                    add(new BonusNatureDamageAffix());
                    add(new BonusThunderDamageAffix());
                    add(new BonusWaterDamageAffix());

                    add(new BonusFireResistAffix());
                    add(new BonusNatureResistAffix());
                    add(new BonusThunderResistAffix());
                    add(new BonusWaterResistAffix());

                    add(new LessAllFireDmgAffix());
                    add(new LessAllNatureDmgAffix());
                    add(new LessAllThunderDmgAffix());
                    add(new LessAllWaterDmgAffix());

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

        All.forEach(x -> SlashRegistry.getRegistry(SlashRegistryType.MAP_AFFIX)
                .register(x));

    }

}
