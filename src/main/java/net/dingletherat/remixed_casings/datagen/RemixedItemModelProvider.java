
package net.dingletherat.remixed_casings.datagen;

import net.minecraft.data.PackOutput;
import net.dingletherat.remixed_casings.item.RemixedItems;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class RemixedItemModelProvider extends ItemModelProvider {
    public RemixedItemModelProvider(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, "remixed_casings", fileHelper);
    }

    @Override
    public void registerModels() {
        basicItem(RemixedItems.ACACIA_BARK.get());
    }
}
