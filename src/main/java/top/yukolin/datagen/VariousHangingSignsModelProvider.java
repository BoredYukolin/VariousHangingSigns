package top.yukolin.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import top.yukolin.world.block.VariousHangingSignsBlocks;
import top.yukolin.world.item.VariousHangingSignsItems;

public class VariousHangingSignsModelProvider extends FabricModelProvider {

    protected VariousHangingSignsModelProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        VariousHangingSignsBlocks.CEILING_SIGNS_TO_WALL_SIGNS.forEach((ceilingSign, wallSign) -> {
            Block log = ceilingSign.getLog();
            MultiVariant multiVariant = blockStateModelGenerator.createParticleOnlyBlockModel(ceilingSign, log);
            blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(ceilingSign, multiVariant));
            blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(wallSign, multiVariant));
        });
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        for (Item hangingSign : VariousHangingSignsItems.VARIOUS_HANGING_SIGNS) {
            itemModelGenerator.generateFlatItem(hangingSign, ModelTemplates.FLAT_ITEM);
        }
    }
}
