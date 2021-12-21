package com.hellobike.iocannotationproxy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.hellobike.ioc.InjectTool;
import com.hellobike.ioc.annotation.OnClickCommon;
import com.hellobike.ioc.annotation.OnLongClickCommon;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InjectTool.inject(this);
    }

    @OnClickCommon(R.id.bt_1)
    private void showInfoFirst(){
        Toast.makeText(this, "This is Button 1!", Toast.LENGTH_SHORT).show();
    }

    @OnLongClickCommon(R.id.bt_2)
    private boolean showInfoSecond(){
        Toast.makeText(this, "This is Button 2!", Toast.LENGTH_SHORT).show();
        return true;
    }
}