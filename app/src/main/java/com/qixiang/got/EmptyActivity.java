
package com.qixiang.got;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class EmptyActivity extends AppCompatActivity {

    ImageView imageView;
    Button btn_test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        imageView = findViewById(R.id.iv_test);
        btn_test = findViewById(R.id.btn_test);
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPicFromP();
            }
        });
    }

    private void getPicFromP() {
        //通过intent去找到本机中所有属性为image的文件；
        Intent intent = new Intent();
        intent.setType("image/*");
//ACTION_GET_CONTENT 允许用户选择特殊种类的数据，并返回，ACTION_Pick
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取到的图片为uri格式，imageView控件可以设置uri
        Uri uri = data.getData();
        Log.e("onActivityResult: ", String.valueOf(uri));
        imageView.setImageURI(uri);
    }

}
