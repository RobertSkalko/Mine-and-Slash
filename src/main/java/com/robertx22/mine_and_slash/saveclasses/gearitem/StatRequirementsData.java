package com.robertx22.mine_and_slash.saveclasses.gearitem;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.items.unique_items.ISpecificStatReq;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Storable
public class StatRequirementsData {

    @Store
    private List<String> stats = new ArrayList<>();

    public List<String> getStatRequirements() {
        return stats;
    }

    private HashMap<String, Integer> getReqs(GearItemData data) {
        if (data.isUnique()) {
            try {
                return data.uniqueStats.getUniqueItem()
                        .getRequirements()
                        .getRequirements(data.level, data.getRarity());
            } catch (Exception e) {
                e.printStackTrace();
                return new HashMap<>();
            }

        } else {

            if (data.GetBaseGearType() instanceof ISpecificStatReq) {

                ISpecificStatReq specific = (ISpecificStatReq) data.GetBaseGearType();
                return specific.getRequirements()
                        .getRequirements(data.getLevel(), data.getRarity());
            } else {
                HashMap<String, Integer> map = new HashMap<>();
                float requirementMulti = data.getRarity().requirementMulti();

                for (String str : stats) {
                    if (SlashRegistry.Stats().isRegistered(str)) {
                        map.put(str, (int) (this.getAmount(data) / stats.size() * requirementMulti));
                    }
                }
                return map;
            }
        }

    }

    public void create(GearItemData data) {

        this.stats = new ArrayList<>();

        /*
        if (data.isUnique()) {
            unique(data);
        } else {
            singleStat(data);
        }

         */

    }

    public static int getAmount(GearItemData data) {
        return getAmount(data.getLevel());
    }

    public static int getAmount(int lvl) {
        int req = (int) ((lvl - ((float) lvl / 5F) - 5) * ModConfig.INSTANCE.Server.STAT_REQUIREMENTS_MULTI
                .get());
        return MathHelper.clamp(req, 0, 100000);
    }
/*
    private void singleStat(GearItemData data) {
        List<Stat> possibleReq = data.GetBaseGearType().statRequirements();
        Stat stat = RandomUtils.weightedRandom(possibleReq);
        this.stats.add(stat.GUID());

    }

    private void doubleStat(GearItemData data) {

        List<Stat> possibleReq = data.GetBaseGearType().statRequirements();
        List<Stat> picked = RandomUtils.uniqueWightedRandoms(possibleReq, 2);

        for (Stat stat : picked) {
            this.stats.add(stat.GUID());
        }
    }

    private void unique(GearItemData data) {

    }

 */

    public boolean meetsRequirements(UnitData data, GearItemData gear) {

        if (data != null && data.getUnit() != null) {

            for (Map.Entry<String, Integer> entry : getReqs(gear).entrySet()) {

                if (SlashRegistry.Stats().isRegistered(entry.getKey())) {

                    if (entry.getValue() <= 0) {
                        return true;
                    }

                    if (!data.getUnit().getStats().containsKey(entry.getKey())) {
                        return false;
                    }

                    if (data.getUnit().getStat(entry.getKey()).Value < entry.getValue()) {
                        return false;
                    }

                }
            }
        }

        return true;
    }

    public List<ITextComponent> GetTooltipString(TooltipInfo info, GearItemData gear) {

        List<ITextComponent> list = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : getReqs(gear).entrySet()) {
            if (entry.getValue() > 0) {
                if (SlashRegistry.Stats().isRegistered(entry.getKey())) {
                    Stat stat = SlashRegistry.Stats().get(entry.getKey());

                    list.add(TooltipUtils.requirement(stat.locName(), (int) info.unitdata.getUnit()
                            .getStat(stat).Value, entry.getValue()));
                }
            }
        }

        return list;
    }
}
