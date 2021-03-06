package org.grameenfoundation.poc;

import org.digitalcampus.mobile.learningGF.R;
import org.digitalcampus.oppia.application.DbHelper;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class CalculatorsMenuActivity extends BaseActivity {

//	Context mContext;
	private ListView listView_calculators;
	private JSONObject json;
	private DbHelper dbh;
	private Long end_time;
	private Long start_time;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_postnatal_care_sections);
	    mContext=CalculatorsMenuActivity.this;
	    dbh=new DbHelper(mContext);
        getSupportActionBar().setTitle("Point of Care");
        getSupportActionBar().setSubtitle("ANC Calculators");
	    start_time=System.currentTimeMillis();
	    json=new JSONObject();
	    try {
			json.put("page", "ANC Calculators");
			json.put("section", "");
			json.put("ver", dbh.getVersionNumber(mContext));
			json.put("battery", dbh.getBatteryStatus(mContext));
			json.put("device", dbh.getDeviceName());
			json.put("imei", dbh.getDeviceImei(mContext));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	    listView_calculators=(ListView) findViewById(R.id.listView_postnatalCareSections);
	    String[] items={"Trimester & Due Date Calculator","Dosage Calculator"};
	    CalculatorsSectionsListAdapter adapter=new CalculatorsSectionsListAdapter(mContext,items);
	    listView_calculators.setAdapter(adapter);
	    listView_calculators.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent;
				String url;
				switch(position){
				case 0:
					intent=new Intent(mContext,EstimateTrimester.class);
					startActivity(intent);
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_right);
					break;
				case 1:
					intent=new Intent(mContext,DosageCalculatorActivity.class);
					startActivity(intent);
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_right);
					break;
			}
			}
	    });
	}
	class CalculatorsSectionsListAdapter extends BaseAdapter{
		Context mContext;
		String[] listItems;
		 public LayoutInflater minflater;
		
		public CalculatorsSectionsListAdapter(Context mContext,String[] listItems){
		this.mContext=mContext;
		this.listItems=listItems;
		 minflater = LayoutInflater.from(mContext);
		}
		@Override
		public int getCount() {
			return listItems.length;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if( convertView == null ){
				  convertView = minflater.inflate(R.layout.other_listview_single,parent, false);
			    }
			 TextView text=(TextView) convertView.findViewById(R.id.textView_otherCategory);
			 text.setText(listItems[position]);
			    return convertView;
		}
		
	}
	public void onBackPressed()
	{
	    end_time=System.currentTimeMillis();
		//dbh.insertCCHLog("Point of Care", "Antenatal Care", start_time.toString(), end_time.toString());
	    dbh.insertCCHLog("Point of Care", json.toString(), start_time.toString(), end_time.toString());
		finish();
	}
}
