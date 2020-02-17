package com.robertx22.mine_and_slash.database.spells.spell_tree;

import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.talent_tree.BasePerkEffect;
import com.robertx22.mine_and_slash.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

public class SynergyPerkEffect extends BasePerkEffect implements ISlashRegistryEntry<SynergyPerkEffect> {

    public Synergy synergy;

    public SynergyPerkEffect(Synergy synergy) {
        this.synergy = synergy;

        this.TEXTURE = synergy.getIconLoc();
        this.hasTexture = true;

        this.registerToSlashRegistry();
    }

    public SynergyPerkEffect setGameChanger() {
        this.isGameChanger = true;
        return this;
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        if (synergy != null) {
            list.addAll(synergy.GetTooltipString(info));
        }

        return list;
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.SYNERGY_EFFECT;
    }

    @Override
    public String GUID() {
        return synergy.GUID();
    }

}
