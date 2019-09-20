package com.robertx22.mine_and_slash.saveclasses.gearitem;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
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
public class StatRequirementsData implements ITooltipList {

    @Store
    public HashMap<String, Integer> requirements = new HashMap<>();

    public void create(GearItemData data) {

        int amount = MathHelper.clamp(data.getLevel() - 5, 0, 100000);

        if (amount > 0) {
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
    }

    public int getAmount(GearItemData data) {
        return MathHelper.clamp(data.getLevel() - (data.getLevel() / 4) - 10, 0, 100000);
    }

    private void singleStat(GearItemData data) {
        List<Stat> possibleReq = data.GetBaseGearType().statRequirements();
        Stat stat = RandomUtils.weightedRandom(possibleReq);
        this.requirements.put(stat.GUID(), getAmount(data));

    }

    private void doubleStat(GearItemData data) {
        int amount = getAmount(data);
        List<Stat> possibleReq = data.GetBaseGearType().statRequirements();
        List<Stat> stats = RandomUtils.uniqueWightedRandoms(possibleReq, 2);

        int i = 0;

        for (Stat stat : stats) {

            int r = 0;

            if (i == stats.size() - 1) {
                r = amount; // if last, take all the available
            } else {
                r = RandomUtils.RandomRange(amount / 5, (int) (amount / 1.5F));
            }

            this.requirements.put(stat.GUID(), r);
            amount -= r;

            i++;
        }
    }

    private void unique(GearItemData data) {

    }

    public boolean meetsRequirements(UnitData data) {

        for (Map.Entry<String, Integer> entry : requirements.entrySet()) {
            if (SlashRegistry.Stats().isRegistered(entry.getKey())) {
                if (data.getUnit().getStat(entry.getKey()).Value < entry.getValue()) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : requirements.entrySet()) {
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
