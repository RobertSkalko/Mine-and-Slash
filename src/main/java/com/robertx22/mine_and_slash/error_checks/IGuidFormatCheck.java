package com.robertx22.mine_and_slash.error_checks;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.error_checks.base.IErrorCheck;

public class IGuidFormatCheck implements IErrorCheck {

    @Override
    public void check() {
        String formatted = IGUID.getformattedString("DamageFlat");
        String finalstr = "damage_flat";

        if (!formatted.equals(finalstr)) {
            throw new RuntimeException("GUID formatter is incorrect");
        }
        formatted = IGUID.getformattedString("DAMageFLAt");
        finalstr = "damage_flat";

        if (!formatted.equals(finalstr)) {
            throw new RuntimeException("GUID formatter is incorrect");
        }
    }
}
