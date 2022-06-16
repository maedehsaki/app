package ir.ivan.faal.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;

import ir.ivan.faal.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClickFal(View v)
    {
        Intent intent1 = new Intent(this,
                FalActivity.class);

        Bundle b = new Bundle();
        intent1.putExtra("bundle data", b);
        startActivity(intent1);
    }



    public void onClickPoet(View v)
    {
        Intent intent1 = new Intent(this,
                SherActivity.class);

        Bundle b = new Bundle();
        intent1.putExtra("bundle data", b);
        startActivity(intent1);
    }
}