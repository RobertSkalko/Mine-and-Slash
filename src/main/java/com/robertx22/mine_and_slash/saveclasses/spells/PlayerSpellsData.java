package com.robertx22.mine_and_slash.saveclasses.spells;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.cleric.InstantHealSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean_mystic.BlizzardSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean_mystic.FrostballSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.shaman.ThunderspearSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.shaman.ThunderstormSpell;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.item_classes.SpellItemData;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashMap;

@Storable
public class PlayerSpellsData {

    @Store
    public int castingTicksLeft = 0;

    @Store
    public int lastSpellCastTimeInTicks = 0;

    @Store
    public String spellBeingCast = "";

    public void cancelCast() {
        spellBeingCast = "";
        castingTicksLeft = 0;
        lastSpellCastTimeInTicks = 0;
    }

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

    public boolean isCasting() {
        return castingTicksLeft > 0;
    }

    public void onTimePass(int ticks) {
        spellDatas.values().forEach(x -> x.tickCooldown(ticks));

        castingTicksLeft--;
    }

    public void setToCast(int key, PlayerEntity player, int ticks) {
        BaseSpell spell = getSpellByKeybind(key);

        this.spellBeingCast = spell.GUID();

        this.castingTicksLeft = spell.useTimeTicks();
        this.lastSpellCastTimeInTicks = spell.useTimeTicks();

    }

    public void tryCast(PlayerEntity player) {

        if (!spellBeingCast.isEmpty()) {
            if (castingTicksLeft <= 0) {
                BaseSpell spell = SlashRegistry.Spells().get(spellBeingCast);
                spell.cast(player, spell.useTimeTicks(), new SpellItemData());

                spellBeingCast = "";
            }
        }

    }

    public boolean canCast(int key, PlayerEntity player) {

        if (isCasting()) {
            return false;
        }

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

        firstHotbar.put(0, new FrostballSpell().GUID());
        firstHotbar.put(1, new BlizzardSpell().GUID());
        firstHotbar.put(2, new ThunderspearSpell().GUID());
        firstHotbar.put(3, new ThunderstormSpell().GUID());
        firstHotbar.put(4, new InstantHealSpell().GUID());

        return SlashRegistry.Spells().get(firstHotbar.get(key));
    }
}
