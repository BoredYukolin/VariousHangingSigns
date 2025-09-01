package top.yukolin.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.HangingSignEditScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.yukolin.world.block.entity.VariousHangingSignBlockEntity;

@Environment(EnvType.CLIENT)
@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {
    @Shadow
    @Final
    protected Minecraft minecraft;

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;setScreen(Lnet/minecraft/client/gui/screens/Screen;)V", ordinal = 1), method = "openTextEdit", cancellable = true)
    private void init(SignBlockEntity signBlockEntity, boolean bl, CallbackInfo ci) {
        if (signBlockEntity instanceof VariousHangingSignBlockEntity) {
            this.minecraft.setScreen(new HangingSignEditScreen(signBlockEntity, bl, this.minecraft.isTextFilteringEnabled()));
            ci.cancel();
        }
    }
}