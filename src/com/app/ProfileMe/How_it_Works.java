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
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.facebook.facebook.FacebookConstant;
import com.android.facebook.facebook.SessionEvents;
import com.app.milesoft.facebook.DialogError;
import com.app.milesoft.facebook.FacebookError;
import com.app.milesoft.facebook.Util;

public class How_it_Works extends Activity implements OnClickListener {
	protected static final int INVISIBLE = 0;
	SocialAuthAdapter adapter;
	View popUpView;
	Context context;
	PopupWindow mpopup;
	SocialAuthAdapter adapter1, adapter2;
	int screen_width, screen_height;
	VideoView play;
	String uri;
	LinearLayout ll_click;
	 int check_id,length;
	 boolean pressed=false;
	 //Twitter mTwitter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_how_it_work);
		setBodyUI();
		getDimension();
		uri = "android.resource://" + getPackageName() + "/"
		+ R.raw.welcomevideo;
		
		
		//mTwitter = new Twitter(this, IConstants.TWITTER_API_KEY, IConstants.TWITTER_SECRET_KEY);
		
		
		
		play.setOnTouchListener(new OnTouchListener() {
			
		@Override
		public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				play.pause();
				pressed=true;
				ll_click.setVisibility(View.VISIBLE);
		
				return false;
			}
		});
		ll_click.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			
				if(pressed==true)
				{
					ll_click.setVisibility(View.INVISIBLE);
					play.start();
					
				}
				else{
					
					ll_click.setVisibility(View.INVISIBLE);
					setVideo();
				}
				
				// TODO Auto-generated method stub
			

			}

		});
		
		

	}
	public void setVideo() {
		play.setVideoURI(Uri.parse(uri));

		if (uri.equals("") || uri.equals(null)) {
			System.out.println("no video");

		} else {
			play.start();
		}
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

	public void new_login(){
		if(Welcome_page.facebookConnector.getFacebook().isSessionValid()){
			new FacebookLogin().execute();
		}else{
			
			Toast.makeText(How_it_Works.this,
					"No Session...Trying for Session", Toast.LENGTH_LONG)
					.show();
			Welcome_page.facebookConnector.getFacebook().authorize(How_it_Works.this, FacebookConstant.PERMISSIONS, new LoginDialogListener());
		}
		
	}
	
	private class FacebookLogin extends AsyncTask<String, Integer, String> {

		ProgressDialog progress;

		JSONObject json = null;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			progress = ProgressDialog.show(How_it_Works.this, "",
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
				

			startActivity(new Intent(getApplicationContext(), How_it_Works.class));
			finish();

			} else {
				System.out.println("else=======================");
				Toast.makeText(How_it_Works.this, " No Response.",Toast.LENGTH_LONG).show();
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
			Toast.makeText(How_it_Works.this,
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
	
	
	private void setBodyUI() {
		findViewById(R.id.btn_back).setOnClickListener(this);

		findViewById(R.id.btn_GetStarted).setOnClickListener(this);

		findViewById(R.id.btn_welcome).setOnClickListener(this);
	
		findViewById(R.id.btn_howItWork).setOnClickListener(this);
		findViewById(R.id.btn_tellAFriend).setOnClickListener(this);
		findViewById(R.id.btn_more).setOnClickListener(this);
		play=(VideoView)findViewById(R.id.videoView1);
		ll_click=(LinearLayout)findViewById(R.id.ll_click);

	}
	
	/*public void Tweet_now() {
		// TODO Auto-generated method stub
		mTwitter.setAuthListener(new Twitter.TwitterAuthListener() {
			
			public void onAuthenticated() {
				try {
					mTwitter.tweet(App_constants.messgae_upload);
					//Toast.makeText(Welcome_page.this, "Message posted to twitter", Toast.LENGTH_SHORT);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		mTwitter.authenticate();
	}*/
	
	public void createPopup() {
		
		adapter = new SocialAuthAdapter(new ResponseListener());
		
		popUpView = getLayoutInflater().inflate(R.layout.new_popup, null); // inflating
																			// popup
																			// layout
		mpopup = new PopupWindow(popUpView, screen_width - screen_width / 6,
				screen_height - screen_height / 4, true); // Creation of popup
		mpopup.setAnimationStyle(android.R.style.Animation_Dialog);
		mpopup.showAtLocation(popUpView, Gravity.CENTER, 0, 0); // Displaying
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

		//adapter2 = new SocialAuthAdapter(new ResponseListener());
	
		//adapter2.addProvider(Provider.LINKEDIN, R.drawable.bntlinkedin);

		//adapter2.enable(ll_two);
	

	}

	private final class ResponseListener implements DialogListener {
		@Override
		public void onComplete(Bundle values) {

			Log.d("ShareButton", "Authentication Successful");

			final String providerName = values
					.getString(SocialAuthAdapter.PROVIDER);
			Log.d("ShareButton", "Provider Name = " + providerName);
		
			/*if (providerName.equals("linkedin")) {
				System.out.println("val is" + providerName);
				//adapter2.updateStatus(App_constants.messgae_upload);
			}

			Toast.makeText(How_it_Works.this,
					"Message posted on " + providerName, Toast.LENGTH_LONG)
					.show();

			findViewById(R.id.btn_tellAFriend).setBackgroundResource(
					R.drawable.bottommenutellafiend);
			*/
			AlertDialog.Builder alert = new AlertDialog.Builder(How_it_Works.this);

			alert.setTitle("Enter Text For Post");
			alert.setMessage("Post Message");

			// Set an EditText view to get user input
			final EditText input = new EditText(How_it_Works.this);
			input.setHeight(140);
		
			input.setText(App_constants.messgae_upload);	
			
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
		Intent i = new Intent();
		switch (v.getId()) {
		case R.id.btn_back:
			i.setClass(How_it_Works.this, Welcome_page.class);
			startActivity(i);
			How_it_Works.this.finish();

			break;

		case R.id.btn_GetStarted:
			i.setClass(How_it_Works.this, Welcome_page.class);
			startActivity(i);
			How_it_Works.this.finish();

			break;

		case R.id.btn_welcome:
		
			i.setClass(How_it_Works.this, Welcome_page.class);
			startActivity(i);
			How_it_Works.this.finish();

			break;
		case R.id.btn_tellAFriend:
			findViewById(R.id.btn_howItWork).setBackgroundResource(R.drawable.bottommenuhowitworks);
			//findViewById(R.id.btn_tellAFriend).setBackgroundResource(R.drawab);
		
			createPopup();
			break;
		case R.id.btn_howItWork:
			/*
			 * i.setClass(How_it_Works.this, How_it_Works.class);
			 * startActivity(i); How_it_Works.this.finish();
			 */

			break;
		case R.id.btn_more:
			i.setClass(How_it_Works.this, UpSell.class);
			startActivity(i);
			How_it_Works.this.finish();

			break;

		case R.id.ll_one:
		 //new_login();
		 adapter.authorize(this, Provider.FACEBOOK);
			
			break;
	case R.id.ll_two:
			
			check_id=2;
			//adapter2 = new SocialAuthAdapter(new ResponseListener());
			adapter.authorize(this, Provider.LINKEDIN);
			
			break;
	case R.id.ll_three:
		
		//Tweet_now();
		
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

		default:
			break;
		}

	}
	private final class MessageListener implements SocialAuthListener<Integer> {
		@Override
		public void onExecute(String provider, Integer t) {
			Integer status = t;
			if (status.intValue() == 200 || status.intValue() == 201
					|| status.intValue() == 204)
				Toast.makeText(How_it_Works.this,
						"Message posted on " + provider, Toast.LENGTH_LONG)
						.show();
			else
				Toast.makeText(How_it_Works.this,
						"Message not posted on " + provider, Toast.LENGTH_LONG)
						.show();
		}

		@Override
		public void onError(SocialAuthError arg0) {
			// TODO Auto-generated method stub
			
		}
	}

}
