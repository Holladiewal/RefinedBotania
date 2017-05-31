package me.beepbeat.RefinedBotania.blocks;

import com.raoulvdberge.refinedstorage.block.BlockNode;
import com.raoulvdberge.refinedstorage.block.Direction;
import me.beepbeat.RefinedBotania.tiles.TileRuneCrafter;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockRuneCrafter extends BlockNode {
    public BlockRuneCrafter() {
        super("runecrafter");
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileRuneCrafter();
    }

    @Override
    public Direction getDirection() {
        return Direction.ANY_FACE_PLAYER;
    }

    @Override
    public boolean hasConnectivityState() {
        return true;
    }


}
