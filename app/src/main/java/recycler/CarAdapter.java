package recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import entity.Car;
import top.academy.R;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {
    private List<Car> carList;

    public CarAdapter(List<Car> carList) {
        this.carList = carList;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CarViewHolder holder, int position) {
        Car car = carList.get(position);
        holder.imageCar.setImageResource(car.getImageCarId());
        holder.brandAndModelCar.setText(car.getBrand() + ", " + car.getModel() + " (" + car.getYear() + ")");
        holder.descriptionCar.setText(car.getDescription());
        holder.costCar.setText(String.valueOf(car.getCost()));
    }

    @Override public int getItemCount() {
        return carList.size();
    }

    public static class CarViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageCar;
        public TextView brandAndModelCar;
        public TextView descriptionCar;
        public TextView costCar;

        public CarViewHolder(View itemView) {
            super(itemView);
            imageCar = itemView.findViewById(R.id.image_car);
            brandAndModelCar = itemView.findViewById(R.id.brand_model_car);
            descriptionCar = itemView.findViewById(R.id.description_car);
            costCar = itemView.findViewById(R.id.cost_car);
        }
    }
}



