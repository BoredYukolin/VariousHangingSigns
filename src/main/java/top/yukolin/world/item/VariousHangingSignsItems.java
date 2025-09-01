package top.yukolin.world.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import top.yukolin.VariousHangingSigns;
import top.yukolin.world.block.VariousHangingSignsBlocks;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class VariousHangingSignsItems {

    public static final List<Item> VARIOUS_HANGING_SIGNS = new ArrayList<>();

    static {
        VariousHangingSignsBlocks.CEILING_SIGNS_TO_WALL_SIGNS.forEach((ceilingSign, wallSign) -> {
            Item item = Items.registerBlock(ceilingSign, (block, properties) -> new HangingSignItem(block, wallSign, properties),
                    new Item.Properties().stacksTo(16));
            VARIOUS_HANGING_SIGNS.add(item);
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(itemGroup ->
                itemGroup.addAfter(Items.WARPED_HANGING_SIGN, (VARIOUS_HANGING_SIGNS.toArray(new Item[0]))));

        VariousHangingSigns.LOGGER.info("Initialized Various Hanging Signs Items.");
    }

    public static void initialize() {
    }

    public static Item registerItem(String name, Function<Item.Properties, Item> factory, Item.Properties properties) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(VariousHangingSigns.MOD_ID, name));
        Item item = factory.apply(properties.setId(key));
        Registry.register(BuiltInRegistries.ITEM, key, item);
        return item;
    }
}
