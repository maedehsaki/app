package ir.ivan.faal.Activities;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import ir.ivan.faal.R;

public class FalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fal);
    }
    public void onClickBack(View v)
    {
        Intent intent2 = new Intent(this,
                MainActivity.class);

        Bundle b = new Bundle();
        intent2.putExtra("bundle data", b);
        startActivity(intent2);
    }
}