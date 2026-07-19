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
            createEncasedCogwheel("encased_cogwheel", RemixedCasings.ALUMINUM.getCogwheel(), aluminumTexture, aluminumGearbox, aluminumEncasedCogwheelSide);
            createEncasedCogwheel("encased_large_cogwheel", RemixedCasings.ALUMINUM.getLargeCogwheel(), aluminumTexture, aluminumGearbox, aluminumEncasedCogwheelSide);

            createEncasedShaft(RemixedCasings.STEEL.getShaft(), steelTexture, steelGearboxTexture);
            createEncasedCogwheel("encased_cogwheel", RemixedCasings.STEEL.getCogwheel(), steelTexture, steelGearboxTexture, steelEncasedCogwheelSide);
            createEncasedCogwheel("encased_large_cogwheel", RemixedCasings.STEEL.getLargeCogwheel(), steelTexture,steelGearboxTexture, steelEncasedCogwheelSide);
        }

        // Andesite
        ResourceLocation andesiteDarkOak = ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/andesite/dark_oak");
        ResourceLocation andesiteDarkOakShaft = ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/andesite/dark_oak_shaft");
        ResourceLocation andesiteDarkOakCogwheel = ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/andesite/dark_oak_cogwheel");
        simpleBlockWithItem(RemixedCasings.ANDESITE_DARK_OAK.getCasing(), models().cubeAll(name(RemixedCasings.ANDESITE_DARK_OAK.getCasing()), andesiteDarkOak));
        createEncasedShaft(RemixedCasings.ANDESITE_DARK_OAK.getShaft(), andesiteDarkOak, andesiteDarkOakShaft);
        createEncasedCogwheel("encased_cogwheel", RemixedCasings.ANDESITE_DARK_OAK.getCogwheel(), andesiteDarkOak, andesiteDarkOakShaft, andesiteDarkOakCogwheel);
        createEncasedCogwheel("encased_large_cogwheel", RemixedCasings.ANDESITE_DARK_OAK.getLargeCogwheel(), andesiteDarkOak, andesiteDarkOakShaft, andesiteDarkOakCogwheel);

        ResourceLocation andesiteTrain = ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/andesite/train");
        ResourceLocation andesiteTrainShaft = ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/andesite/train_shaft");
        ResourceLocation andesiteTrainCogwheel = ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/andesite/train_cogwheel");
        simpleBlockWithItem(RemixedCasings.ANDESITE_TRAIN.getCasing(), models().cubeAll(name(RemixedCasings.ANDESITE_TRAIN.getCasing()), andesiteTrain));
        createEncasedShaft(RemixedCasings.ANDESITE_TRAIN.getShaft(), andesiteTrain, andesiteTrainShaft);
        createEncasedCogwheel("encased_cogwheel", RemixedCasings.ANDESITE_TRAIN.getCogwheel(), andesiteTrain, andesiteTrainShaft, andesiteTrainCogwheel);
        createEncasedCogwheel("encased_large_cogwheel", RemixedCasings.ANDESITE_TRAIN.getLargeCogwheel(), andesiteTrain, andesiteTrainShaft, andesiteTrainCogwheel);

        ResourceLocation andesiteAcaciaBark = ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/andesite/acacia_bark");
        ResourceLocation andesiteAcaciaBarkShaft = ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/andesite/acacia_bark_shaft");
        ResourceLocation andesiteAcaciaBarkCogwheel = ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/andesite/acacia_bark_cogwheel");
        simpleBlockWithItem(RemixedCasings.ANDESITE_ACACIA_BARK.getCasing(), models().cubeAll(name(RemixedCasings.ANDESITE_ACACIA_BARK.getCasing()), andesiteAcaciaBark));
        createEncasedShaft(RemixedCasings.ANDESITE_ACACIA_BARK.getShaft(), andesiteAcaciaBark, andesiteAcaciaBarkShaft);
        createEncasedCogwheel("encased_cogwheel", RemixedCasings.ANDESITE_ACACIA_BARK.getCogwheel(), andesiteAcaciaBark, andesiteAcaciaBarkShaft, andesiteAcaciaBarkCogwheel);
        createEncasedCogwheel("encased_large_cogwheel", RemixedCasings.ANDESITE_ACACIA_BARK.getLargeCogwheel(), andesiteAcaciaBark, andesiteAcaciaBarkShaft, andesiteAcaciaBarkCogwheel);

        // Brass
        ResourceLocation brassSpruce = ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/brass/spruce");
        ResourceLocation brassSpruceShaft = ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/brass/spruce_shaft");
        ResourceLocation brassSpruceCogwheel = ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/brass/spruce_cogwheel");
        simpleBlockWithItem(RemixedCasings.BRASS_SPRUCE.getCasing(), models().cubeAll(name(RemixedCasings.BRASS_SPRUCE.getCasing()), brassSpruce));
        createEncasedShaft(RemixedCasings.BRASS_SPRUCE.getShaft(), brassSpruce, brassSpruceShaft);
        createEncasedCogwheel("encased_cogwheel", RemixedCasings.BRASS_SPRUCE.getCogwheel(), brassSpruce, brassSpruceShaft, brassSpruceCogwheel);
        createEncasedCogwheel("encased_large_cogwheel", RemixedCasings.BRASS_SPRUCE.getLargeCogwheel(), brassSpruce, brassSpruceShaft, brassSpruceCogwheel);

        ResourceLocation brassAcaciaBark = ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/brass/acacia_bark");
        ResourceLocation brassAcaciaBarkShaft = ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/brass/acacia_bark_shaft");
        ResourceLocation brassAcaciaBarkCogwheel = ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/brass/acacia_bark_cogwheel");
        simpleBlockWithItem(RemixedCasings.BRASS_ACACIA_BARK.getCasing(), models().cubeAll(name(RemixedCasings.BRASS_ACACIA_BARK.getCasing()), brassAcaciaBark));
        createEncasedShaft(RemixedCasings.BRASS_ACACIA_BARK.getShaft(), brassAcaciaBark, brassAcaciaBarkShaft);
        createEncasedCogwheel("encased_cogwheel", RemixedCasings.BRASS_ACACIA_BARK.getCogwheel(), brassAcaciaBark, brassAcaciaBarkShaft, brassAcaciaBarkCogwheel);
        createEncasedCogwheel("encased_large_cogwheel", RemixedCasings.BRASS_ACACIA_BARK.getLargeCogwheel(), brassAcaciaBark, brassAcaciaBarkShaft, brassAcaciaBarkCogwheel);

        // Copper
        ResourceLocation copperDarkOak = ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/copper/dark_oak");
        ResourceLocation copperDarkOakShaft = ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/copper/dark_oak_shaft");
        ResourceLocation copperDarkOakCogwheel = ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/copper/dark_oak_cogwheel");
        simpleBlockWithItem(RemixedCasings.COPPER_DARK_OAK.getCasing(), models().cubeAll(name(RemixedCasings.COPPER_DARK_OAK.getCasing()), copperDarkOak));
        createEncasedShaft(RemixedCasings.COPPER_DARK_OAK.getShaft(), copperDarkOak, copperDarkOakShaft);
        createEncasedCogwheel("encased_cogwheel", RemixedCasings.COPPER_DARK_OAK.getCogwheel(), copperDarkOak, copperDarkOakShaft, copperDarkOakCogwheel);
        createEncasedCogwheel("encased_large_cogwheel", RemixedCasings.COPPER_DARK_OAK.getLargeCogwheel(), copperDarkOak, copperDarkOakShaft, copperDarkOakCogwheel);

        ResourceLocation copperSpruce = ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/copper/spruce");
        ResourceLocation copperSpruceShaft = ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/copper/spruce_shaft");
        ResourceLocation copperSpruceCogwheel = ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/copper/spruce_cogwheel");
        simpleBlockWithItem(RemixedCasings.COPPER_SPRUCE.getCasing(), models().cubeAll(name(RemixedCasings.COPPER_SPRUCE.getCasing()), copperSpruce));
        createEncasedShaft(RemixedCasings.COPPER_SPRUCE.getShaft(), copperSpruce, copperSpruceShaft);
        createEncasedCogwheel("encased_cogwheel", RemixedCasings.COPPER_SPRUCE.getCogwheel(), copperSpruce, copperSpruceShaft, copperSpruceCogwheel);
        createEncasedCogwheel("encased_large_cogwheel", RemixedCasings.COPPER_SPRUCE.getLargeCogwheel(), copperSpruce, copperSpruceShaft, copperSpruceCogwheel);

        ResourceLocation copperTrain = ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/copper/train");
        ResourceLocation copperTrainShaft = ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/copper/train_shaft");
        ResourceLocation copperTrainCogwheel = ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/copper/train_cogwheel");
        simpleBlockWithItem(RemixedCasings.COPPER_TRAIN.getCasing(), models().cubeAll(name(RemixedCasings.COPPER_TRAIN.getCasing()), copperTrain));
        createEncasedShaft(RemixedCasings.COPPER_TRAIN.getShaft(), copperTrain, copperTrainShaft);
        createEncasedCogwheel("encased_cogwheel", RemixedCasings.COPPER_TRAIN.getCogwheel(), copperTrain, copperTrainShaft, copperTrainCogwheel);
        createEncasedCogwheel("encased_large_cogwheel", RemixedCasings.COPPER_TRAIN.getLargeCogwheel(), copperTrain, copperTrainShaft, copperTrainCogwheel);
    }

    public String name(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

    public void createEncasedShaft(Block block, ResourceLocation casing, ResourceLocation shaft) {
        String name = BuiltInRegistries.BLOCK.getKey(block).getPath();
        BlockModelBuilder model = models().getBuilder("block/" + name)
            .parent(new ModelFile.UncheckedModelFile(ResourceLocation.fromNamespaceAndPath("create", "block/encased_shaft/block")))
            .texture("casing", casing.toString())
            .texture("opening", shaft.toString())
            .texture("particle", casing.toString());
        axisBlock(block, model);
    }

    public void createEncasedCogwheel(String type, Block block, ResourceLocation casing, ResourceLocation shaft, ResourceLocation cogwheel) {
        String name = BuiltInRegistries.BLOCK.getKey(block).getPath();
       BlockModelBuilder model = models().getBuilder("block/" + name)
            .parent(new ModelFile.UncheckedModelFile(ResourceLocation.fromNamespaceAndPath("tfmg", "block/" + type + "/block")))
            .texture("1", casing.toString())
            .texture("4", shaft.toString())
            .texture("casing", casing.toString())
            .texture("particle", casing.toString())
            .texture("side", cogwheel.toString());

        BlockModelBuilder modelTop = models().getBuilder("block/" + name + "_top")
            .parent(new ModelFile.UncheckedModelFile(ResourceLocation.fromNamespaceAndPath("tfmg", "block/" + type + "/block_top")))
            .texture("1", casing.toString())
            .texture("4", shaft.toString())
            .texture("casing", casing.toString())
            .texture("particle", casing.toString())
            .texture("side", cogwheel.toString());

        BlockModelBuilder modelBottom = models().getBuilder("block/" + name + "_bottom")
            .parent(new ModelFile.UncheckedModelFile(ResourceLocation.fromNamespaceAndPath("tfmg", "block/" + type + "/block_bottom")))
            .texture("1", casing.toString())
            .texture("4", shaft.toString())
            .texture("casing", casing.toString())
            .texture("particle", casing.toString())
            .texture("side", cogwheel.toString());

        BlockModelBuilder modelTopBottom = models().getBuilder("block/" + name + "_top_bottom")
            .parent(new ModelFile.UncheckedModelFile(ResourceLocation.fromNamespaceAndPath("tfmg", "block/" + type + "/block_top_bottom")))
            .texture("1", casing.toString())
            .texture("4", shaft.toString())
            .texture("casing", casing.toString())
            .texture("particle", casing.toString())
            .texture("side", cogwheel.toString());
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
