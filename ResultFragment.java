package devines.com.devines20;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultFragment #newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultFragment extends Fragment implements OnClickListener {
   ImageView im1, im2, im3, im4, im5, im6;

    public ResultFragment() {
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
       View view = inflater.inflate(R.layout.fragment_result, container, false);
       im1 = (ImageView) view.findViewById(R.id.ivblock1);
       im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Baie.class));
            }
        });

        im2 = (ImageView) view.findViewById(R.id.ivblock2);
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Density.class));
            }
        });

        im3 = (ImageView) view.findViewById(R.id.ivblock3);
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Maladie.class));
            }
        });

        im4 = (ImageView) view.findViewById(R.id.ivblock4);
        im4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ViticoleWork.class));
            }
        });

        im5 = (ImageView) view.findViewById(R.id.ivblock5);
        im5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Anomalies.class));
            }
        });

        im6 = (ImageView) view.findViewById(R.id.ivblock6);
        im6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Meteo.class));
            }
        });

       return view;
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getActivity(), "You clicked on button", Toast.LENGTH_SHORT).show();
        //getFragmentManager().beginTransaction().replace(R.id.fragment_dashboard,
              //  new DashboardFragment()).commit();
        startActivity(new Intent(getActivity(), Baie.class));
    }
}