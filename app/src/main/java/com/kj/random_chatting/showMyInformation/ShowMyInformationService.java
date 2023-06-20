package com.kj.random_chatting.showMyInformation;

import static android.content.Context.MODE_PRIVATE;
import static com.kj.random_chatting.common.Constants.EMPTY_IMAGE_PATH;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.kj.random_chatting.R;
import com.kj.random_chatting.common.Enum;
import com.kj.random_chatting.databinding.ShowMyInfomationFragmentBinding;
import com.kj.random_chatting.databinding.UserListActivityBinding;
import com.kj.random_chatting.userList.FindUserInformationTaskRxJava;
import com.kj.random_chatting.userList.RegistMatchingTaskRxJava;
import com.kj.random_chatting.userList.UserListDTO;

import java.util.ArrayList;
import java.util.List;

public class ShowMyInformationService {
    private static final String TAG = "ShowMyInformationService";
    private Context context;
    private ShowMyInfomationFragmentBinding binding;

    private SharedPreferences prefs;
    //page (1페이지 부터 시작)
    private Integer currentPageCnt = 1;

    public ShowMyInformationService(Context mContext, ShowMyInfomationFragmentBinding mBinding) {
        Log.d(TAG, "Log : " + TAG + " -> UserListService");
        context = mContext;
        binding = mBinding;
        prefs =  context.getSharedPreferences("token_prefs", MODE_PRIVATE);
        showPhoto();
    }


    public void showPhoto() {
        Glide.with(context)
                .load(R.drawable.ic_upload_photo)
                .circleCrop()
                .into(binding.showMyInfomationFragmentBtnPicture);
    }

    public void btnNextUserClick() {

    }

    /**************************************************************
     *  버튼 클릭 이벤트 시작
     **************************************************************/

    public void btnActionClick(Enum.ActionStatus actionStatus) {
        Log.d(TAG, "Log : " + TAG + " -> btnUserClick");

        UserListDTO.matchingInputDTO input  = new UserListDTO.matchingInputDTO();
        String userId = prefs.getString("userId",null);

        input.setUserId(userId);
        input.setStatus(String.valueOf(actionStatus));
    }
    /**************************************************************
     *  버튼 클릭 이벤트 끝
     **************************************************************/

}