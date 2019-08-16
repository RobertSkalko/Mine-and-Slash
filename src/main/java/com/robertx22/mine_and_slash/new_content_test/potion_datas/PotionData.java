package com.robertx22.mine_and_slash.new_content_test.potion_datas;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public class PotionData {

    @Store
    public String potionGUID = "";

    @Store
    public float usesRemaining = 1;

    public BaseDataPotion getDataPotion() {
        return null;
    }

}
