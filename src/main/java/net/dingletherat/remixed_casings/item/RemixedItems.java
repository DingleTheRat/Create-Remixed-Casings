package net.dingletherat.remixed_casings.item;

import net.dingletherat.remixed_casings.CreateRemixedCasings;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RemixedItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreateRemixedCasings.MOD_ID);

    public static final DeferredItem<Item> ACACIA_BARK = ITEMS.register("acacia_bark", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
