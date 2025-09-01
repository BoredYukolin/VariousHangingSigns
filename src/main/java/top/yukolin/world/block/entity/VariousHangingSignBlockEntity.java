package top.yukolin.world.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import top.yukolin.VariousHangingSigns;

public class VariousHangingSignBlockEntity extends SignBlockEntity {

    public static final BlockEntityType<VariousHangingSignBlockEntity> TYPE =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, VariousHangingSigns.makeId("hanging_sign"),
                    FabricBlockEntityTypeBuilder.create(VariousHangingSignBlockEntity::new).build());

    public VariousHangingSignBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(TYPE, blockPos, blockState);
    }

    @Override
    public int getTextLineHeight() {
        return 9;
    }

    @Override
    public int getMaxTextLineWidth() {
        return 60;
    }

    @Override
    public @NotNull SoundEvent getSignInteractionFailedSoundEvent() {
        return SoundEvents.WAXED_HANGING_SIGN_INTERACT_FAIL;
    }
}
