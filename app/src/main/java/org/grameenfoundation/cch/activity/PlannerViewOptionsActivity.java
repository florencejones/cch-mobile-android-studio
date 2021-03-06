package org.grameenfoundation.cch.activity;


import org.digitalcampus.mobile.learningGF.R;
import org.grameenfoundation.adapters.PlannerBaseAdapter;
import org.grameenfoundation.cch.model.AgeGroupsFacilityTargetsActivity;
import org.grameenfoundation.poc.BaseActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class PlannerViewOptionsActivity extends BaseActivity implements OnItemClickListener{

	private ListView listView_monthOption;
	private Button sync;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_month_options);
        getSupportActionBar().setTitle("Planner");
        getSupportActionBar().setSubtitle("View events/targets");
	    listView_monthOption=(ListView) findViewById(R.id.listView_targetMonthOptions);
	    listView_monthOption.setOnItemClickListener(this);
	    sync=(Button) findViewById(R.id.button_sync);
	    sync.setVisibility(View.GONE);
	    String[] items={"View/Update events","View individual targets","View facility performance"};
		   int[] images={R.drawable.ic_calendar_new,R.drawable.ic_target_new,R.drawable.ic_facility_performance};
		   PlannerBaseAdapter adapter=new PlannerBaseAdapter(PlannerViewOptionsActivity.this,items,images);
		   
		    listView_monthOption.setAdapter(adapter);
        }
	
	    
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent;
		switch(position){
		case 0:
			intent=new Intent(PlannerViewOptionsActivity.this,EventsViewActivity.class);
        	startActivity(intent);
        	overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_right);
        	break;
        case 1:
        	intent=new Intent(PlannerViewOptionsActivity.this,NewEventPlannerActivity.class);
        	startActivity(intent);
        	overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_right);
        	break;
        case 2:
        	intent=new Intent(PlannerViewOptionsActivity.this,FacilityTargetOptionsActivity.class);
        	startActivity(intent);
        	overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_right);
        	break;
       
		
	}
	
	}
}
