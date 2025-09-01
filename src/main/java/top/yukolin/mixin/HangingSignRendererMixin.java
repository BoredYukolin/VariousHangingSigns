package top.yukolin.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Environment(EnvType.CLIENT)
@Mixin(HangingSignRenderer.class)
public class HangingSignRendererMixin {

    @ModifyArg(method = "createHangingSignLayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/geom/builders/CubeListBuilder;texOffs(II)Lnet/minecraft/client/model/geom/builders/CubeListBuilder;", ordinal = 4), index = 0)
    private static int modifyTexOff1(int i) {
        return 48;
    }

    @ModifyArg(method = "createHangingSignLayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/geom/builders/CubeListBuilder;texOffs(II)Lnet/minecraft/client/model/geom/builders/CubeListBuilder;", ordinal = 5), index = 0)
    private static int modifyTexOff2(int i) {
        return 54;
    }
}
