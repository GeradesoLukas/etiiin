package net.geradesolukas.etiiin.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class EtiiinConfig {


    public static class Client {
        public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
        public static final ForgeConfigSpec SPEC;

        public enum DietDisplayType { ICON, TEXT, NONE }

        public static final ForgeConfigSpec.ConfigValue<Boolean> etiin_info_tooltips;

        public static final ForgeConfigSpec.ConfigValue<Boolean> food_info_tooltips;
        public static final ForgeConfigSpec.ConfigValue<Boolean> flexitarian_info_tooltip;
        public static final ForgeConfigSpec.ConfigValue<Boolean> pescetarian_info_tooltip;
        public static final ForgeConfigSpec.ConfigValue<Boolean> vegetarian_info_tooltip;
        public static final ForgeConfigSpec.ConfigValue<Boolean> vegan_info_tooltip;
        public static final ForgeConfigSpec.ConfigValue<Boolean> halal_info_tooltip;
        public static final ForgeConfigSpec.ConfigValue<Boolean> nutrition_info_tooltip;
        public static final ForgeConfigSpec.ConfigValue<Boolean> saturation_info_tooltip;
        public static final ForgeConfigSpec.ConfigValue<Boolean> effect_info_tooltip;
        public static final ForgeConfigSpec.EnumValue<DietDisplayType> diet_info_tooltip_mode;

        public static final ForgeConfigSpec.ConfigValue<Boolean> fuel_info_tooltips;
        public static final ForgeConfigSpec.ConfigValue<Boolean> ipf_info_tooltips;
        public static final ForgeConfigSpec.ConfigValue<Boolean> furnace_info_tooltips;

        public static final ForgeConfigSpec.ConfigValue<Boolean> gear_info_tooltips;
        public static final ForgeConfigSpec.ConfigValue<Boolean> durability_info_tooltips;
        public static final ForgeConfigSpec.ConfigValue<Boolean> repaircost_info_tooltips;
        public static final ForgeConfigSpec.ConfigValue<Boolean> harvestlevel_info_tooltips;

        public static final ForgeConfigSpec.ConfigValue<Boolean> beehive_info_tooltips;

        public static final ForgeConfigSpec.ConfigValue<Boolean> lightlevel_info_tooltips;

        static {
            BUILDER.comment("General Configuration").push("common");
            etiin_info_tooltips = BUILDER.comment("This Option will allow you to disable all tooltips the Mod adds").define("Enable Etiiin Tooltips",true);



            BUILDER.comment("Food Configuration").push("food");

            food_info_tooltips = BUILDER.comment("This Option will allow you to disable all food-related tooltips the mod adds").define("Enable Food Tooltips",true);
            diet_info_tooltip_mode = BUILDER.comment("This Option will change how and if the Diet Tooltips are displayed",
                            "ICON: Displays the diets as icons.",
                            "TEXT: Displays the diets as text.",
                            "NONE: Displays no diet info.")
                    .defineEnum("Diet Display Type", DietDisplayType.ICON);
            flexitarian_info_tooltip = BUILDER.comment("This Option will allow you to the \"Flexitarian\" Tooltip").define("Enable Flexitarian Tooltip",true);
            pescetarian_info_tooltip = BUILDER.comment("This Option will allow you to the \"Pescetarian\" Tooltip").define("Enable Pescetarian Tooltip",true);
            vegetarian_info_tooltip = BUILDER.comment("This Option will allow you to the \"Vegetarian\" Tooltip").define("Enable Vegetarian Tooltip",true);
            vegan_info_tooltip = BUILDER.comment("This Option will allow you to the \"Vegan\" Tooltip").define("Enable Vegan Tooltip",true);
            halal_info_tooltip = BUILDER.comment("This Option will allow you to the \"Halal\" Tooltip").define("Enable Halal Tooltip",true);
            nutrition_info_tooltip = BUILDER.comment("This Option will allow you to the \"Nutrition\" Tooltip").define("Enable Nutrition Tooltip",true);
            saturation_info_tooltip = BUILDER.comment("This Option will allow you to the \"Saturation\" Tooltip").define("Enable Saturation Tooltip",true);
            effect_info_tooltip = BUILDER.comment("This Option will allow you to the \"Saturation\" Tooltip").define("Enable Effect Tooltip",true);


            BUILDER.pop();


            BUILDER.comment("Fuel Configuration").push("fuel");
            fuel_info_tooltips = BUILDER.comment("This Option will allow you to disable fuel-related tooltips the mod adds").define("Enable Fuel Tooltips",true);
            ipf_info_tooltips = BUILDER.comment("This Option will allow you to disable \"Items per Fuel\" tooltips the mod adds").define("Enable Items Per Fuel Tooltips",true);
            furnace_info_tooltips = BUILDER.comment("This Option will allow you to disable \"Burn Speed\" tooltips the mod adds").define("Enable Burn Speed Tooltips",true);


            BUILDER.pop();

            BUILDER.comment("Gear Configuration").push("gear");
            gear_info_tooltips = BUILDER.comment("This Option will allow you to disable gear-related tooltips the mod adds").define("Enable Gear Tooltips",true);
            durability_info_tooltips = BUILDER.comment("This Option will allow you to the \"Durability\" Tooltip").define("Enable Durability Tooltips",true);
            repaircost_info_tooltips = BUILDER.comment("This Option will allow you to the \"Repair Cost\" Tooltip").define("Enable Repair Cost Tooltips",true);
            harvestlevel_info_tooltips = BUILDER.comment("This Option will allow you to the \"Harvest Level\" Tooltip").define("Enable Harvest Level Tooltips",true);


            BUILDER.pop();

            BUILDER.comment("Misc Configuration").push("misc");
            beehive_info_tooltips = BUILDER.comment("This Option will allow you to the \"Bee Hive\" Tooltips").define("Enable Bee Hive Tooltips",true);
            lightlevel_info_tooltips = BUILDER.comment("This Option will allow you to the \"Light Level\" Tooltips").define("Enable Light Level Tooltips",true);

            BUILDER.pop();



            SPEC = BUILDER.build();
        }
    }

    public static class Server {
        public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

        public static final ForgeConfigSpec SPEC;
        public static final ForgeConfigSpec.ConfigValue<List<? extends String>> flexitarian;
        public static final ForgeConfigSpec.ConfigValue<List<? extends String>> pescetarian;
        public static final ForgeConfigSpec.ConfigValue<List<? extends String>> vegetarian;
        public static final ForgeConfigSpec.ConfigValue<List<? extends String>> vegan;
        public static final ForgeConfigSpec.ConfigValue<List<? extends String>> not_halal;
        public static final ForgeConfigSpec.ConfigValue<List<? extends String>> beehives;
        public static final ForgeConfigSpec.ConfigValue<List<? extends String>> effect_food;


        static {
            BUILDER.comment("Food Lists").push("lists");
            flexitarian = BUILDER.comment("Flexitarian Food").defineList("flexitarian", ConfigLists.FLEXITARIAN, o -> o instanceof String);
            pescetarian = BUILDER.comment("Pescetarian Food").defineList("pescetarian", ConfigLists.PESCETARIAN, o -> o instanceof String);
            vegetarian = BUILDER.comment("Vegetarian Food").defineList("vegetarian", ConfigLists.VEGETARIAN, o -> o instanceof String);
            vegan = BUILDER.comment("Vegan Food").defineList("vegan", ConfigLists.VEGAN, o -> o instanceof String);
            not_halal = BUILDER.comment("Haram Food").defineList("not_halal", ConfigLists.NOT_HALAL, o -> o instanceof String);
            beehives = BUILDER.comment("Items considered Beehives").defineList("beehives", ConfigLists.BEEHIVES, o -> o instanceof String);
            effect_food = BUILDER.comment("Flexitarian Food").defineList("effect_foods", ConfigLists.EFFECT_FOOD, o -> o instanceof String);

            SPEC = BUILDER.build();
        }
    }

}
