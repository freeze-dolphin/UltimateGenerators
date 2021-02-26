package io.freeze_dolphin.ultimate_generators;

import java.util.HashMap;
import java.util.Map;

public class DefaultConfig {

    private static final Map<String, String> map = new HashMap<>();

    static {
        map.put("enable-update-notification", "true");
        map.put("update-check-timeout", "9000");
        map.put("show-machine-indicator", "true");
        map.put("magnesium-salt-require-zinc-dust", "false");
        map.put("generation-per-crystal", "128");
        map.put("warning-crystal-number", "9");
        map.put("reinforced-rainbow-glass-twinkling-delay-in-ticks", "40");
        map.put("reinforced-rainbow-glass-twinkling-randomly", "true");
        map.put("reinforced-rainbow-glass-crafting-number-on-once", "1");
        
        map.put("glass-electricity-transmitter-crafting-number-on-once", "12");
    }

    public static String getConfig(String s) {
        return Loader.getProperties().getProperty(s, map.get(s));
    }

}
