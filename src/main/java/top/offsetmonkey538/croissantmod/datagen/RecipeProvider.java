package top.offsetmonkey538.croissantmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.VanillaRecipeProvider;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import top.offsetmonkey538.croissantmod.init.ModItems;

import java.util.function.Consumer;

public class RecipeProvider extends FabricRecipeProvider {
    public RecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.CROISSANT)
                .input('w', Items.WHEAT)
                .criterion("has_wheat", VanillaRecipeProvider.conditionsFromItem(Items.WHEAT))
                .pattern(" ww")
                .pattern("ww ")
                .pattern("w  ")
                .offerTo(exporter);
    }
}
