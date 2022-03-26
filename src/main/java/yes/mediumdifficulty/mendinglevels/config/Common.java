package yes.mediumdifficulty.mendinglevels.config;


import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.common.ForgeConfigSpec;

public class Common {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> MaxLevel;
    public static final ForgeConfigSpec.ConfigValue<Boolean> IsTreasure;
    public static final ForgeConfigSpec.EnumValue<Enchantment.Rarity> Rarity;
    public static final ForgeConfigSpec.ConfigValue<Integer> XpLvlsPerLvl;
    public static final ForgeConfigSpec.ConfigValue<Integer> MinTableLvl;

    static {
        BUILDER.push("CONFIG");

        MaxLevel = BUILDER.comment("The maximum mending level").define("max_level", 5);
        IsTreasure = BUILDER.comment("If the mending enchantment is treasure (true = can't be in enchanting table)").define("is_treasure", false);
        Rarity = BUILDER.comment("Rarity of the mending enchantment").defineEnum("rarity", Enchantment.Rarity.VERY_RARE);
        XpLvlsPerLvl = BUILDER.comment("How many experience levels are required per mending level.").define("exp_levels_per_level", 2);
        MinTableLvl = BUILDER.comment("The minimum enchantment table level required for mending to appear").define("min_table_lvl", 20);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
