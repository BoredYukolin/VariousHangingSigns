package top.yukolin.world.block;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jetbrains.annotations.NotNull;
import top.yukolin.world.block.entity.VariousHangingSignBlockEntity;

public class VariousCeilingHangingSignBlock extends CeilingHangingSignBlock implements AbstractVariousHangingSign {

    private final Block chain1;
    private final Block chain2;
    private final Block log;
    private final ResourceLocation texture;

    public VariousCeilingHangingSignBlock(Block chain1, Block chain2, Block log, WoodType woodType, Properties properties) {
        super(woodType, properties);
        this.chain1 = chain1;
        this.chain2 = chain2;
        this.log = log;
        this.texture = BlockUtils.createTexture(this);
    }

    public Block getChain1() {
        return chain1;
    }

    public Block getChain2() {
        return chain2;
    }

    public Block getLog() {
        return log;
    }

    public ResourceLocation getTexture() {
        return texture;
    }

    @Override
    public @NotNull BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new VariousHangingSignBlockEntity(blockPos, blockState);
    }
}
