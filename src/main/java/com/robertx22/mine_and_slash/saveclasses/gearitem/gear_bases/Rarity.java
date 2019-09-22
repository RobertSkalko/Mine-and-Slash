package com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.base.DropSoundData;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.interfaces.IColor;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import net.minecraft.util.text.TextFormatting;

public interface Rarity extends IWeighted, IAutoLocName, IColor {

    int colorInt();

    MinMax SpawnDurabilityHit();

    String GUID();

    int Rank();

    String Color();

    int Weight();

    default DropSoundData getDropSound() {
        return new DropSoundData();
    }

    TextFormatting textFormatColor();

    @Override
    public default String locNameLangFileGUID() {
        return Ref.MODID + "s.rarity." + formattedGUID();
    }

    @Override
    public default AutoLocGroup locNameGroup() {
        return AutoLocGroup.Rarities;
    }

}
