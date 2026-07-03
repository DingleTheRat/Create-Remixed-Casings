package net.dingletherat.remixed_casings;

import org.checkerframework.checker.units.qual.s;

import com.drmangotea.tfmg.TFMG;
import com.drmangotea.tfmg.registry.TFMGBlocks;
import com.simibubi.create.content.decoration.encasing.CasingBlock;
import com.simibubi.create.foundation.block.connected.AllCTTypes;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.CTSpriteShifter;

import fr.iglee42.createcasing.casings.CasingSet;
import fr.iglee42.createcasing.casings.CasingSets;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModList;

public class RemixedCasings {
    // TFMG Casings
    public static CTSpriteShiftEntry ALUMINUM_SHIFT;
    public static CasingSet ALUMINUM;

    public static CTSpriteShiftEntry STEEL_SHIFT;
    public static CasingSet STEEL;

    // Andesite Casing Shifts
    public static final CTSpriteShiftEntry ANDESITE_DARK_OAK_SHIFT = CTSpriteShifter.getCT(AllCTTypes.OMNIDIRECTIONAL, ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/andesite/dark_oak"), ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/andesite/dark_oak_connected"));
    public static final CTSpriteShiftEntry ANDESITE_TRAIN_SHIFT = CTSpriteShifter.getCT(AllCTTypes.OMNIDIRECTIONAL, ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/andesite/train"), ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/andesite/train_connected"));

    // Andesite Casing Sets
    public static final CasingSet ANDESITE_DARK_OAK = CasingSets.register("andesite_dark_oak", new CasingSet.Options().ctSprite(() -> ANDESITE_DARK_OAK_SHIFT).casing());
    public static final CasingSet ANDESITE_TRAIN = CasingSets.register("andesite_train", new CasingSet.Options().ctSprite(() -> ANDESITE_TRAIN_SHIFT).casing());

    // Brass Casing Shifts
    public static final CTSpriteShiftEntry BRASS_SPRUCE_SHIFT = CTSpriteShifter.getCT(AllCTTypes.OMNIDIRECTIONAL, ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/brass/spruce"), ResourceLocation.fromNamespaceAndPath("remixed_casings", "block/brass/spruce_connected"));

    // Brass Casing Sets
    public static final CasingSet BRASS_SPRUCE = CasingSets.register("brass_spruce", new CasingSet.Options().ctSprite(() -> BRASS_SPRUCE_SHIFT).casing());

    public static void register() {
        if (ModList.get().isLoaded(TFMG.MOD_ID)) {
            ALUMINUM_SHIFT = CTSpriteShifter.getCT(
                AllCTTypes.OMNIDIRECTIONAL,
                ResourceLocation.fromNamespaceAndPath("tfmg", "block/industrial_aluminum_casing"),
                ResourceLocation.fromNamespaceAndPath("tfmg", "block/industrial_aluminum_casing_connected")
            );
            ALUMINUM = CasingSets.register("aluminum", new CasingSet.Options()
                .existingCasing(() -> TFMGBlocks.ALUMINUM_CASING.get())
                .ctSprite(() -> ALUMINUM_SHIFT)
                .shaft()
                .cogwheel(() -> ALUMINUM_SHIFT, () -> ALUMINUM_SHIFT)
                .largeCogwheel()
            );
            STEEL_SHIFT = CTSpriteShifter.getCT(
                AllCTTypes.OMNIDIRECTIONAL,
                ResourceLocation.fromNamespaceAndPath("tfmg", "block/steel_casing"),
                ResourceLocation.fromNamespaceAndPath("tfmg", "block/steel_casing_connected")
            );
            STEEL = CasingSets.register("steel", new CasingSet.Options()
                .existingCasing(() -> TFMGBlocks.STEEL_CASING.get())
                .ctSprite(() -> STEEL_SHIFT)
                .shaft()
                .cogwheel(() -> STEEL_SHIFT, () -> STEEL_SHIFT)
                .largeCogwheel()
            );
        }
    }
}
