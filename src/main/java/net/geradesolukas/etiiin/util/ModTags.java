package net.geradesolukas.etiiin.util;

import net.geradesolukas.etiiin.Etiiin;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class ModTags {

    public static class Blocks {

        private static Tags.IOptionalNamedTag<Block> createTag(String name) {
            return BlockTags.createOptional(new ResourceLocation(Etiiin.MOD_ID, name));
        }

        private static Tags.IOptionalNamedTag<Block> createForgeTag(String name) {
            return BlockTags.createOptional(new ResourceLocation("forge", name));
        }

    }

    public static class Items {

        public static final Tags.IOptionalNamedTag<Item> FOOD_ALL =
                createTag("food_all");

        public static final Tags.IOptionalNamedTag<Item> FLEXITARIAN =
                createTag("food_flexitarian");

        public static final Tags.IOptionalNamedTag<Item> VEGETARIAN =
                createTag("food_vegetarian");

        public static final Tags.IOptionalNamedTag<Item> VEGAN =
                createTag("food_vegan");

        public static final Tags.IOptionalNamedTag<Item> PESCETARIAN =
                createTag("food_pescetarian");

        public static final Tags.IOptionalNamedTag<Item> NOT_HALAL =
                createTag("food_not_halal");

        public static final Tags.IOptionalNamedTag<Item> BEEHIVES =
                createTag("beehives");
        public static final Tags.IOptionalNamedTag<Item> FOOD_WITH_EFFECTS =
                createTag("food_with_effects");


        private static Tags.IOptionalNamedTag<Item> createTag(String name) {
            return ItemTags.createOptional(new ResourceLocation(Etiiin.MOD_ID, name));
        }

        private static Tags.IOptionalNamedTag<Item> createForgeTag(String name) {
            return ItemTags.createOptional(new ResourceLocation("forge", name));
        }

    }
}
