package yes.mediumdifficulty.mendinglevels.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.MendingEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import yes.mediumdifficulty.mendinglevels.config.Common;

@Mixin(MendingEnchantment.class)
public class MendingEnchantmentMixin {
    @ModifyArg(
            method = "<init>",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/Enchantment;<init>(Lnet/minecraft/enchantment/Enchantment$Rarity;Lnet/minecraft/enchantment/EnchantmentType;[Lnet/minecraft/inventory/EquipmentSlotType;)V"),
            index = 0
    )
    private static Enchantment.Rarity registerMendingInjection(Enchantment.Rarity weight) {
        return Common.Rarity.get();
    }
    /**
     * @author Calamitous_End
     * @reason fix mending not appearing up in enchantment table.
     */
    @Overwrite
    public int getMinEnchantability(final int enchantmentLevel) {
        return Math.max(30-Common.XpLvlsPerLvl.get()+(enchantmentLevel - 1),Common.MinTableLvl.get());
    }
    /**
     * @author Calamitous_End
     * @reason overwrites MaxLevel
     */
    @Overwrite
    public int getMaxLevel() {
        return Common.MaxLevel.get();
    }
    /**
     * @author Calamitous_End
     * @reason determines whether mending can show up in enchantment table.
     */
    @Overwrite
    public boolean isTreasureEnchantment() {
        return Common.IsTreasure.get();
    }
}
