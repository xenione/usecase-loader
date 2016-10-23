## Introduction:

Sometimes we need to execute a piece of code in a separate thread to avoid to block the main thread. It is mandatory if you want to perform some network access or tasks that may take potentially longer than 4 secs, without getting an **ARN**. A good practice is to do it for every task that takes longer than 100 ms . Otherwise we are blocking (for more than 100 ms) the main thread and it may cause a bad user experience (for instance freeze scrolling). Spawn threads in Java is a mechanism that is provided by Java itself. Sometimes this “worker threads” have to give callbacks to the “main thread.” For that also Java provides “handlers“ to deal with inter-threads communication. Android provides some more elaborate “classes” beside framework that make it easy deal with that like **AsyncTask**.


## Issue:

**We can not keep an activity or fragment reference (hard reference) inside an AsyncTask without getting into memory leak issue and or a NullPointerException**.


## Why:

During recreation “Activity/Fragment” as when orientation screen occurs. The old Activity instance is destroyed and the new one is created instead, but unfortunately the **AsyncTask** keeps the previous one reference (the old instance activity) so **GC** (garbage collector) cannot free memory heap up since that old instance Activity is still referenced (this is known as leak memory). It also drives a bad performance because callbacks will take place in the old “Activity/Fragment” instance and not in the new one. It may cause a NullPointerException if the hierarchy of views has been destroyed and **AsyncTask** tries to update UI.

Workarounds like to cancel task and execute it again and update the new instance “Activity/Fragment” reference keeps away from leak memory. However, it means that we have to discard the previous task and execute it again. In case we can discard tasks, in most cases, tasks can not be canceled only the callbacks are canceled so that it can drive into a possible duplicated execution task. Anyway, in the best case, it drives into a bad performance. Why should it cancels a task just because device configuration changed (f.i. when I rotate the device).


## Solutions: 

There are several solutions ranging from blocking the rotation (no recommended because recreation not only happens when screen rotates) or Handling the Configuration Change yourself or keep **AsyncTask** reference during recreation and update listeners (the most used in the past) till more elaborate ones like using Loaders or RxAndroid that allows binding executions callbacks into the “Activity/Fragment” lifecycle. There are others like using **EventBus** that I do not recommend at all, or a little bit better could post an event in the message Queue Main Thread or keeping the **AsyncTask** inside a non-UI Fragment and set setRetainInstance to true.


## The solution UseCaseLoader (based on Loaders/AsyncTaskLoaders):

AsyncTaskLoader is an abstract class, so implementation is required. One that framework provides it is the CursorLoader,  to get data from contentProviders in an easy way. Here I create my Loader, a custom class called UseCaseLoader that execute commands **Callable** or **Runnable** objects that can match with the application usecase Interactors.
In the example app, I have two usecases that correspond with GetNoteListUseCase and AddNewNoteUseCase, both to extend UseCaseLoader.

see the example at [DemoLoader](https://github.com/xenione/DemoLoader)


## Brief introduction to **UseCaseLoader**:

Loaders and AsynTaskLoaders are an abstract class so the implementation may vary slightly. This is a brief introdcution to Loaders as was implemented on UseCaseLoaders.

Android 3.0 (API level 11) come up with a new feature called **Loaders**.

Perform a sync task taking account of Activity/Fragment lifecycle.

That means you can safely restore callbacks for the current loader-task into the new Activity or Fragment instance, even if the task was in progress at the time the activity/fragment was recreated.

**Loaders** are managed by **LoaderManager**. You can have access through **getSupportLoaderManager()/ getLoaderManager** in your Activity or Fragment;

**LoaderManager** public interface:

**intLoader** starts new one or reconnect with the previous loader (with the same id)
if loader has not been created before,  **onCreateLoader** callback will be called immediately.

If *Loader* was previously created, it will be updated with new **LoaderCallback** implementation and will be triggered **onLoadFinished()** callback with previous data loaded, even if the task was executing recreation meanwhile Activity/Fragment took place .

**Loaders** keep state during activity/fragment recreation, so if previous data was loaded, no reload needed. If you want to force load You can force loader calling **restartLoader() **it discard old data and reload again.

Place **initLoaders** **onActivityCreated()** Fragment Or OnCreate() Activity. 

Keep in mind that loader is in the activity scope.

So If I add a Fragment and this last one initializes a **Loader**, for the first time, the **Loader** will be created. When I remove the fragment, and after that, I add a new instance of the same fragment into the same activity, the previous **Loader** should be reused no new should be created. So no **OnCreateLoader** callback will be called instead previous loaded data will be dispatched through **onLoadFinished()**.

You can destroy loaders calling **destroyLoader()**, alternatively, just cancel task invoke **cancelLoader()**.
