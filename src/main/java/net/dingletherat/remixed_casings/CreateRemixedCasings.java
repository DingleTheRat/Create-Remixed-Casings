package net.dingletherat.remixed_casings;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;

import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.fml.ModContainer;

@Mod(CreateRemixedCasings.MOD_ID)
public class CreateRemixedCasings {
    public static final String MOD_ID = "remixed_casings";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);

    public CreateRemixedCasings(IEventBus modEventBus, ModContainer modContainer) {
        REGISTRATE.registerEventListeners(modEventBus);
        RemixedCasings.register();
        modEventBus.addListener(this::onGatherData);
    }

    public void onGatherData(GatherDataEvent event) {
        PackOutput packOutput = event.getGenerator().getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        event.getGenerator().addProvider(event.includeClient(), new RemixedCasingAssetProvider(packOutput, existingFileHelper));
    }
}
