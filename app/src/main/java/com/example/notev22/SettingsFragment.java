package com.example.notev22;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;


public class SettingsFragment extends Fragment {

    Settings settings;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    RadioButton radioButtonAdd;
    RadioButton radioButtonReplace;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        settings = ((MainActivity) getActivity()).getSettings();
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        radioButtonAdd = v.findViewById(R.id.radioButtonAdd);
        radioButtonReplace = v.findViewById(R.id.radioButtonReplace);
        SwitchCompat switchBackStack =  v.findViewById(R.id.switchBackStack);
        SwitchCompat switchBackAsRemove =  v.findViewById(R.id.switchBackAsRemove);
        SwitchCompat switchDeleteBeforeAdd =  v.findViewById(R.id.switchDeleteBeforeAdd);

/*
        switchDeleteBeforeAdd.setChecked(com.example.notev22.Settings.isDeleteFragment);
        switchBackAsRemove.setChecked(com.example.notev22.Settings.isBackIsRemove);
        switchBackStack.setChecked(com.example.notev22.Settings.isBackStack);
        radioButtonReplace.setChecked(com.example.notev22.Settings.isReplaceFragment);
        radioButtonAdd.setChecked(com.example.notev22.Settings.isAddFragment);
*/
        switchDeleteBeforeAdd.setChecked(settings.getDeleteFragment());
        switchBackAsRemove.setChecked(settings.getBackIsRemove());
        switchBackStack.setChecked(settings.getBackStack());
        radioButtonReplace.setChecked(settings.getReplaceFragment());
        radioButtonAdd.setChecked(settings.getAddFragment());

//        radioButtonReplace.setChecked(true);//сделал кнопкой по умолчанию

        radioButtonAdd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                com.example.notev22.Settings.isAddFragment =  isChecked;
                settings.setAddFragment(isChecked);
                writeSettings();
            }
        });

        radioButtonReplace.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                com.example.notev22.Settings.isReplaceFragment =  isChecked;
                settings.setReplaceFragment(isChecked);
                writeSettings();
            }
        });
        switchBackStack.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                com.example.notev22.Settings.isBackStack=  isChecked;
                settings.setBackStack(isChecked);
                writeSettings();
            }
        });
        switchBackAsRemove.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                com.example.notev22.Settings.isBackIsRemove=  isChecked;
                settings.setBackIsRemove(isChecked);
                writeSettings();
            }
        });

        switchDeleteBeforeAdd .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                com.example.notev22.Settings.isDeleteFragment=  isChecked;
                settings.setDeleteFragment(isChecked);
                writeSettings();
            }
        });
    }

    private void writeSettings() {
        SharedPreferences sharedPreferences = requireActivity()
                .getSharedPreferences(com.example.notev22.Settings.SHARED_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
/*        editor.putBoolean(com.example.notev22.Settings.IS_ADD_FRAGMENT_USED, com.example.notev22.Settings.isAddFragment);
        editor.putBoolean(com.example.notev22.Settings.IS_REPLACE_FRAGMENT_USED, com.example.notev22.Settings.isReplaceFragment);
        editor.putBoolean(com.example.notev22.Settings.IS_BACK_STACK_USED, com.example.notev22.Settings.isBackStack);
        editor.putBoolean(com.example.notev22.Settings.IS_BACK_IS_REMOVE_FRAGMENT, com.example.notev22.Settings.isBackIsRemove);
        editor.putBoolean(com.example.notev22.Settings.IS_DELETE_FRAGMENT_BEFORE_ADD, com.example.notev22.Settings.isDeleteFragment);
*/
        editor.putBoolean(settings.IS_ADD_FRAGMENT_USED, settings.getAddFragment());
        editor.putBoolean(settings.IS_REPLACE_FRAGMENT_USED, settings.getReplaceFragment());
        editor.putBoolean(settings.IS_BACK_STACK_USED, settings.getBackStack());
        editor.putBoolean(settings.IS_BACK_IS_REMOVE_FRAGMENT, settings.getBackIsRemove());
        editor.putBoolean(settings.IS_DELETE_FRAGMENT_BEFORE_ADD, settings.getDeleteFragment());
        editor.apply();
    }
}