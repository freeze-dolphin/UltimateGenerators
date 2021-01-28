package io.freeze_dolphin.ultimate_generators;

import io.freeze_dolphin.ultimate_generators.listeners.*;

public class UGListenersRegister {
	
	private Loader plug;
	
	public UGListenersRegister(Loader loader) {
		this.plug = loader;
	}
	
	public void registerAll() {
		/*
		plug.getServer().getPluginManager().registerEvents(new EnderCrystalListener(), Loader.getImplement());
		 */
		
		plug.getServer().getPluginManager().registerEvents(new UltimateElectricityStorageCraftingListener(), Loader.getImplement());
	}
	
}
