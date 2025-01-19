package top.academy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import entity.Car;
import entity.CarDatabase;

public class SearchCarActivity extends AppCompatActivity {

    private AutoCompleteTextView brandAutoComplete;
    private AutoCompleteTextView modelAutoComplete;
    private Spinner fromYearSpinner;
    private Spinner toYearSpinner;
    private Spinner fromCostSpinner;
    private Spinner toCostSpinner;
    private Button searchButton;
    private CarDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search_car);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle(R.string.toolbar_search_name);

        toolbar.setNavigationOnClickListener(v -> {
            getOnBackPressedDispatcher().onBackPressed();
        });

        brandAutoComplete = findViewById(R.id.input_brand);
        modelAutoComplete = findViewById(R.id.input_model);
        fromYearSpinner = findViewById(R.id.spinner_from_year);
        toYearSpinner = findViewById(R.id.spinner_to_year);
        fromCostSpinner = findViewById(R.id.spinner_from_cost);
        toCostSpinner = findViewById(R.id.spinner_to_cost);
        searchButton = findViewById(R.id.matches_btn);

        db = Room.databaseBuilder(getApplicationContext(), CarDatabase.class, "car-database")
                .allowMainThreadQueries()
                .build();

        setupAutoCompleteTextView();
        setupSpinners();

        searchButton.setOnClickListener(v-> performSearch());

        setupTextMatches();
        setupSpinnerListeners();
    }

    private void setupAutoCompleteTextView() {
        List<String> brands = db.carDao().getAllBrands();
        List<String> models = db.carDao().getAllModel();

        ArrayAdapter<String> brandAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, brands);
        ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, models);

        brandAutoComplete.setAdapter(brandAdapter);
        modelAutoComplete.setAdapter(modelAdapter);
    }
    private void setupSpinners() {
        List<Integer> years = db.carDao().getAllYears();
        List<Float> costs = db.carDao().getAllCosts();

        ArrayAdapter<Integer> yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, years);
        ArrayAdapter<Float> costAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, costs);

        fromYearSpinner.setAdapter(yearAdapter);
        toYearSpinner.setAdapter(yearAdapter);
        fromCostSpinner.setAdapter(costAdapter);
        toCostSpinner.setAdapter(costAdapter);
    }
    private void performSearch() {
        String brand = brandAutoComplete.getText().toString();
        String model = modelAutoComplete.getText().toString();

        int minYear = (int)fromYearSpinner.getSelectedItem();
        int maxYear = (int)toYearSpinner.getSelectedItem();

        float minCost = (float) fromCostSpinner.getSelectedItem();
        float maxCost = (float) toCostSpinner.getSelectedItem();

        List<Car> filteredCar = db.carDao().searchCars(brand, model, minYear, maxYear, minCost, maxCost);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putParcelableArrayListExtra("filteredCars", (ArrayList<Car>) filteredCar );
        startActivity(intent);
    }
    private void setupTextMatches() {
        brandAutoComplete.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateSearchButtonText();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        modelAutoComplete.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateSearchButtonText();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
    private void setupSpinnerListeners() {
        fromYearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateSearchButtonText();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        toYearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateSearchButtonText();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        fromCostSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateSearchButtonText();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        toCostSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateSearchButtonText();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void updateSearchButtonText() {
        String brand = brandAutoComplete.getText().toString();
        String model = modelAutoComplete.getText().toString();

        int minYear = (int)fromYearSpinner.getSelectedItem();
        int maxYear = (int)toYearSpinner.getSelectedItem();

        float minCost = (float) fromCostSpinner.getSelectedItem();
        float maxCost = (float) toCostSpinner.getSelectedItem();

        List<Car> filteredCar = db.carDao().searchCars(brand, model, minYear, maxYear, minCost, maxCost);
        int count = filteredCar.size();

        searchButton.setText("(" + count + ") matches");
    }
}