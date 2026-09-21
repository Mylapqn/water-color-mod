package com.mylapqn.thirstwatercolor.mixin;

import cn.mlus.thirst.content.registry.ThirstComponent;
import com.mylapqn.thirstwatercolor.util.PurityColors;
import net.neoforged.neoforge.fluids.FluidStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin to inject custom tint colors into fluid type client extensions based on water purity.
 */
@Mixin(targets = "net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions")
public interface MixinWaterClientExtensions {

    /**
     * Modifies the fluid tint color returned by client fluid type extensions according to fluid stack purity.
     *
     * @param stack the fluid stack being rendered
     * @param cir   callback info returnable containing the tint color integer
     */
    @Inject(
            method = "getTintColor(Lnet/neoforged/neoforge/fluids/FluidStack;)I",
            at = @At("RETURN"),
            cancellable = true,
            remap = false
    )
    private static void modifyPurityTintColor(FluidStack stack, CallbackInfoReturnable<Integer> cir) {
        if (stack == null || stack.isEmpty()) {
            return;
        }

        Integer purity = stack.get(ThirstComponent.PURITY);
        if (purity == null || purity >= 3) {
            return;
        }
        int color = PurityColors.getRGB(purity);
        cir.setReturnValue(color != -1 ? color : cir.getReturnValue());
    }
}