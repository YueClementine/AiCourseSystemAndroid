package com.yuebing.aicoursesystemandroid.ui;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.ui.teacher.TeacherFragment;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {
    //3个TAB的布局文件
    private LinearLayout mTab1;
    private LinearLayout mTab2;
    private LinearLayout mTab3;

    //3 ImageButton
    private ImageButton mImg1;
    private ImageButton mImg2;
    private ImageButton mImg3;

    //3 Fragment
    private Fragment mFrag1;
    private Fragment mFrag2;
    private Fragment mFrag3;

    //token
    private String token;

    //role
    private int role;
    //userid
    private long userid;
    //username
    private String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
        token = getIntent().getStringExtra("token");
        role = getIntent().getIntExtra("role", 1);
        userid = getIntent().getLongExtra("userid", 1L);
        username = getIntent().getStringExtra("username");
        initViews();//初始化控件
        initEvents();//初始化事件
        selectTab(0);//默认选中第一个Tab
    }

    private void selectTab(int i) {
        //获取FragmentManager对象
        FragmentManager manager = getSupportFragmentManager();
        //获取FragmentTransaction对象
        FragmentTransaction transaction = manager.beginTransaction();
        //先隐藏所有的Fragment
        hideFragments(transaction);
        switch (i) {
            //当选中点击的是第一页的Tab时
            case 0:
                //设置第一页的ImageButton为绿色
                mImg1.setBackgroundResource(R.mipmap.application_selected);
                //如果第一页对应的Fragment没有实例化，则进行实例化，并显示出来
                if (role == 0) {
                    if (mFrag1 == null) {
                        mFrag1 = new StudentMainFragment();
                        transaction.add(R.id.id_content, mFrag1);
                    } else {
                        //如果第一页对应的Fragment已经实例化，则直接显示出来
                        transaction.show(mFrag1);
                    }
                } else {
                    //老师和学生的页面作区分
                    if (mFrag1 == null) {
                        mFrag1 = new TeacherFragment();
                        transaction.add(R.id.id_content, mFrag1);
                    } else {
                        //如果第一页对应的Fragment已经实例化，则直接显示出来
                        transaction.show(mFrag1);
                    }
                }

                break;
            case 1:
                mImg2.setBackgroundResource(R.drawable.moment_selected);
                if (mFrag2 == null) {
                    mFrag2 = new AiMomentFragment();
                    transaction.add(R.id.id_content, mFrag2);
                } else {
                    transaction.show(mFrag2);
                }
                break;
            case 2:
                mImg3.setBackgroundResource(R.mipmap.me_selected);
                if (mFrag3 == null) {
                    mFrag3 = new ProfileFragment();
                    transaction.add(R.id.id_content, mFrag3);
                } else {
                    transaction.show(mFrag3);
                }
                break;
        }
        //提交事务
        transaction.commit();

    }

    private void hideFragments(FragmentTransaction transaction) {
        if (mFrag1 != null) {
            transaction.hide(mFrag1);
        }
        if (mFrag2 != null) {
            transaction.hide(mFrag2);
        }
        if (mFrag3 != null) {
            transaction.hide(mFrag3);
        }

    }

    private void initEvents() {
        //初始化四个Tab的点击事件
        mTab1.setOnClickListener(this);
        mTab2.setOnClickListener(this);
        mTab3.setOnClickListener(this);
    }

    private void initViews() {
        //初始化四个Tab的布局文件
        mTab1 = (LinearLayout) findViewById(R.id.id_tab1);
        mTab2 = (LinearLayout) findViewById(R.id.id_tab2);
        mTab3 = (LinearLayout) findViewById(R.id.id_tab3);


        //初始化四个ImageButton
        mImg1 = (ImageButton) findViewById(R.id.id_discuss_student);
        mImg2 = (ImageButton) findViewById(R.id.id_tab_img2);
        mImg3 = (ImageButton) findViewById(R.id.id_tab_img3);

    }


    @Override
    public void onClick(View view) {
        resetImgs(); //先将四个ImageButton置为灰色
        switch (view.getId()) {
            case R.id.id_tab1:
                selectTab(0);
                break;
            case R.id.id_tab2:
                selectTab(1);
                break;
            case R.id.id_tab3:
                selectTab(2);
                break;
        }

    }

    private void resetImgs() {
        mImg1.setBackgroundResource(R.mipmap.application);
        mImg2.setBackgroundResource(R.mipmap.moment);
        mImg3.setBackgroundResource(R.mipmap.me);

    }
}
