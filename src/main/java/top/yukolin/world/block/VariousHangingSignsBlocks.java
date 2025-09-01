package top.yukolin.world.block;

import net.fabricmc.fabric.api.client.rendering.v1.SpecialBlockRendererRegistry;
import net.minecraft.client.renderer.special.HangingSignSpecialRenderer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import top.yukolin.VariousHangingSigns;
import top.yukolin.world.block.entity.VariousHangingSignBlockEntity;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class VariousHangingSignsBlocks {

    public static final Map<VariousCeilingHangingSignBlock, VariousWallHangingSignBlock> CEILING_SIGNS_TO_WALL_SIGNS = new LinkedHashMap<>();

    static {
        forEachChain(chain1 -> forEachChain(chain2 -> {
            if (chain1 == Blocks.IRON_CHAIN && chain2 == Blocks.IRON_CHAIN) return;
            registerHangingSign(chain1, chain2, Blocks.STRIPPED_OAK_LOG, Blocks.OAK_HANGING_SIGN);
            registerHangingSign(chain1, chain2, Blocks.STRIPPED_SPRUCE_LOG, Blocks.SPRUCE_HANGING_SIGN);
            registerHangingSign(chain1, chain2, Blocks.STRIPPED_BIRCH_LOG, Blocks.BIRCH_HANGING_SIGN);
            registerHangingSign(chain1, chain2, Blocks.STRIPPED_JUNGLE_LOG, Blocks.JUNGLE_HANGING_SIGN);
            registerHangingSign(chain1, chain2, Blocks.STRIPPED_ACACIA_LOG, Blocks.ACACIA_HANGING_SIGN);
            registerHangingSign(chain1, chain2, Blocks.STRIPPED_DARK_OAK_LOG, Blocks.DARK_OAK_HANGING_SIGN);
            registerHangingSign(chain1, chain2, Blocks.STRIPPED_MANGROVE_LOG, Blocks.MANGROVE_HANGING_SIGN);
            registerHangingSign(chain1, chain2, Blocks.STRIPPED_CHERRY_LOG, Blocks.CHERRY_HANGING_SIGN);
            registerHangingSign(chain1, chain2, Blocks.STRIPPED_PALE_OAK_LOG, Blocks.PALE_OAK_HANGING_SIGN);
            registerHangingSign(chain1, chain2, Blocks.STRIPPED_BAMBOO_BLOCK, Blocks.BAMBOO_HANGING_SIGN);
            registerHangingSign(chain1, chain2, Blocks.STRIPPED_WARPED_STEM, Blocks.WARPED_HANGING_SIGN);
            registerHangingSign(chain1, chain2, Blocks.STRIPPED_CRIMSON_STEM, Blocks.CRIMSON_HANGING_SIGN);
        }));

        VariousHangingSigns.LOGGER.info("Initialized Various Hanging Signs Blocks.");
    }

    public static void initialize() {
    }

    public static void forEachChain(Consumer<Block> consumer) {
        Blocks.COPPER_CHAIN.forEach(consumer);
        consumer.accept(Blocks.IRON_CHAIN);
    }

    public static void registerHangingSign(Block chain1, Block chain2, Block log, Block copyFrom) {
        SignBlock sign = (SignBlock) copyFrom;
        String ceilingId, wallId;
        String chain1Id = BlockUtils.getBlockIdRaw(chain1);
        if (chain1 == chain2) {
            ceilingId = sign.type().name() + "_hanging_sign_with_" + chain1Id;
            wallId = sign.type().name() + "_wall_hanging_sign_with_" + chain1Id;
        } else {
            String chain2Id = BlockUtils.getBlockIdRaw(chain2);
            ceilingId = sign.type().name() + "_hanging_sign_with_" + chain1Id + "_and_" + chain2Id;
            wallId = sign.type().name() + "_wall_hanging_sign_with_" + chain1Id + "_and_" + chain2Id;
        }

        Block ceilingHangingSign = register(
                ceilingId,
                properties -> new VariousCeilingHangingSignBlock(chain1, chain2, log, sign.type(), properties),
                BlockBehaviour.Properties.ofFullCopy(copyFrom)
        );
        Block wallHangingSign = register(
                wallId,
                properties -> new VariousWallHangingSignBlock(chain1, chain2, log, sign.type(), properties),
                BlockBehaviour.Properties.ofFullCopy(copyFrom).overrideLootTable(ceilingHangingSign.getLootTable()).overrideDescription(ceilingHangingSign.getDescriptionId())
        );
        VariousHangingSignBlockEntity.TYPE.addSupportedBlock(ceilingHangingSign);
        VariousHangingSignBlockEntity.TYPE.addSupportedBlock(wallHangingSign);
        CEILING_SIGNS_TO_WALL_SIGNS.put((VariousCeilingHangingSignBlock) ceilingHangingSign, (VariousWallHangingSignBlock) wallHangingSign);
    }

    public static Block register(String name, Function<Block.Properties, Block> factory, Block.Properties properties) {
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(VariousHangingSigns.MOD_ID, name));
        Block item = factory.apply(properties.setId(key));
        Registry.register(BuiltInRegistries.BLOCK, key, item);
        return item;
    }
}
