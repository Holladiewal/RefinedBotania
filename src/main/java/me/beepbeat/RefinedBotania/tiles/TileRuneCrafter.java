package me.beepbeat.RefinedBotania.tiles;

import com.raoulvdberge.refinedstorage.apiimpl.network.node.NetworkNodeCrafter;
import com.raoulvdberge.refinedstorage.tile.TileNode;
import com.raoulvdberge.refinedstorage.tile.data.ITileDataConsumer;
import com.raoulvdberge.refinedstorage.tile.data.ITileDataProducer;
import com.raoulvdberge.refinedstorage.tile.data.TileDataParameter;
import me.beepbeat.RefinedBotania.stuff.NodeRuneCrafter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import vazkii.botania.api.mana.IManaReceiver;

import javax.annotation.Nonnull;

/**
 * Created by beepbeat/holladiewal on 28.05.2017.
 */
public class TileRuneCrafter extends TileNode<NetworkNodeCrafter> implements IManaReceiver {
    public static final TileDataParameter TRIGGERED_AUTOCRAFTING;
    public int maxMana = 5000, currentMana = 0;

    public TileRuneCrafter() {
        this.dataManager.addWatchedParameter(TRIGGERED_AUTOCRAFTING);
    }

    @Nonnull
    public NetworkNodeCrafter createNode(World world, BlockPos pos) {
        return new NodeRuneCrafter(world, pos);
    }

    static {
        TRIGGERED_AUTOCRAFTING = new TileDataParameter(DataSerializers.BOOLEAN, Boolean.valueOf(false), new ITileDataProducer() {
            @Override
            public Object getValue(TileEntity tile) {
                return ((TileRuneCrafter) tile).getNode().isTriggeredAutocrafting();
            }
        }, new ITileDataConsumer() {
            @Override
            public void setValue(TileEntity tile, Object value) {
                ((TileRuneCrafter) tile).getNode().setTriggeredAutocrafting((boolean) value);
                ((TileRuneCrafter) tile).getNode().markDirty();
            }
        });
    }


    @Override
    public boolean isFull() {
        return getCurrentMana() >= maxMana;
    }

    @Override
    public void recieveMana(int i) {
        currentMana += i;
    }

    @Override
    public boolean canRecieveManaFromBursts() {
        return true;
    }

    @Override
    public int getCurrentMana() {
        return currentMana;
    }

    public boolean useMana(int i){
        if (i > getCurrentMana()) return false;
        currentMana -= i;
        return true;
    }
}
