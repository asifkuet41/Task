package com.example.task.home.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.task.R;
import com.example.task.auth.AuthViewModel;
import com.example.task.database.User;
import com.example.task.database.UserSessionManager;
import com.example.task.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;
    UserSessionManager userSessionManager;
    String userId;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

       binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false);
       userSessionManager = new UserSessionManager(getContext());
       userId = userSessionManager.getUserId();

        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);



        homeViewModel.getUser(userId).observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                updateUI(user);
            }
        });
        return binding.getRoot();
    }

    private void updateUI(User user){
        binding.nameText.setText(user.getName().toString());
        binding.textDepartment.setText(user.getDepartment().toString());
        binding.textDesignation.setText(user.getDesignation().toString());
        binding.textDob.setText(user.getDob().toString());
        binding.textStaffNo.setText(user.getStaffNo().toString());
        binding.textMobile.setText(user.getMobile().toString());
        binding.textShop.setText(String.valueOf(user.getShop()).toString());
    }
}