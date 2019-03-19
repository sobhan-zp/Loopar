package com.loopar.zp.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpPost;
import com.koushikdutta.async.http.AsyncHttpResponse;
import com.koushikdutta.async.http.body.MultipartFormDataBody;
import com.loopar.zp.Classes.AppConfig;
import com.loopar.zp.Classes.SharedPrefSave;
import com.loopar.zp.R;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SingupActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.et_username_singup)
    EditText etUsernameSingup;
    @BindView(R.id.et_pass_singup)
    EditText etPassSingup;
    @BindView(R.id.et_email_singup)
    EditText etEmailSingup;
    @BindView(R.id.btn_signup)
    Button btnSignup;
    @BindView(R.id.btn_singupTologin)
    Button btnSingupTologin;
    @BindView(R.id.cv_singup)
    CardView cvSingup;
    @BindView(R.id.et_email_login)
    EditText etEmailLogin;
    @BindView(R.id.et_pass_login)
    EditText etPassLogin;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_loginTosingup)
    Button btnLoginTosingup;
    @BindView(R.id.cv_login)
    CardView cvLogin;
    private SharedPrefSave save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        ButterKnife.bind(this);


        save = new SharedPrefSave(this);

        if (save.load("sign","0").equals("1")){
            startActivity(new Intent(SingupActivity.this , MainActivity.class));
            finish();
        }

        btnLoginTosingup.setOnClickListener(this);
        btnSingupTologin.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnSignup.setOnClickListener(this);

    }

    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_loginTosingup:
                cvLogin.setVisibility(View.GONE);
                cvSingup.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_singupTologin:
                cvSingup.setVisibility(View.GONE);
                cvLogin.setVisibility(View.VISIBLE);
                break;

            case R.id.btn_login:

                if (etEmailLogin.getText().toString().equals("")){
                    Toast.makeText(this, "ایمیل باید پر باشد!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (etPassLogin.getText().toString().equals("")){
                    Toast.makeText(this, "رمز عبور نباید خالی باشد!", Toast.LENGTH_SHORT).show();
                    return;
                }

                AsyncHttpPost asyncHttpPost = new AsyncHttpPost(AppConfig.URL_LOGIN);
                asyncHttpPost.setTimeout(18000);

                MultipartFormDataBody multipartFormDataBody = new MultipartFormDataBody();
                multipartFormDataBody.addStringPart("email",etEmailLogin.getText().toString());
                multipartFormDataBody.addStringPart("password",etPassLogin.getText().toString());

                asyncHttpPost.setBody(multipartFormDataBody);

                AsyncHttpClient.getDefaultInstance().executeString(asyncHttpPost, new AsyncHttpClient.StringCallback() {
                    @Override
                    public void onCompleted(Exception e, AsyncHttpResponse source, String result) {
                        try {
                            JSONObject object2 = new JSONObject(result);

                            if (e!=null){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(SingupActivity.this, "اتصال خود به اینترنت را برسی کنید!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                            if (object2.getString("message").equals("no")){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(SingupActivity.this, "نام کاربری یا رمز عبور اشتباه است!", Toast.LENGTH_SHORT).show();

                                    }
                                });

                            }

                            if (object2.getString("message").equals("ok")){

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(SingupActivity.this, "خوش آمدید!", Toast.LENGTH_SHORT).show();
                                        save.save("email", etEmailLogin.getText().toString());
                                        save.save("pass" , etPassLogin.getText().toString());
                                        save.save("sign","1");
                                        startActivity(new Intent(SingupActivity.this , MainActivity.class));
                                        finish();
                                    }
                                });

                            }

                            if (object2.getString("message").equals("null")){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(SingupActivity.this, "اطلاعات کامل نیست!", Toast.LENGTH_SHORT).show();

                                    }
                                });

                            }
                        } catch (JSONException e2) {
                            e.printStackTrace();
                        }
                    }
                });


                break;

            case R.id.btn_signup:

                if (etUsernameSingup.getText().toString().equals("")){
                    Toast.makeText(this, "نام کاریری نباید خالی باشد!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (etEmailSingup.getText().toString().equals("")){
                    Toast.makeText(this, "ایمیل نمی تواند خالی باشد!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (etPassSingup.getText().toString().equals("")){
                    Toast.makeText(this, "رمز عبور نمی تواند خالی باشد!", Toast.LENGTH_SHORT).show();
                    return;
                }

                AsyncHttpPost asyncHttpPost1 = new AsyncHttpPost(AppConfig.URL_SIGNUP);
                asyncHttpPost1.setTimeout(18000);

                MultipartFormDataBody multipartFormDataBody1 = new MultipartFormDataBody();
                multipartFormDataBody1.addStringPart("username",etUsernameSingup.getText().toString());
                multipartFormDataBody1.addStringPart("password",etPassSingup.getText().toString());
                multipartFormDataBody1.addStringPart("email",etEmailSingup.getText().toString());

                asyncHttpPost1.setBody(multipartFormDataBody1);

                AsyncHttpClient.getDefaultInstance().executeString(asyncHttpPost1, new AsyncHttpClient.StringCallback() {
                    @Override
                    public void onCompleted(Exception e, AsyncHttpResponse source, String result) {
                        try {
                            JSONObject object = new JSONObject(result);

                            try {
                                if (object.getString("message").equals("old_user")){
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(SingupActivity.this, "نام کاربری وجود دارد!", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }

                                if (object.getString("message").equals("ok")){

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(SingupActivity.this, "خوش آمدید!", Toast.LENGTH_SHORT).show();
                                            save.save("username" , etUsernameSingup.getText().toString());
                                            save.save("email" , etEmailSingup.getText().toString());
                                            save.save("pass" , etPassSingup.getText().toString());
                                            save.save("sign","1");
                                            startActivity(new Intent(SingupActivity.this , MainActivity.class));
                                            finish();
                                        }
                                    });

                                }

                                if (object.getString("message").equals("no")){

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(SingupActivity.this, "لطفا چند لحضه ی دیگر امتحان کنید!", Toast.LENGTH_SHORT).show();
                                        }
                                    });


                                }

                                if (object.getString("message").equals("null")){
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(SingupActivity.this, "اطلاعات کافی نیست!", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }


                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }

                    }
                });

                break;
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
