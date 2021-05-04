package io.freeze_dolphin.ultimate_generators;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/*
import io.freeze_dolphin.api.updating_server.UpdatingServerUtils;
import io.freeze_dolphin.api.updating_server.UpdatingServerUtils.VersionInfo;
import static io.freeze_dolphin.api.updating_server.UpdatingServerUtils.getLatestVersion;
import static io.freeze_dolphin.api.updating_server.UpdatingServerUtils.getVersionInfo;
 */
import io.freeze_dolphin.ultimate_generators.lists.UGCategories;
import io.freeze_dolphin.ultimate_generators.lists.UGItems;
import io.freeze_dolphin.ultimate_generators.lists.UGRecipeType;
/*
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
*/
//import org.dom4j.DocumentException;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;

public class Loader extends JavaPlugin implements SlimefunAddon {

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

        // load
        try {
            new UGItems();
            new UGRecipeType();
            new UGCategories();

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
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/freeze-dolphin/UltimateGenerators/issues/";
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    public SlimefunAddon getAddonHandle() {
        return this;
    }

}
