package org.grameenfoundation.poc;

import org.digitalcampus.mobile.learningGF.R;
import org.digitalcampus.oppia.application.DbHelper;
import org.digitalcampus.oppia.application.MobileLearning;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;

public class PostpartumExerciseNextActivity extends BaseActivity {

	private WebView myWebView;
	private Button button_next;
	private DbHelper dbh;
	private Long start_time;
	private Long end_time;
	private JSONObject json;  
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_postpartum_exercises_next);
	    mContext =PostpartumExerciseNextActivity.this;
        getSupportActionBar().setTitle("Point of Care");
        getSupportActionBar().setSubtitle("PNC Counselling: Postpartum Exercises");
	    dbh=new DbHelper(PostpartumExerciseNextActivity.this);
	    start_time=System.currentTimeMillis();
	    json=new JSONObject();
	    try {
			json.put("page", "PNC Counselling: Postpartum Exercises");
			json.put("section", MobileLearning.CCH_COUNSELLING);
			json.put("ver", dbh.getVersionNumber(mContext));
			json.put("battery", dbh.getBatteryStatus(mContext));
			json.put("device", dbh.getDeviceName());
			json.put("imei", dbh.getDeviceImei(mContext));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	    myWebView = (WebView) findViewById(R.id.webView1);	    	 
		myWebView.getSettings().setJavaScriptEnabled(true);
		myWebView.loadUrl("file:///android_asset/www/cch/modules/poc/images/headshoulderlift.gif");
		button_next=(Button) findViewById(R.id.button_next);
		button_next.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent=new Intent(PostpartumExerciseNextActivity.this,PostpartumExercisesNextTwoActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_right);
			}
			
		});
		
	}
	public void onBackPressed()
	{
	    end_time=System.currentTimeMillis();
		dbh.insertCCHLog("Point of Care", json.toString(), start_time.toString(), end_time.toString());
		finish();
	}
}
