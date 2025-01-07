package com.koohai.revdemo.ui.md5Enc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.koohai.revdemo.databinding.FragmentMd5encjavaBinding;
import com.koohai.revdemo.ui.md5Enc.Md5EncJavaViewModule;

public class Md5EncJavaFragment extends Fragment {

    private FragmentMd5encjavaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Md5EncJavaViewModule slideshowViewModel =
                new ViewModelProvider(this).get(Md5EncJavaViewModule.class);

        binding = FragmentMd5encjavaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textMd5encJava;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}