package io.freeze_dolphin.ultimate_generators.objects.tasks;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.cscorelib2.collections.LoopIterator;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.math.RandomUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.GlassPane;

import io.github.thebusybiscuit.slimefun4.api.MinecraftVersion;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import io.github.thebusybiscuit.slimefun4.utils.ColoredMaterial;

public class ReinforcedRainbowTicker {

    public static class Fast extends BlockTicker {

        private final LoopIterator<Material> iterator;
        private final boolean glassPanes;
        private Material material;

        public Fast(List<Material> materials) {
            Validate.noNullElements(materials, "A ReinforcedRainbowTicker cannot have a Material that is null!");

            if (materials.isEmpty()) {
                throw new IllegalArgumentException(
                        "A ReinforcedRainbowTicker must have at least one Material associated with it!");
            }

            glassPanes = containsGlassPanes(materials);
            iterator = new LoopIterator<>(materials);
            material = iterator.next();
        }

        public Fast(Material... materials) {
            this(Arrays.asList(materials));
        }

        public Fast(ColoredMaterial material) {
            this(material.asList());
        }

        private boolean containsGlassPanes(List<Material> materials) {
            if (SlimefunPlugin.getMinecraftVersion() == MinecraftVersion.UNIT_TEST) {

                return false;
            }

            for (Material type : materials) {
                if (type.createBlockData() instanceof GlassPane) {
                    return true;
                }
            }

            return false;
        }

        @Override
        public void tick(Block b, SlimefunItem item, Config data) {
            if (b.getType() == Material.AIR) {
                return;
            }

            if (glassPanes) {
                BlockData blockData = b.getBlockData();

                if (blockData instanceof GlassPane) {
                    BlockData block = material.createBlockData(bd -> {
                        if (bd instanceof GlassPane) {
                            GlassPane previousData = (GlassPane) blockData;
                            GlassPane nextData = (GlassPane) bd;

                            nextData.setWaterlogged(previousData.isWaterlogged());

                            for (BlockFace face : previousData.getAllowedFaces()) {
                                nextData.setFace(face, previousData.hasFace(face));
                            }
                        }
                    });

                    b.setBlockData(block, false);
                    return;
                }
            }

            b.setType(material, false);
        }

        @Override
        public void uniqueTick() {
            material = iterator.next();
        }

        @Override
        public boolean isSynchronized() {
            return true;
        }
    }

    public static class Fancy extends BlockTicker {

        private final LoopIterator<Material> iterator;
        private final boolean glassPanes;
        private Material material;

        public Fancy(List<Material> materials) {
            Validate.noNullElements(materials, "A ReinforcedRainbowTicker cannot have a Material that is null!");

            if (materials.isEmpty()) {
                throw new IllegalArgumentException(
                        "A ReinforcedRainbowTicker must have at least one Material associated with it!");
            }

            glassPanes = containsGlassPanes(materials);
            iterator = new LoopIterator<>(materials);
            material = materials.get(RandomUtils.nextInt(materials.size() - 1));
        }

        public Fancy(Material... materials) {
            this(Arrays.asList(materials));
        }

        public Fancy(ColoredMaterial material) {
            this(material.asList());
        }

        private boolean containsGlassPanes(List<Material> materials) {
            if (SlimefunPlugin.getMinecraftVersion() == MinecraftVersion.UNIT_TEST) {

                return false;
            }

            for (Material type : materials) {
                if (type.createBlockData() instanceof GlassPane) {
                    return true;
                }
            }

            return false;
        }

        @Override
        public void tick(Block b, SlimefunItem item, Config data) {
            if (b.getType() == Material.AIR) {
                return;
            }

            if (glassPanes) {
                BlockData blockData = b.getBlockData();

                if (blockData instanceof GlassPane) {
                    BlockData block = material.createBlockData(bd -> {
                        if (bd instanceof GlassPane) {
                            GlassPane previousData = (GlassPane) blockData;
                            GlassPane nextData = (GlassPane) bd;

                            nextData.setWaterlogged(previousData.isWaterlogged());

                            for (BlockFace face : previousData.getAllowedFaces()) {
                                nextData.setFace(face, previousData.hasFace(face));
                            }
                        }
                    });

                    b.setBlockData(block, false);
                    return;
                }
            }

            b.setType(material, false);
        }

        @Override
        public void uniqueTick() {
            material = iterator.next();
        }

        @Override
        public boolean isSynchronized() {
            return true;
        }

    }

}
