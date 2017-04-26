package apps.xenione.com.demoloader.data.source;


import java.util.List;

public interface DataSource<T> {

    List<T> getAll();

    void save(T user);

    void update(T user);

    void delete(T user);

}
