package top.yukolin.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;
import top.yukolin.world.block.VariousCeilingHangingSignBlock;
import top.yukolin.world.block.VariousHangingSignsBlocks;

import java.util.concurrent.CompletableFuture;

public class VariousHangingSignsRecipeProvider extends FabricRecipeProvider {

    protected VariousHangingSignsRecipeProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
        return new RecipeProvider(registryLookup, exporter) {
            @Override
            public void buildRecipes() {
                for (VariousCeilingHangingSignBlock sign : VariousHangingSignsBlocks.CEILING_SIGNS_TO_WALL_SIGNS.keySet()) {
                    this.hangingSign(sign.getChain1().asItem(), sign.getChain2().asItem(), sign.getLog().asItem(), sign.asItem());
                }
            }

            private void hangingSign(Item chain1, Item chain2, Item log, Item result) {
                this.shaped(RecipeCategory.DECORATIONS, result, 6)
                        .group("hanging_sign")
                        .define('#', log)
                        .define('X', chain1)
                        .define('Y', chain2)
                        .pattern("X Y")
                        .pattern("###")
                        .pattern("###")
                        .unlockedBy("has_stripped_logs", this.has(log))
                        .save(this.output);
            }
        };
    }

    @Override
    public @NotNull String getName() {
        return "VariousHangingSignsRecipeProvider";
    }
}
