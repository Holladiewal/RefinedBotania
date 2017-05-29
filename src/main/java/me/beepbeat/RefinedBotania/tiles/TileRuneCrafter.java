package me.beepbeat.RefinedBotania.tiles;

import com.raoulvdberge.refinedstorage.apiimpl.network.node.NetworkNodeCrafter;
import com.raoulvdberge.refinedstorage.tile.TileNode;
import com.raoulvdberge.refinedstorage.tile.data.TileDataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * Created by beepbeat/holladiewal on 28.05.2017.
 */
public class TileRuneCrafter extends TileNode<NetworkNodeCrafter> {
    public static final TileDataParameter TRIGGERED_AUTOCRAFTING;

    public TileRuneCrafter() {
        this.dataManager.addWatchedParameter(TRIGGERED_AUTOCRAFTING);
    }

    @Nonnull
    public NetworkNodeCrafter createNode(World world, BlockPos pos) {
        return new NetworkNodeCrafter(world, pos);
    }

    static {
        TRIGGERED_AUTOCRAFTING = new TileDataParameter(DataSerializers.BOOLEAN, Boolean.valueOf(false), tile -> ((TileRuneCrafter)tile).getNode().isTriggeredAutocrafting(), (tile, value) -> {
            ((TileRuneCrafter)tile).getNode().setTriggeredAutocrafting((boolean)value);
            ((TileRuneCrafter)tile).getNode().markDirty();
        });
    }
}
