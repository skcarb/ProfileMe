package com.app.ProfileMe;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.brickred.socialauth.android.DialogListener;
import org.brickred.socialauth.android.SocialAuthAdapter;
import org.brickred.socialauth.android.SocialAuthAdapter.Provider;
import org.brickred.socialauth.android.SocialAuthError;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.facebook.facebook.FacebookConstant;
import com.android.facebook.facebook.SessionEvents;
import com.app.milesoft.facebook.DialogError;
import com.app.milesoft.facebook.FacebookError;
import com.app.milesoft.facebook.Util;

public class Weldone_Page extends Activity implements OnClickListener {
	TextView tt_one, tt_two, tt_three, tt_four, tt_five, tt_six, tt_seven,
			tt_done;
	String report;

	int screen_width, screen_height;

	PopupWindow mpopup;
	View popUpView;
	SocialAuthAdapter adapter;
	//Twitter mTwitter;
	boolean check;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_welldone);
		setBodyUi();
		getDimension();
		setData();

	
		report = createReport();
		System.out.println("repoprt is" + createReport());
		System.out.println("step1" + App_constants.trait_one);
		System.out.println("step2" + App_constants.trait_two);
		System.out.println("step3" + App_constants.trait_three);
		System.out.println("step4" + App_constants.trait_four);
		System.out.println("step5" + App_constants.trait_five);
		System.out.println("step6" + App_constants.trait_six);
		System.out.println("step7" + App_constants.trait_seven);
	}

	private void getDimension() {
		// TODO Auto-generated method stub
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		screen_height = displaymetrics.heightPixels;
		screen_width = displaymetrics.widthPixels;
		System.out.println("111111" + screen_height);
		System.out.println("222222" + screen_width);
	}

	public void createPopup() {

		popUpView = getLayoutInflater().inflate(R.layout.new_popup_tellafriend, null); // inflating
		// popup
		// layout
		mpopup = new PopupWindow(popUpView, screen_width - screen_width / 6,
				screen_height - screen_height / 4, true); // Creation of popup
		mpopup.setAnimationStyle(android.R.style.Animation_Dialog);
		mpopup.showAtLocation(popUpView, Gravity.BOTTOM, 0, 0); // Displaying
		// popup

		LinearLayout ll_one = (LinearLayout) popUpView
				.findViewById(R.id.ll_one);
		LinearLayout ll_two = (LinearLayout) popUpView
				.findViewById(R.id.ll_two);
		LinearLayout ll_three = (LinearLayout) popUpView
				.findViewById(R.id.ll_three);
		LinearLayout ll_four = (LinearLayout) popUpView
				.findViewById(R.id.ll_four);
		LinearLayout ll_five = (LinearLayout) popUpView
				.findViewById(R.id.ll_five);

		ll_one.setOnClickListener(this);

		ll_two.setOnClickListener(this);
		ll_three.setOnClickListener(this);

		ll_four.setOnClickListener(this);
		ll_five.setOnClickListener(this);

		
	}
	
	public void createPopuptellAfriend() {

		popUpView = getLayoutInflater().inflate(R.layout.new_popup, null); // inflating
		// popup
		// layout
		mpopup = new PopupWindow(popUpView, screen_width - screen_width / 6,
				screen_height - screen_height / 4, true); // Creation of popup
		mpopup.setAnimationStyle(android.R.style.Animation_Dialog);
		mpopup.showAtLocation(popUpView, Gravity.BOTTOM, 0, 0); // Displaying
		// popup

		LinearLayout ll_one = (LinearLayout) popUpView
				.findViewById(R.id.ll_one);
		LinearLayout ll_two = (LinearLayout) popUpView
				.findViewById(R.id.ll_two);
		LinearLayout ll_three = (LinearLayout) popUpView
				.findViewById(R.id.ll_three);
		LinearLayout ll_four = (LinearLayout) popUpView
				.findViewById(R.id.ll_four);
		LinearLayout ll_five = (LinearLayout) popUpView
				.findViewById(R.id.ll_five);

		ll_one.setOnClickListener(this);

		ll_two.setOnClickListener(this);
		ll_three.setOnClickListener(this);

		ll_four.setOnClickListener(this);
		ll_five.setOnClickListener(this);


	}


	private final class ResponseListener implements DialogListener {
		@Override
		public void onComplete(Bundle values) {

			Log.d("ShareButton", "Authentication Successful");
		

			final String providerName = values
					.getString(SocialAuthAdapter.PROVIDER);
			Log.d("ShareButton", "Provider Name = " + providerName);
		
	
			
			if (providerName.equals("linkedin")) {
				System.out.println("val is" + providerName);
				//adapter2.updateStatus(report);
			}

			Toast.makeText(Weldone_Page.this,
					"Message posted on " + providerName, Toast.LENGTH_LONG)
					.show();

			findViewById(R.id.btn_tellAFriend).setBackgroundResource(
					R.drawable.bottommenutellafiend);
			mpopup.dismiss();

		}

		@Override
		public void onCancel() {
			Log.d("ShareButton", "Authentication Cancelled");
		}

		@Override
		public void onBack() {
			Log.d("Share-Button", "Dialog Closed by pressing Back Key");
		}

		@Override
		public void onError(SocialAuthError arg0) {
			// TODO Auto-generated method stub

		}

	}
	

	private String createReport() {
		report = "\n\nCONFIDENCE\n" + tt_one.getText().toString()
				+ "\n\nFRIENDLINESS\n" + tt_two.getText().toString()
				+ "\n\nTOLERANCE\n" + tt_three.getText().toString()
				+ "\n\nSENSE OF HUMOUR\n" + tt_four.getText().toString()
				+ "\n\nGENEROSITY\n" + tt_five.getText().toString()
				+ "\n\nWORLD VIEW\n" + tt_six.getText().toString()
				+ "\n\nMAGNETISM\n" + tt_seven.getText();
		return report;
	}
	
	
	public void new_login(){
		if(Welcome_page.facebookConnector.getFacebook().isSessionValid()){
			new FacebookLogin().execute();
		}else{
			
			Toast.makeText(Weldone_Page.this,
					"No Session...Trying for Session", Toast.LENGTH_LONG)
					.show();
			Welcome_page.facebookConnector.getFacebook().authorize(Weldone_Page.this, FacebookConstant.PERMISSIONS, new LoginDialogListener());
		}
		
	}
	
	private class FacebookLogin extends AsyncTask<String, Integer, String> {

		ProgressDialog progress;

		JSONObject json = null;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			progress = ProgressDialog.show(Weldone_Page.this, "",
					"processing....");
		}

		@Override
		protected String doInBackground(String... params) {

			try {
				json = Util.parseJson(Welcome_page.facebookConnector.getFacebook().request(
						"me"));


			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FacebookError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(String result) {
			if (json != null) {
				// addFriendsToArrayList();
				Bundle bundle=new Bundle();
				bundle.putString("message", report);
				try {
					Welcome_page.facebookConnector.getFacebook().request("me/feed", bundle, "POST");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

			startActivity(new Intent(getApplicationContext(), Weldone_Page.class));
			finish();

			} else {
				System.out.println("else=======================");
				Toast.makeText(Weldone_Page.this, " No Response.",Toast.LENGTH_LONG).show();
			}
			progress.dismiss();
		}
	}
	
	// Facebook login dialog
	public final class LoginDialogListener implements DialogListener, com.app.milesoft.facebook.Facebook.DialogListener {

		public void onComplete(Bundle values) {
			SessionEvents.onLoginSuccess();
			new FacebookLogin().execute();

		}

		public void onCancel() {
			Toast.makeText(Weldone_Page.this,
					"Something went wrong. Please try again.",
					Toast.LENGTH_LONG).show();
		}

		// @Override
		public void onError(DialogError error) {
			// TODO Auto-generated method stub
			SessionEvents.onLoginError(error.getMessage());

		}

		// @Override
		public void onFacebookError(FacebookError error) {
			// TODO Auto-generated method stub
			SessionEvents.onLoginError(error.getMessage());

		}

		@Override
		public void onBack() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onError(SocialAuthError arg0) {
			// TODO Auto-generated method stub
			
		}

		

	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	private void setData() {
		// TODO Auto-generated method stub

		if (App_constants.trait_one == 1) {
			tt_one.setText(ProfileMe_desc.report_type_a);
		} else if (App_constants.trait_one == 2) {
			tt_one.setText(ProfileMe_desc.report_type_b);
		} else {
			tt_one.setText(ProfileMe_desc.report_type_c);
		}

		// ------------------------------------------------------
		if (App_constants.trait_two == 1) {
			tt_two.setText(R.string.app_trait2_type_a);

		} else if (App_constants.trait_two == 2) {
			tt_two.setText(R.string.app_trait2_type_b);
		} else {
			tt_two.setText(R.string.app_trait2_type_c);
		}
		// -------------------------------------------------------
		if (App_constants.trait_three == 1) {
			tt_three.setText(R.string.app_trait3_type_a);
		} else if (App_constants.trait_three == 2) {
			tt_three.setText(R.string.app_trait3_type_b);
		} else {
			tt_three.setText(R.string.app_trait3_type_c);
		}
		// --------------------------------------------------
		if (App_constants.trait_four == 1) {
			tt_four.setText(R.string.app_trait4_type_a);
		} else if (App_constants.trait_four == 2) {
			tt_four.setText(R.string.app_trait4_type_b);
		} else {
			tt_four.setText(R.string.app_trait4_type_c);
		}
		// ---------------------------------------------------------
		if (App_constants.trait_five == 1) {
			tt_five.setText(R.string.app_trait5_type_a);
		} else if (App_constants.trait_five == 2) {
			tt_five.setText(R.string.app_trait5_type_b);
		} else {
			tt_five.setText(R.string.app_trait5_type_c);
		}
		// ----------------------------------------------------------
		if (App_constants.trait_six == 1) {
			tt_six.setText(R.string.app_trait6_type_a);

		} else if (App_constants.trait_six == 2) {
			tt_six.setText(R.string.app_trait6_type_b);
		} else {
			tt_six.setText(R.string.app_trait6_type_c);
		}
		// ------------------------------------------
		if (App_constants.trait_seven == 1) {
			tt_seven.setText(R.string.app_trait7_type_a);
		} else {
			tt_seven.setText(R.string.app_trait7_type_b);
		}

	}

	private void setBodyUi() {
		// TODO Auto-generated method stub
		findViewById(R.id.btn_back).setOnClickListener(this);

		findViewById(R.id.btn_share).setOnClickListener(this);
		//findViewById(R.id.btn_next).setOnClickListener(this);
		findViewById(R.id.btn_welcome).setOnClickListener(this);

		findViewById(R.id.btn_howItWork).setOnClickListener(this);
		findViewById(R.id.btn_tellAFriend).setOnClickListener(this);
		findViewById(R.id.btn_more).setOnClickListener(this);
		tt_one = (TextView) findViewById(R.id.tt_one);
		tt_two = (TextView) findViewById(R.id.tt_two);
		tt_three = (TextView) findViewById(R.id.tt_three);
		tt_four = (TextView) findViewById(R.id.tt_four);
		tt_five = (TextView) findViewById(R.id.tt_five);
		tt_six = (TextView) findViewById(R.id.tt_six);
		tt_seven = (TextView) findViewById(R.id.tt_seven);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i = new Intent();
		switch (v.getId()) {
		case R.id.btn_back:
			i.setClass(Weldone_Page.this,Welcome_page.class);
			startActivity(i);
			Weldone_Page.this.finish();

			break;

		case R.id.btn_welcome:
			i.setClass(Weldone_Page.this, Welcome_page.class);
			startActivity(i);
			Weldone_Page.this.finish();

			break;
		case R.id.btn_tellAFriend:
			report = App_constants.messgae_upload;
		
			findViewById(R.id.btn_welcome).setBackgroundResource(
					R.drawable.bottommenuwelcome);

			//createPopup();
			createPopuptellAfriend();
			break;
		case R.id.btn_howItWork:

			i.setClass(Weldone_Page.this, How_it_Works.class);
			startActivity(i);
			Weldone_Page.this.finish();

			break;
		case R.id.btn_more:
			i.setClass(Weldone_Page.this, UpSell.class);
			startActivity(i);
			Weldone_Page.this.finish();

			break;
		case R.id.btn_share:
			report=createReport();
			check=true;
			createPopup();

			break;
	
		case R.id.ll_one:
			if(check==true)
			{
				
			}
			else{
				report=App_constants.messgae_upload;
			}
			new_login();

			break;
		case R.id.ll_two:
		
			if(check==true)
			{
				report=report.substring(0,600);
			
				
			}
			
		
			//adapter2 = new SocialAuthAdapter(new ResponseListener());
			adapter.authorize(this, Provider.TWITTER);
			break;
		case R.id.ll_three:
			if(check==true)
			{
				report=report.substring(0,140);
			}
			else{
				report=App_constants.messgae_upload;
			}
			
	
			//Tweet_now();
			adapter.authorize(this, Provider.TWITTER);
			break;

		case R.id.ll_five:

			findViewById(R.id.btn_tellAFriend).setBackgroundResource(
					R.drawable.bottommenutellafiend);
			mpopup.dismiss();

			break;

		case R.id.ll_four:
			if(check==true)
			{
				report=App_constants.email_title+"\n"+report;
			}
	
			final Intent intent = new Intent(Intent.ACTION_SEND);
			intent.setType("plain/text");
			intent.putExtra(Intent.EXTRA_SUBJECT,"You should try out this ProfileMe App");
			intent.putExtra(Intent.EXTRA_TEXT,report);
			startActivity(Intent.createChooser(intent, "Email:"));

			// adapter4 = new SocialAuthAdapter(new ResponseListener());

			break;

		}

	}

	public void new_report() {
		// TODO Auto-generated method stub
		if(check==true)
		{
			report=report.substring(0,600);
		}
		else{
			report=App_constants.messgae_upload;
		}
		
	}
}
