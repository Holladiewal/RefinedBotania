package me.beepbeat.RefinedBotania.stuff;

import com.raoulvdberge.refinedstorage.apiimpl.network.node.NetworkNodeCrafter;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipeRuneAltar;
import vazkii.botania.common.crafting.recipe.HeadRecipe;
import vazkii.botania.common.item.ModItems;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by beepbeat/holladiewal on 30.05.2017.
 */
public class NodeRuneCrafter extends NetworkNodeCrafter {
    public NodeRuneCrafter(World world, BlockPos pos) {
        super(world, pos);
    }

    private boolean firstTick = true;
    private List<RecipeRuneAltar> actualRecipes = new ArrayList<>();

    @Override
    public void update() {
        //super.update();
        if (firstTick){rebuildPatterns();}
        firstTick = false;

        if (isTriggeredAutocrafting()  && this.network != null){
            for (RecipeRuneAltar rec : actualRecipes) {
                System.out.println(this.network.getCraftingManager().schedule(rec.getOutput(), rec.getOutput().getCount(), 3).isValid());
            }
        }

    }

    private void rebuildPatterns() {
        List<RecipeRuneAltar> recipes = BotaniaAPI.runeAltarRecipes;
        for (RecipeRuneAltar rec : recipes) {
            if (rec instanceof HeadRecipe) continue;
            if (rec.getOutput().isItemEqual(new ItemStack(ModItems.rune, 1, 3))) {

                List<Object> tmp = new ArrayList<Object>();
                for (Object obj : rec.getInputs()) {
                    if (obj instanceof ItemStack && ((ItemStack) obj).getItem() == Item.getItemFromBlock(Blocks.CARPET)) {
                        tmp.add(new ItemStack(Blocks.CARPET, 1, 0));
                    } else {
                        tmp.add(obj);
                    }
                }
                actualRecipes.add(new RecipeRuneAltar(rec.getOutput(), rec.getManaUsage(), tmp.toArray()));
                continue;
            }

            if (rec.getOutput().isItemEqual(new ItemStack(ModItems.rune, 1, 7))) {
                List<Object> tmp = new ArrayList<Object>();
                for (Object obj : rec.getInputs()) {
                    if (obj instanceof ItemStack && ((ItemStack) obj).getItem() == Item.getItemFromBlock(Blocks.WOOL)) {
                        tmp.add(new ItemStack(Blocks.WOOL, 1, 0));
                    } else {
                        tmp.add(obj);
                    }
                }
                actualRecipes.add(new RecipeRuneAltar(rec.getOutput(), rec.getManaUsage(), tmp));
                continue;
            }
            actualRecipes.add(rec);
        }
    }
}
