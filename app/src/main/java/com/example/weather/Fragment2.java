package com.example.weather;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatDelegate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {
    private Button btn4;
    private Button btnDark;
    private Fragment1 fragment1;
    private View view;
    private Switch s2Press;
    private Switch s3Speed;
    private Switch sTheme;

    public Fragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment1 = new Fragment1();
        view = inflater.inflate(R.layout.fragment_2, null);
        btn4 = view.findViewById(R.id.button4);

        s2Press = view.findViewById(R.id.switch2);
        s3Speed = view.findViewById(R.id.switch3);
        sTheme = view.findViewById(R.id.switchTheme);
        TextView textCity = view.findViewById(R.id.textView6);

        String[] data = getResources().getStringArray(R.array.arrayCity);
        spinerMethod(textCity, data);

        if(MainActivity.isSwitchPress()) s2Press.setChecked(true);
        else s2Press.setChecked(false);

        if(MainActivity.isSwitchSpeed()) s3Speed.setChecked(true);
        else s3Speed.setChecked(false);

        if(MainActivity.isSwitchTheme()) sTheme.setChecked(true);
        else sTheme.setChecked(false);

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, fragment1);
                //ft.addToBackStack("");
                ft.commit();
            }
        });

        s2Press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s2Press.isChecked()) {
                    s2Press.setChecked(false);
                    Snackbar.make(view, R.string.textchengPress,
                            Snackbar.LENGTH_LONG).setAction(R.string.show_button, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MainActivity.setSwitchPress(true);
                            s2Press.setChecked(true);
                        }
                    }).show();
                }
                else {
                    s2Press.setChecked(true);

                    Snackbar.make(view, R.string.textchengPress_2,
                            Snackbar.LENGTH_LONG).setAction(R.string.rem_pres, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MainActivity.setSwitchPress(false);
                            s2Press.setChecked(false);
                        }
                    }).show();
                }


            }
        });

        s3Speed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s3Speed.isChecked()){
                    s3Speed.setChecked(false);
                    Snackbar.make(view, R.string.show_speed,
                            Snackbar.LENGTH_LONG).setAction(R.string.show_button, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MainActivity.setSwitchSpeed(true);
                            s3Speed.setChecked(true);
                        }
                    }).show();
                }
                else{
                    s3Speed.setChecked(true);
                    Snackbar.make(view, R.string.rem_speed,
                            Snackbar.LENGTH_LONG).setAction(R.string.remove_but, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MainActivity.setSwitchSpeed(false);
                            s3Speed.setChecked(false);
                        }
                    }).show();

                }
            }
        });

        sTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sTheme.isChecked()) MainActivity.setSwitchTheme(true);
                else MainActivity.setSwitchTheme(false);
                getActivity().recreate();// пересоздать активити



            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    private void spinerMethod(final TextView t6, final String[] data) {
        //адаптер----------------------------------------------------
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner) view.findViewById(R.id.spiner);
        spinner.setAdapter(adapter);
        // заголовок--------------------------------------------------
        spinner.setPrompt("Title");
        // выделяем элемент ------------------------------
        spinner.setSelection(MainActivity.getPosition());
        // устанавливаем обработчик нажатия---------------------------
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                switch (position){
                    case 0: t6.setText(data[position]);
                        break;

                    case 1: t6.setText(data[position]);
                        break;

                    case 2: t6.setText(data[position]);
                        break;
                }
                MainActivity.setSity(t6.getText().toString());
                MainActivity.setPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }




}
