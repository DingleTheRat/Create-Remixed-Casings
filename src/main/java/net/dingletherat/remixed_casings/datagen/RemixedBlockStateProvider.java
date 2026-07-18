package net.dingletherat.remixed_casings.datagen;

import net.minecraft.data.PackOutput;
import net.dingletherat.remixed_casings.block.RemixedBlocks;

import net.neoforged.neoforge.client.model.generators.BlockStateProvider;

import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class RemixedBlockStateProvider extends BlockStateProvider {
    public RemixedBlockStateProvider(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, "remixed_casings", fileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItem(RemixedBlocks.ACACIA_BARK_PLANKS.get(), cubeAll(RemixedBlocks.ACACIA_BARK_PLANKS.get()));
    }
}
