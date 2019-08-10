package com.sppcco.merchandise.ui.util.barcode_scanner;

import android.os.Bundle;
import android.view.ViewGroup;

import com.google.zxing.Result;
import com.sppcco.merchandise.R;

import org.greenrobot.eventbus.EventBus;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ActivityBarcodeScanner extends ActivityBaseBarcodeScanner implements ZXingScannerView.ResultHandler {
  private ZXingScannerView mScannerView;

  @Override
  public void onCreate(Bundle state) {
    super.onCreate(state);
    setContentView(R.layout.activity_barcode_scanner);
    setupToolbar();

    ViewGroup contentFrame = findViewById(R.id.content_frame);
    mScannerView = new ZXingScannerView(this);
    contentFrame.addView(mScannerView);
  }

  @Override
  public void onResume() {
    super.onResume();
    mScannerView.setResultHandler(this);
    mScannerView.startCamera();
  }

  @Override
  public void onPause() {
    super.onPause();
    mScannerView.stopCamera();
  }

  @Override
  public void handleResult(Result rawResult) {

    EventBus.getDefault().post(rawResult);
    finish();
  }
}
