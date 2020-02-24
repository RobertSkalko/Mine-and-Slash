package com.robertx22.mine_and_slash.database.world_providers;

import com.robertx22.mine_and_slash.db_lists.bases.IBonusLootMulti;
import com.robertx22.mine_and_slash.saveclasses.mapitem.MapAffixData;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ModDimension;

import java.util.List;

public interface IWP extends IWeighted, IAutoLocName, IBonusLootMulti {
    abstract String GUID();

    ModDimension getModDim();

    ResourceLocation getResourceLoc();

    ModDimension newModDimension();

    List<MapAffixData> getMapAffixes(); // missing thunder damage maps.. hmm

    void setModDimension(ModDimension mod);

    @Override
    public default AutoLocGroup locNameGroup() {
        return AutoLocGroup.World_Types;
    }

}
