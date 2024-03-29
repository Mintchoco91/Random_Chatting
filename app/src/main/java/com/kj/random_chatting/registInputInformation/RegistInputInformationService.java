package com.kj.random_chatting.registInputInformation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.kj.random_chatting.common.SignUpRegistDTO;
import com.kj.random_chatting.databinding.RegistInputInformationActivityBinding;
import com.kj.random_chatting.registInputGender.RegistInputGenderActivity;
import com.kj.random_chatting.util.UtilClass;

public class RegistInputInformationService extends Activity {
    private static final String TAG = "RegistInputInformationService";
    private RegistInputInformationActivityBinding binding;
    private Context context;
    private SignUpRegistDTO.input intentData = new SignUpRegistDTO.input();

    public RegistInputInformationService(Context mContext, RegistInputInformationActivityBinding mBinding, SignUpRegistDTO.input mIntentData) {
        UtilClass.basicWriteLog(TAG, Thread.currentThread().getStackTrace()[2].getMethodName());

        context = mContext;
        binding = mBinding;
        intentData = mIntentData;
    }

    /**************************************************************
     *  버튼 클릭 이벤트 시작
     **************************************************************/

    public void btnContinueClick() {
        UtilClass.basicWriteLog(TAG, Thread.currentThread().getStackTrace()[2].getMethodName());
        //validation 필요

        intentData.setNickName(binding.registInputInformationActivityEtNickname.getText().toString());
        intentData.setBirthday(binding.registInputInformationActivityEtBirthday.getText().toString());

        Intent intent = new Intent(context, RegistInputGenderActivity.class);
        intent.putExtra("intentData", intentData);
        context.startActivity(intent);
    }


    /**************************************************************
     *  버튼 클릭 이벤트 끝
     **************************************************************/
}
