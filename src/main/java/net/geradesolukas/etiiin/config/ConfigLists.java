package net.geradesolukas.etiiin.config;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class ConfigLists {
    private static final String MCID = "minecraft:";
    public static final List<String> FLEXITARIAN = ImmutableList.of(MCID + "chicken",MCID + "cooked_chicken",MCID + "porkchop",
            MCID + "cooked_porkchop",MCID + "beef",MCID + "cooked_beef",MCID + "mutton",MCID + "cooked_mutton",MCID + "rabbit",
            MCID + "cooked_rabbit",MCID + "rabbit_stew",MCID + "rotten_flesh",MCID + "spider_eye");
    public static final List<String> PESCETARIAN = ImmutableList.of(MCID + "salmon",MCID + "cooked_salmon",MCID + "cod",
            MCID + "cooked_cod",MCID + "tropical_fish",MCID + "pufferfish");
    public static final List<String> VEGETARIAN = ImmutableList.of(MCID + "honey_bottle",MCID + "cake",MCID + "pumpkin_pie",
            MCID + "milk_bucket",MCID + "egg");
    public static final List<String> VEGAN = ImmutableList.of(MCID + "apple",MCID + "mushroom_stew",MCID + "bread",
            MCID + "golden_apple",MCID + "enchanted_golden_apple",MCID + "dried_kelp",MCID + "melon_slice",MCID + "cookie",MCID + "sweet_berries",
            MCID + "carrot",MCID + "golden_carrot",MCID + "potato",MCID + "baked_potato",MCID + "poisonous_potato",MCID + "beetroot",
            MCID + "beetroot_soup",MCID + "suspicious_stew",MCID + "wheat",MCID + "sugar",MCID + "cocoa_beans",MCID + "brown_mushroom",MCID + "red_mushroom");
    public static final List<String> NOT_HALAL = ImmutableList.of(MCID + "porkchop",MCID + "cooked_porkchop");
    public static final List<String> BEEHIVES = ImmutableList.of(MCID + "bee_nest",MCID + "beehive");
    public static final List<String> EFFECT_FOOD = ImmutableList.of(MCID + "golden_apple",MCID + "enchanted_golden_apple",MCID + "rotten_flesh",
            MCID + "spider_eye",MCID + "pufferfish",MCID + "chicken",MCID + "poisonous_potato");
}
