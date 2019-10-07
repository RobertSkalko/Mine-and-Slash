package com.robertx22.mine_and_slash.new_content_test.talent_tree;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IApplyableStats;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PerkEffect implements ITooltipList, IApplyableStats, ISlashRegistryEntry<PerkEffect> {

    public PerkType type = PerkType.SMALL;
    public List<ExactStatData> exactStats;
    private Words word;

    boolean hasTexture = false;
    private String guid;

    public boolean isGameChanger = false;
    private ResourceLocation TEXTURE;

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
        this.exactStats.forEach(x -> list.addAll(x.GetTooltipString(info)));
        return list;
    }

    @Override
    public void applyStats(EntityCap.UnitData data) {
        exactStats.forEach(x -> x.applyStats(data));
    }

    public void render(int x, int y) {

        if (hasTexture) {
            RenderUtils.renderPerkIcon(this.TEXTURE, x, y);
        } else {

            if (this.exactStats.size() > 1) {
                RenderUtils.renderMoreThanOnePerk(exactStats, x, y);
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

    @Override
    public int Weight() {
        return 1000;
    }

    @Override
    public int getRarityRank() {
        return 0;
    }

    @Override
    public Rarity getRarity() {
        return Rarities.Items.get(getRarityRank());
    }

    @Override
    public int Tier() {
        return 0;
    }
}
