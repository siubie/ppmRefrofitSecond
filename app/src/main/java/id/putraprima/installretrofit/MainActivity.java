package id.putraprima.installretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import id.putraprima.installretrofit.generator.ServiceGenerator;
import id.putraprima.installretrofit.models.ChuckNorrisQuote;
import id.putraprima.installretrofit.services.ChuckService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ChuckService chuckService;
    TextView txtData;
    ImageView imgChuck;
    Button btnReload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chuckService = ServiceGenerator.createService(ChuckService.class);
        txtData = (TextView) findViewById(R.id.txtData);
        imgChuck = (ImageView) findViewById(R.id.imgChuck);
        reloadData();
        btnReload = (Button) findViewById(R.id.btnReload);
        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               reloadData();
            }
        });
    }

    private void reloadData() {
        Call<ChuckNorrisQuote> call = chuckService.getQuote();
        call.enqueue(new Callback<ChuckNorrisQuote>() {
            @Override
            public void onResponse(Call<ChuckNorrisQuote> call, Response<ChuckNorrisQuote> response) {
                txtData.setText(response.body().getValue());
                Picasso.with(getApplicationContext()).load(response.body().getIconUrl()).into(imgChuck);
            }

            @Override
            public void onFailure(Call<ChuckNorrisQuote> call, Throwable t) {
                txtData.setText(t.getMessage());
            }
        });
    }
}
