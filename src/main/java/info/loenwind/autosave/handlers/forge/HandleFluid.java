/*
package info.loenwind.autosave.handlers.forge;

import java.lang.reflect.Type;
import java.util.Set;

import javax.annotation.Nullable;

import info.loenwind.autosave.Registry;
import info.loenwind.autosave.exceptions.NoHandlerFoundException;
import info.loenwind.autosave.handlers.IHandler;
import info.loenwind.autosave.util.NBTAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class HandleFluid implements IHandler<Fluid> {

  public HandleFluid() {
  }

  @Override
  public Class<?> getRootType() {
    return Fluid.class;
  }

  @Override
  public boolean store(Registry registry, Set<NBTAction> phase, CompoundNBT nbt, Type type, String name, Fluid object)
      throws IllegalArgumentException, IllegalAccessException, InstantiationException, NoHandlerFoundException {
    String fluidName = FluidRegistry.getFluidName(object);
    if (fluidName == null) {
      throw new IllegalArgumentException("Can only save a registered and default Fluid object.");
    }
    nbt.setString(name, fluidName);
    return true;
  }

  @Override
  public @Nullable Fluid read(Registry registry, Set<NBTAction> phase, CompoundNBT nbt, Type type, String name,
      @Nullable Fluid object) throws IllegalArgumentException, IllegalAccessException, InstantiationException, NoHandlerFoundException {
    return nbt.contains(name) ? FluidRegistry.getFluid(nbt.getString(name)) : object;
  }

}
*/