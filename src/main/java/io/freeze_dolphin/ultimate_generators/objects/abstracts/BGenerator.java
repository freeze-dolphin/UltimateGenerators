package io.freeze_dolphin.ultimate_generators.objects.abstracts;

import io.freeze_dolphin.ultimate_generators.Utils;
import io.freeze_dolphin.ultimate_generators.lists.UGItems;
import io.freeze_dolphin.ultimate_generators.objects.basics.UniversalMaterial;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.AdvancedMenuClickHandler;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.InvUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunBlockHandler;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.UnregisterReason;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineFuel;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineHelper;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
import me.mrCookieSlime.Slimefun.api.energy.EnergyTicker;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

@SuppressWarnings("unused")
public abstract class BGenerator extends SlimefunItem {

    protected static final int INDICATOR = 22;
    protected static final int MACHINE_INFO = 8;
    private static final int[] border
            = {
            0,
            9, 17,
            18, 19, 20, 21, 23, 24, 25, 26,
            27, 35,
            36, 44
    };
    private static final int[] border_in
            = {
            1, 7,
            10, 11, 12, 13, 14, 15, 16
    };
    private static final int[] border_out
            = {
            28, 29, 30, 31, 32, 33, 34,
            37, 43
    };
    public static Map<Location, MachineFuel> processing = new HashMap<>();
    public static Map<Location, Integer> progress = new HashMap<>();
    private final String ID;
    private final boolean displayMachineInfo;
    private final Set<MachineFuel> recipes = new HashSet<>();

    public BGenerator(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe, boolean displayMachineInfo) {
        super(category, item, id, recipeType, recipe);

        ID = id;
        this.displayMachineInfo = displayMachineInfo;

        new BlockMenuPreset(id, getInventoryTitle()) {

            @Override
            public void init() {
                constructMenu(this);
            }

            @Override
            public void newInstance(BlockMenu menu, Block b) {
            }

            @Override
            public boolean canOpen(Block b, Player p) {
                return Utils.reflectCanOpenMethod(b, p);
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                if (flow.equals(ItemTransportFlow.INSERT)) {
                    return getInputSlots();
                } else {
                    return getOutputSlots();
                }
            }
        };

        registerBlockHandler(id, new SlimefunBlockHandler() {

            @Override
            public void onPlace(Player p, Block b, SlimefunItem item) {

            }

            @Override
            public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
                BlockMenu inv = BlockStorage.getInventory(b);
                if (inv != null) {
                    for (int slot : getInputSlots()) {
                        if (inv.getItemInSlot(slot) != null) {
                            b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
                            inv.replaceExistingItem(slot, null);
                        }
                    }
                    for (int slot : getOutputSlots()) {
                        if (inv.getItemInSlot(slot) != null) {
                            b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
                            inv.replaceExistingItem(slot, null);
                        }
                    }
                }
                progress.remove(b.getLocation());
                processing.remove(b.getLocation());
                return true;
            }
        });
        registerDefaultRecipes();
    }

    public int[] getInputSlots() {
        return new int[]{2, 3, 4, 5, 6};
    }

    public int[] getOutputSlots() {
        return new int[]{38, 39, 40, 41, 42};
    }

    private void constructMenu(BlockMenuPreset preset) {
        for (int i : border) {
            preset.addItem(i, new CustomItem(new UniversalMaterial(Material.STAINED_GLASS_PANE, 7), " "), (Player arg0, int arg1, ItemStack arg2, ClickAction arg3) -> false);
        }
        for (int i : border_in) {
            preset.addItem(i, new CustomItem(new UniversalMaterial(Material.STAINED_GLASS_PANE, 9), " "), (Player arg0, int arg1, ItemStack arg2, ClickAction arg3) -> false);
        }
        for (int i : border_out) {
            preset.addItem(i, new CustomItem(new UniversalMaterial(Material.STAINED_GLASS_PANE, 1), " "), (Player arg0, int arg1, ItemStack arg2, ClickAction arg3) -> false);
        }

        for (int i : getOutputSlots()) {
            preset.addMenuClickHandler(i, new AdvancedMenuClickHandler() {

                @Override
                public boolean onClick(Player p, int slot, ItemStack cursor, ClickAction action) {
                    return false;
                }

                @Override
                public boolean onClick(InventoryClickEvent e, Player p, int slot, ItemStack cursor, ClickAction action) {
                    return cursor == null || cursor.getType() == null || cursor.getType() == Material.AIR;
                }
            });
        }

        preset.addItem(INDICATOR, new CustomItem(new UniversalMaterial(Material.STAINED_GLASS_PANE, 15), " "), (Player arg0, int arg1, ItemStack arg2, ClickAction arg3) -> false);

        if (displayMachineInfo) {
            preset.addItem(MACHINE_INFO, new CustomItem(new UniversalMaterial(Material.EMPTY_MAP), "&f机器信息", "&7 - &3发电量: &e" + getEnergyProduction() * 2 + " J/s", "&7 - &3工作速度: &e" + (getSpeed() == 1 ? "&f默认" : getSpeed())), (Player arg0, int arg1, ItemStack arg2, ClickAction arg3) -> false);
        } else {
            preset.addItem(MACHINE_INFO, new CustomItem(new UniversalMaterial(Material.STAINED_GLASS_PANE, 7), " "), (Player arg0, int arg1, ItemStack arg2, ClickAction arg3) -> false);
        }

    }

    public abstract void registerDefaultRecipes();

    public abstract String getInventoryTitle();

    public abstract int getEnergyProduction();

    public abstract int getSpeed();

    public abstract ItemStack getProgressBar();

    public String getMachineIdentifier() {
        return ID;
    }

    public MachineFuel getProcessing(Location l) {
        return processing.get(l);
    }

    public boolean isProcessing(Location l) {
        return progress.containsKey(l);
    }

    public void registerFuel(MachineFuel fuel) {
        this.recipes.add(fuel);
    }

    @Override
    public void register(boolean slimefun) {
        addItemHandler(new EnergyTicker() {

            @Override
            public double generateEnergy(Location l, SlimefunItem sf, Config data) {

                if (l.getBlock().getBlockPower() > 1) {
                    return 0D;
                }

                if (isProcessing(l)) {
                    int timeleft = progress.get(l);
                    if (timeleft > 0) {
                        ItemStack item = getProgressBar().clone();
                        item.setDurability(MachineHelper.getDurability(item, timeleft, processing.get(l).getTicks()));
                        ItemMeta im = item.getItemMeta();
                        im.setDisplayName(" ");
                        List<String> lore = new ArrayList<>();
                        lore.add(MachineHelper.getProgress(timeleft, processing.get(l).getTicks()));
                        lore.add("");
                        lore.add(MachineHelper.getTimeLeft(timeleft / 2));
                        im.setLore(lore);
                        item.setItemMeta(im);

                        BlockStorage.getInventory(l).replaceExistingItem(INDICATOR, item);

                        if (ChargableBlock.isChargable(l)) {
                            if (ChargableBlock.getMaxCharge(l) - ChargableBlock.getCharge(l) >= getEnergyProduction()) {
                                ChargableBlock.addCharge(l, getEnergyProduction());
                                progress.put(l, timeleft - 1);
                                return ChargableBlock.getCharge(l);
                            }
                            return 0;
                        } else {
                            progress.put(l, timeleft - 1);
                            return getEnergyProduction();
                        }
                    } else {
                        ItemStack fuel = processing.get(l).getInput();
                        if (SlimefunManager.isItemSimiliar(fuel, new ItemStack(Material.LAVA_BUCKET), true)) {
                            pushItems(l, new ItemStack[]{new ItemStack(Material.BUCKET)});
                        } else if (SlimefunManager.isItemSimiliar(fuel, SlimefunItems.BUCKET_OF_FUEL, true)) {
                            pushItems(l, new ItemStack[]{new ItemStack(Material.BUCKET)});
                        } else if (SlimefunManager.isItemSimiliar(fuel, SlimefunItems.BUCKET_OF_OIL, true)) {
                            pushItems(l, new ItemStack[]{new ItemStack(Material.BUCKET)});
                        } else if (SlimefunManager.isItemSimiliar(fuel, UGItems.BIOFUEL_BUCKET, true)) {
                            pushItems(l, new ItemStack[]{new ItemStack(Material.BUCKET)});
                        } else if (SlimefunManager.isItemSimiliar(fuel, UGItems.BIOMASS_BUCKET, true)) {
                            pushItems(l, new ItemStack[]{new ItemStack(Material.BUCKET)});
                        } else if (SlimefunManager.isItemSimiliar(fuel, UGItems.DIESEL_BUCKET, true)) {
                            pushItems(l, new ItemStack[]{new ItemStack(Material.BUCKET)});
                        }
                        BlockStorage.getInventory(l).replaceExistingItem(INDICATOR, new CustomItem(new UniversalMaterial(Material.STAINED_GLASS_PANE, 15), " "));

                        progress.remove(l);
                        processing.remove(l);
                        return 0;
                    }
                } else {
                    MachineFuel r = null;
                    Map<Integer, Integer> found = new HashMap<>();
                    outer:
                    for (MachineFuel recipe : recipes) {
                        for (int slot : getInputSlots()) {
                            if (SlimefunManager.isItemSimiliar(BlockStorage.getInventory(l).getItemInSlot(slot), recipe.getInput(), true)) {
                                found.put(slot, recipe.getInput().getAmount());
                                r = recipe;
                                break outer;
                            }
                        }
                    }

                    if (r != null) {
                        found.forEach((key, value) -> BlockStorage.getInventory(l).replaceExistingItem(key, InvUtils.decreaseItem(BlockStorage.getInventory(l).getItemInSlot(key), value)));
                        processing.put(l, r);
                        progress.put(l, r.getTicks());
                    }
                    return 0;
                }
            }

            @Override
            public boolean explode(Location l) {
                return false;
            }
        });

        super.register(slimefun);
    }

    public Set<MachineFuel> getFuelTypes() {
        return this.recipes;
    }

    private Inventory inject(Location l) {
        int size = BlockStorage.getInventory(l).toInventory().getSize();
        Inventory inv = Bukkit.createInventory(null, size);
        for (int i = 0; i < size; i++) {
            inv.setItem(i, new CustomItem(Material.COMMAND, " &4ALL YOUR PLACEHOLDERS ARE BELONG TO US", 0));
        }
        for (int slot : getOutputSlots()) {
            inv.setItem(slot, BlockStorage.getInventory(l).getItemInSlot(slot));
        }
        return inv;
    }

    protected void pushItems(Location l, ItemStack[] items) {
        Inventory inv = inject(l);
        inv.addItem(items);

        for (int slot : getOutputSlots()) {
            BlockStorage.getInventory(l).replaceExistingItem(slot, inv.getItem(slot));
        }
    }
}
