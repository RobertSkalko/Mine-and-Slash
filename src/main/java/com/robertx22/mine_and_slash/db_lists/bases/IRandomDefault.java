package com.robertx22.mine_and_slash.db_lists.bases;

import com.robertx22.mine_and_slash.database.requirements.bases.GearRequestedFor;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;

import java.util.List;
import java.util.stream.Collectors;

public interface IRandomDefault<T extends IhasRequirements & IWeighted> extends IRandom<T, GearRequestedFor> {

    public default T random(GearRequestedFor gearRequestedFor) {

        List<T> allThatMeetReq = allThatMeetRequirement(All(), gearRequestedFor);

        if (allThatMeetReq.size() == 0) {
            return null;
        }

        return RandomUtils.weightedRandom(allThatMeetReq);
    }

    public default T random() {
        return RandomUtils.weightedRandom(All());
    }

    public default List<T> allThatMeetRequirement(List<T> list, GearRequestedFor gearRequestedFor) {
        return list.stream()
                .filter(x -> x.requirements().satisfiesAllRequirements(gearRequestedFor))
                .collect(Collectors.toList());
    }

}
