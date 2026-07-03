package net.dingletherat.remixed_casings;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.content.decoration.encasing.CasingBlock;
import com.simibubi.create.foundation.data.CreateRegistrate;

import fr.iglee42.createcasing.casings.CasingSets;
import fr.iglee42.createcasing.registries.EncasedBlocks;
import fr.iglee42.createcasing.registries.EncasedItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.fml.ModContainer;

@Mod(CreateRemixedCasings.MOD_ID)
public class CreateRemixedCasings {
    public static final String MOD_ID = "remixed_casings";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static Map<Item, Block> CASING_CONVERSION_MAP;

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);

    public CreateRemixedCasings(IEventBus modEventBus, ModContainer modContainer) {
        REGISTRATE.registerEventListeners(modEventBus);
        RemixedCasings.register();

        modEventBus.addListener(this::onGatherData);
        modEventBus.addListener(this::commonSetup);
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        CASING_CONVERSION_MAP = Map.of(
           Items.COPPER_INGOT, AllBlocks.COPPER_CASING.get(),
           AllItems.ANDESITE_ALLOY.get(), AllBlocks.ANDESITE_CASING.get(),
           AllItems.BRASS_INGOT.get(), AllBlocks.BRASS_CASING.get(),
           AllItems.STURDY_SHEET.get(), AllBlocks.RAILWAY_CASING.get(),
           AllItems.SHADOW_STEEL.get(), CasingSets.SHADOW_STEEL.getCasing(),
           AllItems.REFINED_RADIANCE.get(), CasingSets.REFINED_RADIANCE.getCasing(),
           EncasedItems.CHORIUM_INGOT.get(), CasingSets.CREATIVE.getCasing()
        ); 
    }

    public void onGatherData(GatherDataEvent event) {
        PackOutput packOutput = event.getGenerator().getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        event.getGenerator().addProvider(event.includeClient(), new RemixedCasingAssetProvider(packOutput, existingFileHelper));
    }
}
