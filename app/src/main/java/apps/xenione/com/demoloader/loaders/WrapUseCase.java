package apps.xenione.com.demoloader.loaders;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * Created by Eugeni on 17/09/2016.
 */
public class WrapUseCase<T> implements Callable<T> {


    public static <T> Callable<T> wrap(Callable<T> callable) {
        return new WrapUseCase<>(callable);
    }

    public static Callable<Object> wrap(Runnable runnable) {
        return new WrapUseCase<>(runnable);
    }

    private Callable<T> mCallable;

    public WrapUseCase(Callable<T> callable) {
        mCallable = callable;
    }

    @SuppressWarnings("unchecked")
    public WrapUseCase(Runnable runnable) {
        mCallable = (Callable<T>) Executors.callable(runnable);
    }

    @Override
    public T call() throws Exception {
        return mCallable.call();
    }
}
