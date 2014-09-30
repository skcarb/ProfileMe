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
import android.graphics.Bitmap;
import android.graphics.Color;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.android.facebook.facebook.FacebookConstant;
import com.android.facebook.facebook.SessionEvents;
import com.app.milesoft.facebook.DialogError;
import com.app.milesoft.facebook.FacebookError;
import com.app.milesoft.facebook.Util;


public class Trait7_page extends Activity implements OnClickListener {
	 ImageView opt_one,opt_two,opt_three,image_full;
	  static Bitmap image_one,image_two,image_three;
boolean select_image;
//Twitter mTwitter;
		
	   PopupWindow mpopup;
	   View popUpView ;
	   SocialAuthAdapter adapter;
	   int screen_width, screen_height;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_trait_seven);
		App_constants.trait_selected=7;
		setBodyUI();
		getDimension();
		setImage();
		opt_one.setOnClickListener(this);
		opt_two.setOnClickListener(this);
		//opt_three.setOnClickListener(this);
		// mTwitter = new Twitter(this, IConstants.TWITTER_API_KEY, IConstants.TWITTER_SECRET_KEY);
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
			
			Toast.makeText(Trait7_page.this,
					"No Session...Trying for Session", Toast.LENGTH_LONG)
					.show();
			Welcome_page.facebookConnector.getFacebook().authorize(Trait7_page.this, FacebookConstant.PERMISSIONS, new LoginDialogListener());
		}
		
	}
	
	
	
	
	private class FacebookLogin extends AsyncTask<String, Integer, String> {

		ProgressDialog progress;

		JSONObject json = null;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			progress = ProgressDialog.show(Trait7_page.this, "",
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
				

			startActivity(new Intent(getApplicationContext(), Trait7_page.class));
			finish();

			} else {
				System.out.println("else=======================");
				Toast.makeText(Trait7_page.this, " No Response.",Toast.LENGTH_LONG).show();
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
			Toast.makeText(Trait7_page.this,
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
			
			adapter = new SocialAuthAdapter(new ResponseListener());
		
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
	
			/*if (providerName.equals("linkedin")) {
				System.out.println("val is" + providerName);
				//adapter2.updateStatus(App_constants.messgae_upload);
				adapter.updateStatus(App_constants.messgae_upload,new MessageListener(), false);
			}
			if (providerName.equals("twitter")) {
				System.out.println("val is" + providerName);
				//adapter3.updateStatus(App_constants.messgae_upload);
				adapter.updateStatus(App_constants.messgae_upload,new MessageListener(), false);
			}

			Toast.makeText(Trait7_page.this,
					"Message posted on " + providerName, Toast.LENGTH_LONG)
					.show();

			findViewById(R.id.btn_tellAFriend).setBackgroundResource(
					R.drawable.bottommenutellafiend);
			*/
			
			AlertDialog.Builder alert = new AlertDialog.Builder(Trait7_page.this);

			alert.setTitle("Enter Text For Post");
			alert.setMessage("Post Message");

			// Set an EditText view to get user input
			final EditText input = new EditText(Trait7_page.this);
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
	private void setImage() {
		// TODO Auto-generated method stub
		if(App_constants.user_gender.equals("Female"))
		{
			opt_one.setBackgroundResource(R.drawable.checkfemale_lighter);
			opt_two.setBackgroundResource(R.drawable.checkfemale_darker);
		

		}
		else{
			opt_one.setBackgroundResource(R.drawable.check_lighter);
			opt_two.setBackgroundResource(R.drawable.check_darker);
			
		
			
			
		}
		
	}

	private void setBodyUI() {
		findViewById(R.id.btn_back).setOnClickListener(this);

		findViewById(R.id.btn_video).setOnClickListener(this);
		findViewById(R.id.btn_description).setOnClickListener(this);
		findViewById(R.id.btn_next).setOnClickListener(this);
		findViewById(R.id.btn_welcome).setOnClickListener(this);

		findViewById(R.id.btn_howItWork).setOnClickListener(this);
		findViewById(R.id.btn_tellAFriend).setOnClickListener(this);
		findViewById(R.id.btn_more).setOnClickListener(this);
		findViewById(R.id.btn_pre).setOnClickListener(this);
		opt_one=(ImageView)findViewById(R.id.img_one);
		opt_two=(ImageView)findViewById(R.id.img_two);
	/*	opt_three=(ImageView)findViewById(R.id.image_optn3);
		image_full=(ImageView)findViewById(R.id.imageView1);*/
		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i=new Intent();
		switch (v.getId()) {
		case R.id.btn_back:
			i.setClass(Trait7_page.this, Welcome_page.class);
			startActivity(i);
			Trait7_page.this.finish();
			
			break;

		case R.id.btn_pre:
			i.setClass(Trait7_page.this, Trait6_page.class);
			startActivity(i);
			Trait7_page.this.finish();
			
			break;
		case R.id.btn_video:
			App_constants.trait_count=7;
			i.setClass(Trait7_page.this, Video_play.class);
			startActivity(i);
			Trait7_page.this.finish();
		
		break;
		case R.id.btn_description:
			App_constants.trait_count=7;
			i.setClass(Trait7_page.this, Trait_desc.class);
			startActivity(i);
			Trait7_page.this.finish();
			break;
		case R.id.btn_next:
			if(select_image==true)
			{
				i.setClass(Trait7_page.this,Weldone_Page.class);
				startActivity(i);
				Trait7_page.this.finish();
			}
			else{
				Toast toast=Toast.makeText(Trait7_page.this, "Please select the seventh trait", Toast.LENGTH_SHORT);
				toast.getView().setBackgroundColor(Color.RED);
				toast.show();
			}
		
			break;
	case R.id.btn_welcome:
		i.setClass(Trait7_page.this, Welcome_page.class);
		startActivity(i);
		Trait7_page.this.finish();
			
			break;
		case R.id.btn_tellAFriend:
		
			findViewById(R.id.btn_welcome).setBackgroundResource(R.drawable.bottommenuwelcome);

		
			createPopup();
	
			break;
		case R.id.btn_howItWork:
		
			i.setClass(Trait7_page.this, How_it_Works.class);
			startActivity(i);
			Trait7_page.this.finish();
			
			break;

		case R.id.btn_more:
			i.setClass(Trait7_page.this, UpSell.class);
			startActivity(i);
			Trait7_page.this.finish();
			
			break;
		case R.id.img_one:
			select_image=true;
			i.setClass(Trait7_page.this, Image_full.class);
			startActivity(i);
			Trait7_page.this.finish();
			App_constants.trait_seven=1;
			
			
			break;
		case R.id.img_two:
			select_image=true;
			i.setClass(Trait7_page.this, Image_full.class);
			startActivity(i);
			Trait7_page.this.finish();
			App_constants.trait_seven=2;
			
			
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
				Toast.makeText(Trait7_page.this,
						"Message posted on " + provider, Toast.LENGTH_LONG)
						.show();
			else
				Toast.makeText(Trait7_page.this,
						"Message not posted on " + provider, Toast.LENGTH_LONG)
						.show();
		}

		@Override
		public void onError(SocialAuthError arg0) {
			// TODO Auto-generated method stub
			
		}
	}
}