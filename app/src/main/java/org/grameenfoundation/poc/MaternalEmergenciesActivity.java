package org.grameenfoundation.poc;

import org.digitalcampus.mobile.learningGF.R;
import org.digitalcampus.oppia.application.DbHelper;
import org.digitalcampus.oppia.application.MobileLearning;
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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MaternalEmergenciesActivity extends BaseActivity {

	private ListView listView;
	private DbHelper dbh;
	private Long start_time;
	private Long end_time;
	private Button button_no;
	private JSONObject json;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    mContext=MaternalEmergenciesActivity.this;
	    setContentView(R.layout.activity_maternal_emergencies);
	    dbh=new DbHelper(MaternalEmergenciesActivity.this);
	    start_time=System.currentTimeMillis();
        getSupportActionBar().setTitle("Point of Care");
        getSupportActionBar().setSubtitle("ANC Diagnostic: Maternal Emergencies");
	    json=new JSONObject();
	    try {
			json.put("page", "ANC Diagnostic: Maternal Emergencies");
			json.put("section", MobileLearning.CCH_DIAGNOSTIC);
			json.put("ver", dbh.getVersionNumber(mContext));
			json.put("battery", dbh.getBatteryStatus(mContext));
			json.put("device", dbh.getDeviceName());
			json.put("imei", dbh.getDeviceImei(mContext));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	    listView=(ListView) findViewById(R.id.listView_postnatalCareMotherSections);
	    String[] items={"Difficulty breathing or central cyanosis ",
	    				"Shock ",
	    				"Heavy bleeding",
	    				"Convulsion, Unconscious"};
	    ListAdapter adapter=new ListAdapter(MaternalEmergenciesActivity.this,items);
	    listView.setAdapter(adapter);
	    listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
					Intent intent;
					switch(position){
					case 0:
						intent=new Intent(MaternalEmergenciesActivity.this,TakeActionMaternalEmergenciesActivity.class);
						intent.putExtra("value","difficulty_breathing");
						startActivity(intent);
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_right);
						break;
					case 1:
						intent=new Intent(MaternalEmergenciesActivity.this,TakeActionMaternalEmergenciesActivity.class);
						intent.putExtra("value","shock");
						startActivity(intent);
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_right);
						break;
					case 2:
						intent=new Intent(MaternalEmergenciesActivity.this,TakeActionMaternalEmergenciesActivity.class);
						intent.putExtra("value","heavy_bleeding");
						startActivity(intent);
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_right);
						break;
					case 3:
						intent=new Intent(MaternalEmergenciesActivity.this,TakeActionMaternalEmergenciesActivity.class);
						intent.putExtra("value","convulsion");
						startActivity(intent);
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_right);
						break;
					}
				
			}
	    	
	    });
	    button_no=(Button) findViewById(R.id.button_no);
	    button_no.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent=new Intent(MaternalEmergenciesActivity.this,RecordsAskActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_right);
			}
	    	
	    });
	}
	class ListAdapter extends BaseAdapter{
		Context mContext;
		String[] items;
		 public LayoutInflater minflater;
		public ListAdapter(Context c,String[] items){
			this.mContext=c;
			this.items=items;
			minflater = LayoutInflater.from(mContext);
		}
		@Override
		public int getCount() {
			return items.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if( convertView == null ){
			      
				  convertView = minflater.inflate(R.layout.listview_text_single,parent, false);
			    }
			 TextView text=(TextView) convertView.findViewById(R.id.textView_listViewText);
			 text.setText(items[position]);
			    return convertView;
		}
		
	}
	public void onBackPressed()
	{
	    end_time=System.currentTimeMillis();
		dbh.insertCCHLog("Point of Care", json.toString(), start_time.toString(), end_time.toString());
		finish();
	}
}
