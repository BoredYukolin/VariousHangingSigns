package top.yukolin.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Block;
import top.yukolin.world.block.BlockUtils;
import top.yukolin.world.block.VariousHangingSignsBlocks;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class VariousHangingSignsLanguageProvider extends FabricLanguageProvider {

    protected VariousHangingSignsLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder translationBuilder) {
        for (Block hangingSign : VariousHangingSignsBlocks.CEILING_SIGNS_TO_WALL_SIGNS.keySet()) {
            String name = Arrays.stream(BlockUtils.getBlockIdRaw(hangingSign).split("_"))
                    .map(w -> w.equals("with") || w.equals("and") ? w :
                            w.substring(0, 1).toUpperCase() + w.substring(1).toLowerCase())
                    .collect(Collectors.joining(" "));

            translationBuilder.add(hangingSign, name);
        }
    }
}
