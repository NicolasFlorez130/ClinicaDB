package company.model.persistence;
import java.util.List;

public interface Dao<T> {
    List<T> read();

    T read(String value);

    void create(T Obj);

    void update(T Obj);

    void delete(String id);
}
