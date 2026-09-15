package com.mylapqn.thirstwatercolor.client;

import cn.mlus.thirst.Thirst;
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

public class ClientModEvents {

    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        // 1. Tint Water Potions
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

        // 2. Tint Water Buckets
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

        // 3. Tint Terracotta Water Bowl
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

        // 3. Tint Terracotta Water Bowl
        event.register((stack, tintIndex) -> {
            if (tintIndex == 1) { // Layer 1: Water liquid inside bowl
                Integer purity = getPurity(stack);
                if (purity != null && purity < 3) {
                    return PurityColors.getRGB(purity);
                }
                return PurityColors.DEFAULT_WATER_COLOR;
            }
            return -1; // Layer 0 (Terracotta bowl structure) remains unchanged
        }, BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("thirst", "wooden_water_bowl")));
    }

    private static Integer getPurity(ItemStack stack) {
        return stack.get(ThirstComponent.PURITY);
    }
}