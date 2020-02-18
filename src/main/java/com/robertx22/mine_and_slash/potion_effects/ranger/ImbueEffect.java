package com.robertx22.mine_and_slash.potion_effects.ranger;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class ImbueEffect extends BasePotionEffect {

    private ImbueEffect() {
        super(EffectType.BENEFICIAL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));
    }

    public static ImbueEffect getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "imbue";
    }

    @Override
    public String locNameForLangFile() {
        return "Imbue";
    }

    @Override
    public int getMaxStacks() {
        return 5;
    }

    @Override
    public int getDurationInSeconds() {
        return 25;
    }

    @Override
    public List<ITextComponent> getEffectTooltip(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        list.add(new SText(TextFormatting.GREEN + "This buff provides extra effects to other Ranger spells."));

        return list;

    }

    private static class SingletonHolder {
        private static final ImbueEffect INSTANCE = new ImbueEffect();
    }
}

