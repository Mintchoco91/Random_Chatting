package com.kj.random_chatting.onboarding;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.kj.random_chatting.common.Enum;
import com.kj.random_chatting.databinding.ActivityOnboardingBinding;
import com.kj.random_chatting.login.LoginActivity;
import com.kj.random_chatting.registPhoneNumber.RegistPhoneNumberActivity;
import com.kj.random_chatting.util.UtilClass;

public class OnboardingService extends Activity {
    private static final String TAG = "OnboardingService";
    private ActivityOnboardingBinding binding;
    private Context context;
    private UtilClass utilClass;

    public OnboardingService(Context mContext, ActivityOnboardingBinding mBinding) {
        UtilClass.basicWriteLog(TAG, Thread.currentThread().getStackTrace()[2].getMethodName());

        context = mContext;
        binding = mBinding;
    }

    /**************************************************************
     *  버튼 클릭 이벤트 시작
     **************************************************************/

    public void btnLoginClick() {
        UtilClass.basicWriteLog(TAG, Thread.currentThread().getStackTrace()[2].getMethodName());
        Intent intentUploadList = new Intent(context, LoginActivity.class);
        context.startActivity(intentUploadList);
    }

    public void btnRegistClick() {
        UtilClass.basicWriteLog(TAG, Thread.currentThread().getStackTrace()[2].getMethodName());
        Intent intentUploadList = new Intent(context, RegistPhoneNumberActivity.class);
        context.startActivity(intentUploadList);
    }


    /**************************************************************
     *  버튼 클릭 이벤트 끝
     **************************************************************/
}
