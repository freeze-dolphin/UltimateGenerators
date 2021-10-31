package io.freeze_dolphin.ultimate_generators;

import io.freeze_dolphin.ultimate_generators.listeners.EnderCrystalEnhancerListener;
import io.freeze_dolphin.ultimate_generators.listeners.UltimateElectricityStorageCraftingListener;

class UGListenersRegister {

    private final PlugGividado plug;

    public UGListenersRegister(PlugGividado loader) {
        this.plug = loader;
    }

    public void registerAll() {
        plug.getServer().getPluginManager().registerEvents(new UltimateElectricityStorageCraftingListener(), PlugGividado.getImplement());
        plug.getServer().getPluginManager().registerEvents(new EnderCrystalEnhancerListener(), PlugGividado.getImplement());
        // plug.getServer().getPluginManager().registerEvents(new ReinforcedRainbowGlassBlastProofListener(), Loader.getImplement());
    }

}
