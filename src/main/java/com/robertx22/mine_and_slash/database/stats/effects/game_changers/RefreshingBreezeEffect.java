package com.robertx22.mine_and_slash.database.stats.effects.game_changers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.game_changers.RefreshingBreeze;
import com.robertx22.mine_and_slash.database.stats.types.resources.EnergyRegen;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;

public class RefreshingBreezeEffect implements IStatEffect {

    public static final RefreshingBreezeEffect INSTANCE = new RefreshingBreezeEffect();

    @Override
    public int GetPriority() {
        return Priority.Last.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Target;
    }

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data,
                                      Stat stat) {

        try {
            if (Effect instanceof DamageEffect) {

                DamageEffect dmg = (DamageEffect) Effect;

                if (dmg.isDodged) {
                    float energy = dmg.targetData.getUnit()
                            .getStat(EnergyRegen.GUID).Value * RefreshingBreeze.PERCENT / 100;
                    ResourcesData.Context ene = new ResourcesData.Context(dmg.targetData, dmg.target, ResourcesData.Type.ENERGY, energy, ResourcesData.Use.RESTORE);
                    dmg.targetData.getResources().modify(ene);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}
