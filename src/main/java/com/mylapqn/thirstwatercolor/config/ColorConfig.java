package com.mylapqn.thirstwatercolor.config;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.regex.Pattern;

public class ColorConfig {

    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.ConfigValue<String> PURITY_0_COLOR;
    public static final ModConfigSpec.ConfigValue<String> PURITY_1_COLOR;
    public static final ModConfigSpec.ConfigValue<String> PURITY_2_COLOR;
    public static final ModConfigSpec.ConfigValue<String> DEFAULT_WATER_COLOR;

    private static final Pattern HEX_PATTERN = Pattern.compile("^0x[0-9a-fA-F]{8}$");

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        builder.comment("Water Purity Color Configurations (32-bit ARGB Hex)").push("colors");

        PURITY_0_COLOR = builder
                .comment("32-bit ARGB Hex color for Purity 0 (Dirty Water). Default: 0xFF957453")
                .define("purity_0", "0xFF957453", ColorConfig::validateHex);

        PURITY_1_COLOR = builder
                .comment("32-bit ARGB Hex color for Purity 1 (Slightly Dirty). Default: 0xFF63787B")
                .define("purity_1", "0xFF63787B", ColorConfig::validateHex);

        PURITY_2_COLOR = builder
                .comment("32-bit ARGB Hex color for Purity 2 (Acceptable / Murky). Default: 0xFF4E7DA3")
                .define("purity_2", "0xFF4E7DA3", ColorConfig::validateHex);

        DEFAULT_WATER_COLOR = builder
                .comment("32-bit ARGB Hex color for default pure water. Default: 0xFF3C6EFF")
                .define("default_water", "0xFF3C6EFF", ColorConfig::validateHex);

        builder.pop();
        SPEC = builder.build();
    }

    private static boolean validateHex(Object obj) {
        if (obj instanceof String str) {
            return HEX_PATTERN.matcher(str).matches();
        }
        return false;
    }
}