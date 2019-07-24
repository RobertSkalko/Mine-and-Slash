package info.loenwind.autosave.handlers.util;

import java.lang.reflect.Type;
import java.util.Set;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import info.loenwind.autosave.Registry;
import info.loenwind.autosave.exceptions.NoHandlerFoundException;
import info.loenwind.autosave.handlers.IHandler;
import info.loenwind.autosave.util.NBTAction;
import info.loenwind.autosave.util.NonnullType;
import info.loenwind.autosave.util.TypeUtil;
import net.minecraft.nbt.CompoundNBT;

public class DelegatingHandler<T, R> implements IHandler<T> {
  
  private final @Nonnull Type type;
  private final @Nonnull IHandler<R> delegate;
  private final @Nonnull Function<@NonnullType T, R> storeConverter;
  private final @Nonnull Function<@NonnullType R, T> readConverter;
  
  /**
   * This exists to make javac happy in cases where T might not be strongly
   * bound by the function parameters.
   */
  public DelegatingHandler(Class<T> type, IHandler<R> delegate, Function<@NonnullType T, R> storeConverter, Function<@NonnullType R, T> readConverter) {
    this((Type) type, delegate, storeConverter, readConverter);
  }

  public DelegatingHandler(Type type, IHandler<R> delegate, Function<@NonnullType T, R> storeConverter, Function<@NonnullType R, T> readConverter) {
    this.type = type;
    this.delegate = delegate;
    this.storeConverter = storeConverter;
    this.readConverter = readConverter;
  }
  
  @Override
  public Class<?> getRootType() {
    return TypeUtil.toClass(type);
  }

  @Override
  public boolean store(Registry registry, Set<NBTAction> phase, CompoundNBT nbt, Type type, String name, T object)
      throws IllegalArgumentException, IllegalAccessException, InstantiationException, NoHandlerFoundException {
    R obj = storeConverter.apply(object);
    if (obj == null) {
      throw new IllegalArgumentException("Store converter returned null unexpectedly.");
    }
    return delegate.store(registry, phase, nbt, type, name, obj);
  }
  
  @Override
  @Nullable
  public T read(Registry registry, Set<NBTAction> phase, CompoundNBT nbt, Type type, String name, @Nullable T object)
      throws IllegalArgumentException, IllegalAccessException, InstantiationException, NoHandlerFoundException {
    R intermediate = delegate.read(registry, phase, nbt, type, name, object == null ? null : storeConverter.apply(object));
    return intermediate == null ? null : readConverter.apply(intermediate);
  }
}
