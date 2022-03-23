package yes.mediumdifficulty.mendinglevels.mixin;


import net.minecraft.world.item.enchantment.MendingEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yes.mediumdifficulty.mendinglevels.config.Common;

@Mixin(MendingEnchantment.class)
public class MendingEnchantmentMixin {
    @Inject(method = "getMaxLevel", at = @At("RETURN"), cancellable = true)
    private void getMaxLevelInjection(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(Common.MAX_LEVEL.get());
    }
}
