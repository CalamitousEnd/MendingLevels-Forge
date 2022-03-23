package yes.mediumdifficulty.mendinglevels;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import yes.mediumdifficulty.mendinglevels.config.Common;

@Mod(Main.MODID)
public class Main {

    public static final String MODID = "mendinglevels";

    public Main() {

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Common.SPEC, MODID + "-common.toml");
        System.out.println(MODID + ":registering mod and configs...");
    }

    public static void Setup(final FMLCommonSetupEvent e) {
    }
}
