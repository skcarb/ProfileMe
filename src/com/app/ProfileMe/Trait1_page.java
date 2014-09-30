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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.facebook.facebook.FacebookConstant;
import com.android.facebook.facebook.SessionEvents;
import com.app.milesoft.facebook.DialogError;
import com.app.milesoft.facebook.FacebookError;
import com.app.milesoft.facebook.Util;

public class Trait1_page extends Activity implements OnClickListener {
	Button btnOpenPopup, btn_update;
	SocialAuthAdapter adapter1, adapter2;
	View popUpView;
	Context context;
	PopupWindow mpopup;
	VideoView desc_video;

	ImageView opt_one, opt_two, opt_three, full_image;
	static Bitmap image_one, image_two, image_three;
	boolean seltect_image;
	LinearLayout ll_popup;
	EditText edit_message;
	int screen_width, screen_height;
	Toast toast;
	//Twitter mTwitter;
	String toast_str;
	String check;
	SocialAuthAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_trait_one);
		App_constants.trait_selected = 1;
		setBodyUI();
		getDimension();
		setImage();
		
		//mTwitter = new Twitter(this, IConstants.TWITTER_API_KEY,IConstants.TWITTER_SECRET_KEY);

		opt_one.setOnClickListener(this);
		opt_two.setOnClickListener(this);
		opt_three.setOnClickListener(this);
	}

	/*
	 * public static Bitmap reduceImageSize(Bitmap m){
	 * 
	 * 
	 * ByteArrayOutputStream bos = new ByteArrayOutputStream();
	 * m.compress(CompressFormat.PNG, 0 ignored for PNG, bos); byte[] bitmapdata
	 * = bos.toByteArray(); BitmapFactory.Options o = new
	 * BitmapFactory.Options(); o.inJustDecodeBounds = true;
	 * BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.length,o); final
	 * int REQUIRED_SIZE=150; int width_tmp=o.outWidth, height_tmp=o.outHeight;
	 * int scale=1; while(true){ if(width_tmp/2 < REQUIRED_SIZE || height_tmp/2
	 * < REQUIRED_SIZE) break; width_tmp/=2; height_tmp/=2; scale*=2; }
	 * 
	 * //Decode with inSampleSize BitmapFactory.Options o2 = new
	 * BitmapFactory.Options(); o2.inSampleSize=scale; m =
	 * BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.length,o2);
	 * return m; }
	 */

	private void setImage() {
		// TODO Auto-generated method stub
		if (App_constants.user_gender.equals("Female")) {
			/*
			 * opt_one.setPadding(3, 3, 3, 3); opt_two.setPadding(3, 3, 3, 3);
			 * opt_three.setPadding(3, 3, 3, 3);
			 */
			opt_one.setBackgroundResource(R.drawable.six_full);
			opt_two.setBackgroundResource(R.drawable.newthree_full);
			opt_three.setBackgroundResource(R.drawable.newnine_full);

		} else {
			opt_one.setBackgroundResource(R.drawable.one_full);
			opt_two.setBackgroundResource(R.drawable.nine_full);
			opt_three.setBackgroundResource(R.drawable.newtwo_full);

		}

	}

	public void new_login(){
		if(Welcome_page.facebookConnector.getFacebook().isSessionValid()){
			new FacebookLogin().execute();
		}else{
			
			Toast.makeText(Trait1_page.this,
					"No Session...Trying for Session", Toast.LENGTH_LONG)
					.show();
			Welcome_page.facebookConnector.getFacebook().authorize(Trait1_page.this, FacebookConstant.PERMISSIONS, new LoginDialogListener());
		}
		
	}

	private class FacebookLogin extends AsyncTask<String, Integer, String> {

		ProgressDialog progress;

		JSONObject json = null;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			progress = ProgressDialog.show(Trait1_page.this, "",
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
				

			startActivity(new Intent(getApplicationContext(), Trait1_page.class));
			finish();

			} else {
				System.out.println("else=======================");
				Toast.makeText(Trait1_page.this, " No Response.",Toast.LENGTH_LONG).show();
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
			Toast.makeText(Trait1_page.this,
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
		btnOpenPopup = (Button) findViewById(R.id.btn_vedio);
		findViewById(R.id.btn_back).setOnClickListener(this);

		findViewById(R.id.btn_vedio).setOnClickListener(this);
		findViewById(R.id.btn_description).setOnClickListener(this);
		findViewById(R.id.btn_next).setOnClickListener(this);
		findViewById(R.id.btn_welcome).setOnClickListener(this);

		findViewById(R.id.btn_hwitworks).setOnClickListener(this);
		findViewById(R.id.btn_tell_friend).setOnClickListener(this);
		findViewById(R.id.btn_more).setOnClickListener(this);
		opt_one = (ImageView) findViewById(R.id.image_optn1);
		opt_two = (ImageView) findViewById(R.id.image_optn2);
		opt_three = (ImageView) findViewById(R.id.image_optn3);
		full_image = (ImageView) findViewById(R.id.image_full);
		ll_popup = (LinearLayout) findViewById(R.id.ll_popup);

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

		//adapter2 = new SocialAuthAdapter(new ResponseListener());
		
		//adapter2.addProvider(Provider.LINKEDIN, R.drawable.bntlinkedin);

		//adapter2.enable(ll_two);

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

	private void getDimension() {
		// TODO Auto-generated method stub
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		screen_height = displaymetrics.heightPixels;
		screen_width = displaymetrics.widthPixels;
		System.out.println("111111" + screen_height);
		System.out.println("222222" + screen_width);
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * private void open_dilog() { // TODO Auto-generated method stub
	 * 
	 * //set up dialog dialog = new Dialog(Trait1_page.this);
	 * dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	 * dialog.setContentView(R.layout.share_bar); //dialog.setTitle(signal);
	 * dialog.setCancelable(false);
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * //set up button btn_update = (Button)dialog.findViewById(R.id.update);
	 * edit_message = (EditText)dialog.findViewById(R.id.editTxt);
	 * 
	 * 
	 * dialog.show();
	 * 
	 * 
	 * }
	 */

	private final class ResponseListener implements DialogListener {
		@Override
		public void onComplete(Bundle values) {

			Log.d("ShareButton", "Authentication Successful");

			final String providerName = values.getString(SocialAuthAdapter.PROVIDER);
			Log.d("ShareButton", "Provider Name = " + providerName);
			
			AlertDialog.Builder alert = new AlertDialog.Builder(Trait1_page.this);

			alert.setTitle("Enter Text For Post");
			alert.setMessage("Post Message");

			// Set an EditText view to get user input
			final EditText input = new EditText(Trait1_page.this);
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
			
			/*
			if (providerName.equals("linkedin")) {
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

			Toast.makeText(Trait1_page.this,
					"Message posted on " + providerName, Toast.LENGTH_LONG)
					.show();

			findViewById(R.id.btn_tell_friend).setBackgroundResource(R.drawable.bottommenutellafiend);
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

	/*
	 * 
	 * public void startVideo() { // TODO Auto-generated method stub
	 * 
	 * 
	 * String uri = "android.resource://" + getPackageName() + "/" +
	 * R.raw.confidence; System.out.println("uri is "+uri); MediaController mc =
	 * new MediaController(this); desc_video.setMediaController(mc);
	 * //desc_video.setVideoURI(uri);
	 * 
	 * desc_video.setVideoURI(Uri.parse(uri)); desc_video.requestFocus();
	 * 
	 * if(uri.equals("") || uri.equals(null)){ System.out.println("no video");
	 * 
	 * } else{ desc_video.start(); }
	 * 
	 * }
	 */

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i = new Intent();
		switch (v.getId()) {
		case R.id.btn_back:
			i.setClass(Trait1_page.this, Welcome_page.class);
			startActivity(i);
			Trait1_page.this.finish();

			break;

		case R.id.btn_vedio:
			App_constants.trait_count = 1;
			i.setClass(Trait1_page.this, Video_play.class);
			startActivity(i);
			Trait1_page.this.finish();
			break;
		case R.id.btn_description:
			App_constants.trait_count = 1;
			i.setClass(Trait1_page.this, Trait_desc.class);
			startActivity(i);
			Trait1_page.this.finish();
			break;
		case R.id.btn_next:
			if (seltect_image == true) {
				i.setClass(Trait1_page.this, Trait2_page.class);
				startActivity(i);
				Trait1_page.this.finish();
			} else {
				toast_str = "Please select the first trait";
				toast = Toast.makeText(Trait1_page.this, toast_str,
						Toast.LENGTH_SHORT);
				toast.getView().setBackgroundColor(Color.RED);
				toast.show();
			}

			break;
		case R.id.btn_welcome:
			i.setClass(Trait1_page.this, Welcome_page.class);
			startActivity(i);
			Trait1_page.this.finish();
			break;
		case R.id.btn_tell_friend:
			findViewById(R.id.btn_welcome).setBackgroundResource(
					R.drawable.bottommenuwelcome);
		

			createPopup();

			break;
		case R.id.btn_hwitworks:

			i.setClass(Trait1_page.this, How_it_Works.class);
			startActivity(i);
			Trait1_page.this.finish();

			break;
		case R.id.btn_more:
			i.setClass(Trait1_page.this, UpSell.class);
			startActivity(i);
			Trait1_page.this.finish();

			break;
		case R.id.image_optn1:
			seltect_image = true;

			i.setClass(Trait1_page.this, Image_full.class);
			startActivity(i);
			Trait1_page.this.finish();

			App_constants.trait_one = 1;

			break;
		case R.id.image_optn2:
			seltect_image = true;

			i.setClass(Trait1_page.this, Image_full.class);
			startActivity(i);
			Trait1_page.this.finish();

			App_constants.trait_one = 2;

			break;
		case R.id.image_optn3:
			seltect_image = true;

			i.setClass(Trait1_page.this, Image_full.class);
			startActivity(i);
			Trait1_page.this.finish();

			App_constants.trait_one = 3;

			break;
		case R.id.ll_one:
			// new_login();
			 check = "FB";
			 adapter.authorize(this, Provider.FACEBOOK);
			break;
		case R.id.ll_two:

			adapter2.setTitleVisible(false);
			adapter.authorize(this, Provider.LINKEDIN);
		
			break;
		case R.id.ll_three:
			//Tweet_now();
			adapter.authorize(this, Provider.TWITTER);
			break;


		case R.id.ll_five:

			findViewById(R.id.btn_tell_friend).setBackgroundResource(
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
	
			// adapter4 = new SocialAuthAdapter(new ResponseListener());

			break;

		default:
			break;
		}

	}
	// To get status of message after authentication
			private final class MessageListener implements SocialAuthListener<Integer> {
				@Override
				public void onExecute(String provider, Integer t) {
					Integer status = t;
					if (status.intValue() == 200 || status.intValue() == 201
							|| status.intValue() == 204)
						Toast.makeText(Trait1_page.this,
								"Message posted on " + provider, Toast.LENGTH_LONG)
								.show();
					else
						Toast.makeText(Trait1_page.this,
								"Message not posted on " + provider, Toast.LENGTH_LONG)
								.show();
				}

				@Override
				public void onError(SocialAuthError arg0) {
					// TODO Auto-generated method stub
					
				}
			}
}
