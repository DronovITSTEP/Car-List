package entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CarDao {
    @Insert
    void insert(Car car);

    @Delete
    void delete(Car car);

    @Query("SELECT * FROM cars")
    List<Car> getAllCars();

    @Query("select * from cars where id = :id")
    Car getCarById(int id);

    @Query("select count(*) from cars")
    int getCarCount();
}
