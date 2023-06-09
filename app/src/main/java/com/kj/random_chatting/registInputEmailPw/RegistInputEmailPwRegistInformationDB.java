package com.kj.random_chatting.registInputEmailPw;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.kj.random_chatting.common.SignUpRegistDTO;
import com.kj.random_chatting.databinding.FileUploadActivityBinding;
import com.kj.random_chatting.databinding.RegistInputEmailPwActivityBinding;
import com.kj.random_chatting.userRegist.UserRegistDTO;
import com.kj.random_chatting.util.Retrofit_client;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;

public class RegistInputEmailPwRegistInformationDB {
    private static final String TAG = "RegistInputEmailPwRegistInformationDB";
    RegistInputEmailPwActivityBinding binding;
    Context context;
    Disposable backgroundTask;

    private SignUpRegistDTO.input intentData = new SignUpRegistDTO.input();

    public RegistInputEmailPwRegistInformationDB(Context mContext, RegistInputEmailPwActivityBinding mBinding, SignUpRegistDTO.input mIntentData) {
        Log.d(TAG, "Log : " + TAG + " -> RegistInputEmailPwRegistInformationDB");

        context = mContext;
        binding = mBinding;
        intentData = mIntentData;
    }

    //결과 처리
    private void resultPost(SignUpRegistDTO.output result) {
        if (result.getResultCode() == 0) {
            //insert 후 id
            intentData.setUserInfoId(result.getReturnId());
            //사진 추가
            RegistInputEmailPwRegistPhotoDB registInputEmailPwRegistPhotoDB = new RegistInputEmailPwRegistPhotoDB(context, binding, intentData);
            registInputEmailPwRegistPhotoDB.runFunc();
        } else {
            Toast.makeText(context, "등록 실패 ! ErrorCode : " + result.getResultCode(), Toast.LENGTH_SHORT).show();
        }
    }

    public void runFunc(String... params) {
        backgroundTask = Observable.fromCallable(() -> {
                    return userRegistInformation();
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> resultPost(result));
    }

    private SignUpRegistDTO.output userRegistInformation() {
        SignUpRegistDTO.output result = new SignUpRegistDTO.output();
        Integer resultCode = 0;
        try {
            Call<String> call = Retrofit_client.getApiService().signUpRegist(intentData);
            String jsonResponse = call.execute().body();

            try {
                JSONObject jsonObject = new JSONObject(jsonResponse);
                if (jsonObject.optString("status").equals("true")) {
                    result.setReturnId(jsonObject.optString("returnId"));
                    result.setResultCode(0);
                } else {
                    result.setResultCode(1);
                }
            } catch (JSONException e) {
                result.setResultCode(2);
            }
        } catch (Exception e) {
            result.setResultCode(3);
        }

        result.setResultCode(resultCode);
        return result;
    }

}