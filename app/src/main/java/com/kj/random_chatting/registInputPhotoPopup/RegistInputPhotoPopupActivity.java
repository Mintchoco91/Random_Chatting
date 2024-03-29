package com.kj.random_chatting.registInputPhotoPopup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.kj.random_chatting.R;
import com.kj.random_chatting.common.Enum;
import com.kj.random_chatting.databinding.RegistInputPhotoActivityBinding;
import com.kj.random_chatting.databinding.RegistInputPhotoPopupActivityBinding;
import com.kj.random_chatting.util.UtilClass;

import java.util.HashMap;

public class RegistInputPhotoPopupActivity extends Activity {
    private static final String TAG = "RegistInputPhotoPopupActivity";
    private RegistInputPhotoPopupActivityBinding binding;
    private Context context;
    private RegistInputPhotoPopupService registInputPhotoPopupService;
    private String strChoiceNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UtilClass.basicWriteLog(TAG, Thread.currentThread().getStackTrace()[2].getMethodName());
        binding = RegistInputPhotoPopupActivityBinding.inflate(getLayoutInflater());
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

        //데이터 가져오기
        Intent intent = getIntent();
        strChoiceNumber = intent.getStringExtra("choiceNumber");
        registInputPhotoPopupService = new RegistInputPhotoPopupService(context, binding, strChoiceNumber);
    }

    private void setListener() {
        UtilClass.basicWriteLog(TAG, Thread.currentThread().getStackTrace()[2].getMethodName());
        View.OnClickListener Listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.regist_input_photo_popup_activity_btn_upload_Image:
                        closeModal(Enum.PictureModalStatus.UPLOAD);
                        break;
                    case R.id.regist_input_photo_popup_activity_btn_delete_image:
                        closeModal(Enum.PictureModalStatus.DELETE);
                        break;
                    case R.id.regist_input_photo_popup_activity_btn_cancle:
                        closeModal(Enum.PictureModalStatus.CLOSE);
                        break;
                }
            }
        };

        binding.registInputPhotoPopupActivityBtnUploadImage.setOnClickListener(Listener);
        binding.registInputPhotoPopupActivityBtnDeleteImage.setOnClickListener(Listener);
        binding.registInputPhotoPopupActivityBtnCancle.setOnClickListener(Listener);
    }

    public void closeModal(Enum.PictureModalStatus pictureModalStatus){
        //데이터 전달하기
        Intent intent = new Intent();
        intent.putExtra("choiceMode", pictureModalStatus.toString());
        intent.putExtra("choiceNumber", strChoiceNumber);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }
}
