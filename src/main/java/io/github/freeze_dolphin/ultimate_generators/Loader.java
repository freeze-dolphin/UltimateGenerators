package io.github.freeze_dolphin.ultimate_generators;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.freeze_dolphin.ultimate_generators.lists.UGItems;
import io.github.freeze_dolphin.ultimate_generators.objects.basics.UGConfig;

public class Loader extends JavaPlugin {

	private static Plugin plug;
	private static UGConfig config;

	@Override
	public void onEnable() {

		plug = this;
		config = new UGConfig(this);


		// load
		try {
			new UGItems();
			new GlobalVariables();
			new Implementor();
		} catch (Exception ex) {
			ex.printStackTrace();
			severe("Cannot initialize plugin 'UltimateGenerators', self-disabling...");
			this.setEnabled(false);
		}

		// initialize configurations
		/* (Deprecated)
		try {
			Class<?> ugItemClass = Class.forName("io.github.freeze_dolphin.ultimate_generators.lists.UGItems");
			for (Field f : ugItemClass.getFields()) {
				if (f.isAnnotationPresent(MachineItemStack.class)) {
					if (SlimefunItem.getByID(f.getName()) == null) {
						severe("An unexpected error occurred: Item '" + f.getName() + "' is not loaded!");
						continue;
					} else {
						getUGConfig().setMachineValue(f.getName(), "speed", 1);
					}
				} else {
					continue;
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			severe("In-plugin file 'UGItems.class' is lost, self-disabling...");
			this.setEnabled(false);
		}
		 */

	}

	public static Plugin getImplement() { return plug; }

	public static UGConfig getUGConfig() { return config; }

	public void info(String msg) {
		getLogger().info(msg);
	}

	public void warn(String msg) {
		getLogger().warning(msg);
	}

	public void severe(String msg) {
		getLogger().severe(msg);
	}

}
