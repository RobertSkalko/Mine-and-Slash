package info.loenwind.autosave.handlers.forge;

import java.lang.reflect.Type;
import java.util.Set;

import javax.annotation.Nullable;

import info.loenwind.autosave.Registry;
import info.loenwind.autosave.exceptions.NoHandlerFoundException;
import info.loenwind.autosave.handlers.IHandler;
import info.loenwind.autosave.util.NBTAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fluids.FluidStack;

public class HandleFluidStack implements IHandler<FluidStack> {

  public HandleFluidStack() {
  }

  @Override
  public Class<?> getRootType() {
    return FluidStack.class;
  }

  @Override
  public boolean store(Registry registry, Set<NBTAction> phase, CompoundNBT nbt, Type type, String name, FluidStack object)
      throws IllegalArgumentException, IllegalAccessException, InstantiationException, NoHandlerFoundException {
    CompoundNBT tag = new CompoundNBT();
    object.writeToNBT(tag);
    nbt.put(name, tag);
    return false;
  }

  @Override
  public @Nullable FluidStack read(Registry registry, Set<NBTAction> phase, CompoundNBT nbt, Type type, String name,
      @Nullable FluidStack object) throws IllegalArgumentException, IllegalAccessException, InstantiationException, NoHandlerFoundException {
    if (nbt.contains(name)) {
      return FluidStack.loadFluidStackFromNBT(nbt.getCompound(name));
    }
    return null;
  }

}
