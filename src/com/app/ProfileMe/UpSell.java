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
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.android.facebook.facebook.FacebookConstant;
import com.android.facebook.facebook.SessionEvents;
import com.app.milesoft.facebook.DialogError;
import com.app.milesoft.facebook.FacebookError;
import com.app.milesoft.facebook.Util;

public class UpSell extends Activity implements OnClickListener {
	SocialAuthAdapter adapter;
	ImageView image_click;
	int screen_width, screen_height;
	PopupWindow mpopup;
	View popUpView;
	 //Twitter mTwitter;
	 WebView mWebView;
	 WebViewClient yourWebClient;
	 LinearLayout layout,layout_new;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_upsell);
		
		setBodyUi();
		getDimension();
	
		//mTwitter = new Twitter(this, IConstants.TWITTER_API_KEY, IConstants.TWITTER_SECRET_KEY);
		
	}
/*	private void setUi() {
		// TODO Auto-generated method stub
	      yourWebClient = new WebViewClient()
	       {
	           // Override page so it's load on my view only
	           @Override
	           public boolean shouldOverrideUrlLoading(WebView  view, String  url)
	           {
	            // This line we let me load only pages inside Firstdroid Webpage
	            if ( url.contains("alanstevens") == true )
	               // Load new URL Don't override URL Link
	               return false;
	             
	            // Return true to override url loading (In this case do nothing).
	            return true;
	           }
	       };
		
	}*/
	private void loadData() {
		// TODO Auto-generated method stub
	    //This is the id you gave 
		
		mWebView.setInitialScale(1);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setLoadWithOverviewMode(true);
		mWebView.getSettings().setUseWideViewPort(true);
		mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		mWebView.setScrollbarFadingEnabled(false);//Enable Multitouch if supported by ROM
	   
	        
	       // Load URL
	       mWebView.loadUrl("http://www.amazon.com/dp/B006B5SB0O");
		
	}
	private void getDimension() {
		// TODO Auto-generated method stub
	DisplayMetrics displaymetrics = new DisplayMetrics();
	getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
	screen_height = displaymetrics.heightPixels;
	screen_width = displaymetrics.widthPixels;
	System.out.println("111111"+screen_height);
	System.out.println("222222"+screen_width);
	}

	private void setBodyUi() {
		// TODO Auto-generated method stub
		findViewById(R.id.btn_back).setOnClickListener(this);
		findViewById(R.id.btn_welcome).setOnClickListener(this);
	
		findViewById(R.id.btn_howItWork).setOnClickListener(this);
		findViewById(R.id.btn_tellAFriend).setOnClickListener(this);
		findViewById(R.id.btn_more).setOnClickListener(this);
		findViewById(R.id.img_click).setOnClickListener(this);
		   mWebView = (WebView) findViewById( R.id.webView1);
		   layout=(LinearLayout)findViewById(R.id.lap_new);
		   layout_new=(LinearLayout)findViewById(R.id.lap_old);

	}
	
	
	
	
	
	public void new_login(){
		if(Welcome_page.facebookConnector.getFacebook().isSessionValid()){
			new FacebookLogin().execute();
		}else{
			
			Toast.makeText(UpSell.this,
					"No Session...Trying for Session", Toast.LENGTH_LONG)
					.show();
			Welcome_page.facebookConnector.getFacebook().authorize(UpSell.this, FacebookConstant.PERMISSIONS, new LoginDialogListener());
		}
		
	}
	
	
	
	
	private class FacebookLogin extends AsyncTask<String, Integer, String> {

		ProgressDialog progress;

		JSONObject json = null;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			progress = ProgressDialog.show(UpSell.this, "",
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
				bundle.putString("message", App_constants.messgae_upload);
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
				

			startActivity(new Intent(getApplicationContext(), UpSell.class));
			finish();

			} else {
				System.out.println("else=======================");
				Toast.makeText(UpSell.this, " No Response.",Toast.LENGTH_LONG).show();
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
			Toast.makeText(UpSell.this,
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
	
	
	
	
	
	
	
	
	public void createPopup()
	{
			
		
           popUpView = getLayoutInflater().inflate(R.layout.new_popup, null); // inflating popup layout
            mpopup = new PopupWindow(popUpView, screen_width-screen_width/6, screen_height-screen_height/4, true); //Creation of popup
           mpopup.setAnimationStyle(android.R.style.Animation_Dialog);   
           mpopup.showAtLocation(popUpView, Gravity.BOTTOM, 0, 0);    // Displaying popup 
		      
           LinearLayout ll_one=(LinearLayout)popUpView.findViewById(R.id.ll_one);
           LinearLayout ll_two=(LinearLayout)popUpView.findViewById(R.id.ll_two);
           LinearLayout ll_three=(LinearLayout)popUpView.findViewById(R.id.ll_three);
           LinearLayout ll_four=(LinearLayout)popUpView.findViewById(R.id.ll_four);
           LinearLayout ll_five=(LinearLayout)popUpView.findViewById(R.id.ll_five);
       
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
			// Toast.makeText(Trait1_page.this, providerName + " connected",
			// Toast.LENGTH_LONG).show();

			// Please avoid sending duplicate message. Social Media Providers
			// block duplicate messages.
			
	 if (providerName.equals("linkedin")) {
				System.out.println("val is" + providerName);
				//adapter2.updateStatus(App_constants.messgae_upload);
			}

			Toast.makeText(UpSell.this,
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
	
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i=new Intent();
		switch (v.getId()) {
		case R.id.btn_back:
			i.setClass(UpSell.this, Weldone_Page.class);
			startActivity(i);
			UpSell.this.finish();
			
			break;
		case R.id.btn_welcome:
			i.setClass(UpSell.this, Welcome_page.class);
			startActivity(i);
			UpSell.this.finish();
			
			break;
		case R.id.btn_howItWork:
			i.setClass(UpSell.this, How_it_Works.class);
			startActivity(i);
			UpSell.this.finish();
			
			break;
		case R.id.btn_tellAFriend:
			//sTweet_now();
			createPopup();
	
			
			break;
			
		case R.id.img_click:
			layout_new.setVisibility(View.INVISIBLE);
			layout.setVisibility(View.VISIBLE);
			 loadData();
		
			break;
			
			
			
		case R.id.ll_one:
			 new_login();
			break;
	case R.id.ll_two:
			
			
			//adapter2 = new SocialAuthAdapter(new ResponseListener());
			adapter.authorize(this, Provider.LINKEDIN);
			
			break;
	case R.id.ll_three:
	
		
		adapter.authorize(this, Provider.TWITTER);
		break;
		
		
	case R.id.ll_five:

		findViewById(R.id.btn_tellAFriend).setBackgroundResource(
				R.drawable.bottommenutellafiend);
		mpopup.dismiss();

		
		break;

	case R.id.ll_four:
		final Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("plain/text");
intent.putExtra(Intent.EXTRA_SUBJECT,
				"You should try out this Profile Me App");
intent.putExtra(Intent.EXTRA_TEXT,"Did you know you can profile yourself and other people using the Profile Me App. Try it out to profile your friends and family");
		startActivity(Intent.createChooser(intent, "Email:"));

		//adapter4 = new SocialAuthAdapter(new ResponseListener());
	
		break;
	
			

	
		
	}

}
}
