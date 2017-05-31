package me.beepbeat.RefinedBotania.stuff;

import com.raoulvdberge.refinedstorage.apiimpl.network.node.NetworkNodeCrafter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipeRuneAltar;

import java.util.List;

/**
 * Created by beepbeat/holladiewal on 30.05.2017.
 */
public class NodeRuneCrafter extends NetworkNodeCrafter {
    public NodeRuneCrafter(World world, BlockPos pos) {
        super(world, pos);
    }

    @Override
    public void update() {
        super.update();
        List<RecipeRuneAltar> recipes = BotaniaAPI.runeAltarRecipes;

    }
}
