package com.robertx22.mine_and_slash.saveclasses.professions;

import com.robertx22.mine_and_slash.new_content_test.professions.data.Professions;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

import java.util.HashMap;

@Storable
public class ProfessionListData {

    @Store
    public HashMap<Professions, ProfessionData> professions = new HashMap<>();

    public ProfessionData getProfessionData(Professions prof) {

        if (professions.containsKey(prof) == false) {
            this.professions.put(prof, new ProfessionData());
        }

        return this.professions.get(prof);

    }

}
