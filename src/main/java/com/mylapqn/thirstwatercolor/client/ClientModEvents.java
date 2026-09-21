package com.mylapqn.thirstwatercolor.client;

import cn.mlus.thirst.content.registry.ThirstComponent;
import com.mylapqn.thirstwatercolor.util.PurityColors;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

/**
 * Handles client-side event registration for item color tinting based on water purity.
 */
public class ClientModEvents {

    /**
     * Registers item color handlers for water-containing items (potions, buckets, bowls).
     *
     * @param event the item color handlers registration event
     */
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        // Tint water potions based on purity level
        event.register((stack, tintIndex) -> {
            if (tintIndex == 0) { // Layer 0: Liquid inside bottle
                PotionContents contents = stack.get(DataComponents.POTION_CONTENTS);
                if (contents != null && contents.is(Potions.WATER)) {
                    Integer purity = getPurity(stack);
                    if (purity != null && purity < 3) {
                        return PurityColors.getRGB(purity);
                    }
                }
                return contents != null ? contents.getColor() : -1;
            }
            return -1;
        }, Items.POTION);

        // Tint water buckets liquid overlay
        event.register((stack, tintIndex) -> {
            if (tintIndex == 1) { // Layer 1: Water liquid overlay ONLY
                Integer purity = getPurity(stack);
                if (purity != null && purity < 3) {
                    return PurityColors.getRGB(purity);
                }
                return PurityColors.DEFAULT_WATER_COLOR;
            }
            return -1; // Layer 0 (Bucket body) remains unchanged
        }, Items.WATER_BUCKET);

        // Tint terracotta water bowl liquid
        event.register((stack, tintIndex) -> {
            if (tintIndex == 1) { // Layer 1: Water liquid inside bowl
                Integer purity = getPurity(stack);
                if (purity != null && purity < 3) {
                    return PurityColors.getRGB(purity);
                }
                return PurityColors.DEFAULT_WATER_COLOR;
            }
            return -1; // Layer 0 (Terracotta bowl structure) remains unchanged
        }, BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("thirst", "terracotta_water_bowl")));

        // Tint wooden water bowl liquid
        event.register((stack, tintIndex) -> {
            if (tintIndex == 1) { // Layer 1: Water liquid inside bowl
                Integer purity = getPurity(stack);
                if (purity != null && purity < 3) {
                    return PurityColors.getRGB(purity);
                }
                return PurityColors.DEFAULT_WATER_COLOR;
            }
            return -1; // Layer 0 (Wooden bowl structure) remains unchanged
        }, BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("thirst", "wooden_water_bowl")));
    }

    private static Integer getPurity(ItemStack stack) {
        return stack.get(ThirstComponent.PURITY);
    }
}