package io.freeze_dolphin.ultimate_generators;

import io.freeze_dolphin.ultimate_generators.lists.UGCategories;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.Properties;

public class PlugGividado extends JavaPlugin {

    private static Plugin plug;
    private static Properties pp;

    public static Plugin getImplement() {
        return plug;
    }

    public static void info(String msg) {
        getImplement().getLogger().info(msg);
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
                    if (!ppf.getParentFile().mkdirs()) throw new IOException("Couldn't make directories.");
                }
                InputStream is = this.getClass().getClassLoader().getResourceAsStream("config.properties");
                try (FileOutputStream fos = new FileOutputStream(ppf)) {
                    assert is != null;
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
            new UGCategories(this);

            UGImplementor implementor = new UGImplementor();
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

        /*
        Utils.asyncDelay(() -> {
            if (Boolean.parseBoolean(getProperties().getProperty("enable-update-notification", "true"))) {
                info("Checking Updates...");
                try {
                    UpdatingServerUtils.setTimeOut(Integer.parseInt(DefaultConfig.getConfig("update-check-timeout")));
                    String latest = getLatestVersion(PlugGividado.getImplement().getName());
                    String current = PlugGividado.getImplement().getDescription().getVersion();
                    if (Integer.parseInt(latest.replaceAll("\\.", "")) > Integer.parseInt(current.replaceAll("\\.", ""))) {
                        VersionInfo vi = getVersionInfo(PlugGividado.getImplement().getName(), latest);
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
         */
    }

}
