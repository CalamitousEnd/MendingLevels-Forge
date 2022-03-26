package yes.mediumdifficulty.mendinglevels.mixin;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.MendingEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import yes.mediumdifficulty.mendinglevels.config.Common;

@Mixin(MendingEnchantment.class)
public class MendingEnchantmentMixin {
    @ModifyArg(
            method = "<init>",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/Enchantment;<init>(Lnet/minecraft/world/item/enchantment/Enchantment$Rarity;Lnet/minecraft/world/item/enchantment/EnchantmentCategory;[Lnet/minecraft/world/entity/EquipmentSlot;)V"),
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
    public int getMinCost(final int level) {
        return Math.max(30-Common.XpLvlsPerLvl.get()+(level - 1),Common.MinTableLvl.get());
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
    public boolean isTreasureOnly() {
        return Common.IsTreasure.get();
    }
}
