package com.robertx22.mine_and_slash.api;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.map_affixes.BaseMapAffix;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.status_effects.bases.BaseStatusEffect;
import com.robertx22.mine_and_slash.registry.SlashRegistry;

//Some methods have been removed as i resigned the system to work with datapacks.
//Please look at how i generate them if you don't know how to do it. All those classes have built in toJson()
//so you can easily turn them into json files.
public class MineAndSlashAPI {

    public static void addMapAffix(BaseMapAffix affix) {
        SlashRegistry.MapAffixes()
            .register(affix);
    }

    public static void addSpell(BaseSpell spell) {
        SlashRegistry.Spells()
            .register(spell);

    }

    public static void addMobEffect(BaseStatusEffect effect) {
        SlashRegistry.StatusEffects()
            .register(effect);
    }

    public static void addGearItemType(GearItemSlot type) {
        SlashRegistry.GearTypes()
            .register(type);
    }

}
