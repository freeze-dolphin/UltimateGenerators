package io.freeze_dolphin.ultimate_generators;

import io.freeze_dolphin.ultimate_generators.listeners.*;

class UGListenersRegister {

    private final Loader plug;

    public UGListenersRegister(Loader loader) {
        this.plug = loader;
    }

    public void registerAll() {
        /*
		plug.getServer().getPluginManager().registerEvents(new EnderCrystalListener(), Loader.getImplement());
         */

        plug.getServer().getPluginManager().registerEvents(new UltimateElectricityStorageCraftingListener(), Loader.getImplement());
        plug.getServer().getPluginManager().registerEvents(new EnderCrystalEnhancerListener(), Loader.getImplement());
    }

}
