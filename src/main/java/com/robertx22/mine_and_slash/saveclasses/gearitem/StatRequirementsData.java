package com.robertx22.mine_and_slash.saveclasses.gearitem;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
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
    
    private HashMap<String, Integer> getReqs(GearItemData data) {
        HashMap<String, Integer> map = new HashMap<>();

        float requirementMulti = data.getRarity().requirementMulti();

        for (String str : stats) {
            if (SlashRegistry.Stats().isRegistered(str)) {
                map.put(str, (int) (this.getAmount(data) / stats.size() * requirementMulti));
            }
        }

        return map;
    }

    public void create(GearItemData data) {

        this.stats = new ArrayList<>();

        if (data.isUnique()) {
            unique(data);
        } else {
            List<Stat> possibleReq = data.GetBaseGearType().statRequirements();

            if (possibleReq != null) {
                if (possibleReq.size() > 0) {
                    if (possibleReq.size() > 1) {
                        if (RandomUtils.roll(50)) {
                            doubleStat(data);

                        } else {
                            singleStat(data);
                        }
                    } else {
                        singleStat(data);
                    }
                }
            }

        }
    }

    public int getAmount(GearItemData data) {
        return MathHelper.clamp(data.getLevel() - (data.getLevel() / 4) - 10, 0, 100000);
    }

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

    public boolean meetsRequirements(UnitData data, GearItemData gear) {

        for (Map.Entry<String, Integer> entry : getReqs(gear).entrySet()) {
            if (SlashRegistry.Stats().isRegistered(entry.getKey())) {
                if (data.getUnit().getStat(entry.getKey()).Value < entry.getValue()) {
                    return false;
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
