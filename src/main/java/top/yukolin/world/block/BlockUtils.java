package top.yukolin.world.block;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import top.yukolin.VariousHangingSigns;

public class BlockUtils {

    public static String getBlockIdRaw(Block block) {
        return getBlockId(block).getPath();
    }

    public static ResourceLocation getBlockId(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    public static ResourceLocation createTexture(AbstractVariousHangingSign sign) {
        if (sign.getChain1() == sign.getChain2()) return VariousHangingSigns.makeId(sign.type().name() + '_' + BlockUtils.getBlockIdRaw(sign.getChain1()));
        return VariousHangingSigns.makeId(sign.type().name() + '_' + BlockUtils.getBlockIdRaw(sign.getChain1()) + '_' + BlockUtils.getBlockIdRaw(sign.getChain2()));
    }

}
