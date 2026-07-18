package net.dingletherat.remixed_casings.mixin;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.content.decoration.encasing.CasingBlock;
import com.simibubi.create.foundation.advancement.AllAdvancements;

import net.dingletherat.remixed_casings.CreateRemixedCasings;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
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
        Block block = state.getBlock();
        Map<Item, Block> mapEntry = CreateRemixedCasings.CASING_CONVERSION_MAP.getOrDefault(block, null);

        if (level.isClientSide()) return ItemInteractionResult.CONSUME;
        if (mapEntry == null) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        if (!mapEntry.containsKey(itemStack.getItem())) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        if (mapEntry.get(itemStack.getItem()) == block) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        BlockState newState = mapEntry.get(itemStack.getItem()).defaultBlockState();
        level.setBlockAndUpdate(position, newState);
        level.levelEvent(null, 2001, position, Block.getId(state)); // Makes particle, but better than anything I can do with generateParticles()

        if (itemStack.getItem() == AllItems.STURDY_SHEET.get()) AllAdvancements.TRAIN_CASING.awardTo(player);

        if (!player.isCreative()) itemStack.shrink(1);
        return ItemInteractionResult.SUCCESS;
    }
}
