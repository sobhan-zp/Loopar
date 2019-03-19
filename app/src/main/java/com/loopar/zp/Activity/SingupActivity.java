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

                JSONObject jsonObjectLogin = new JSONObject();

                try {
                    jsonObjectLogin.put("email" , etEmailLogin.getText().toString());
                    jsonObjectLogin.put("password" , etPassLogin.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                final JsonObjectRequest requestLogin = new JsonObjectRequest(Request.Method.POST, AppConfig.URL_LOGIN, jsonObjectLogin, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getString("message").equals("no")){
                                Toast.makeText(SingupActivity.this, "نام کاربری یا رمز عبور اشتباه است!", Toast.LENGTH_SHORT).show();
                            }

                            if (response.getString("message").equals("ok")){
                                Toast.makeText(SingupActivity.this, "خوش آمدید!", Toast.LENGTH_SHORT).show();
                                save.save("email", etEmailLogin.getText().toString());
                                save.save("pass" , etPassLogin.getText().toString());
                                save.save("sign","1");
                                startActivity(new Intent(SingupActivity.this , MainActivity.class));
                                finish();
                            }

                            if (response.getString("message").equals("null")){
                                Toast.makeText(SingupActivity.this, "اطلاعات کامل نیست!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });


                requestLogin.setRetryPolicy(new DefaultRetryPolicy(18000 , DefaultRetryPolicy.DEFAULT_MAX_RETRIES , DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                Volley.newRequestQueue(this).add(requestLogin);

                break;

            case R.id.btn_signup:

                if (etUsernameSingup.getText().toString().equals("")){
                    Toast.makeText(this, "نام کاریری نباید خالی باشد!", Toast.LENGTH_SHORT).show();
                    return;
                }else if (etEmailSingup.getText().toString().equals("")){
                    Toast.makeText(this, "ایمیل نمی تواند خالی باشد!", Toast.LENGTH_SHORT).show();
                    return;
                }else if (etPassSingup.getText().toString().equals("")){
                    Toast.makeText(this, "رمز عبور نمی تواند خالی باشد!", Toast.LENGTH_SHORT).show();
                    return;
                }

                JSONObject jsonObjectSignUp = new JSONObject();
                try {
                    jsonObjectSignUp.put("username" , etUsernameSingup.getText().toString());
                    jsonObjectSignUp.put("email" ,   etEmailSingup.getText().toString());
                    jsonObjectSignUp.put("password", etPassSingup.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest requestSignUp = new JsonObjectRequest(Request.Method.POST, AppConfig.URL_SIGNUP, jsonObjectSignUp, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                           if (response.getString("message").equals("old_user")){
                               Toast.makeText(SingupActivity.this, "نام کاربری وجود دارد!", Toast.LENGTH_SHORT).show();
                           }

                           if (response.getString("message").equals("ok")){
                               Toast.makeText(SingupActivity.this, "خوش آمدید!", Toast.LENGTH_SHORT).show();
                               save.save("username" , etUsernameSingup.getText().toString());
                               save.save("email" , etEmailSingup.getText().toString());
                               save.save("pass" , etPassSingup.getText().toString());
                               save.save("sign","1");
                               startActivity(new Intent(SingupActivity.this , MainActivity.class));
                               finish();
                           }

                           if (response.getString("message").equals("no")){
                               Toast.makeText(SingupActivity.this, "لطفا چند لحضه ی دیگر امتحان کنید!", Toast.LENGTH_SHORT).show();
                           }

                           if (response.getString("message").equals("null")){
                               Toast.makeText(SingupActivity.this, "اطلاعات کافی نیست!", Toast.LENGTH_SHORT).show();
                           }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SingupActivity.this, "لطفا اتصال خود به اینترنت را برسی کنید!", Toast.LENGTH_SHORT).show();
                    }
                });

                requestSignUp.setRetryPolicy(new DefaultRetryPolicy(18000 , DefaultRetryPolicy.DEFAULT_MAX_RETRIES , DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                Volley.newRequestQueue(this).add(requestSignUp);
                break;
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
