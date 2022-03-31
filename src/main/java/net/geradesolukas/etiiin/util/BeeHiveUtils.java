package net.geradesolukas.etiiin.util;


import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import java.util.Optional;

public class BeeHiveUtils {
    public static class BeeHiveData {
        public int intAdults;
        public int intBabies;
        public String honeyLevel;

        public BeeHiveData(int intAdults, int intBabies, String honeyLevel) {
            this.intAdults = intAdults;
            this.intBabies = intBabies;
            this.honeyLevel = honeyLevel;
        }
    }
    public static Optional<BeeHiveData> extractBeeHiveData(ItemStack stack) {
        CompoundNBT nbt;
        if ((nbt = stack.getTag()) == null) return Optional.empty();
        CompoundNBT blockEntityTag = nbt.getCompound("BlockEntityTag");
        CompoundNBT blockStateTag = nbt.getCompound("BlockStateTag");
        ListNBT beesTag = blockEntityTag.getList("Bees", 10);
        String honeyLevel = blockStateTag.getString("honey_level");
        int adults = 0;
        int babies = 0;
        if (honeyLevel.isEmpty()) {
            honeyLevel = "" + blockStateTag.getInt("honey_level");
        }
        for (INBT bee : beesTag) {
            CompoundNBT entityTag = ((CompoundNBT) bee).getCompound("EntityData");
            int age = entityTag.getInt("Age");
            if (age >= 0) adults++;
            else babies++;
        }
        return Optional.of(new BeeHiveData(adults, babies, honeyLevel));
    }
}
