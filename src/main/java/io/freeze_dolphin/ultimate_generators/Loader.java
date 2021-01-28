package io.freeze_dolphin.ultimate_generators;

import java.io.InputStreamReader;
import java.util.logging.Logger;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import io.freeze_dolphin.ultimate_generators.lists.UGCategories;
import io.freeze_dolphin.ultimate_generators.lists.UGItems;
import io.freeze_dolphin.ultimate_generators.lists.UGRecipeType;

public class Loader extends JavaPlugin {

	private static Plugin plug;
	
	private static Logger logger;
	
	@Override
	public void onEnable() {

		plug = this;
		logger = getLogger();
		
		// load
		try {
			new UGItems(this);
			new UGRecipeType();
			new UGCategories(this);
			
			UGImplementor implementor = new UGImplementor();
			implementor.implementIngredients();
			implementor.implementMachines();
			implementor.implementSingleGenerators();
			implementor.implementModularGenerators();
			
			UGListenersRegister register = new UGListenersRegister(this);
			register.registerAll();
		} catch (Exception ex) {
			ex.printStackTrace();
			severe("Cannot initialize the plugin, self-disabling...");
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

	public static void info(String msg) {
		logger.info(msg);
	}

	public static void warn(String msg) {
		logger.warning(msg);
	}

	public static void severe(String msg) {
		logger.severe(msg);
	}
	
	public static boolean getDisplaySw() {
		YamlConfiguration pdf = new YamlConfiguration();
		try {
			pdf.load(new InputStreamReader(Loader.class.getClassLoader().getResourceAsStream("plugin.yml")));
		} catch (Exception e) {
			e.printStackTrace();
			plug.getLogger().severe("The internal plugin description file 'plugin.yml' cannot be read, self-disabling...");
			plug.getServer().getPluginManager().disablePlugin(plug);
		}
		
		return pdf.contains("show-machine-indicator") && pdf.isBoolean("show-machine-indicator") && pdf.getBoolean("show-machine-indicator");
		
	}
	
}
