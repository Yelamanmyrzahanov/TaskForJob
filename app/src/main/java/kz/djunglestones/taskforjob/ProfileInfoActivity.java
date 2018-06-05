package kz.djunglestones.taskforjob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileInfoActivity extends AppCompatActivity {

    private Toolbar profile_info_toolbar;
    private CircleImageView profile_info_image;
    private TextView user_full_name, profile_info_employment, profile_info_email, profile_info_gender, profile_info_ip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_info);

        profile_info_image = findViewById(R.id.profile_info_image);
        user_full_name = findViewById(R.id.profile_info_username);
        profile_info_employment = findViewById(R.id.profile_info_employment);
        profile_info_email = findViewById(R.id.profile_info_email);
        profile_info_gender = findViewById(R.id.profile_info_gender);
        profile_info_ip = findViewById(R.id.profile_info_ip);

//        getSupportActionBar().hide();
        profile_info_toolbar = (Toolbar) findViewById(R.id.profile_info_toolbar);
        setSupportActionBar(profile_info_toolbar);
        getSupportActionBar().setTitle("Profile Info");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String first_name = getIntent().getExtras().getString("first_name");
        String last_name = getIntent().getExtras().getString("last_name");
        String email = getIntent().getExtras().getString("email");
        String gender = getIntent().getExtras().getString("gender");
        String ip_address = getIntent().getExtras().getString("ip_address");
        String photo = getIntent().getExtras().getString("photo");
        String name = getIntent().getExtras().getString("name");
        String position = getIntent().getExtras().getString("position");

        user_full_name.setText(first_name+" "+last_name);
        profile_info_email.setText(email);
        profile_info_gender.setText(gender);
        profile_info_ip.setText(ip_address);
        profile_info_employment.setText(name+", "+position);
        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

        Glide.with(this).load(photo).apply(requestOptions).into(profile_info_image);

    }
}
