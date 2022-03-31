package net.geradesolukas.etiiin.util;


import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import java.util.Optional;

public class BeeHiveUtils {
    public static class BeeData {
        public int intAdults, intBabies;
        public String honeyLevel;

        public BeeData(int intAdults, int intBabies, String honeyLevel) {
            this.intAdults = intAdults;
            this.intBabies = intBabies;
            this.honeyLevel = honeyLevel;
        }
    }
    public static Optional<BeeData> extractBeeData(ItemStack stack) {
        CompoundNBT tag;
        if ((tag = stack.getTag()) == null) return Optional.empty();
        CompoundNBT blockEntityTag = tag.getCompound("BlockEntityTag");
        CompoundNBT blockStateTag = tag.getCompound("BlockStateTag");
        ListNBT beesTag = blockEntityTag.getList("Bees", 10);
        String honeyLevel = blockStateTag.getString("honey_level");
        int adults = 0, babies = 0;
        for (INBT bee : beesTag) {
            CompoundNBT entityTag = ((CompoundNBT) bee).getCompound("EntityData");
            int age = entityTag.getInt("Age");
            if (age >= 0) adults++;
            else babies++;
        }
        if (honeyLevel.isEmpty()) {
            honeyLevel = "" + blockStateTag.getInt("honey_level");
        }
        return Optional.of(new BeeData(adults, babies, honeyLevel));
    }
}
