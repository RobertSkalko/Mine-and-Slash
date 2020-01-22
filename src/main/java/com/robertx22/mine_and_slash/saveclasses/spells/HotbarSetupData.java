package com.robertx22.mine_and_slash.saveclasses.spells;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public class HotbarSetupData {

    @Store
    public int number = -1;

    @Store
    public PlayerSpellsData.Hotbar hotbar = PlayerSpellsData.Hotbar.FIRST;

    public boolean isPicking() {
        return number > -1;
    }

    public void setPicking(int num, PlayerSpellsData.Hotbar bar) {
        this.number = num;
        this.hotbar = bar;
    }

    public void cancel() {
        this.number = -1;
    }

}
