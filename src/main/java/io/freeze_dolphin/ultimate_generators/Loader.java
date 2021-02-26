package io.freeze_dolphin.ultimate_generators;

import io.freeze_dolphin.api.updating_server.UpdatingServerUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import io.freeze_dolphin.api.updating_server.UpdatingServerUtils.VersionInfo;
import static io.freeze_dolphin.api.updating_server.UpdatingServerUtils.getLatestVersion;
import static io.freeze_dolphin.api.updating_server.UpdatingServerUtils.getVersionInfo;
import io.freeze_dolphin.ultimate_generators.lists.UGCategories;
import io.freeze_dolphin.ultimate_generators.lists.UGItems;
import io.freeze_dolphin.ultimate_generators.lists.UGRecipeType;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import org.dom4j.DocumentException;

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

                if (!ppf.getParentFile().exists()) {
                    ppf.getParentFile().mkdirs();
                }
                InputStream is = this.getClass().getClassLoader().getResourceAsStream("config.properties");
                try (FileOutputStream fos = new FileOutputStream(ppf)) {
                    byte[] b = new byte[is.available()];
                    while (is.read(b) != -1) {
                        fos.write(b);
                    }
                }
                info("Sucessfully created the default configuration!");
            }

            pp = new Properties();
            pp.load(new FileReader(ppf));

        } catch (IOException ex) {
            ex.printStackTrace();
            severe("Cannot initialize the configuration, self-disabling...");
            this.setEnabled(false);
            return;
        }

        DefaultConfig.init();
        
        // load
        try {
            new UGItems(this);
            new UGRecipeType();
            new UGCategories(this);

            UGImplementor implementor = new UGImplementor(this);
            implementor.implementIngredients();
            implementor.implementMachines();
            implementor.implementSingleGenerators();
            implementor.implementModularGenerators();

            new UGListenersRegister(this).registerAll();
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
        Utils.asyncDelay(() -> {
            if (Boolean.parseBoolean(getProperties().getProperty("enable-update-notification", "true"))) {
                info("Checking Updates...");
                try {
                    UpdatingServerUtils.setTimeOut(Integer.parseInt(DefaultConfig.getConfig("update-check-timeout")));
                    String latest = getLatestVersion(Loader.getImplement().getName());
                    String current = Loader.getImplement().getDescription().getVersion();
                    if (Integer.parseInt(latest.replaceAll("\\.", "")) > Integer.parseInt(current.replaceAll("\\.", ""))) {
                        VersionInfo vi = getVersionInfo(Loader.getImplement().getName(), latest);
                        warn("Update detected: v" + latest + " (Current: v" + current + ")" + "\n\t· " + vi.getName() + "\n\t· " + vi.getDescription() + "\n\t· " + vi.getURL());
                    } else {
                        info("You are now in the latest version!");
                    }
                } catch (IOException | KeyManagementException | NoSuchAlgorithmException ex) {
                    // ex.printStackTrace();
                    warn("Unable to check updates! Make sure that you have configured your Internet properly and try again by restarting the server!");
                } catch (DocumentException | NumberFormatException | NullPointerException | NoSuchProviderException ex) {
                    // ex.printStackTrace();
                    warn("Unable to check updates! Please contact the plugin author to fix the updating-server bug, or you can temporarily disable the update checker by setting 'enable-update-notification' to 'false' in 'config.properties'");
                }
            }
        });
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

        return Boolean.parseBoolean(getProperties().getProperty("show-machine-indicator", "true"));

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
