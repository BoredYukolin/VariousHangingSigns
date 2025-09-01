package top.yukolin.world.block;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.WoodType;
import top.yukolin.VariousHangingSigns;

public interface AbstractVariousHangingSign {

    Block getChain1();

    Block getChain2();

    Block getLog();

    WoodType type();

    ResourceLocation getTexture();
}
