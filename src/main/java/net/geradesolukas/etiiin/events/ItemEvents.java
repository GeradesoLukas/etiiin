package net.geradesolukas.etiiin.events;


import net.geradesolukas.etiiin.Etiiin;
import net.geradesolukas.etiiin.config.EtiiinConfig;
import net.geradesolukas.etiiin.util.BeeHiveUtils;
import net.geradesolukas.etiiin.util.ModTags;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.*;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import java.text.DecimalFormat;
import java.util.*;


@Mod.EventBusSubscriber(modid = Etiiin.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ItemEvents {


    ///tellraw @a {"text":"\uEaf2","font":"etiiin:default"}

    @SubscribeEvent
    public static void addItemToolTip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        Item item = stack.getItem();
        PlayerEntity player = event.getPlayer();
        Boolean config_tooltips_all = EtiiinConfig.etiin_info_tooltips.get();
        Boolean config_tooltips_food = EtiiinConfig.food_info_tooltips.get();
        Boolean config_tooltip_flexitarian = EtiiinConfig.flexitarian_info_tooltip.get();
        Boolean config_tooltip_pescetarian = EtiiinConfig.pescetarian_info_tooltip.get();
        Boolean config_tooltip_vegetarian = EtiiinConfig.vegetarian_info_tooltip.get();
        Boolean config_tooltip_vegan = EtiiinConfig.vegan_info_tooltip.get();
        Boolean config_tooltip_halal = EtiiinConfig.halal_info_tooltip.get();
        Boolean config_tooltip_nutrition = EtiiinConfig.nutrition_info_tooltip.get();
        Boolean config_tooltip_saturation = EtiiinConfig.saturation_info_tooltip.get();
        Boolean config_tooltip_effect = EtiiinConfig.effect_info_tooltip.get();
        Boolean config_tooltips_fuel = EtiiinConfig.fuel_info_tooltips.get();
        Boolean config_tooltips_ipf = EtiiinConfig.ipf_info_tooltips.get();
        Boolean config_tooltips_furnace = EtiiinConfig.furnace_info_tooltips.get();
        Boolean config_tooltips_gear = EtiiinConfig.gear_info_tooltips.get();
        Boolean config_tooltips_durability = EtiiinConfig.durability_info_tooltips.get();
        Boolean config_tooltips_repaircost = EtiiinConfig.repaircost_info_tooltips.get();
        Boolean config_tooltips_harvestlevel = EtiiinConfig.harvestlevel_info_tooltips.get();
        Boolean config_tooltips_bee_hives = EtiiinConfig.beehive_info_tooltips.get();


        double itemperfuel = ForgeHooks.getBurnTime(new ItemStack(item.getItem()), IRecipeType.SMELTING);
        itemperfuel /= 200;

        //int lightlevel = SimpleRegistry.BLOCK.;
                //get.getLightValue(new ItemStack(item.getItem()))

        double repaircost = stack.getRepairCost();
        double durability = stack.getMaxDamage();
        durability -= stack.getDamage();

        boolean canHarvestStone = stack.canHarvestBlock(Blocks.STONE.getDefaultState());
        boolean canHarvestIron = stack.canHarvestBlock(Blocks.IRON_ORE.getDefaultState());
        boolean canHarvestDiamond = stack.canHarvestBlock(Blocks.DIAMOND_ORE.getDefaultState());
        boolean canHarvestObsidian = stack.canHarvestBlock(Blocks.OBSIDIAN.getDefaultState());

        DecimalFormat doubleDecimalFormat = new DecimalFormat("#.##");

        List<ITextComponent> tooltip = new ArrayList<>();
        List<ITextComponent> full_tooltip = event.getToolTip();


        Style GRAY = Style.EMPTY.setColor(Color.fromTextFormatting(TextFormatting.GRAY));
        ResourceLocation ETIIINFONT = new ResourceLocation("etiiin", "default");
        Style NEWFONT = Style.EMPTY.setFontId(ETIIINFONT);
        Style DEFAULTFONT = Style.EMPTY.setFontId(Style.DEFAULT_FONT);
        String arrow = " ??7??? ";

        boolean is_gear_item = item instanceof TieredItem || item instanceof ArmorItem || item instanceof ShootableItem || item instanceof TridentItem || item instanceof ShieldItem || item instanceof FlintAndSteelItem;
        boolean is_smelter_item = stack.getItem() == Items.FURNACE || stack.getItem() == Items.BLAST_FURNACE || stack.getItem() == Items.SMOKER || stack.getItem() == Items.CAMPFIRE || stack.getItem() == Items.SOUL_CAMPFIRE;

        boolean is_etiiin_affected_item = AbstractFurnaceTileEntity.isFuel(stack) || item.isFood() || stack.getItem().isIn(ModTags.Items.FOOD_ALL) || is_gear_item || is_smelter_item || stack.getItem().isIn(ModTags.Items.BEEHIVES);







        if(config_tooltips_all && is_etiiin_affected_item) {
            if(Screen.hasShiftDown()) {
                tooltip.add(new TranslationTextComponent("tooltip.etiiin.item_info"));


                // Durability
                if(config_tooltips_gear && config_tooltips_durability && is_gear_item) {
                    tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").setStyle(DEFAULTFONT).appendSibling(new StringTextComponent("\uEaf5").setStyle(NEWFONT)).appendSibling(new StringTextComponent(" " +  doubleDecimalFormat.format(durability)).setStyle(GRAY)));
                    //tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").appendSibling(new TranslationTextComponent("tooltip.etiiin.tool.durability")).appendSibling(new StringTextComponent(" " + doubleDecimalFormat.format(durability)).setStyle(GRAY)));
                }

                // Repair Cost
                if(config_tooltips_gear && config_tooltips_repaircost && is_gear_item && repaircost != 0) {
                    tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").setStyle(DEFAULTFONT).appendSibling(new StringTextComponent("\uEaf6").setStyle(NEWFONT)).appendSibling(new StringTextComponent(" " +  doubleDecimalFormat.format(repaircost)).setStyle(GRAY)));
                    //tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").appendSibling(new TranslationTextComponent("tooltip.etiiin.tool.repaircost")).appendSibling(new StringTextComponent(" " + doubleDecimalFormat.format(repaircost)).setStyle(GRAY)));
                }

                // HarvestLevel
                if(config_tooltips_gear && config_tooltips_harvestlevel && item instanceof PickaxeItem) {

                    if( canHarvestStone && !canHarvestIron&& !canHarvestDiamond && !canHarvestObsidian) {
                        tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").setStyle(DEFAULTFONT).appendSibling(new StringTextComponent( "\uEaa0").setStyle(NEWFONT)));
                    }
                    if(canHarvestIron &&!canHarvestDiamond && !canHarvestObsidian) {
                        tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").setStyle(DEFAULTFONT).appendSibling(new StringTextComponent( "\uEaa1").setStyle(NEWFONT)));
                    }
                    if(canHarvestDiamond && !canHarvestObsidian) {
                        tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").setStyle(DEFAULTFONT).appendSibling(new StringTextComponent( "\uEaa2").setStyle(NEWFONT)));
                    }
                    if(canHarvestObsidian) {
                        tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").setStyle(DEFAULTFONT).appendSibling(new StringTextComponent( "\uEaa3").setStyle(NEWFONT)));
                    }



                }


                // Fuel Items
                if(config_tooltips_fuel && config_tooltips_ipf && AbstractFurnaceTileEntity.isFuel(stack)) {
                    //tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").setStyle(DEFAULTFONT).appendSibling(new TranslationTextComponent("tooltip.etiiin.fuel.ipf", itemperfuel).setStyle(GRAY)));
                    tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").setStyle(DEFAULTFONT).appendSibling(new StringTextComponent(doubleDecimalFormat.format(itemperfuel)+ "x ").setStyle(GRAY)).appendSibling(new StringTextComponent("\uEaf4").setStyle(NEWFONT)));
                }

                // Furnace Items
                if(config_tooltips_fuel && config_tooltips_furnace && is_smelter_item) {
                    if(stack.getItem() == Items.FURNACE) {
                        tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").appendSibling(new StringTextComponent("\uEaf1 ").setStyle(NEWFONT)).appendSibling(new TranslationTextComponent("tooltip.etiiin.fuel.furnace").setStyle(GRAY)));
                    }

                    if(stack.getItem() == Items.BLAST_FURNACE) {
                        tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").appendSibling(new StringTextComponent("\uEaf1 ").setStyle(NEWFONT)).appendSibling(new TranslationTextComponent("tooltip.etiiin.fuel.blast_furnace").setStyle(GRAY)));
                    }

                    if(stack.getItem() == Items.SMOKER) {
                        tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").appendSibling(new StringTextComponent("\uEaf1 ").setStyle(NEWFONT)).appendSibling(new TranslationTextComponent("tooltip.etiiin.fuel.smoker").setStyle(GRAY)));
                    }

                    if(stack.getItem() == Items.CAMPFIRE || stack.getItem() == Items.SOUL_CAMPFIRE) {
                        tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").appendSibling(new StringTextComponent("\uEaf1 ").setStyle(NEWFONT)).appendSibling(new TranslationTextComponent("tooltip.etiiin.fuel.campfires").setStyle(GRAY)));
                    }
                }





                // Food Items
                if(config_tooltips_food) {

                    if (config_tooltip_nutrition && item.isFood()) {
                        if(Objects.requireNonNull(item.getFood()).getHealing() <= 20 ) {
                            tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").appendSibling(new TranslationTextComponent("tooltip.hunger.icon." + Objects.requireNonNull(item.getFood()).getHealing()).setStyle(NEWFONT)));
                        }

                        if(Objects.requireNonNull(item.getFood()).getHealing() > 20 ) {
                            double nutritionvalue = item.getFood().getHealing();
                            nutritionvalue /= 2;
                            tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").appendSibling(new StringTextComponent(doubleDecimalFormat.format(nutritionvalue) + "x ").setStyle(GRAY)).appendSibling(new StringTextComponent("\uEFF1").setStyle(NEWFONT)));

                        }


                       }



                    if (config_tooltip_saturation && item.isFood()) {

                        float saturationdisplayvalue = Objects.requireNonNull(item.getFood()).getSaturation();
                        //saturationdisplayvalue *= item.getFood().getHealing();
                        //saturationdisplayvalue *= 2f;

                        if(saturationdisplayvalue <= 2.0 ) {
                            tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").appendSibling(new TranslationTextComponent("tooltip.saturation.icon." + saturationdisplayvalue).setStyle(NEWFONT)));

                        }
                        if(saturationdisplayvalue > 2.0 ) {
                            double saturationvalue = saturationdisplayvalue;
                            saturationvalue *= 10;
                            saturationvalue /= 2;
                            tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").appendSibling(new StringTextComponent(doubleDecimalFormat.format(saturationvalue) + "x ").setStyle(GRAY)).appendSibling(new StringTextComponent("\uEFF3").setStyle(NEWFONT)));

                        }


                    }

                    if (stack.getItem().isIn(ModTags.Items.FOOD_ALL)) {
                        if (config_tooltip_effect && item.isFood() && stack.getItem().isIn(ModTags.Items.FOOD_WITH_EFFECTS)) {
                            //item.getFood().getEffects() != null
                            //stack.getItem().isIn(ModTags.Items.FOOD_WITH_EFFECTS)

                            //boolean found_regen = Objects.requireNonNull(item.getFood()).getEffects().stream().map(pair -> Pair.of(pair.getFirst() != null ? pair.getFirst().getAmplifier() : null, pair.getSecond())).collect(java.util.stream.Collectors.toMap()) ;  //list.stream()
                            //boolean found_regen = Objects.requireNonNull(item.getFood()).getEffects().stream().anyMatch(m -> m.equals()) ;  //list.stream()


                            if (stack.getItem() == Items.GOLDEN_APPLE) {
                                tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").appendSibling(new StringTextComponent("\uEfe4\uEfe7").setStyle(NEWFONT)));
                            }
                            if (stack.getItem() == Items.ENCHANTED_GOLDEN_APPLE) {
                                tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").appendSibling(new StringTextComponent("\uEfe4\uEfe5\uEfe6\uEfe7").setStyle(NEWFONT)));
                            }
                            if (stack.getItem() == Items.PUFFERFISH) {
                                tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").appendSibling(new StringTextComponent("\uEfe1\uEfe2\uEfe3").setStyle(NEWFONT)));
                            }
                            if (stack.getItem() == Items.CHICKEN) {
                                tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").appendSibling(new StringTextComponent("\uEfe2").setStyle(NEWFONT)));
                            }
                            if (stack.getItem() == Items.ROTTEN_FLESH) {
                                tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").appendSibling(new StringTextComponent("\uEfe2").setStyle(NEWFONT)));
                            }
                            if (stack.getItem() == Items.SPIDER_EYE) {
                                tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").appendSibling(new StringTextComponent("\uEfe1").setStyle(NEWFONT)));
                            }
                            if (stack.getItem() == Items.POISONOUS_POTATO) {
                                tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").appendSibling(new StringTextComponent("\uEfe1").setStyle(NEWFONT)));
                            }

                        }
                        if (EtiiinConfig.diet_info_tooltip_mode.get() == EtiiinConfig.DietDisplayType.TEXT) {
                            if (config_tooltip_flexitarian) {
                                if (stack.getItem().isIn(ModTags.Items.FLEXITARIAN)) {
                                    tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").appendSibling(new TranslationTextComponent("tooltip.etiiin.flexitarian")));
                                } else {
                                    tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").appendSibling(new TranslationTextComponent("tooltip.etiiin.flexitarian_off")));
                                }
                            }
                            if (config_tooltip_pescetarian) {
                                if (stack.getItem().isIn(ModTags.Items.PESCETARIAN)) {
                                    tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").appendSibling(new TranslationTextComponent("tooltip.etiiin.pescetarian")));
                                } else {
                                    tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").appendSibling(new TranslationTextComponent("tooltip.etiiin.pescetarian_off")));
                                }
                            }
                            if (config_tooltip_vegetarian) {
                                if (stack.getItem().isIn(ModTags.Items.VEGETARIAN)) {
                                    tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").appendSibling(new TranslationTextComponent("tooltip.etiiin.vegetarian")));
                                } else {
                                    tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").appendSibling(new TranslationTextComponent("tooltip.etiiin.vegetarian_off")));
                                }
                            }
                            if (config_tooltip_vegan) {
                                if (stack.getItem().isIn(ModTags.Items.VEGAN)) {
                                    tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").appendSibling(new TranslationTextComponent("tooltip.etiiin.vegan")));
                                } else {
                                    tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").appendSibling(new TranslationTextComponent("tooltip.etiiin.vegan_off")));
                                }
                            }
                            if (config_tooltip_halal) {
                                if (stack.getItem().isIn(ModTags.Items.NOT_HALAL)) {
                                    tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").appendSibling(new TranslationTextComponent("tooltip.etiiin.halal_off")));
                                } else {
                                    tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").appendSibling(new TranslationTextComponent("tooltip.etiiin.halal")));
                                }
                            }


                        }

                        if (EtiiinConfig.diet_info_tooltip_mode.get() == EtiiinConfig.DietDisplayType.ICON) {
                            String flexitarian;
                            String pescetarian;
                            String vegetarian;
                            String vegan;
                            String not_halal;

                            if (config_tooltip_flexitarian && stack.getItem().isIn(ModTags.Items.FLEXITARIAN)) {
                                flexitarian = "\uEfa0";
                            } else {
                                flexitarian = "\uEfa1";
                            }

                            if (config_tooltip_pescetarian && stack.getItem().isIn(ModTags.Items.PESCETARIAN)) {
                                pescetarian = "\uEfa2";
                            } else {
                                pescetarian = "\uEfa3";
                            }

                            if (config_tooltip_vegetarian && stack.getItem().isIn(ModTags.Items.VEGETARIAN)) {
                                vegetarian = "\uEfa4";
                            } else {
                                vegetarian = "\uEfa5";
                            }

                            if (config_tooltip_vegan && stack.getItem().isIn(ModTags.Items.VEGAN)) {
                                vegan = "\uEfa6";
                            } else {
                                vegan = "\uEfa7";
                            }

                            if (config_tooltip_halal && stack.getItem().isIn(ModTags.Items.NOT_HALAL)) {
                                not_halal = "\uEfa9";
                            } else {
                                not_halal = "\uEfa8";
                            }
                            tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow").appendSibling(new StringTextComponent(flexitarian).appendString(pescetarian).appendString(vegetarian).appendString(vegan).appendString(not_halal).setStyle(NEWFONT)));
                        }
                    }
                }



                //Bee Hives

                if(config_tooltips_bee_hives && stack.getItem().isIn(ModTags.Items.BEEHIVES)) {
                   Optional<BeeHiveUtils.BeeHiveData> optional = BeeHiveUtils.extractBeeHiveData(stack);
                      if (optional.isPresent()) {
                        BeeHiveUtils.BeeHiveData data = optional.get();

                          String blockhoneylevel = null;

                          if(data.honeyLevel.equals("0")) {
                              blockhoneylevel = "\uEAB0";
                          }
                          if(data.honeyLevel.equals("1")) {
                              blockhoneylevel = "\uEAB1";
                          }
                          if(data.honeyLevel.equals("2")) {
                              blockhoneylevel = "\uEAB2";
                          }
                          if(data.honeyLevel.equals("3")) {
                              blockhoneylevel = "\uEAB3";
                          }
                          if(data.honeyLevel.equals("4")) {
                              blockhoneylevel = "\uEAB4";
                          }
                          if(data.honeyLevel.equals("5")) {
                              blockhoneylevel = "\uEAB5";
                          }
                          assert blockhoneylevel != null;
                          tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow")
                                  .appendSibling(new StringTextComponent(data.intAdults + "x").setStyle(GRAY))
                                  .appendSibling(new StringTextComponent("\uEAF2").setStyle(NEWFONT))
                                  .appendSibling(new StringTextComponent(" " + data.intBabies + "x").setStyle(GRAY))
                                  .appendSibling(new StringTextComponent("\uEAF3").setStyle(NEWFONT))
                                  .appendSibling(new StringTextComponent(" "+ blockhoneylevel).setStyle(NEWFONT)));
                   } else {


                         tooltip.add(new TranslationTextComponent("tooltip.etiiin.arrow")
                                 .appendSibling(new StringTextComponent("0x").setStyle(GRAY))
                                 .appendSibling(new StringTextComponent("\uEAF2").setStyle(NEWFONT))
                                 .appendSibling(new StringTextComponent(" 0x").setStyle(GRAY))
                                 .appendSibling(new StringTextComponent("\uEAF3").setStyle(NEWFONT))
                                 .appendSibling(new StringTextComponent(" ").setStyle(GRAY))
                                 .appendSibling(new StringTextComponent("\uEab0").setStyle(NEWFONT)));
                   }
                }



            }else {
                tooltip.add(new TranslationTextComponent("tooltip.etiiin.tooltip_press_shift"));

        }
            full_tooltip.addAll(1, tooltip);
        }
    }
}
