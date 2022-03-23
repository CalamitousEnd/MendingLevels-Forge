package yes.mediumdifficulty.mendinglevels.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;

@Mixin(ExperienceOrbEntity.class)
public class ExperienceOrbEntityMixin {
    private int getMendingRepairRatio(int mendingLevel) {
        float finalCalculation = (2 + (mendingLevel - 1) / 2f);
        return (int) finalCalculation;
    }

    private int getMendingRepairCost(int repairAmount, int mendingLevel) {
        float finalCalculation = Math.max(2f * repairAmount / (mendingLevel + 3), 1f);
        return (int) finalCalculation;
    }

    private int tempMendingLevel = 0;

    @Inject(
            method = "onCollideWithPlayer", at = @At(value = "INVOKE_ASSIGN", target = "Ljava/util/Map$Entry;getValue()Ljava/lang/Object;"),
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    private void repairPlayerItemsCapture(PlayerEntity p_70100_1_, CallbackInfo ci, Map.Entry entry) {
        tempMendingLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.MENDING, (ItemStack) entry.getValue());
    }

    @Redirect(
            method = "onCollideWithPlayer",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getXpRepairRatio()F")
    )
    private float repairPlayerItemsGetMendingRepairRatioInjection(ItemStack stack) {
        return getMendingRepairRatio(tempMendingLevel);
    }

    @Redirect(
            method = "onCollideWithPlayer",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/item/ExperienceOrbEntity;durabilityToXp(I)I")
    )
    private int repairPlayerItemsGetMendingRepairCostInjection(ExperienceOrbEntity instance, int repairAmount) {
        return getMendingRepairCost(repairAmount, tempMendingLevel);
    }
}

