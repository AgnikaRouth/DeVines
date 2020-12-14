package devines.com.devines20;

import android.content.Context;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment implements View.OnClickListener {

    private OnFragmentItemSelectedListener listener;

    ImageView img1;
    LinearLayout hiddenView;
    CardView cardView;
    private Button btemployee, btweather1, btreceiver1;


    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        cardView = view. findViewById(R.id.base_cardview);
        img1 = view.findViewById(R.id.ivheader1);
        hiddenView = view.findViewById(R.id.hidden_view);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // If the CardView is already expanded, set its visibility
                //  to gone and change the expand less icon to expand more.
                if (hiddenView.getVisibility() == View.VISIBLE) {

                    // The transition of the hiddenView is carried out
                    //  by the TransitionManager class.
                    // Here we use an object of the AutoTransition
                    // Class to create a default transition.
                    TransitionManager.beginDelayedTransition(cardView,
                            new AutoTransition());
                    hiddenView.setVisibility(View.GONE);
                    img1.setImageResource(R.drawable.ic_lea);
                }

                // If the CardView is not expanded, set its visibility
                // to visible and change the expand more icon to expand less.
                else {

                    TransitionManager.beginDelayedTransition(cardView,
                            new AutoTransition());
                    hiddenView.setVisibility(View.VISIBLE);
                    //img1.setImageResource(R.drawable.ic_block1details);
                }
            }
        });


        btemployee = (Button) view.findViewById(R.id.btemployee);
        btemployee.setOnClickListener(this);

        btweather1 = (Button) view.findViewById(R.id.btweather);
        btweather1.setOnClickListener(this);

        btreceiver1 = (Button) view.findViewById(R.id.btreceiver);
        btreceiver1.setOnClickListener(this);

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
            default:
                Toast.makeText(getActivity(), "You clicked I dont know where", Toast.LENGTH_SHORT).show();
        }
    }
    public interface  OnFragmentItemSelectedListener{
        public void onButtonSelected();


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }
}
