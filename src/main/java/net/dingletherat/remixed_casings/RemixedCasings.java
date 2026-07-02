package net.dingletherat.remixed_casings;

import com.drmangotea.tfmg.TFMG;
import com.drmangotea.tfmg.registry.TFMGBlocks;
import com.simibubi.create.foundation.block.connected.AllCTTypes;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.CTSpriteShifter;

import fr.iglee42.createcasing.casings.CasingSet;
import fr.iglee42.createcasing.casings.CasingSets;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModList;

public class RemixedCasings {
    public static CTSpriteShiftEntry ALUMINUM_SHIFT;
    public static CasingSet ALUMINUM;

    public static CTSpriteShiftEntry STEEL_SHIFT;
    public static CasingSet STEEL;

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
