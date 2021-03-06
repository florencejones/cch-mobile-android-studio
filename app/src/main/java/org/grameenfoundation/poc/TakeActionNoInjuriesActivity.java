package org.grameenfoundation.poc;

import org.digitalcampus.mobile.learningGF.R;
import org.digitalcampus.oppia.application.DbHelper;
import org.digitalcampus.oppia.application.MobileLearning;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class TakeActionNoInjuriesActivity extends BaseActivity {

	private String take_action_category;
	private Long start_time;
	private Long end_time;
	private DbHelper dbh;
	private JSONObject json;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    Bundle extras = getIntent().getExtras(); 
	    mContext=TakeActionNoInjuriesActivity.this;
	    dbh=new DbHelper(TakeActionNoInjuriesActivity.this);
	    start_time=System.currentTimeMillis();
        getSupportActionBar().setTitle("Point of Care");
	    
        if (extras != null) {
          take_action_category= extras.getString("category");
        }
        if(take_action_category.equals("club foot")){
	    setContentView(R.layout.activity_club_foot);
            getSupportActionBar().setSubtitle("PNC Diagnostic: Other Serious Conditions > Club Foot");
	    json=new JSONObject();
	    try {
			json.put("page", "PNC Diagnostic: Other Serious Conditions > Club Foot");
			json.put("section", MobileLearning.CCH_DIAGNOSTIC);
			json.put("ver", dbh.getVersionNumber(mContext));
			json.put("battery", dbh.getBatteryStatus(mContext));
			json.put("device", dbh.getDeviceName());
			json.put("imei", dbh.getDeviceImei(mContext));
		} catch (JSONException e) {
			e.printStackTrace();
		}
        }else if(take_action_category.equals("cleft palate")){
        setContentView(R.layout.activity_cleft_palate);
            getSupportActionBar().setSubtitle("PNC Diagnostic: Other Serious Conditions > Cleft Palate");
	    json=new JSONObject();
	    try {
			json.put("page", "PNC Diagnostic: Other Serious Conditions > Cleft Palate");
			json.put("section", MobileLearning.CCH_DIAGNOSTIC);
			json.put("ver", dbh.getVersionNumber(mContext));
			json.put("battery", dbh.getBatteryStatus(mContext));
			json.put("device", dbh.getDeviceName());
			json.put("imei", dbh.getDeviceImei(mContext));
		} catch (JSONException e) {
			e.printStackTrace();
		}
        TextView click_here=(TextView) findViewById(R.id.textView_clickHere);
		   click_here.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent=new Intent(TakeActionNoInjuriesActivity.this,KeepingBabyWarmAndMalariaActivity.class);
				intent.putExtra("value", "keeping_baby_warm");
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_right);
			}
			   
		   });
        }else if(take_action_category.equals("unusual appearance")){
        setContentView(R.layout.activity_unusual_appearance);
            getSupportActionBar().setSubtitle("PNC Diagnostic: Other Serious Conditions > Unusual Appearance");
	    json=new JSONObject();
	    try {
			json.put("page", "PNC Diagnostic: Other Serious Conditions > Unusual Appearance");
			json.put("section", MobileLearning.CCH_DIAGNOSTIC);
			json.put("ver", dbh.getVersionNumber(mContext));
			json.put("battery", dbh.getBatteryStatus(mContext));
			json.put("device", dbh.getDeviceName());
			json.put("imei", dbh.getDeviceImei(mContext));
		} catch (JSONException e) {
			e.printStackTrace();
		}
        TextView click_here=(TextView) findViewById(R.id.textView_clickHere);
		   click_here.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent=new Intent(TakeActionNoInjuriesActivity.this,KeepingBabyWarmAndMalariaActivity.class);
				intent.putExtra("value", "keeping_baby_warm");
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_right);
			}
			   
		   });
        }else if(take_action_category.equals("no_injuries")){
            setContentView(R.layout.activity_no_birth_abnormalities);
            getSupportActionBar().setSubtitle("PNC Diagnostic: Other Serious Conditions > No Injuries");
    	    json=new JSONObject();
    	    try {
    			json.put("page", "PNC Diagnostic: Other Serious Conditions > No Injuries");
    			json.put("section", MobileLearning.CCH_DIAGNOSTIC);
    			json.put("ver", dbh.getVersionNumber(mContext));
    			json.put("battery", dbh.getBatteryStatus(mContext));
    			json.put("device", dbh.getDeviceName());
    			json.put("imei", dbh.getDeviceImei(mContext));
    		} catch (JSONException e) {
    			e.printStackTrace();
    		}
            TextView click_here=(TextView) findViewById(R.id.textView_clickHere);
        String text = "<a href='#'> diarrhoea </a>";
           	click_here.setText("Continue examination, and check baby for "+Html.fromHtml(text));
    		   click_here.setOnClickListener(new OnClickListener(){

    			@Override
    			public void onClick(View v) {
    				Intent intent=new Intent(TakeActionNoInjuriesActivity.this,DiarrhoeaActivity.class);
    				intent.putExtra("value", "keeping_baby_warm");
    				startActivity(intent);
    				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_right);
    			}
    			   
    		   });
            }
	}
	public void onBackPressed()
	{
		 end_time=System.currentTimeMillis();
		dbh.insertCCHLog("Point of Care", json.toString(), start_time.toString(), end_time.toString());
		finish();
	}
}
