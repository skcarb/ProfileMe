package com.app.ProfileMe;

import android.app.Activity;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;

import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

import android.widget.Button;
import android.widget.LinearLayout;

import android.widget.VideoView;

public class Video_play extends Activity {
	VideoView play;
	Intent i;
	String uri;
	int height, width;
	Button dismiss;

	LinearLayout ll_click;
	boolean pressed=false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.play_video);
		setBodyUi();
		setClass();

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

		dismiss.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				play.stopPlayback();

				startActivity(i);
				Video_play.this.finish();

			}

		});

	}

	private void setBodyUi() {
		// TODO Auto-generated method stub
		i = new Intent();
		play = (VideoView) findViewById(R.id.videoView1);

		dismiss = (Button) findViewById(R.id.btn_dismiss);
		//play_button = (Button) findViewById(R.id.btn_play);
		ll_click = (LinearLayout) findViewById(R.id.ll_click);
		
	}

	public void setVideo() {
		play.setVideoURI(Uri.parse(uri));

		if (uri.equals("") || uri.equals(null)) {
			System.out.println("no video");

		} else {
			play.start();
		}
	}

	public void setClass() {
		// TODO Auto-generated method stub
		switch (App_constants.trait_count) {
		case 1:
			uri = "android.resource://" + getPackageName() + "/"
					+ R.raw.confidence;
			System.out.println("uri is " + uri);
			i.setClass(Video_play.this, Trait1_page.class);
			break;
		case 2:
			uri = "android.resource://" + getPackageName() + "/"
					+ R.raw.friendliness;
			System.out.println("uri is " + uri);
			i.setClass(Video_play.this, Trait2_page.class);
			break;
		case 3:
			uri = "android.resource://" + getPackageName() + "/"
					+ R.raw.tolerance;
			System.out.println("uri is " + uri);
			i.setClass(Video_play.this, Trait3_page.class);

			break;
		case 4:
			uri = "android.resource://" + getPackageName() + "/"
					+ R.raw.senseofhumour;
			System.out.println("uri is " + uri);
			i.setClass(Video_play.this, Trait4_page.class);
			break;
		case 5:
			uri = "android.resource://" + getPackageName() + "/"
					+ R.raw.generosity;
			System.out.println("uri is " + uri);
			i.setClass(Video_play.this, Trait5_page.class);
			break;
		case 6:
			uri = "android.resource://" + getPackageName() + "/"
					+ R.raw.worldview;
			System.out.println("uri is " + uri);
			i.setClass(Video_play.this, Trait6_page.class);
			break;
		case 7:
			uri = "android.resource://" + getPackageName() + "/"
					+ R.raw.magnetism;
			System.out.println("uri is " + uri);
			i.setClass(Video_play.this, Trait7_page.class);

			break;

		default:
			break;
		}

	}

}
