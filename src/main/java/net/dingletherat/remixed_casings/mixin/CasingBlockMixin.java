package net.dingletherat.remixed_casings.mixin;

import org.spongepowered.asm.mixin.Mixin;

import com.simibubi.create.content.decoration.encasing.CasingBlock;

import net.dingletherat.remixed_casings.CreateRemixedCasings;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

@Mixin(CasingBlock.class)
public class CasingBlockMixin extends Block {
    public CasingBlockMixin(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos position, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.isClientSide()) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        if (!CreateRemixedCasings.CASING_CONVERSION_MAP.containsKey(itemStack.getItem())) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        if (!CreateRemixedCasings.CASING_CONVERSION_MAP.containsValue(state.getBlock())) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        level.setBlockAndUpdate(position, CreateRemixedCasings.CASING_CONVERSION_MAP.get(itemStack.getItem()).defaultBlockState());
        if (!player.isCreative()) itemStack.shrink(1);

        return ItemInteractionResult.SUCCESS;
    }
}
