package luqmansen.me.moviecatalogue1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDetail = findViewById(R.id.homeDetailButton);
        btnDetail.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
