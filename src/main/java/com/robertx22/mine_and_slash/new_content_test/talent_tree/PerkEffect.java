package com.robertx22.mine_and_slash.new_content_test.talent_tree;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IApplyableStats;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PerkEffect implements ITooltipList, IApplyableStats {

    public PerkType type = PerkType.SMALL;
    public List<ExactStatData> exactStats;
    private Words word;

    boolean hasTexture = false;

    private ResourceLocation TEXTURE;

    public PerkEffect(List<ExactStatData> exactStats, String render) {
        this.exactStats = exactStats;
        setupTexture(render);
    }

    public PerkEffect(ExactStatData exactStat, String render) {
        this.exactStats = Arrays.asList(exactStat);
        setupTexture(render);

    }

    private void setupTexture(String id) {
        this.TEXTURE = new ResourceLocation(Ref.MODID, "textures/gui/talents/icons/" + id + ".png");
        this.hasTexture = !id.isEmpty();
    }

    public PerkEffect type(PerkType type) {
        this.type = type;
        return this;
    }

    public ITextComponent getName() {
        return null;
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();
        this.exactStats.stream().forEach(x -> list.addAll(x.GetTooltipString(info)));
        return list;
    }

    @Override
    public void applyStats(EntityCap.UnitData data) {
        exactStats.forEach(x -> x.applyStats(data));
    }

    public void render(int x, int y) {
        if (hasTexture) {
            RenderUtils.renderPerkIcon(this.TEXTURE, x, y);
        }
    }

}
