package devines.com.devines20;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeatherFragment #newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeatherFragment extends Fragment  implements View.OnClickListener {
    Button btemployee, btreceiver;
    View view;



    public WeatherFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_weather, container, false);

        btemployee = (Button) view.findViewById(R.id.btemployee);
        btemployee.setOnClickListener(this);

        btreceiver = (Button) view.findViewById(R.id.btreceiver);
        btreceiver.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.btemployee:
                getFragmentManager().beginTransaction().replace(R.id.fragment_dashboard,
                        new DashboardFragment()).commit();
                break;

            case R.id.btweather:
                getFragmentManager().beginTransaction().replace(R.id.fragment_dashboard,
                        new WeatherFragment()).commit();
                break;

            case R.id.btreceiver:
                getFragmentManager().beginTransaction().replace(R.id.fragment_dashboard,
                        new ReceiverFragment()).commit();
                break;


        }

    }
}