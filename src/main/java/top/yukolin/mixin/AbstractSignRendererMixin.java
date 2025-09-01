package top.yukolin.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.AbstractSignRenderer;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.util.Unit;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import top.yukolin.world.block.AbstractVariousHangingSign;

@Environment(EnvType.CLIENT)
@Mixin(AbstractSignRenderer.class)
public abstract class AbstractSignRendererMixin {

    @Shadow
    protected abstract float getSignModelRenderScale();

    @Shadow
    @Final
    private MaterialSet materials;

    @WrapOperation(method = "submitSignWithText", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/blockentity/AbstractSignRenderer;submitSign(Lcom/mojang/blaze3d/vertex/PoseStack;IILnet/minecraft/world/level/block/state/properties/WoodType;Lnet/minecraft/client/model/Model$Simple;Lnet/minecraft/client/renderer/feature/ModelFeatureRenderer$CrumblingOverlay;Lnet/minecraft/client/renderer/SubmitNodeCollector;)V"))
    private void modifySignMaterial(AbstractSignRenderer instance, PoseStack poseStack, int i, int j, WoodType woodType, Model.Simple simple, ModelFeatureRenderer.CrumblingOverlay crumblingOverlay, SubmitNodeCollector submitNodeCollector, Operation<Void> original, @Local(argsOnly = true) LocalRef<SignBlock> signBlock) {
        if (signBlock.get() instanceof AbstractVariousHangingSign variousHangingSign) {
            Material material = Sheets.HANGING_SIGN_MAPPER.apply(variousHangingSign.getTexture());
            submitSignWithMaterial(poseStack, i, j, simple, crumblingOverlay, submitNodeCollector, material);
        } else {
            original.call(instance, poseStack, i, j, woodType, simple, crumblingOverlay, submitNodeCollector);
        }
    }

    @Unique
    private void submitSignWithMaterial(
            PoseStack poseStack,
            int i,
            int j,
            Model.Simple simple,
            @Nullable ModelFeatureRenderer.CrumblingOverlay crumblingOverlay,
            SubmitNodeCollector submitNodeCollector,
            Material material
    ) {
        poseStack.pushPose();
        float f = this.getSignModelRenderScale();
        poseStack.scale(f, -f, -f);
        RenderType renderType = material.renderType(simple::renderType);
        submitNodeCollector.submitModel(simple, Unit.INSTANCE, poseStack, renderType, i, j, -1, this.materials.get(material), 0, crumblingOverlay);
        poseStack.popPose();
    }
}
