package cat.dme.smart.marcopolo.fragments.trip;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.activities.EditPaymentMethodActivity;
import cat.dme.smart.marcopolo.adapters.PaymentMethodArrayAdapter;
import cat.dme.smart.marcopolo.dao.impl.PaymentMethodDaoImpl;
import cat.dme.smart.marcopolo.model.PaymentMethod;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * link PaymentMethodFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PaymentMethodFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaymentMethodFragment extends Fragment {

    private static final String ARG_PARAM1 = "currentTripId";

    private Long currentTripId;
    //private OnFragmentInteractionListener mListener;

    /**
     * Default constructor
     */
    public PaymentMethodFragment() {
        super();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param currentTripId Parameter 1.
     * @return A new instance of fragment PaymentMethodFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PaymentMethodFragment newInstance(Long currentTripId) {
        PaymentMethodFragment fragment = new PaymentMethodFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM1, currentTripId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.currentTripId = this.getArguments().getLong(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment_method, container, false);
    }
/*
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
*/
    @Override
    public void onResume() {
        super.onResume();
        TextView title = (TextView) this.getView().findViewById(R.id.payment_method_title);

        List<PaymentMethod> paymentMethods = PaymentMethodDaoImpl.getInstance().getAllByTrip(this.currentTripId);

        // Get ListView object from xml
        final ListView listView = (ListView) this.getView().findViewById(R.id.payment_method_list);

        ArrayAdapter<PaymentMethod> adapter = new PaymentMethodArrayAdapter(this.getContext(), paymentMethods);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PaymentMethod itemValue = (PaymentMethod) listView.getItemAtPosition(position);
                startActivity(EditPaymentMethodActivity.runActivity(view.getContext(), itemValue));
            }
        });

        FloatingActionButton fab = (FloatingActionButton) this.getView().findViewById(R.id.fab_add_payment_method);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(EditPaymentMethodActivity.runActivity(view.getContext(), currentTripId));
            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*)      if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
       // mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
/*    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    } */
}
