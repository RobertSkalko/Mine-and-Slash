package com.robertx22.mine_and_slash.saveclasses.gearitem;

import com.robertx22.mine_and_slash.database.affixes.BaseAffix;
import com.robertx22.mine_and_slash.database.requirements.bases.GearRequestedFor;
import com.robertx22.mine_and_slash.db_lists.initializers.Suffixes;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ICreateSpecific;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import info.loenwind.autosave.annotations.Storable;

import java.io.Serializable;

@Storable
public class SuffixData extends AffixData implements ICreateSpecific<BaseAffix>, Serializable {

    public SuffixData() {

    }

    public SuffixData(GearItemData gear, String affixname, Integer percent) {
        super();
        this.baseAffix = affixname;
        this.percent = percent;
    }

    @Override
    public void create(GearItemData gear, BaseAffix suffix) {
        baseAffix = suffix.GUID();
        RerollNumbers(gear);
    }

    @Override
    public void RerollFully(GearItemData gear) {

        BaseAffix suffix = Suffixes.INSTANCE.random(new GearRequestedFor(gear));

        this.create(gear, suffix);

    }

}
