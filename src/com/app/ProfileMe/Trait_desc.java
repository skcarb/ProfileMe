package com.app.ProfileMe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Trait_desc extends Activity {
	Intent i;
	TextView text_desc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.text_desc);
		i=new Intent();
		Button dismiss=(Button)findViewById(R.id.btn_dismiss);
		text_desc=(TextView)findViewById(R.id.textView3);
		setData();
		
		dismiss.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				startActivity(i);
				Trait_desc.this.finish();
				
			}
		
	
		});
	}

	private void setData() {
		// TODO Auto-generated method stub
		switch (App_constants.trait_count) {
		case 1:
			text_desc.setText(ProfileMe_desc.app_trait1_desc);
			i.setClass(Trait_desc.this, Trait1_page.class);
			break;
		case 2:
			text_desc.setText(R.string.app_trait2_desc);
			i.setClass(Trait_desc.this, Trait2_page.class);
			break;
		case 3:
			text_desc.setText(R.string.app_trait3_desc);
			i.setClass(Trait_desc.this, Trait3_page.class);

			break;
		case 4:
			text_desc.setText(R.string.app_trait4_desc);
			i.setClass(Trait_desc.this, Trait4_page.class);
			break;
		case 5:
			text_desc.setText(R.string.app_trait5_desc);
			i.setClass(Trait_desc.this, Trait5_page.class);
			break;
		case 6:
			text_desc.setText(R.string.app_trait6_desc);
			i.setClass(Trait_desc.this, Trait6_page.class);
			break;
		case 7:
			text_desc.setText(R.string.app_trait7_desc);
			i.setClass(Trait_desc.this, Trait7_page.class);

			break;

		default:
			break;
		
	}
	}
}
