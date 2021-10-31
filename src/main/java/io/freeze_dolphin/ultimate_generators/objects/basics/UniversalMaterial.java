package io.freeze_dolphin.ultimate_generators.objects.basics;

import org.bukkit.Material;
import org.bukkit.material.MaterialData;

public class UniversalMaterial extends MaterialData {

    public UniversalMaterial(Material m) {
        super(m);
    }

    @SuppressWarnings("deprecation")
    public UniversalMaterial(Material m, int data) {
        super(m, (byte) data);
    }

    public MaterialData toData() {
        return this;
    }

}
