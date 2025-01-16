package top.academy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

import entity.Car;
import entity.CarDatabase;
import recycler.CarAdapter;

public class MainActivity extends AppCompatActivity {

    private List<Car> carList;
    private RecyclerView recyclerCar;
    private CarAdapter adapter;
    private CarDatabase db;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setIcon(R.mipmap.car_icon);
        getSupportActionBar().setTitle(R.string.toolbar_name);

        db = Room.databaseBuilder(getApplicationContext(),
                CarDatabase.class,
                "car-database").allowMainThreadQueries().build();

        insertCarData();

        carList = db.carDao().getAllCars();

        adapter = new CarAdapter(carList);

        recyclerCar = findViewById(R.id.recyclerCar);
        recyclerCar.setLayoutManager(new LinearLayoutManager(this));

        recyclerCar.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_search) {
            Intent intent = new Intent(this, SearchCarActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void insertCarData() {
        if (db.carDao().getCarCount() == 0) {
            Car car1 = new Car(R.drawable.car1, 230000, "автомобиль мечты", 2022, "TT", "Audi");
            Car car2 = new Car(R.drawable.car2, 458845, "быстрый, надежный", 2020, "RG-56FX", "Haval");
            Car car3 = new Car(R.drawable.car3, 356770, "шайтан карета", 2021, "Trevel", "BMW");
            Car car4 = new Car(R.drawable.car4, 789999, "для унтонченного вкуса", 2023, "Next Wall", "Ferarri");
            Car car5 = new Car(R.drawable.car5, 6666666, "для тех, кто хочет быть на высоте", 2025, "Matiz-Limuzin", "Deo");
            db.carDao().insert(car1);
            db.carDao().insert(car2);
            db.carDao().insert(car3);
            db.carDao().insert(car4);
            db.carDao().insert(car5);
        }
    }
}