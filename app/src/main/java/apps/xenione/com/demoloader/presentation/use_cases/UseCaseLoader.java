package apps.xenione.com.demoloader.presentation.use_cases;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

import java.util.concurrent.Callable;

/**
 * Created by Eugeni on 24/04/2016.
 */
public class UseCaseLoader<T> extends AsyncTaskLoader<UseCaseLoader.LoaderUseCaseResult<T>> {

    private final Callable<T> callable;
    private LoaderUseCaseResult<T> result;

    @SuppressWarnings("unchecked")
    public UseCaseLoader(Runnable runnable) {
        this((Callable<T>) WrapUseCase.wrap(runnable));
    }

    public UseCaseLoader(Callable<T> callable) {
        super(new NoContext());
        this.callable = callable;
    }

    @Override
    public LoaderUseCaseResult<T> loadInBackground() {
        LoaderUseCaseResult<T> loaderUseCaseResult;
        try {
            loaderUseCaseResult = LoaderUseCaseResult.success(callable.call());
        } catch (Exception e) {
            e.printStackTrace();
            loaderUseCaseResult = LoaderUseCaseResult.failure(e);
        }
        return loaderUseCaseResult;
    }

    @Override
    public void deliverResult(LoaderUseCaseResult<T> result) {
        if(!result.isError()) {
            this.result = result;
        }

        if (isStarted()) {
            super.deliverResult(result);
        }
    }

    @Override
    protected void onStartLoading() {
        if (result != null) {
            deliverResult(result);
        }

        if (takeContentChanged() || result == null) {
            forceLoad();
        }
    }

    @Override
    protected void onReset() {
        super.onReset();
        result = null;
    }

    public static abstract class UseCaseLoaderCallback<T> implements LoaderManager.LoaderCallbacks<LoaderUseCaseResult<T>> {

        public abstract void onSuccess(T u);

        public abstract void onFailure(Exception e);

        public abstract UseCaseLoader<T> onCreateUseCaseLoader(Bundle args);

        @Override
        public Loader<LoaderUseCaseResult<T>> onCreateLoader(int id, Bundle args) {
            return onCreateUseCaseLoader(args);
        }

        @Override
        public void onLoadFinished(Loader<LoaderUseCaseResult<T>> loader, LoaderUseCaseResult<T> data) {
            if (!data.isError()) {
                onSuccess(data.data);
            } else {
                onFailure(data.exception);
            }
        }

        @Override
        public void onLoaderReset(Loader<LoaderUseCaseResult<T>> loader) {
        }
    }

    public static class LoaderUseCaseResult<U> {

        public static <U> LoaderUseCaseResult<U> success(U result) {
            return new LoaderUseCaseResult<>(result, null);
        }

        public static <U> LoaderUseCaseResult<U> failure(Exception exception) {
            return new LoaderUseCaseResult<>(null, exception);
        }

        public U data;
        public Exception exception;

        private LoaderUseCaseResult(U result, Exception exception) {
            this.data = result;
            this.exception = exception;
        }

        public boolean isError() {
            return exception != null;
        }
    }
}
