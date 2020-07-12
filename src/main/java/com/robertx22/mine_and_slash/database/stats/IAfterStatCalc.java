package com.robertx22.mine_and_slash.database.stats;

import com.robertx22.mine_and_slash.saveclasses.unit.StatData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;

public interface IAfterStatCalc {

    void doAfterStatCalc(StatData data, EntityCap.UnitData unit, PlayerSpellCap.ISpellsCap spells);

}

