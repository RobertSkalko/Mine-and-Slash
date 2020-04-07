package com.robertx22.mine_and_slash.saveclasses.spells;

import com.robertx22.mine_and_slash.registry.SlashRegistry;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.math.MathHelper;

@Storable
public class AbilityData {

    @Store
    private int currentLvl = 0;

    @Store
    public String id = "";

    @Store
    public IAbility.Type type = IAbility.Type.SPELL;

    public AbilityData(String id, IAbility.Type type) {
        this.id = id;
        this.type = type;
    }

    public void setLevel(int i) {
        this.currentLvl = i;
    }

    public void addLevels(int i) {
        currentLvl = MathHelper.clamp(currentLvl + i, 0, getAbility().getMaxSpellLevelNormal());
    }

    public int getCurrentLevel() {
        currentLvl = MathHelper.clamp(currentLvl, 0, getAbility().getMaxSpellLevelNormal());

        return currentLvl;

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

        if (currentLvl < 1) {
            return false;
        }

        if (type == IAbility.Type.SPELL) {
            return SlashRegistry.Spells()
                .isRegistered(id);
        } else if (type == IAbility.Type.EFFECT) {
            return SlashRegistry.PotionEffects()
                .isRegistered(id);
        } else if (type == IAbility.Type.SYNERGY) {
            return SlashRegistry.Synergies()
                .isRegistered(id);
        }
        return false;

    }

    public IAbility getAbility() {

        if (type == IAbility.Type.SPELL) {
            return SlashRegistry.Spells()
                .get(id);
        } else if (type == IAbility.Type.SYNERGY) {
            return SlashRegistry.Synergies()
                .get(id);
        } else if (type == IAbility.Type.EFFECT) {
            return SlashRegistry.PotionEffects()
                .get(id);
        }
        return null;
    }

}
