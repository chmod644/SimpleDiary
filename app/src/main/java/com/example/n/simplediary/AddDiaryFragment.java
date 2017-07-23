package com.example.n.simplediary;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddDiaryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddDiaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddDiaryFragment extends Fragment {
    // // TODO: Rename parameter arguments, choose names that match
    // // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    // private static final String ARG_PARAM1 = "param1";
    // private static final String ARG_PARAM2 = "param2";

    // // TODO: Rename and change types of parameters
    // private String mParam1;
    // private String mParam2;

    // private OnFragmentInteractionListener mListener;

    private int _year;
    private int _month;
    private int _date;

    public AddDiaryFragment() {
        // Required empty public constructor
    }

    // /**
    //  * Use this factory method to create a new instance of
    //  * this fragment using the provided parameters.
    //  *
    //  * @param param1 Parameter 1.
    //  * @param param2 Parameter 2.
    //  * @return A new instance of fragment AddDiaryFragment.
    //  */
    // // TODO: Rename and change types and number of parameters
    // public static AddDiaryFragment newInstance(String param1, String param2) {
    //     AddDiaryFragment fragment = new AddDiaryFragment();
    //     Bundle args = new Bundle();
    //     args.putString(ARG_PARAM1, param1);
    //     args.putString(ARG_PARAM2, param2);
    //     fragment.setArguments(args);
    //     return fragment;
    // }
    //
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_diary, container, false);

        final EditText dateText = (EditText) view.findViewById(R.id.editDate);

        // Set date
        Calendar cal = Calendar.getInstance();
        _year = cal.get(Calendar.YEAR);
        _month = cal.get(Calendar.MONTH);
        _date = cal.get(Calendar.DATE);

        String dateStr = _year + "/" + (_month+1) + "/" + _date;
        dateText.setText(dateStr);

        dateText.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

                final int year = _year;
                final int month = _month;
                final int date = _date;

                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                        _year = y;
                        _month = m;
                        _date = d;
                        String date = y + "/" + (m+1) + "/" + d;
                        dateText.setText(date);
                    }
                }, year, month, date);

                datePickerDialog.show();
            }
        });

        DiaryOpenHelper helper = new DiaryOpenHelper(getContext());
        Button saveButton = (Button) view.findViewById(R.id.button);
        saveButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast toast = Toast.makeText(getActivity(), "Buttonにリスナ登録できているかテスト!", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
        );
        return view;
    }

    // // TODO: Rename method, update argument and hook method into UI event
    // public void onButtonPressed(Uri uri) {
    //     if (mListener != null) {
    //         mListener.onFragmentInteraction(uri);
    //     }
    // }

    // @Override
    // public void onAttach(Context context) {
    //     super.onAttach(context);
    //     if (context instanceof OnFragmentInteractionListener) {
    //         mListener = (OnFragmentInteractionListener) context;
    //     } else {
    //         throw new RuntimeException(context.toString()
    //                 + " must implement OnFragmentInteractionListener");
    //     }
    // }

    // @Override
    // public void onDetach() {
    //     super.onDetach();
    //     mListener = null;
    // }

    // /**
    //  * This interface must be implemented by activities that contain this
    //  * fragment to allow an interaction in this fragment to be communicated
    //  * to the activity and potentially other fragments contained in that
    //  * activity.
    //  * <p>
    //  * See the Android Training lesson <a href=
    //  * "http://developer.android.com/training/basics/fragments/communicating.html"
    //  * >Communicating with Other Fragments</a> for more information.
    //  */
    // public interface OnFragmentInteractionListener {
    //     // TODO: Update argument type and name
    //     void onFragmentInteraction(Uri uri);
    // }
}
