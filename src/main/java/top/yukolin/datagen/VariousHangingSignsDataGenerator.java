package top.yukolin.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class VariousHangingSignsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(VariousHangingSignsLanguageProvider::new);
        pack.addProvider(VariousHangingSignsModelProvider::new);
        pack.addProvider(VariousHangingSignsRecipeProvider::new);
	}
}
