package io.freeze_dolphin.ultimate_generators;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Properties;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import io.freeze_dolphin.ultimate_generators.lists.UGCategories;
import io.freeze_dolphin.ultimate_generators.lists.UGItems;
import io.freeze_dolphin.ultimate_generators.lists.UGRecipeType;

import java.io.IOException;

public class Loader extends JavaPlugin {

    private static Plugin plug;
    private static Properties pp;

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public void onEnable() {

        plug = this;

        // load configuration
        try {

            File ppf = new File(getDataFolder().getPath() + File.separatorChar + "config.properties");
            if (!ppf.exists()) {
                info("Configuration is not exist, creating one...");
                InputStream is = this.getClass().getClassLoader().getResourceAsStream("/config.properties");
                try (FileOutputStream fos = new FileOutputStream(ppf)) {
                    byte[] b = new byte[is.available()];
                    while (is.read(b) != -1) {
                        fos.write(b);
                    }
                }
            }
            info("Sucessfully created the default configuration!");

            pp = new Properties();
            pp.load(new FileReader(ppf));

        } catch (IOException ex) {
            ex.printStackTrace();
            severe("Cannot initialize the configuration, self-disabling...");
            this.setEnabled(false);
            return;
        }

        // load
        try {
            UGItems ugItems = new UGItems(this);
            UGRecipeType ugRecipeType = new UGRecipeType();
            UGCategories ugCategories = new UGCategories(this);

            UGImplementor implementor = new UGImplementor(this);
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
        /*
		 * (Deprecated) try { Class<?> ugItemClass =
		 * Class.forName("io.github.freeze_dolphin.ultimate_generators.lists.UGItems");
		 * for (Field f : ugItemClass.getFields()) { if
		 * (f.isAnnotationPresent(MachineItemStack.class)) { if
		 * (SlimefunItem.getByID(f.getName()) == null) {
		 * severe("An unexpected error occurred: Item '" + f.getName() +
		 * "' is not loaded!"); continue; } else {
		 * getUGConfig().setMachineValue(f.getName(), "speed", 1); } } else { continue;
		 * } } } catch (ClassNotFoundException e) { e.printStackTrace();
		 * severe("In-plugin file 'UGItems.class' is lost, self-disabling...");
		 * this.setEnabled(false); }
         */
    }

    public static Plugin getImplement() {
        return plug;
    }

    public static void info(String msg) {
        getImplement().getLogger().info(msg);
    }

    public static void warn(String msg) {
        getImplement().getLogger().warning(msg);
    }

    public static void severe(String msg) {
        getImplement().getLogger().severe(msg);
    }

    public static Properties getProperties() {
        return pp;
    }

    public static boolean getDisplaySw() {

        return getProperties().contains("show-machine-indicator") && pp.get("show-machine-indicator").equals("true");

        /*
		 * YamlConfiguration pdf = new YamlConfiguration(); try { pdf.load(new
		 * InputStreamReader(Loader.class.getClassLoader().getResourceAsStream(
		 * "plugin.yml"))); } catch (Exception e) { e.printStackTrace();
		 * plug.getLogger()
		 * .severe("The internal plugin description file 'plugin.yml' cannot be read, self-disabling..."
		 * ); plug.getServer().getPluginManager().disablePlugin(plug); }
		 * 
		 * return pdf.contains("show-machine-indicator") &&
		 * pdf.isBoolean("show-machine-indicator") &&
		 * pdf.getBoolean("show-machine-indicator");
         */
    }

}
