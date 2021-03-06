package org.grameenfoundation.poc;

import org.digitalcampus.mobile.learningGF.R;
import org.digitalcampus.oppia.application.DbHelper;
import org.digitalcampus.oppia.application.MobileLearning;
import org.grameenfoundation.poc.NoInjuriesActivity.NoInjuriesListAdapter;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TakeActionSomeDehydrationEncounterNextActivity extends BaseActivity {

	private String take_action_category;
	private ListView listView_someDehydrationNo;
	private Long start_time;
	private Long end_time;
	private DbHelper dbh;
	private JSONObject json;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    Bundle extras = getIntent().getExtras(); 
	    mContext=TakeActionSomeDehydrationEncounterNextActivity.this;
	    dbh=new DbHelper(TakeActionSomeDehydrationEncounterNextActivity.this);
	    start_time=System.currentTimeMillis();
	    json=new JSONObject();
        getSupportActionBar().setTitle("Point of Care");
	  
        if (extras != null) {
          take_action_category= extras.getString("category");
        }
        if(take_action_category.equals("chps_one_next")){
	    setContentView(R.layout.activity_chps_one_next);
            getSupportActionBar().setSubtitle("PNC Diagnostic: Diarrhoea > CHPS Visit");
	    try {
			json.put("page", "PNC Diagnostic: Diarrhoea > CHPS Visit");
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
				Intent intent=new Intent(TakeActionSomeDehydrationEncounterNextActivity.this,ReturningForCareActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_right);
			}
			   
		   });
        }else if(take_action_category.equals("chps_two_next")){
        setContentView(R.layout.activity_chps_two_next);
            getSupportActionBar().setSubtitle("PNC Diagnostic: Diarrhoea > CHPS Visit");
	    try {
			json.put("page", "PNC Diagnostic: Diarrhoea > CHPS Visit");
			json.put("section", MobileLearning.CCH_DIAGNOSTIC);
			json.put("ver", dbh.getVersionNumber(mContext));
			json.put("battery", dbh.getBatteryStatus(mContext));
			json.put("device", dbh.getDeviceName());
			json.put("imei", dbh.getDeviceImei(mContext));
		} catch (JSONException e) {
			e.printStackTrace();
		}
        TextView click_here_too=(TextView) findViewById(R.id.textView_clickHereToo);
		   click_here_too.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent=new Intent(TakeActionSomeDehydrationEncounterNextActivity.this,BreastProblemsCounsellingActivity.class);
				intent.putExtra("value", "keeping_baby_warm");
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_right);
			}
			   
		   });
		   TextView click_here=(TextView) findViewById(R.id.textView_clickHere);
		   click_here.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent=new Intent(TakeActionSomeDehydrationEncounterNextActivity.this,ReturningForCareActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_right);
			}
			   
		   });
        }
	}
	public void onBackPressed()
	{
		 end_time=System.currentTimeMillis();
		dbh.insertCCHLog("Point of Care", json.toString() , start_time.toString(), end_time.toString());
		finish();
	}
}
