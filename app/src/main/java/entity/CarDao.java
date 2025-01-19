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

    @Query("select distinct brand from cars")
    List<String> getAllBrands();

    @Query("select distinct model from cars")
    List<String> getAllModel();

    @Query("select distinct year from cars")
    List<Integer> getAllYears();

    @Query("select distinct cost from cars")
    List<Float> getAllCosts();

    @Query("select * from cars where " +
            "(:brand is null or brand = :brand) and " +
            "(:model is null or model = :model) and " +
            "(:minYear is null or year >= :minYear) and " +
            "(:maxYear is null or year <= :maxYear) and " +
            "(:minCost is null or cost >= :minCost) and " +
            "(:maxCost is null or cost <= :maxCost)")
    List<Car> searchCars(String brand, String model, int minYear, int maxYear, float minCost, float maxCost);
}
