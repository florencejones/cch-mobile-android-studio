package org.grameenfoundation.poc;

import org.digitalcampus.mobile.learningGF.R;
import org.digitalcampus.oppia.application.DbHelper;
import org.digitalcampus.oppia.application.MobileLearning;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;

public class AskMotherRecordsPNCNextActivity extends BaseActivity {

	private Long start_time;
	private Long end_time;
	private DbHelper dbh;
	private JSONObject json;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    mContext = AskMotherRecordsPNCNextActivity.this;
	    setContentView(R.layout.activity_ask_mother_records_pnc_next);
        getSupportActionBar().setTitle("Point of Care");
        getSupportActionBar().setSubtitle("PNC Mother Diagnostic: Records & History");
	    dbh=new DbHelper(AskMotherRecordsPNCNextActivity.this);
	    start_time=System.currentTimeMillis();
	    json=new JSONObject();
	    try {
			json.put("page", "PNC Mother Diagnostic: Records & History");
			json.put("section", MobileLearning.CCH_DIAGNOSTIC);
			json.put("ver", dbh.getVersionNumber(mContext));
			json.put("battery", dbh.getBatteryStatus(mContext));
			json.put("device", dbh.getDeviceName());
			json.put("imei", dbh.getDeviceImei(mContext));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	public void onBackPressed()
	{
		 end_time=System.currentTimeMillis();
		//dbh.insertCCHLog("Point of Care", "PNC Mother Diagnostic: Records & History", start_time.toString(), end_time.toString());
		 dbh.insertCCHLog("Point of Care", json.toString(), start_time.toString(), end_time.toString());
		finish();
	}
}
