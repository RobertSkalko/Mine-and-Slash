package com.robertx22.mine_and_slash.saveclasses;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.Utilities;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

import java.util.UUID;

@Storable
public class EntitySpellData {

    @Store
    public float charge = 1;

    @Store
    private String casterID;

    private LivingEntity caster;

    @Store
    public int lifeInTicks = 100;

    @Store
    public Elements ele = Elements.Physical;

    @Store
    private String spellGUID = "";

    private BaseSpell spell;

    public EntitySpellData(BaseSpell spell, LivingEntity caster, int lifeInTicks) {
        if (spell != null) {
            this.ele = spell.getElement();
            this.spellGUID = spell.GUID();
        }
        this.casterID = caster.getUniqueID().toString();
        this.lifeInTicks = lifeInTicks;
    }

    private UUID getCasterUUID() {
        return UUID.fromString(casterID);
    }

    public EntitySpellData() {
    }

    public BaseSpell getSpell() {
        if (spell == null) {
            spell = SlashRegistry.Spells().get(spellGUID);
        }
        return spell;
    }

    public BaseSpell.SpellType getSpellType() {
        return getSpell().getSpellType();
    }

    public LivingEntity getCaster(World world) {

        if (caster == null) {
            caster = Utilities.getLivingEntityByUUID(world, getCasterUUID());
        }
        return caster;

    }
}
