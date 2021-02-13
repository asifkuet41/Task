package com.example.task.home.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.task.R;
import com.example.task.database.User;
import com.example.task.database.UserSessionManager;
import com.example.task.databinding.FragmentNotificationsBinding;
import com.example.task.home.ui.home.HomeViewModel;


public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    FragmentNotificationsBinding binding;

    private String name;
    private String Department;
    private String Designation;

    UserSessionManager userSessionManager;
    String userId;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_notifications, container, false);

        userSessionManager = new UserSessionManager(getContext());
        userId = userSessionManager.getUserId();


        notificationsViewModel.getUser(userId).observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
               setValue(user);
            }
        });

        binding.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.userInfoLayout.setVisibility(View.GONE);
                binding.checkBox2.setChecked(false);
            }
        });

        binding.checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.userInfoLayout.setVisibility(View.VISIBLE);
                binding.checkBox.setChecked(false);
            }
        });
        return binding.getRoot();
    }


    void setValue(User user){
        name = user.getName().toString();
        Department = user.getDepartment().toString();
        Designation = user.getDesignation().toString();
        binding.rTextName.setText(name);
        binding.rTextDepartment.setText(Department);
        binding.rTextDesignation.setText(Designation);
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkBox:
                if (checked){
                    binding.userInfoLayout.setVisibility(View.GONE);
                }
                break;
            case R.id.checkBox2:
                if (checked){
                    binding.userInfoLayout.setVisibility(View.VISIBLE);
                }
                break;
        }
    }
}