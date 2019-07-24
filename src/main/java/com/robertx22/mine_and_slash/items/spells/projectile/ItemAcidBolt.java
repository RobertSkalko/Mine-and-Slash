package com.robertx22.mine_and_slash.items.spells.projectile;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.spells.bases.BaseSpell;
import com.robertx22.mine_and_slash.spells.projectile.SpellAcidBolt;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

public class ItemAcidBolt extends BaseBoltItem {

    public ItemAcidBolt() {
        super();
    }

    @ObjectHolder(Ref.MODID + ":spell_acidbolt")
    public static final Item ITEM = null;

    @Override
    public BaseSpell Spell() {
        return new SpellAcidBolt();
    }

    @Override
    public String GUID() {
        return Ref.MODID + ":spell_acidbolt";
    }

}
