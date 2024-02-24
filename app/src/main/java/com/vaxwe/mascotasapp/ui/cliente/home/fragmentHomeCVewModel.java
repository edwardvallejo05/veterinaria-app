package com.vaxwe.mascotasapp.ui.cliente.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class fragmentHomeCVewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public fragmentHomeCVewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
