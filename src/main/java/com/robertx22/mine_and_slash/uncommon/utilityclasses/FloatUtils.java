package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import java.text.DecimalFormat;

public class FloatUtils {

    private static DecimalFormat decimalFormat = new DecimalFormat();

    static {
        decimalFormat.setMaximumFractionDigits(1);
    }

    public static String format(float val) {
        return decimalFormat.format(val);
    }

}
