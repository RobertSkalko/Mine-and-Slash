package com.robertx22.mine_and_slash.database.talent_tree;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IApplyableStats;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PerkEffect extends BasePerkEffect implements IApplyableStats, ISlashRegistryEntry<PerkEffect> {

    public List<ExactStatData> exactStats;

    public PerkEffect setGameChanger() {
        this.isGameChanger = true;
        return this;
    }

    public PerkEffect(String guid, List<ExactStatData> exactStats, String render) {
        this.exactStats = exactStats;
        this.guid = guid;

        //setupTexture(render); ENABLE LATER WHEN I MAKE ALL COMBO ICONS

        this.registerToSlashRegistry();
    }

    public PerkEffect(String guid, ExactStatData exactStat, Stat stat) {
        this.exactStats = Arrays.asList(exactStat);
        this.guid = guid;
        if (stat != null) {
            setupTexture(stat);
        }
        this.registerToSlashRegistry();
    }

    public PerkEffect(String guid, ExactStatData exactStat, String render) {
        this.exactStats = Arrays.asList(exactStat);
        this.guid = guid;
        if (render != null) {
            setupTexture(render);
        }

        this.registerToSlashRegistry();
    }

    public boolean isGameChanger() {
        return isGameChanger;
    }

    private void setupTexture(Stat stat) {
        this.TEXTURE = stat.getIconLocation();
        this.hasTexture = true;

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

        int before = list.size();
        this.exactStats.forEach(x -> list.addAll(x.GetTooltipString(info)));
        int after = list.size();

        if (after > before) {
            if (!Screen.hasAltDown()) {
                list.add(new StringTextComponent(TextFormatting.BLUE + "[").appendSibling(
                        Words.PressAltForStatInfo.locName()).appendText("]"));
            }
        }

        return list;
    }

    @Override
    public void applyStats(EntityCap.UnitData data) {
        exactStats.forEach(x -> x.applyStats(data));
    }

    public void render(int x, int y) {

        if (hasTexture) {
            RenderUtils.renderIcon(this.TEXTURE, x, y);
        } else {

            if (this.exactStats.size() > 1) {
                RenderUtils.renderIcons(exactStats, x, y);
            }

        }

    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.PERK_EFFECT;
    }

    @Override
    public String GUID() {
        return this.guid;
    }

}
