package com.kj.random_chatting.registInputEmailPw;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.kj.random_chatting.R;
import com.kj.random_chatting.common.Enum;
import com.kj.random_chatting.common.SignUpRegistDTO;
import com.kj.random_chatting.databinding.ActivityOnboardingBinding;
import com.kj.random_chatting.databinding.RegistInputEmailPwActivityBinding;
import com.kj.random_chatting.util.UtilClass;

import java.util.HashMap;

public class RegistInputEmailPwActivity extends Activity {
    private static final String TAG = "RegistInputEmailPwActivity";
    private RegistInputEmailPwActivityBinding binding;
    private Context context;
    private RegistInputEmailPwService registInputEmailPwService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UtilClass.basicWriteLog(TAG, Thread.currentThread().getStackTrace()[2].getMethodName());
        binding = RegistInputEmailPwActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        initializeView();
        setListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UtilClass.basicWriteLog(TAG, Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    private void initializeView() {
        UtilClass.basicWriteLog(TAG, Thread.currentThread().getStackTrace()[2].getMethodName());
        context = this;
        Intent intent = getIntent();
        SignUpRegistDTO.input intentData = (SignUpRegistDTO.input) intent.getSerializableExtra("intentData");

        registInputEmailPwService = new RegistInputEmailPwService(context, binding, intentData);
    }

    private void setListener() {
        UtilClass.basicWriteLog(TAG, Thread.currentThread().getStackTrace()[2].getMethodName());
        View.OnClickListener Listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.regist_input_email_pw_activity_btn_regist:
                        registInputEmailPwService.btnRegistClick();
                        break;
                }
            }
        };

        binding.registInputEmailPwActivityEtEmail.setOnClickListener(Listener);
        binding.registInputEmailPwActivityEtPassword.setOnClickListener(Listener);
        binding.registInputEmailPwActivityEtConfirmPassword.setOnClickListener(Listener);
        binding.registInputEmailPwActivityBtnRegist.setOnClickListener(Listener);
    }
}
