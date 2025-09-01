package top.yukolin;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.yukolin.world.block.VariousHangingSignsBlocks;
import top.yukolin.world.item.VariousHangingSignsItems;

public class VariousHangingSigns implements ModInitializer {
	public static final String MOD_ID = "various-hanging-signs";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static ResourceLocation makeId(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Hello Fabric world!");
        VariousHangingSignsBlocks.initialize();
        VariousHangingSignsItems.initialize();
	}
}