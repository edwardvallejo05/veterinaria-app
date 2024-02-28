package com.vaxwe.mascotasapp.ui.admin.razas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RazasViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    public RazasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }

}
