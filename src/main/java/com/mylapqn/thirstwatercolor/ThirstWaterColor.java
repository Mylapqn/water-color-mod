package com.mylapqn.thirstwatercolor;

import com.mylapqn.thirstwatercolor.client.ClientModEvents;
import com.mylapqn.thirstwatercolor.config.ColorConfig;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(ThirstWaterColor.MODID)
public class ThirstWaterColor {
    public static final String MODID = "thirstwatercolor";

    /**
     * Constructs the main mod instance and sets up event bus listeners and configuration registration.
     */
    public ThirstWaterColor(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.CLIENT, ColorConfig.SPEC);

        if (FMLEnvironment.dist.isClient()) {
            modEventBus.addListener(ClientModEvents::registerItemColors);
        }
    }
}