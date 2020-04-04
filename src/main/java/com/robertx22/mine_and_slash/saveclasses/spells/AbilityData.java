package com.robertx22.mine_and_slash.saveclasses.spells;

import com.robertx22.mine_and_slash.registry.SlashRegistry;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public class AbilityData {

    @Store
    public int currentLvl = 0;

    @Store
    public String id = "";

    @Store
    public IAbility.Type type = IAbility.Type.SPELL;

    public AbilityData(String id, IAbility.Type type) {
        this.id = id;
        this.type = type;
    }

    public AbilityData(IAbility ability) {
        this.id = ability.GUID();
        this.type = ability.getAbilityType();
    }

    public AbilityData() {

    }

    public boolean isValid() {

        if (id == null || id.isEmpty()) {
            return false;
        }

        if (type == IAbility.Type.SPELL) {
            return SlashRegistry.Spells()
                .isRegistered(id);
        } else {
            return SlashRegistry.Synergies()
                .isRegistered(id);
        }
    }

    public IAbility getAbility() {

        if (type == IAbility.Type.SPELL) {
            return SlashRegistry.Spells()
                .get(id);
        } else {
            return SlashRegistry.Synergies()
                .get(id);
        }

    }

}
