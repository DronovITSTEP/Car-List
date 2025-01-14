package entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cars")
public class Car {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String brand; // - Марка авто
    private String model; // - Модель авто
    private  int year; // - Год выпуска авто
    private String description; // - Произвольное описание от продавца автомобиля
    private float cost; // - Стоимость авто
    private int imageCarId; // Фото автомобилей

    public Car(int imageCarId, float cost, String description, int year, String model, String brand) {
        this.imageCarId = imageCarId;
        this.cost = cost;
        this.description = description;
        this.year = year;
        this.model = model;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getImageCarId() {
        return imageCarId;
    }

    public void setImageCarId(int imageCarId) {
        this.imageCarId = imageCarId;
    }
}
