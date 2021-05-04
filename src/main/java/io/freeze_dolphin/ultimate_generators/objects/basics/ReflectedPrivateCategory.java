package io.freeze_dolphin.ultimate_generators.objects.basics;

import java.lang.reflect.Field;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import io.freeze_dolphin.ultimate_generators.Loader;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;

public class ReflectedPrivateCategory {

    private String label;

    public ReflectedPrivateCategory(String defaultCategoryLabel) {
        this.label = defaultCategoryLabel;
    }

    public Category getCategory() throws Exception {
            Class<?> CDefaultCategories = Class.forName("io.github.thebusybiscuit.slimefun4.implementation.setup.DefaultCategories");
            Field LCategory = CDefaultCategories.getDeclaredField(label);
            LCategory.setAccessible(true);
            return ((Category) LCategory.get(new Category(new NamespacedKey(Loader.getImplement(), "reflected_priv_category_" + label), new CustomItem(Material.BARRIER))));
    }

}
