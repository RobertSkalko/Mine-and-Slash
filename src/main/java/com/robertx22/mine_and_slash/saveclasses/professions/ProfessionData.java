package com.robertx22.mine_and_slash.saveclasses.professions;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public class ProfessionData {

    @Store
    public int level = 1;
    @Store
    public int exp = 0;

}
