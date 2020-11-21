package io.github.freeze_dolphin.ultimate_generators;

import org.bukkit.plugin.Plugin;

import io.github.freeze_dolphin.ultimate_generators.listeners.EnderCrystalListener;

public class UGListenersRegister {
	
	private Plugin plug;
	
	public UGListenersRegister(Plugin loader) {
		this.plug = loader;
	}
	
	public void registerAll() {
		plug.getServer().getPluginManager().registerEvents(new EnderCrystalListener(), Loader.getImplement());
	}
	
}
