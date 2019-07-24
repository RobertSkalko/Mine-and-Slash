package com.robertx22.mine_and_slash.items.consumables;

import com.robertx22.mine_and_slash.items.consumables.bases.BaseGiveBuffItem;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ConsumableRegister;
import com.robertx22.mine_and_slash.potion_effects.SpellPotionBase;
import com.robertx22.mine_and_slash.potion_effects.all.ManaRegenPotion;

public class GiveManaRegenBuffItem extends BaseGiveBuffItem {

    @Override
    public SpellPotionBase potion() {
        return ManaRegenPotion.INSTANCE;
    }

    @Override
    public int seconds() {
        return 20;
    }

    @Override
    public String GUID() {
        return ConsumableRegister.GIVE_MANA_REGEN_BUFF_ID;
    }

}
