package apps.xenione.com.demoloader.presentation;

public interface BaseView<T> {

    void setLoadingIndicator(boolean active);

    boolean isActive();

}
