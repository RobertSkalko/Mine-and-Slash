package com.robertx22.mine_and_slash.error_checks.base;

import com.robertx22.mine_and_slash.error_checks.AllGearsHavePossibleAffixCheck;
import com.robertx22.mine_and_slash.error_checks.IGuidFormatCheck;
import com.robertx22.mine_and_slash.error_checks.TierReqIsLessThanMaxTier;

import java.util.ArrayList;
import java.util.List;

public class ErrorChecks {

    public static List<IErrorCheck> getAll() {

        List<IErrorCheck> list = new ArrayList<>();

        list.add(new IGuidFormatCheck());
        list.add(new AllGearsHavePossibleAffixCheck());
        list.add(new TierReqIsLessThanMaxTier());

        return list;

    }

}
