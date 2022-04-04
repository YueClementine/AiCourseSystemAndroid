package com.yuebing.aicoursesystemandroid.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.common.Constant;
import com.yuebing.aicoursesystemandroid.model.Course;
import com.yuebing.aicoursesystemandroid.model.Note;
import com.yuebing.aicoursesystemandroid.service.CommonNetService;
import com.yuebing.aicoursesystemandroid.task.AimomentTask;
import com.yuebing.aicoursesystemandroid.utils.JsonListUtil;

import java.io.IOException;
import java.util.List;

public class AiMomentFragment extends Fragment {
    private List<Note> notes;
    private String token;
    private ListView lv_aimoment;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2, container, false);
        token = getActivity().getIntent().getStringExtra("token");

        new Thread(new AimomentTask(token, handler)).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    String result = CommonNetService.getByToken(Constant.GET_ALL_NOTES, token);
//
//                    notes = JsonListUtil.getObjectList(result, Note.class);
//
//                }catch (IOException e){
//                    Toast.makeText(getActivity().getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        }).start();


        return view;
    }


    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            Bundle bundle = message.getData();
            if (bundle.getString("error") != null) {
                return false;
            }
            String result = bundle.getString("result");
            notes = JsonListUtil.getObjectList(result, Note.class);
            lv_aimoment = getActivity().findViewById(R.id.lv_aimoment);
            AiMomentAdapter adapter = new AiMomentAdapter(getActivity().getApplicationContext(), R.layout.item_aimoment, notes);
            lv_aimoment.setAdapter(adapter);

            return true;
        }
    });

}
