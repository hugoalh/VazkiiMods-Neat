package vazkii.neat;

import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkConstants;

@Mod(NeatConfig.MOD_ID)
public class NeatForgeInitializer {

	public NeatForgeInitializer() {
		ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> NetworkConstants.IGNORESERVERONLY, (incoming, isNetwork) -> true));
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		NeatForgeConfig.init();
	}

	public void setup(FMLClientSetupEvent event) {
		// Constructing KEY itself accesses global vanilla state which is incompatible with parallel mod loading
		event.enqueueWork(() -> ClientRegistry.registerKeyBinding(ToggleKeybind.KEY));
	}
}
