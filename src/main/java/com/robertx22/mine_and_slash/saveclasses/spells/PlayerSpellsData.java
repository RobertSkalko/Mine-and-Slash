package com.robertx22.mine_and_slash.saveclasses.spells;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean_mystic.BlizzardSpell;
import com.robertx22.mine_and_slash.saveclasses.item_classes.SpellItemData;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashMap;

@Storable
public class PlayerSpellsData {

    public enum Hotbar {
        FIRST,
        SECOND
    }

    @Store
    private HashMap<Integer, String> firstHotbar = new HashMap<>();

    @Store
    private HashMap<Integer, String> secondHotbar = new HashMap<>();

    @Store
    private HashMap<String, SpellData> spellDatas = new HashMap<>();

    public void onTimePassTickCooldowns(int ticks) {
        spellDatas.values().forEach(x -> x.tickCooldown(ticks));
    }

    public void cast(int key, PlayerEntity player, int ticks) {
        BaseSpell spell = getSpellByKeybind(key);

        spell.cast(player, ticks, new SpellItemData());

        onSpellCast(spell);

    }

    public boolean canCast(int key, PlayerEntity player) {

        BaseSpell spell = getSpellByKeybind(key);

        if (!spell.CanCast(player, new SpellItemData())) {
            return false;
        }

        SpellData data = getDataBySpell(spell, Hotbar.FIRST);

        return data.cooldownIsReady();
    }

    private void onSpellCast(BaseSpell spell) {
        SpellData data = spellDatas.getOrDefault(spell.GUID(), new SpellData());

        data.setCooldown(20); // TODO

        spellDatas.put(spell.GUID(), data);

    }

    public HashMap<Integer, String> getMap(Hotbar hotbar) {
        if (hotbar == Hotbar.FIRST) {
            return this.firstHotbar;
        } else {
            return this.secondHotbar;
        }
    }

    public SpellData getDataBySpell(BaseSpell spell, Hotbar hotbar) {

        String id = spell.GUID();

        if (spellDatas.containsKey(id) == false) {
            spellDatas.put(id, new SpellData());
        }

        return spellDatas.get(id);

    }

    public SpellData getDataByKeybind(int key, Hotbar hotbar) {

        String id = getMap(hotbar).get(key);

        if (spellDatas.containsKey(id) == false) {
            spellDatas.put(id, new SpellData());
        }

        return spellDatas.get(id);

    }

    public BaseSpell getSpellByKeybind(int key) {
        return new BlizzardSpell(); // todo
    }
}
