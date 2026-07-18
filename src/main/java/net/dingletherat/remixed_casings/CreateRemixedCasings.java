package net.dingletherat.remixed_casings;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.foundation.data.CreateRegistrate;

import net.dingletherat.remixed_casings.block.RemixedBlocks;
import net.dingletherat.remixed_casings.datagen.CasingBlockStateProvider;
import net.dingletherat.remixed_casings.datagen.RemixedBlockStateProvider;
import net.dingletherat.remixed_casings.datagen.RemixedItemModelProvider;
import net.dingletherat.remixed_casings.item.RemixedItems;
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

    public static final Map<Block, Map<Item, Block>> CASING_CONVERSION_MAP = new HashMap<>();

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);

    public CreateRemixedCasings(IEventBus modEventBus, ModContainer modContainer) {
        REGISTRATE.registerEventListeners(modEventBus);
        RemixedCasings.register();

        RemixedItems.register(modEventBus);
        RemixedBlocks.register(modEventBus);

        modEventBus.addListener(this::onGatherData);
        modEventBus.addListener(this::setupConversionMap);
    }

    public void setupConversionMap(FMLCommonSetupEvent event) {
        // Andesite
        Map<Item, Block> andesite = Map.of(
            // Planks
            Items.DARK_OAK_PLANKS, RemixedCasings.ANDESITE_DARK_OAK.getCasing(),
            RemixedBlocks.ACACIA_BARK_PLANKS.get().asItem(), RemixedCasings.ANDESITE_ACACIA_BARK.getCasing(),
            AllItems.STURDY_SHEET.get(), RemixedCasings.ANDESITE_TRAIN.getCasing(),

            // Materials
            AllItems.BRASS_INGOT.get(), RemixedCasings.BRASS_SPRUCE.getCasing()
        );
        CASING_CONVERSION_MAP.put(AllBlocks.ANDESITE_CASING.get(), andesite);

        // Dark Oak Andesite
        Map<Item, Block> andesiteDarkOak = Map.of(
            // Planks
            Items.SPRUCE_PLANKS, AllBlocks.ANDESITE_CASING.get(),
            RemixedBlocks.ACACIA_BARK_PLANKS.get().asItem(), RemixedCasings.ANDESITE_ACACIA_BARK.getCasing(),
            AllItems.STURDY_SHEET.get(), RemixedCasings.ANDESITE_TRAIN.getCasing(),

            // Materials
            AllItems.BRASS_INGOT.get(), AllBlocks.BRASS_CASING.get()
        );
        CASING_CONVERSION_MAP.put(RemixedCasings.ANDESITE_DARK_OAK.getCasing(), andesiteDarkOak);

        // Andesite Acacia Bark
        Map<Item, Block> andesiteAcaciaBark = Map.of(
            // Planks
            Items.SPRUCE_PLANKS, AllBlocks.ANDESITE_CASING.get(),
            Items.DARK_OAK_PLANKS, RemixedCasings.ANDESITE_DARK_OAK.getCasing(),
            AllItems.STURDY_SHEET.get(), RemixedCasings.ANDESITE_TRAIN.getCasing(),

            // Materials
            AllItems.BRASS_INGOT.get(), RemixedCasings.BRASS_ACACIA_BARK.getCasing()
        );
        CASING_CONVERSION_MAP.put(RemixedCasings.ANDESITE_ACACIA_BARK.getCasing(), andesiteAcaciaBark);

        // Andesite Train
        Map<Item, Block> andesiteTrain = Map.of(
            // Planks
            Items.SPRUCE_PLANKS, AllBlocks.ANDESITE_CASING.get(),
            Items.DARK_OAK_PLANKS, RemixedCasings.ANDESITE_DARK_OAK.getCasing(),
            RemixedBlocks.ACACIA_BARK_PLANKS.get().asItem(), RemixedCasings.ANDESITE_ACACIA_BARK.getCasing(),

            // Materials
            AllItems.BRASS_INGOT.get(), AllBlocks.RAILWAY_CASING.get()
        );
        CASING_CONVERSION_MAP.put(RemixedCasings.ANDESITE_TRAIN.getCasing(), andesiteTrain);

        // Brass
        Map<Item, Block> brass = Map.of(
            // Planks
            Items.SPRUCE_PLANKS, RemixedCasings.BRASS_SPRUCE.getCasing(),
            RemixedBlocks.ACACIA_BARK_PLANKS.get().asItem(), RemixedCasings.BRASS_ACACIA_BARK.getCasing(),

            // Materials
            AllItems.ANDESITE_ALLOY.get(), RemixedCasings.ANDESITE_DARK_OAK.getCasing()
        );
        CASING_CONVERSION_MAP.put(AllBlocks.BRASS_CASING.get(), brass);

        // Brass Spruce
        Map<Item, Block> brassSpruce = Map.of(
            // Planks
            Items.DARK_OAK_PLANKS, AllBlocks.BRASS_CASING.get(),
            RemixedBlocks.ACACIA_BARK_PLANKS.get().asItem(), RemixedCasings.BRASS_ACACIA_BARK.getCasing(),
            AllItems.STURDY_SHEET.get(), AllBlocks.RAILWAY_CASING.get(),

            // Materials
            AllItems.ANDESITE_ALLOY.get(), AllBlocks.ANDESITE_CASING.get()
        );
        CASING_CONVERSION_MAP.put(RemixedCasings.BRASS_SPRUCE.getCasing(), brassSpruce);

        // Brass Acacia Bark
        Map<Item, Block> brassAcaciaBark = Map.of(
            // Planks
            Items.DARK_OAK_PLANKS, AllBlocks.BRASS_CASING.get(),
            Items.SPRUCE_PLANKS, RemixedCasings.BRASS_SPRUCE.getCasing(),
            AllItems.STURDY_SHEET.get(), AllBlocks.RAILWAY_CASING.get(),

            // Materials
            AllItems.ANDESITE_ALLOY.get(), RemixedCasings.ANDESITE_ACACIA_BARK.getCasing()
        );
        CASING_CONVERSION_MAP.put(RemixedCasings.BRASS_ACACIA_BARK.getCasing(), brassAcaciaBark);

        // Train Casing
        Map<Item, Block> trainCasing = Map.of(
            // Planks
            Items.DARK_OAK_PLANKS, AllBlocks.BRASS_CASING.get(),
            Items.SPRUCE_PLANKS, RemixedCasings.BRASS_SPRUCE.getCasing(),
            RemixedBlocks.ACACIA_BARK_PLANKS.get().asItem(), RemixedCasings.BRASS_ACACIA_BARK.getCasing(),
            // Materials
            AllItems.ANDESITE_ALLOY.get(), RemixedCasings.ANDESITE_TRAIN.getCasing()
        );
        CASING_CONVERSION_MAP.put(AllBlocks.RAILWAY_CASING.get(), trainCasing);
    }

    public void onGatherData(GatherDataEvent event) {
        PackOutput packOutput = event.getGenerator().getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        event.getGenerator().addProvider(event.includeClient(), new CasingBlockStateProvider(packOutput, existingFileHelper));
        event.getGenerator().addProvider(event.includeClient(), new RemixedBlockStateProvider(packOutput, existingFileHelper));
        event.getGenerator().addProvider(event.includeClient(), new RemixedItemModelProvider(packOutput, existingFileHelper));
    }
}
