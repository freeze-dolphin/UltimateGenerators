package io.freeze_dolphin.ultimate_generators.objects.tasks;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BlockTicker;

import org.apache.commons.lang3.RandomUtils;
import org.bukkit.block.Block;

public class ReinforcedRainbowTicker {

    public static class Fast extends BlockTicker {

        public int meta;
        public int[] queue;

        public Fast() {
            this(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        }

        public Fast(int... data) {
            this.queue = data;
            this.meta = data[0];
        }

        @SuppressWarnings("deprecation")
        @Override
        public void tick(Block b, SlimefunItem item, Config data) {
            b.setData((byte) this.meta, false);
        }

        @Override
        public void uniqueTick() {
            this.meta = this.queue[RandomUtils.nextInt(0, this.queue.length - 1)];
        }

        @Override
        public boolean isSynchronized() {
            return true;
        }
    }

    public static class Fancy extends BlockTicker {

        public int[] queue;

        public Fancy() {
            this(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        }

        public Fancy(int... data) {
            this.queue = data;
        }

        @SuppressWarnings("deprecation")
        @Override
        public void tick(Block b, SlimefunItem item, Config data) {
            b.setData((byte) this.queue[RandomUtils.nextInt(0, this.queue.length - 1)], false);
        }

        @Override
        public void uniqueTick() {
        }

        @Override
        public boolean isSynchronized() {
            return true;
        }
    }

}
