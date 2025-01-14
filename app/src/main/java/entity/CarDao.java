package entity;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CarDao {
    @Insert
    void insert(Car car);

    @Query("SELECT * FROM cars")
    List<Car> getAllCars();
}
