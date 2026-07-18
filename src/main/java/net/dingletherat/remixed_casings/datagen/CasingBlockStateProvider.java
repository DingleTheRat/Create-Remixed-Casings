package net.dingletherat.remixed_casings.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import com.drmangotea.tfmg.TFMG;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogwheelBlock;

import net.dingletherat.remixed_casings.RemixedCasings;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class CasingBlockStateProvider extends BlockStateProvider {
    private final ResourceLocation gearboxTexture = ResourceLocation.fromNamespaceAndPath("create", "block/gearbox");
    private final ResourceLocation depotTopTexture = ResourceLocation.fromNamespaceAndPath("create", "block/depot_top");
    private final ResourceLocation cogwheelSideTexture = ResourceLocation.fromNamespaceAndPath("create", "block/andesite_encased_cogwheel_side");

    private final ResourceLocation aluminumTexture = ResourceLocation.fromNamespaceAndPath("tfmg", "block/industrial_aluminum_casing");

    private final ResourceLocation steelTexture = ResourceLocation.fromNamespaceAndPath("tfmg", "block/steel_casing");
    private final ResourceLocation steelGearboxTexture = ResourceLocation.fromNamespaceAndPath("tfmg", "block/steel_gearbox");
    private final ResourceLocation steelEncasedCogwheelSide = ResourceLocation.fromNamespaceAndPath("tfmg", "block/steel_encased_cogwheel_side");

    public CasingBlockStateProvider(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, "createcasing", fileHelper);

        // Tell the fileHelper that these external textures are mine
        if (ModList.get().isLoaded(TFMG.MOD_ID)) {
            fileHelper.trackGenerated(aluminumTexture, PackType.CLIENT_RESOURCES, ".png", "textures");

            fileHelper.trackGenerated(steelTexture, PackType.CLIENT_RESOURCES, ".png", "textures");
            fileHelper.trackGenerated(steelGearboxTexture, PackType.CLIENT_RESOURCES, ".png", "textures");
            fileHelper.trackGenerated(steelEncasedCogwheelSide, PackType.CLIENT_RESOURCES, ".png", "textures");
        }
        fileHelper.trackGenerated(gearboxTexture, PackType.CLIENT_RESOURCES, ".png", "textures");
        fileHelper.trackGenerated(depotTopTexture, PackType.CLIENT_RESOURCES, ".png", "textures");
        fileHelper.trackGenerated(cogwheelSideTexture, PackType.CLIENT_RESOURCES, ".png", "textures");
    }

    @Override
    protected void registerStatesAndModels() {
        if (ModList.get().isLoaded(TFMG.MOD_ID)) {
            ResourceLocation aluminumGearbox = ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/aluminum_gearbox");
            ResourceLocation aluminumEncasedCogwheelSide = ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/aluminum_encased_cogwheel_side");

            createEncasedShaft(RemixedCasings.ALUMINUM.getShaft(), aluminumTexture, aluminumGearbox);
            createEncasedCogwheel("encased_cogwheel", RemixedCasings.ALUMINUM.getCogwheel(), aluminumTexture, aluminumEncasedCogwheelSide, aluminumGearbox);
            createEncasedCogwheel("encased_large_cogwheel", RemixedCasings.ALUMINUM.getLargeCogwheel(), aluminumTexture, aluminumEncasedCogwheelSide, aluminumGearbox);

            createEncasedShaft(RemixedCasings.STEEL.getShaft(), steelTexture, steelGearboxTexture);
            createEncasedCogwheel("encased_cogwheel", RemixedCasings.STEEL.getCogwheel(), steelTexture, steelEncasedCogwheelSide, steelGearboxTexture);
            createEncasedCogwheel("encased_large_cogwheel", RemixedCasings.STEEL.getLargeCogwheel(), steelTexture, steelEncasedCogwheelSide, steelGearboxTexture);
        }

        // Andesite
        ModelFile andesiteDarkOak = models().cubeAll(name(RemixedCasings.ANDESITE_DARK_OAK.getCasing()), ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/andesite/dark_oak"));
        simpleBlockWithItem(RemixedCasings.ANDESITE_DARK_OAK.getCasing(), andesiteDarkOak);
        ModelFile andesiteTrain = models().cubeAll(name(RemixedCasings.ANDESITE_TRAIN.getCasing()), ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/andesite/train"));
        simpleBlockWithItem(RemixedCasings.ANDESITE_TRAIN.getCasing(), andesiteTrain);
        ModelFile andesiteAcaciaBark = models().cubeAll(name(RemixedCasings.ANDESITE_ACACIA_BARK.getCasing()), ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/andesite/acacia_bark"));
        simpleBlockWithItem(RemixedCasings.ANDESITE_ACACIA_BARK.getCasing(), andesiteAcaciaBark);

        // Brass
        ModelFile brassSpruce = models().cubeAll(name(RemixedCasings.BRASS_SPRUCE.getCasing()), ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/brass/spruce"));
        simpleBlockWithItem(RemixedCasings.BRASS_SPRUCE.getCasing(), brassSpruce);
        ModelFile brassAcaciaBark = models().cubeAll(name(RemixedCasings.BRASS_ACACIA_BARK.getCasing()), ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/brass/acacia_bark"));
        simpleBlockWithItem(RemixedCasings.BRASS_ACACIA_BARK.getCasing(), brassAcaciaBark);
    }

    public String name(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

    public void createEncasedShaft(Block block, ResourceLocation casing, ResourceLocation opening) {
        String name = BuiltInRegistries.BLOCK.getKey(block).getPath();
        BlockModelBuilder model = models().getBuilder("block/" + name)
            .parent(new ModelFile.UncheckedModelFile(ResourceLocation.fromNamespaceAndPath("create", "block/encased_shaft/block")))
            .texture("casing", casing.toString())
            .texture("opening", opening.toString())
            .texture("particle", casing.toString());
        axisBlock(block, model);
    }

    public void createEncasedCogwheel(String type, Block block, ResourceLocation casing, ResourceLocation side, ResourceLocation opening) {
        String name = BuiltInRegistries.BLOCK.getKey(block).getPath();
       BlockModelBuilder model = models().getBuilder("block/" + name)
            .parent(new ModelFile.UncheckedModelFile(ResourceLocation.fromNamespaceAndPath("tfmg", "block/" + type + "/block")))
            .texture("1", casing.toString())
            .texture("4", opening.toString())
            .texture("casing", casing.toString())
            .texture("particle", casing.toString())
            .texture("side", side.toString());

        BlockModelBuilder modelTop = models().getBuilder("block/" + name + "_top")
            .parent(new ModelFile.UncheckedModelFile(ResourceLocation.fromNamespaceAndPath("tfmg", "block/" + type + "/block_top")))
            .texture("1", casing.toString())
            .texture("4", opening.toString())
            .texture("casing", casing.toString())
            .texture("particle", casing.toString())
            .texture("side", side.toString());

        BlockModelBuilder modelBottom = models().getBuilder("block/" + name + "_bottom")
            .parent(new ModelFile.UncheckedModelFile(ResourceLocation.fromNamespaceAndPath("tfmg", "block/" + type + "/block_bottom")))
            .texture("1", casing.toString())
            .texture("4", opening.toString())
            .texture("casing", casing.toString())
            .texture("particle", casing.toString())
            .texture("side", side.toString());

        BlockModelBuilder modelTopBottom = models().getBuilder("block/" + name + "_top_bottom")
            .parent(new ModelFile.UncheckedModelFile(ResourceLocation.fromNamespaceAndPath("tfmg", "block/" + type + "/block_top_bottom")))
            .texture("1", casing.toString())
            .texture("4", opening.toString())
            .texture("casing", casing.toString())
            .texture("particle", casing.toString())
            .texture("side", side.toString());
        cogBlock(block, model, modelTop, modelBottom, modelTopBottom);
    }

    private void axisBlock(Block block, BlockModelBuilder model) {
        String name = BuiltInRegistries.BLOCK.getKey(block).getPath();
        getVariantBuilder(block)
            .partialState().with(BlockStateProperties.AXIS, Direction.Axis.X).modelForState().modelFile(model).rotationX(90).rotationY(90).addModel()
            .partialState().with(BlockStateProperties.AXIS, Direction.Axis.Y).modelForState().modelFile(model).addModel()
            .partialState().with(BlockStateProperties.AXIS, Direction.Axis.Z).modelForState().modelFile(model).rotationX(90).rotationY(180).addModel();
        itemModels().withExistingParent("item/" + name, "createcasing:block/" + name);
    }
    private void cogBlock(Block block, BlockModelBuilder model, BlockModelBuilder modelTop, BlockModelBuilder modelBottom, BlockModelBuilder modelTopBottom) {
        String name = BuiltInRegistries.BLOCK.getKey(block).getPath();
        getVariantBuilder(block)
            // Both false
            .partialState().with(BlockStateProperties.AXIS, Direction.Axis.X).with(EncasedCogwheelBlock.BOTTOM_SHAFT, false).with(EncasedCogwheelBlock.TOP_SHAFT, false).modelForState().modelFile(model).rotationX(90).rotationY(90).addModel()
            .partialState().with(BlockStateProperties.AXIS, Direction.Axis.Y).with(EncasedCogwheelBlock.BOTTOM_SHAFT, false).with(EncasedCogwheelBlock.TOP_SHAFT, false).modelForState().modelFile(model).addModel()
            .partialState().with(BlockStateProperties.AXIS, Direction.Axis.Z).with(EncasedCogwheelBlock.BOTTOM_SHAFT, false).with(EncasedCogwheelBlock.TOP_SHAFT, false).modelForState().modelFile(model).rotationX(90).rotationY(180).addModel()

            // Both true
            .partialState().with(BlockStateProperties.AXIS, Direction.Axis.X).with(EncasedCogwheelBlock.BOTTOM_SHAFT, true).with(EncasedCogwheelBlock.TOP_SHAFT, true).modelForState().modelFile(modelTopBottom).rotationX(90).rotationY(90).addModel()
            .partialState().with(BlockStateProperties.AXIS, Direction.Axis.Y).with(EncasedCogwheelBlock.BOTTOM_SHAFT, true).with(EncasedCogwheelBlock.TOP_SHAFT, true).modelForState().modelFile(modelTopBottom).addModel()
            .partialState().with(BlockStateProperties.AXIS, Direction.Axis.Z).with(EncasedCogwheelBlock.BOTTOM_SHAFT, true).with(EncasedCogwheelBlock.TOP_SHAFT, true).modelForState().modelFile(modelTopBottom).rotationX(90).rotationY(180).addModel()

            // True, false 
            .partialState().with(BlockStateProperties.AXIS, Direction.Axis.X).with(EncasedCogwheelBlock.BOTTOM_SHAFT, true).with(EncasedCogwheelBlock.TOP_SHAFT, false).modelForState().modelFile(modelBottom).rotationX(90).rotationY(90).addModel()
            .partialState().with(BlockStateProperties.AXIS, Direction.Axis.Y).with(EncasedCogwheelBlock.BOTTOM_SHAFT, true).with(EncasedCogwheelBlock.TOP_SHAFT, false).modelForState().modelFile(modelBottom).addModel()
            .partialState().with(BlockStateProperties.AXIS, Direction.Axis.Z).with(EncasedCogwheelBlock.BOTTOM_SHAFT, true).with(EncasedCogwheelBlock.TOP_SHAFT, false).modelForState().modelFile(modelBottom).rotationX(90).rotationY(180).addModel()

            // False, true 
            .partialState().with(BlockStateProperties.AXIS, Direction.Axis.X).with(EncasedCogwheelBlock.BOTTOM_SHAFT, false).with(EncasedCogwheelBlock.TOP_SHAFT, true).modelForState().modelFile(modelTop).rotationX(90).rotationY(90).addModel()
            .partialState().with(BlockStateProperties.AXIS, Direction.Axis.Y).with(EncasedCogwheelBlock.BOTTOM_SHAFT, false).with(EncasedCogwheelBlock.TOP_SHAFT, true).modelForState().modelFile(modelTop).addModel()
            .partialState().with(BlockStateProperties.AXIS, Direction.Axis.Z).with(EncasedCogwheelBlock.BOTTOM_SHAFT, false).with(EncasedCogwheelBlock.TOP_SHAFT, true).modelForState().modelFile(modelTop).rotationX(90).rotationY(180).addModel();

        itemModels().withExistingParent("item/" + name, "createcasing:block/" + name);
    }
}
