package com.app.ProfileMe;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.brickred.socialauth.android.DialogListener;
import org.brickred.socialauth.android.SocialAuthAdapter;
import org.brickred.socialauth.android.SocialAuthAdapter.Provider;
import org.brickred.socialauth.android.SocialAuthError;
import org.brickred.socialauth.android.SocialAuthListener;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.android.facebook.facebook.FacebookConnector;
import com.android.facebook.facebook.FacebookConstant;
import com.android.facebook.facebook.SessionEvents;
import com.app.milesoft.facebook.DialogError;
import com.app.milesoft.facebook.FacebookError;
import com.app.milesoft.facebook.Util;

public class Welcome_page extends Activity implements OnClickListener {

	Intent pass_intent;
	
	PopupWindow mpopup;
	View popUpView;
	//SocialAuthAdapter adapter1, adapter2,adapter3;
	int screen_width, screen_height;
	int check_id;
	 //Twitter mTwitter;
	 static FacebookConnector facebookConnector;
	
	 SocialAuthAdapter adapter;
		String check;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		facebookConnector = new FacebookConnector(FacebookConstant.APP_ID, this,getApplicationContext(),FacebookConstant.PERMISSIONS);
		setBodyUI();
		getDimension();

		App_constants.front_image=null;
		App_constants.front_cam=false;

		//mTwitter = new Twitter(this, IConstants.TWITTER_API_KEY,IConstants.TWITTER_SECRET_KEY);

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
	
	public void new_login(){
		if(facebookConnector.getFacebook().isSessionValid()){
			new FacebookLogin().execute();
		}else{
			
			Toast.makeText(Welcome_page.this,
					"No Session...Trying for Session", Toast.LENGTH_LONG).show();
			facebookConnector.getFacebook().authorize(Welcome_page.this, FacebookConstant.PERMISSIONS, new LoginDialogListener());
		}
	}

	private class FacebookLogin extends AsyncTask<String, Integer, String> {

		ProgressDialog progress;

		JSONObject json = null;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			progress = ProgressDialog.show(Welcome_page.this, "",
					"processing....");
		}

		@Override
		protected String doInBackground(String... params) {

			try {
				json = Util.parseJson(facebookConnector.getFacebook().request(
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
					facebookConnector.getFacebook().request("me/feed", bundle, "POST");
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
				

			startActivity(new Intent(getApplicationContext(), Welcome_page.class));
			finish();

			} else {
				System.out.println("else=======================");
				Toast.makeText(Welcome_page.this, " No Response.",Toast.LENGTH_LONG).show();
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
			Toast.makeText(Welcome_page.this,
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

	public void createPopup() {

		adapter = new SocialAuthAdapter(new ResponseListener());
		
		popUpView = getLayoutInflater().inflate(R.layout.new_popup, null); // inflating
		// popup
		// layout
		mpopup = new PopupWindow(popUpView, screen_width - screen_width / 6,
				screen_height - screen_height / 4, true); // Creation of popup
		mpopup.setAnimationStyle(android.R.style.Animation_Dialog);
		mpopup.showAtLocation(popUpView, Gravity.BOTTOM, 0, 0); // Displaying
		// popup

		LinearLayout ll_one = (LinearLayout) popUpView.findViewById(R.id.ll_one);
		LinearLayout ll_two = (LinearLayout) popUpView.findViewById(R.id.ll_two);
		LinearLayout ll_three = (LinearLayout) popUpView.findViewById(R.id.ll_three);
		LinearLayout ll_four = (LinearLayout) popUpView.findViewById(R.id.ll_four);
		LinearLayout ll_five = (LinearLayout) popUpView.findViewById(R.id.ll_five);

		ll_one.setOnClickListener(this);

		ll_two.setOnClickListener(this);
		ll_three.setOnClickListener(this);

		ll_four.setOnClickListener(this);
		ll_five.setOnClickListener(this);

		//adapter.enable(ll_two);
		
		/*adapter2 = new SocialAuthAdapter(new ResponseListener());

		adapter2.addProvider(Provider.LINKEDIN, R.drawable.bntlinkedin);

		adapter2.enable(ll_two);
		
		 */

	}

	private final class ResponseListener implements DialogListener {
		@Override
		public void onComplete(Bundle values) {

			Log.d("ShareButton", "Authentication Successful");

			final String providerName = values.getString(SocialAuthAdapter.PROVIDER);
			
			Log.d("ShareButton", "Provider Name = " + providerName);
			// Toast.makeText(Trait1_page.this, providerName + " connected",
			// Toast.LENGTH_LONG).show();

			// Please avoid sending duplicate message. Social Media Providers
			// block duplicate messages.
			System.out.println("11111111111111" + check_id);
			
			
			/*if (providerName.equals("linkedin")) {
				System.out.println("val is" + providerName);
				//adapter2.updateStatus(App_constants.messgae_upload);
				adapter.updateStatus(App_constants.messgae_upload,
						new MessageListener(), false);
			}
			if (providerName.equals("twitter")) {
				System.out.println("val is" + providerName);
				//adapter3.updateStatus(App_constants.messgae_upload);
				adapter.updateStatus(App_constants.messgae_upload,
						new MessageListener(), false);
			}
			*/
			
			AlertDialog.Builder alert = new AlertDialog.Builder(Welcome_page.this);

			alert.setTitle("Enter Text For Post");
			alert.setMessage("Post Message");

			// Set an EditText view to get user input
			final EditText input = new EditText(Welcome_page.this);
			input.setHeight(140);
			if (check.equals("FB")) {
				input.setText(App_constants.messgae_upload);
			} else {
				input.setText(App_constants.messgae_upload);
			}
			alert.setView(input);

			alert.setPositiveButton("Ok",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog,
								int whichButton) {
							Editable value = input.getText();

							adapter.updateStatus(input.getText().toString(),
									new MessageListener(), false);
							// Do something with value!
						}
					});

			alert.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog,
								int whichButton) {
							// Canceled.
						}
					});

			alert.show();

			// }
			// });

			/*Toast.makeText(Welcome_page.this,
					"Message posted on " + providerName, Toast.LENGTH_LONG)
					.show();

			findViewById(R.id.btn_tell_friend).setBackgroundResource(
					R.drawable.bottommenutellafiend);
			*/
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

	private void setBodyUI() {

		findViewById(R.id.btn_female).setOnClickListener(this);
		findViewById(R.id.btn_male).setOnClickListener(this);

		findViewById(R.id.btn_welcome).setOnClickListener(this);
		findViewById(R.id.btn_hwitworks).setOnClickListener(this);
		findViewById(R.id.btn_tell_friend).setOnClickListener(this);
		findViewById(R.id.btn_more).setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i = new Intent();
		switch (v.getId()) {

		case R.id.btn_female:
			App_constants.user_gender = "Female";
			v.setBackgroundResource(R.drawable.buttonfemaleactive);

			i.setClass(Welcome_page.this, Trait1_page.class);
			startActivity(i);
			Welcome_page.this.finish();
			break;
		case R.id.btn_male:
			App_constants.user_gender = "Male";
			v.setBackgroundResource(R.drawable.buttonmaleactive);
			i.setClass(Welcome_page.this, Trait1_page.class);
			startActivity(i);
			Welcome_page.this.finish();

			break;
		case R.id.btn_welcome:

			break;
		case R.id.btn_tell_friend:
			findViewById(R.id.btn_welcome).setBackgroundResource(
					R.drawable.bottommenuwelcome);

			createPopup();

			break;

		case R.id.btn_hwitworks:

			i.setClass(Welcome_page.this, How_it_Works.class);
			startActivity(i);
			Welcome_page.this.finish();

			break;
		case R.id.btn_more:

			i.setClass(Welcome_page.this, UpSell.class);
			startActivity(i);
			Welcome_page.this.finish();

			break;
		case R.id.ll_one:

			//new_login();
			check = "FB";
			adapter.authorize(this, Provider.FACEBOOK);
			
			break;
		case R.id.ll_two:
			check = "LINKEDIN";
			//adapter2 = new SocialAuthAdapter(new ResponseListener());
			adapter.authorize(this, Provider.LINKEDIN);
			
			break;
		case R.id.ll_three:
			//Tweet_now();
			check = "TWITTER";
			//adapter2 = new SocialAuthAdapter(new ResponseListener());
			adapter.authorize(this, Provider.TWITTER);
			break;

		case R.id.ll_five:

			findViewById(R.id.btn_tell_friend).setBackgroundResource(R.drawable.bottommenutellafiend);
			mpopup.dismiss();

			break;

		case R.id.ll_four:
			final Intent intent = new Intent(Intent.ACTION_SEND);
			intent.setType("plain/text");
			intent.putExtra(Intent.EXTRA_SUBJECT,
					"You should try out this ProfileMe App");
			intent
					.putExtra(
							Intent.EXTRA_TEXT,
							"I just profiled myself using the ProfileMe App. Try it out for yourself. You'll find ProfileMe in the iTunes App store or in Google Play.");
			startActivity(Intent.createChooser(intent, "Email:"));

			break;
		default:
			break;
		}
	}

	/*public void Tweet_now() {
		// TODO Auto-generated method stub
		mTwitter.setAuthListener(new Twitter.TwitterAuthListener() {

			public void onAuthenticated() {
				try {
					mTwitter.tweet(App_constants.messgae_upload);
					// Toast.makeText(Welcome_page.this,
					// "Message posted to twitter", Toast.LENGTH_SHORT);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		mTwitter.authenticate();
	}*/
	
	// To get status of message after authentication
		private final class MessageListener implements SocialAuthListener<Integer> {
			@Override
			public void onExecute(String provider, Integer t) {
				Integer status = t;
				if (status.intValue() == 200 || status.intValue() == 201
						|| status.intValue() == 204)
					Toast.makeText(Welcome_page.this,
							"Message posted on " + provider, Toast.LENGTH_LONG)
							.show();
				else
					Toast.makeText(Welcome_page.this,
							"Message not posted on " + provider, Toast.LENGTH_LONG)
							.show();
			}

			@Override
			public void onError(SocialAuthError arg0) {
				// TODO Auto-generated method stub
				
			}
		}

}
