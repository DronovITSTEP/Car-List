package entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cars")
public class Car implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String brand; // - Марка авто
    private String model; // - Модель авто
    private  int year; // - Год выпуска авто
    private String description; // - Произвольное описание от продавца автомобиля
    private float cost; // - Стоимость авто
    private int imageCarId; // Фото автомобилей

    protected Car(Parcel in) {
        id = in.readInt();
        brand = in.readString();
        model = in.readString();
        year = in.readInt();
        cost = in.readFloat();
        description = in.readString();
        imageCarId = in.readInt();
    }

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

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel source) {
            return new Car(source);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(brand);
        dest.writeString(model);
        dest.writeInt(year);
        dest.writeFloat(cost);
        dest.writeString(description);
        dest.writeInt(imageCarId);
    }
}
