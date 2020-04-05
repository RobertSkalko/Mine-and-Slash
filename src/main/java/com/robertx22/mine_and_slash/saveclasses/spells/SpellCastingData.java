package com.robertx22.mine_and_slash.saveclasses.spells;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Storable
public class SpellCastingData {

    @Store
    public int castingTicksLeft = 0;

    @Store
    public int castingTicksDone = 0;

    @Store
    public int lastSpellCastTimeInTicks = 0;

    @Store
    public String spellBeingCast = "";

    public void cancelCast(PlayerEntity player) {
        try {
            SpellCastContext ctx = new SpellCastContext(player, 0, getSpellBeingCast());

            BaseSpell spell = getSpellBeingCast();
            if (spell != null && spell.goesOnCooldownIfCastCanceled()) {
                SpellData data = spellDatas.getOrDefault(spell.GUID(), new SpellData());
                data.setCooldown(spell.getCooldownInTicks(ctx));
            }

            spellBeingCast = "";
            castingTicksLeft = 0;
            lastSpellCastTimeInTicks = 0;
            castingTicksDone = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void clear() {
        getMap(SpellCastingData.Hotbar.FIRST).clear();
        getMap(SpellCastingData.Hotbar.SECOND).clear();
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

    public void onTimePass(PlayerEntity player, PlayerSpellCap.ISpellsCap spells, int ticks) {

        try {
            spellDatas.values()
                .forEach(x -> x.tickCooldown(ticks));

            BaseSpell spell = SlashRegistry.Spells()
                .get(spellBeingCast);

            SpellCastContext ctx = new SpellCastContext(player, castingTicksDone, spell);

            if (spell != null && spells != null && SlashRegistry.Spells()
                .isRegistered(spell)) {
                spell.onCastingTick(ctx);
                addCastingMoveDebuff(player);
            } else {
                removeCastingMoveDebuff(player);
            }

            castingTicksLeft--;
            castingTicksDone++;

        } catch (Exception e) {

        }

    }

    public static AttributeModifier CASTING_SPEED_DEBUFF = new AttributeModifier(UUID.fromString("d6d3dc82-9787-4722-9a33-924b94490e2a"), SharedMonsterAttributes.MOVEMENT_SPEED.getName(), -0.25F, AttributeModifier.Operation.MULTIPLY_BASE);

    public void addCastingMoveDebuff(PlayerEntity player) {
        IAttributeInstance atri = player.getAttributes()
            .getAttributeInstance(SharedMonsterAttributes.MOVEMENT_SPEED);

        if (!atri.hasModifier(CASTING_SPEED_DEBUFF)) {
            atri.applyModifier(CASTING_SPEED_DEBUFF);
        }
    }

    public void removeCastingMoveDebuff(PlayerEntity player) {
        IAttributeInstance atri = player.getAttributes()
            .getAttributeInstance(SharedMonsterAttributes.MOVEMENT_SPEED);

        if (atri.hasModifier(CASTING_SPEED_DEBUFF)) {
            atri.removeModifier(CASTING_SPEED_DEBUFF);
        }
    }

    public List<String> getSpellsOnCooldown() {
        return spellDatas.entrySet()
            .stream()
            .filter(x -> !x.getValue()
                .cooldownIsReady())
            .map(x -> x.getKey())
            .collect(Collectors.toList());

    }

    public void setToCast(int key, Hotbar hotbar, PlayerEntity player, int ticks) {
        BaseSpell spell = getSpellByKeybind(key, hotbar);

        SpellCastContext ctx = new SpellCastContext(player, 0, spell);

        this.spellBeingCast = spell.GUID();

        this.castingTicksLeft = spell.useTimeTicks(ctx);
        this.lastSpellCastTimeInTicks = spell.useTimeTicks(ctx);
        this.castingTicksDone = 0;

    }

    public void setToCast(BaseSpell spell, PlayerEntity player) {
        SpellCastContext ctx = new SpellCastContext(player, 0, spell);

        this.spellBeingCast = spell.GUID();
        this.castingTicksLeft = spell.useTimeTicks(ctx);
        this.lastSpellCastTimeInTicks = spell.useTimeTicks(ctx);
        this.castingTicksDone = 0;
    }

    public void tryCast(PlayerEntity player, PlayerSpellCap.ISpellsCap spells) {

        if (!spellBeingCast.isEmpty()) {
            if (castingTicksLeft <= 0) {
                BaseSpell spell = SlashRegistry.Spells()
                    .get(spellBeingCast);

                if (spells.getAbilitiesData()
                    .getAllocatedSpells()
                    .contains(spell)) {

                    SpellCastContext ctx = new SpellCastContext(player, this.castingTicksDone, spell);

                    spell.cast(ctx);

                    player.getHeldItemMainhand()
                        .damageItem(1, player, x -> {
                            player.sendBreakAnimation(player.getActiveHand());
                        });

                    spellBeingCast = "";

                    onSpellCast(spell, player, spells);

                }
            }
        }

    }

    public void setHotbar(int number, Hotbar hotbar, String spellID) {

        if (!spellID.isEmpty()) {
            if (!SlashRegistry.Spells()
                .isRegistered(spellID)) {
                try {
                    throw new Exception("Trying to setup spell that isn't registered!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        getMap(hotbar).put(number, spellID);
    }

    public BaseSpell getSpellBeingCast() {
        return SlashRegistry.Spells()
            .get(spellBeingCast);
    }

    public boolean canCast(BaseSpell spell, PlayerEntity player) {

        if (isCasting()) {
            return false;
        }

        if (spell == null) {
            return false;
        }

        SpellData data = getDataBySpell(spell);

        if (data.cooldownIsReady() == false) {
            return false;
        }
        SpellCastContext ctx = new SpellCastContext(player, 0, spell);

        return spell.canCast(ctx);

    }

    public boolean canCast(int key, Hotbar hotbar, PlayerEntity player) {

        if (isCasting()) {
            return false;
        }

        BaseSpell spell = getSpellByKeybind(key, hotbar);

        if (spell == null) {
            return false;
        }

        SpellData data = getDataBySpell(spell);

        if (data.cooldownIsReady() == false) {
            return false;
        }
        SpellCastContext ctx = new SpellCastContext(player, 0, spell);

        return spell.canCast(ctx);

    }

    private void onSpellCast(BaseSpell spell, PlayerEntity player, PlayerSpellCap.ISpellsCap spells) {
        SpellData data = spellDatas.getOrDefault(spell.GUID(), new SpellData());

        SpellCastContext ctx = new SpellCastContext(player, 0, spell);

        if (spell.shouldActivateCooldown(player, spells)) {
            data.setCooldown(spell.getCooldownInTicks(ctx));
        }

        spellDatas.put(spell.GUID(), data);

    }

    public HashMap<Integer, String> getMap(Hotbar hotbar) {
        if (hotbar == Hotbar.FIRST) {
            return this.firstHotbar;
        } else {
            return this.secondHotbar;
        }
    }

    public SpellData getDataBySpell(BaseSpell spell) {

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

        if (SlashRegistry.Spells()
            .isRegistered(id)) {
            return SlashRegistry.Spells()
                .get(id);
        } else {
            return null;
        }

    }
}
