package me.beepbeat.RefinedBotania;

import com.raoulvdberge.refinedstorage.RS;
import com.raoulvdberge.refinedstorage.api.network.node.INetworkNode;
import com.raoulvdberge.refinedstorage.api.network.node.INetworkNodeFactory;
import com.raoulvdberge.refinedstorage.apiimpl.API;
import com.raoulvdberge.refinedstorage.apiimpl.network.node.NetworkNode;
import com.raoulvdberge.refinedstorage.block.BlockBase;
import com.raoulvdberge.refinedstorage.tile.TileBase;
import com.raoulvdberge.refinedstorage.tile.TileNode;
import com.raoulvdberge.refinedstorage.tile.data.TileDataManager;
import me.beepbeat.RefinedBotania.blocks.BlockRuneCrafter;
import me.beepbeat.RefinedBotania.tiles.TileRuneCrafter;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nonnull;

/**
 * Created by beepbeat/holladiewal on 28.05.2017.
 */
@Mod(modid = "refbot", name = "Refined Botania")
public class RefinedBotania {
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        //TODO register Blocks and Tiles
        registerBlock(new BlockRuneCrafter());
        registerTile(TileRuneCrafter.class, "refbot" + "tileRuneCrafter");
    }

    //copied from RefinedStorage CommonProxy
    private void registerTile(Class<? extends TileBase> tile, String id) {
        GameRegistry.registerTileEntity(tile, "refinedstorage:" + id);

        try {
            TileBase e = (TileBase)tile.newInstance();
            if(e instanceof TileNode) {
                String nodeId = ((TileNode)e).createNode((World)null, (BlockPos)null).getId();
                API.instance().getNetworkNodeRegistry().add(nodeId, new INetworkNodeFactory() {
                    @Nonnull
                    @Override
                    public INetworkNode create(NBTTagCompound tag, World world, BlockPos pos) {
                        NetworkNode node = ((TileNode) e).createNode(world, pos);
                        node.read(tag);
                        return node;
                    }
                });
            }

            e.getDataManager().getParameters().forEach(TileDataManager::registerParameter);
        } catch (IllegalAccessException | InstantiationException var5) {
            var5.printStackTrace();
        }

    }
    private void registerBlock(BlockBase block) {
        GameRegistry.register(block);
        GameRegistry.register(block.createItem());
    }


}
