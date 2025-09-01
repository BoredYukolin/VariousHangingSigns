package top.yukolin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.SpecialBlockRendererRegistry;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.client.renderer.special.HangingSignSpecialRenderer;
import net.minecraft.resources.ResourceLocation;
import top.yukolin.world.block.BlockUtils;
import top.yukolin.world.block.VariousHangingSignsBlocks;
import top.yukolin.world.block.entity.VariousHangingSignBlockEntity;

import java.util.Optional;

@Environment(EnvType.CLIENT)
public class VariousHangingSignRenderers {

    static {
        BlockEntityRenderers.register(VariousHangingSignBlockEntity.TYPE, HangingSignRenderer::new);
        VariousHangingSignsBlocks.CEILING_SIGNS_TO_WALL_SIGNS.forEach((ceiling, wall) -> {
            ResourceLocation location = BlockUtils.getBlockId(ceiling);
            SpecialBlockRendererRegistry.register(ceiling, new HangingSignSpecialRenderer.Unbaked(ceiling.type(), Optional.of(location)));
            SpecialBlockRendererRegistry.register(wall, new HangingSignSpecialRenderer.Unbaked(wall.type(), Optional.of(location)));
        });
    }

    public static void initialize() {
    }

}
