package com.google.zxing.client.android.mini.test;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.mini.Intents;
import com.google.zxing.client.android.mini.Intents.Scan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class BarCodeTester extends Activity {
	private static final String TAG = BarCodeTester.class.getSimpleName();
	
	private TextView ScanText;
	private TextView ScanType;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.barcode_main);
		ScanText = (TextView) findViewById(R.id.ScanText);
		ScanType = (TextView) findViewById(R.id.ScanType);
		ScanText.setText("1234567890");
		ScanType.setText("NONE");
	}

	// This method is called at button click because we assigned the name to the
	// "On Click property" of the button
	public void myClickHandler(View view) {
		switch (view.getId()) {
		case R.id.Button01:
			//Intent intent = new Intent(this, BarCodeActivity.class);
			//intent.setAction(Intents.Scan.ACTION);
			Intent intent = new Intent(Intents.Scan.ACTION);
			startActivityForResult(intent, 0);
			ScanText.setText("1234567890");
			ScanType.setText("NONE");
			break;
		}
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		Log.d(TAG, "requestCode[%s], resultCode[%s]"+ requestCode + ":" + resultCode);
		
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				
				String format = intent.getStringExtra(Intents.Scan.RESULT_FORMAT);  //SCAN_RESULT_FORMAT
				String contents = intent.getStringExtra(Intents.Scan.RESULT); //SCAN_RESULT
				
				Log.d(TAG, "Format[%s]: Contents[%s]" + format + ":" + contents);
				
				ScanText.setText(contents);
				ScanType.setText(format);
				
				if (ScanText.getText().length() == 0) {
					Toast.makeText(this, R.string.invalid_scan, Toast.LENGTH_LONG).show();
					return;
				}
				
				// Handle successful scan
			} else if (resultCode == RESULT_CANCELED) {
				// Handle cancel
				Toast.makeText(this, R.string.cancel_by_user, Toast.LENGTH_LONG).show();
			}
		}
	}

}
