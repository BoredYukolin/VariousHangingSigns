package top.yukolin;

import net.fabricmc.api.ClientModInitializer;
import top.yukolin.client.VariousHangingSignRenderers;

public class VariousHangingSignsClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
        VariousHangingSignRenderers.initialize();
	}
}