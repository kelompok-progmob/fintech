package com.tyga.fintech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.tyga.fintech.api.ApiClient;
import com.tyga.fintech.api.ApiService;
import com.tyga.fintech.databinding.ActivityOtpRegisterBinding;
import com.tyga.fintech.model.ResponseMessage;

import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpRegisterActivity extends AppCompatActivity {

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    ActivityOtpRegisterBinding binding;
    ProgressDialog progressDialog;
    ProgressDialog progressDialogVerifiying;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private String mVerificationId;

    private String nama, nik, telepon, password, lpd,no_hp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  DataBindingUtil.setContentView(this,R.layout.activity_otp_register);

        nama = getIntent().getStringExtra("nama");
        nik = getIntent().getStringExtra("nik");
        no_hp = getIntent().getStringExtra("no_hp");
        telepon = getIntent().getStringExtra("no_hp").replaceFirst("^0+(?!$)", "+62");
        password = getIntent().getStringExtra("password");
        lpd = getIntent().getStringExtra("id_lpd");
        Log.e("telepon",telepon);
//        PhoneAuthProvider auth = PhoneAuthProvider.getInstance();
//        if (auth.equals("")){
//
//        }

        binding.buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = binding.editTextCode.getText().toString();

                if (code.isEmpty() || code.length() < 6) {
                    binding.editTextCode.setError("Enter Code . . .");
                }
                else{
                    verifyCode(code);
                }
            }
        });

        binding.buttonResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resendVerificationCode(telepon,mResendToken);

            }
        });

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(final PhoneAuthCredential credential) {
                Log.e("complate", "onVerificationCompleted:" + credential);
                progressDialog.dismiss();
                String code = credential.getSmsCode();
                if (code != null) {
                    binding.editTextCode.setText(code);
                    verifyCode(code);
                }
                else{
                    signInWithPhoneAuthCredential(credential);
                }

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                progressDialog.dismiss();
                Log.e("failed", "onVerificationFailed = "+e.toString());
                Toast.makeText(OtpRegisterActivity.this,"Registration Error ! "+e.toString(), Toast.LENGTH_LONG);
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    Log.d("RegisterPecandu",e.getMessage());
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    Toast.makeText(OtpRegisterActivity.this, "Quota exceeded", Toast.LENGTH_LONG).show();
                    Log.d("RegisterPecandu",e.getMessage());
                }
            }

            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {
                mVerificationId = verificationId;
                mResendToken = token;
            }
        };

        startPhoneNumberVerification(telepon);

    }

    private void verifyCode(String code){
        progressDialogVerifiying = new ProgressDialog(this);
        progressDialogVerifiying.setTitle("Verification...");
        progressDialogVerifiying.setMessage("Please wait");
        progressDialogVerifiying.setCancelable(false);
        progressDialogVerifiying.setCanceledOnTouchOutside(false);
        progressDialogVerifiying.show();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Log.d("dada", "signInWithCredential:success");
                            FirebaseUser user = task.getResult().getUser();

                            insertRegister();

                        } else {
                            progressDialogVerifiying.dismiss();
                            Log.w("dada", "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Log.e("eror broh ",task.getException().toString());
                            }
                        }
                    }
                });
    }

    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Sending Verification Code");
        progressDialog.setMessage("Please wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks,         // OnVerificationStateChangedCallbacks
                token);             // ForceResendingToken from callbacks
    }

    private void startPhoneNumberVerification(String phoneNumber) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Sending Verification Code");
        progressDialog.setMessage("Please wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    private void insertRegister(){
        ApiClient.createService(ApiService.class)
                .register(nik, nama, no_hp, password, lpd, "1")
                .enqueue(new Callback<ResponseMessage>() {
                    @Override
                    public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
//                        progressDialogVerifiying.dismiss();
                        Toast.makeText(OtpRegisterActivity.this,"Register Berhasil",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(OtpRegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<ResponseMessage> call, Throwable t) {

                    }
                });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
