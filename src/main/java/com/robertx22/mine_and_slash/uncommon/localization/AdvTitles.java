package com.robertx22.mine_and_slash.uncommon.localization;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;

public enum AdvTitles implements IAutoLocName {
    FirstAdventureMap("First Adventure Map!"),
    WelcomeMineandslash("Welcome to Mine and Slash!"),
    RepairStation("Repair Station"),
    SalvageStation("Salvage Station"),
    ModifyStation("Modify Station"),
    FactoryStation("Factory Station"),
    CurrencyBag("Currency Bag"),
    MapBag("Map Bag"),
    LootBag("Loot Bag"),
    MapDevice("Map Device"),
    Level("Level"),
    AddAffix("Add Affix"),
    LevelPenalty("Level Penalty"),
    MasterBag("Master Bag");

    private String localization = "";

    AdvTitles(String str) {
        this.localization = str;

    }

    @Override
    public IAutoLocName.AutoLocGroup locNameGroup() {
        return AutoLocGroup.Advancement_titles;
    }

    @Override
    public String locNameLangFileGUID() {
        return Ref.MODID + ".advancement.title." + formattedGUID();
    }

    @Override
    public String locNameForLangFile() {
        return localization;
    }

    @Override
    public String GUID() {
        return this.name().toLowerCase();
    }
}
