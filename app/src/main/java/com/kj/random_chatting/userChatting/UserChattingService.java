package com.kj.random_chatting.userChatting;

import android.app.Activity;
import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.kj.random_chatting.common.UtilClass;
import com.kj.random_chatting.databinding.FragmentUserChattingBinding;

import java.net.URISyntaxException;
import java.util.Random;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class UserChattingService extends Activity {
    private UtilClass utilClass;

    private Socket socket;
    private static final String TAG = "UserChattingService";
    private FragmentUserChattingBinding fragmentUserChattingBinding;
    private Context userChattingServiceContext;
    private final String socketBaseURL = "https://random-chatting-chat-server.herokuapp.com/";
    private String tempId = "";


    public UserChattingService(Context context, FragmentUserChattingBinding binding) {
        Log.d(TAG, "Log : " + TAG + " -> UserChattingService");
        utilClass = new UtilClass();
        userChattingServiceContext = context;
        fragmentUserChattingBinding = binding;

        //임시방편으로 랜덤 닉네임
        Random random = new Random();
        int bound = 999999;
        tempId = "임시계정" + random.nextInt(bound);

        try {
            socket = IO.socket(socketBaseURL);
            socket.on("ServerToClientMsg", onMessage);
            socket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void btnSendClick(String chatMessage){
        //공백 입력일 경우 서버 전송 안함.
        if(!chatMessage.equals("")) {
            socket.emit("clientToServerMsg", tempId + " : " + chatMessage);
        }
    }

    private Emitter.Listener onMessage = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String ServerToClientMsg = (String) args[0];
                    refreshChatScreen(ServerToClientMsg);
                    Log.e("ServerToClientMsg : ", ServerToClientMsg);
                }
            });
        }
    };

    //서버에서 받은 메세지 표기
    private void refreshChatScreen(String ServerToClientMsg){
        String historyChatText = fragmentUserChattingBinding.fragmentUserChattingTvChatScreen.getText().toString();
        //두번째 줄 부터 개행 처리
        if (historyChatText != null && !historyChatText.equals("")) {
            fragmentUserChattingBinding.fragmentUserChattingTvChatScreen.append("\n");
        }
        fragmentUserChattingBinding.fragmentUserChattingTvChatScreen.append(ServerToClientMsg);
        fragmentUserChattingBinding.fragmentUserChattingEtMessage.setText("");
        utilClass.scrollBottom(fragmentUserChattingBinding.fragmentUserChattingTvChatScreen);
    }


}
