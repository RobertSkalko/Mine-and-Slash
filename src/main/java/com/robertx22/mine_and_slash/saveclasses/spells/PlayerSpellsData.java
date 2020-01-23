package com.robertx22.mine_and_slash.saveclasses.spells;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.player.PlayerEntity;

import javax.annotation.Nullable;
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

    public void setToCast(int key, Hotbar hotbar, PlayerEntity player, int ticks) {
        BaseSpell spell = getSpellByKeybind(key, hotbar);

        this.spellBeingCast = spell.GUID();

        this.castingTicksLeft = spell.useTimeTicks();
        this.lastSpellCastTimeInTicks = spell.useTimeTicks();

    }

    public void tryCast(PlayerEntity player) {

        if (!spellBeingCast.isEmpty()) {
            if (castingTicksLeft <= 0) {
                BaseSpell spell = SlashRegistry.Spells().get(spellBeingCast);
                spell.cast(player, spell.useTimeTicks());

                spellBeingCast = "";

                onSpellCast(spell);
            }
        }

    }

    public void setHotbar(int number, Hotbar hotbar, String spellID) {

        if (!spellID.isEmpty()) {
            if (!SlashRegistry.Spells().isRegistered(spellID)) {
                try {
                    throw new Exception("Trying to setup spell that isn't registered!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        getMap(hotbar).put(number, spellID);
    }

    public boolean canCast(int key, Hotbar hotbar, PlayerEntity player) {

        if (isCasting()) {
            return false;
        }

        BaseSpell spell = getSpellByKeybind(key, hotbar);

        if (!spell.CanCast(player)) {
            return false;
        }

        SpellData data = getDataBySpell(spell, Hotbar.FIRST);

        return data.cooldownIsReady();
    }

    private void onSpellCast(BaseSpell spell) {
        SpellData data = spellDatas.getOrDefault(spell.GUID(), new SpellData());

        data.setCooldown(spell.getCooldownInTicks());

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

    @Nullable
    public BaseSpell getSpellByKeybind(int key, Hotbar hotbar) {
        String id = getMap(hotbar).get(key);

        if (SlashRegistry.Spells().isRegistered(id)) {
            return SlashRegistry.Spells().get(id);
        } else {
            return null;
        }

    }
}
