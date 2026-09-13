package com.mylapqn.thirstwatercolor.util;

import com.mylapqn.thirstwatercolor.config.ColorConfig;

public final class PurityColors {

    public static final int DEFAULT_WATER_COLOR = 0xFF3C6EFF;

    private PurityColors() {}

    /**
     * Returns the 32-bit ARGB color for item tinting.
     */
    public static int getRGB(int purity) {
        String hexStr = switch (purity) {
            case 0 -> ColorConfig.PURITY_0_COLOR.get();
            case 1 -> ColorConfig.PURITY_1_COLOR.get();
            case 2 -> ColorConfig.PURITY_2_COLOR.get();
            default -> ColorConfig.DEFAULT_WATER_COLOR.get();
        };

        return parseHex(hexStr, DEFAULT_WATER_COLOR);
    }

    /**
     * Returns the 32-bit ARGB color for fluid tinting.
     */
    public static int getARGB(int purity, int defaultColor) {
        int color = getRGB(purity);
        return color == -1 ? defaultColor : color;
    }

    private static int parseHex(String hex, int fallback) {
        try {
            if (hex.startsWith("0x") || hex.startsWith("0X")) {
                hex = hex.substring(2);
            }
            return (int) Long.parseLong(hex, 16);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }
}