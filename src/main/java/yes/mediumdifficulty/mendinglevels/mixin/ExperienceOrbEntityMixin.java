package yes.mediumdifficulty.mendinglevels.mixin;

import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;

@Mixin(ExperienceOrb.class)
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
            method = "repairPlayerItems", at = @At(value = "INVOKE_ASSIGN", target = "Ljava/util/Map$Entry;getValue()Ljava/lang/Object;"),
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    private void repairPlayerItemsCapture(Player player, int amount, CallbackInfoReturnable<Integer> cir, Map.Entry entry) {
        tempMendingLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.MENDING, (ItemStack) entry.getValue());
    }

    @Redirect(
            method = "repairPlayerItems",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getXpRepairRatio()F")
    )
    private float repairPlayerItemsGetMendingRepairRatioInjection(ItemStack stack) {
        return getMendingRepairRatio(tempMendingLevel);
    }

    @Redirect(
            method = "repairPlayerItems",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/ExperienceOrb;durabilityToXp(I)I")
    )
    private int repairPlayerItemsGetMendingRepairCostInjection(ExperienceOrb instance, int repairAmount) {
        return getMendingRepairCost(repairAmount, tempMendingLevel);
    }
}

