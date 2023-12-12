package com.spdqbr.aoc.utils;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Credit: https://stackoverflow.com/questions/27549864/java-memoization-method
// Darth Android
// does not work for recursive functions
public class Memoize {
	public interface MemoizedFunction<V> {
	    V call(Object... args);
	}
	
	private static class ArgList {
	    public Object[] args;
	
	    @Override
	    public boolean equals(Object o) {
	        if (this == o) {
	            return true;
	        }
	        if (!(o instanceof ArgList)) {
	            return false;
	        }
	
	        ArgList argList = (ArgList) o;
	
	        // Probably incorrect - comparing Object[] arrays with Arrays.equals
	        return Arrays.equals(args, argList.args);
	    }
	
	    @Override
	    public int hashCode() {
	        return args != null ? Arrays.hashCode(args) : 0;
	    }
	}
	
	public static <V> MemoizedFunction<V> memoizeFunction(Class<? super V> returnType, Method method) throws
	                                                                                                  IllegalAccessException {
	    final Map<ArgList, V> memoizedCalls = new HashMap<>();
	    MethodHandles.Lookup lookup = MethodHandles.lookup();
	    MethodHandle methodHandle = lookup.unreflect(method)
	                                      .asSpreader(Object[].class, method.getParameterCount());
	    return args -> {
	        ArgList argList = new ArgList();
	        argList.args = args;
	        return memoizedCalls.computeIfAbsent(argList, argList2 -> {
	            try {
	                //noinspection unchecked
	                return (V) methodHandle.invoke(args);
	            } catch (Throwable throwable) {
	                throw new RuntimeException(throwable);
	            }
	        });
	    };
	}
}