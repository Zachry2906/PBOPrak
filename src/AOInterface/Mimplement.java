package AOInterface;

import java.util.List;

import Model.*;

public interface Mimplement {
    public void insert(DataMovies d);
    public void update(DataMovies d);
    public void delete(String d);
    public List<DataMovies> getAll();
    public void clear();
}
