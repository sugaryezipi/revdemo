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


public class Md5EncJavaFragment extends Fragment {

    private FragmentMd5encjavaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Md5EncJavaViewModule slideshowViewModel =
                new ViewModelProvider(this,
                        new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()))
                        .get(Md5EncJavaViewModule.class);

        binding = FragmentMd5encjavaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textMd5encJava;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // Find the button and set an OnClickListener
        binding.buttonEncrypt.setOnClickListener(v -> {
            // Trigger encryption and update the text view with the result
            String encryptedText = slideshowViewModel.triggerEncryption();
            System.out.println("click btn ，，，");
            textView.setText(encryptedText);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}