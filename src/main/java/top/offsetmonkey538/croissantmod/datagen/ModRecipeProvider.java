package top.offsetmonkey538.croissantmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import top.offsetmonkey538.croissantmod.init.ModItems;

import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.CROISSANT)
                .input('w', Items.WHEAT)
                .criterion("has_wheat", RecipeProvider.conditionsFromItem(Items.WHEAT))
                .pattern(" ww")
                .pattern("ww ")
                .pattern("w  ")
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.ICE_CROISSANT)
                .input('i', Items.ICE)
                .input('c', ModItems.CROISSANT)
                .criterion("has_croissant", RecipeProvider.conditionsFromItem(ModItems.CROISSANT))
                .pattern("iii")
                .pattern("ici")
                .pattern("iii")
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.INFERNAL_CROISSANT)
                .input('i', Items.BLUE_ICE)
                .input('c', ModItems.CROISSANT)
                .input('l', Items.LAVA_BUCKET)
                .criterion("has_croissant", RecipeProvider.conditionsFromItem(ModItems.CROISSANT))
                .pattern("lll")
                .pattern("ici")
                .pattern("iii")
                .offerTo(exporter);
    }
}
