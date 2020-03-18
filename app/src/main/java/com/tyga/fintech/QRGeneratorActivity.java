package com.tyga.fintech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import com.tyga.fintech.api.TokenManager;
import com.tyga.fintech.databinding.QrResultBinding;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class QRGeneratorActivity extends AppCompatActivity {

    QrResultBinding binding;
    private QRGEncoder qrgEncoder;
    private Bitmap bitmap;
    private String value;
    private TokenManager tokenManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  DataBindingUtil.setContentView(this,R.layout.qr_result);

        value = getIntent().getStringExtra("nominal");
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));

        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        int smallerDimension = width < height ? width : height;
        smallerDimension = smallerDimension * 3 / 4;

        String link = value+"#"+tokenManager.getMerchant().getMerchant().getId_merchant();

        qrgEncoder = new QRGEncoder(
                link, null,
                QRGContents.Type.TEXT,
                smallerDimension);

        try {
            bitmap = qrgEncoder.encodeAsBitmap();;
            binding.qrResultImage.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
