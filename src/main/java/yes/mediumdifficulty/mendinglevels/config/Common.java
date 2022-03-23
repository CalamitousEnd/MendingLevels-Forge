package yes.mediumdifficulty.mendinglevels.config;


import net.minecraftforge.common.ForgeConfigSpec;

public class Common {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> MAX_LEVEL;

    static {
        BUILDER.push("Config");

        MAX_LEVEL = BUILDER.comment("The maximum mending level\n(you can effectively disable the mod by setting it to 1, but I have no idea why you would do that seen as you've downloaded this mod to change the max value)").define("max_level", 5);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}