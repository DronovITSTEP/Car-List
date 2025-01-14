package top.academy;

import android.annotation.SuppressLint;
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
import androidx.room.Room;

import java.util.List;

import entity.Car;
import entity.CarDatabase;

public class MainActivity extends AppCompatActivity {

    private List<Car> carList;
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

        TextView name = findViewById(R.id.car_name);
        ImageView img = findViewById(R.id.car_image);

        name.setText(carList.get(0).getBrand() + " " + carList.get(0).getModel());
        img.setImageResource(carList.get(0).getImageCarId());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    private void insertCarData() {
        Car car1 = new Car(R.drawable.car1, 230000, "автомобиль мечты", 2022, "TT", "Audi");
        db.carDao().insert(car1);
    }
}