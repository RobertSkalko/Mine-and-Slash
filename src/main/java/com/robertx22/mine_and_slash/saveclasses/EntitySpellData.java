package com.robertx22.mine_and_slash.saveclasses;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.LivingEntity;

import java.util.UUID;

@Storable
public class EntitySpellData {

    @Store
    private String casterUUIDString;

    @Store
    public int lifeInTicks = 100;

    @Store
    public Elements element = Elements.Physical;

    @Store
    public String spellGUID = "";

    public EntitySpellData(BaseSpell spell, LivingEntity caster, int lifeInTicks) {
        this.element = spell.getElement();
        this.spellGUID = spell.GUID();
        this.casterUUIDString = caster.getUniqueID().toString();
        this.lifeInTicks = lifeInTicks;
    }

    public UUID getCasterUUID() {
        return UUID.fromString(casterUUIDString);
    }

    public EntitySpellData() {
    }

    public BaseSpell getSpell() {
        return SlashRegistry.Spells().get(spellGUID);
    }

    public BaseSpell.SpellType getSpellType() {
        return getSpell().getSpellType();
    }

}
