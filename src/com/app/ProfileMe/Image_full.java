package com.app.ProfileMe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Image_full extends Activity implements OnClickListener {
	ImageView img_front,img_enlarge,img_start;
	Intent i;
	Intent i_new;
	RelativeLayout ll_one,ll_two;
	private static final int PIC_CROP = 1;
	private static final String TAG = "CamTestActivity";
	private static final int CAMERA_PHOTO_REQUEST_CODE = 0;
	Preview preview;
	Context con;
	
	String fileName;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.popup);
		setBodyUi();

		try {
			if(App_constants.front_cam==true)
			{
		
				ll_two.setVisibility(View.VISIBLE);
				img_front.setImageBitmap(App_constants.front_image);
				
				//img_front.setImageBitmap(App_constants.front_image);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		i = new Intent();
		i_new = new Intent();

		setData();

	}


	

	
	
	
	private void setBodyUi() {
		// TODO Auto-generated method stub
		
	
		ll_one=(RelativeLayout)findViewById(R.id.pp1);
		ll_two=(RelativeLayout)findViewById(R.id.pp2);
		img_start= (ImageView) findViewById(R.id.image_full);
		img_front= (ImageView) findViewById(R.id.img_click);
		img_enlarge= (ImageView) findViewById(R.id.img_large);

		findViewById(R.id.btn_large).setOnClickListener(this);
		findViewById(R.id.btn_small).setOnClickListener(this);
	
		findViewById(R.id.btn_choose).setOnClickListener(this);
		findViewById(R.id.btn_can).setOnClickListener(this);
		findViewById(R.id.btn_cam).setOnClickListener(this);

	}
	
	
	
/*	
	public void openCam(){
		Camera cam = null;
		Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
		for (int camNo = 0; camNo < Camera.getNumberOfCameras(); camNo++) {
		    CameraInfo camInfo = new CameraInfo();
		    Camera.getCameraInfo(camNo, camInfo);
		    if (camInfo.facing==Camera.CameraInfo.CAMERA_FACING_FRONT) {
		        cam = Camera.open(camNo);
		    }
		}



}*/
	
	
	
	

	private void setData() {
		// TODO Auto-generated method stub
		if (App_constants.user_gender.equals("Female")) {
			switch (App_constants.trait_selected) {
			case 1:
				i.setClass(Image_full.this, Trait1_page.class);
				i_new.setClass(Image_full.this, Trait2_page.class);
				if (App_constants.trait_one == 1) {
					img_start.setImageResource(R.drawable.check_six);

				} else if (App_constants.trait_one == 2) {
					img_start.setImageResource(R.drawable.check_newthree);

				} else {
					img_start.setImageResource(R.drawable.check_newnine);

				}
				break;
			case 2:
				i.setClass(Image_full.this, Trait2_page.class);
				i_new.setClass(Image_full.this, Trait3_page.class);
				if (App_constants.trait_two == 1) {
					img_start.setImageResource(R.drawable.check_three);

				} else if (App_constants.trait_two == 2) {
					img_start.setImageResource(R.drawable.check_neweight);

				} else {
					img_start.setImageResource(R.drawable.check_newseven);

				}
				break;
			case 3:
				i.setClass(Image_full.this, Trait3_page.class);
				i_new.setClass(Image_full.this, Trait4_page.class);

				if (App_constants.trait_three == 1) {
					img_start.setImageResource(R.drawable.check_newsix);

				} else if (App_constants.trait_three == 2) {
					img_start.setImageResource(R.drawable.check_two);

				} else {
					img_start.setImageResource(R.drawable.check_seven);

				}
				break;
			case 4:
				i.setClass(Image_full.this, Trait4_page.class);
				i_new.setClass(Image_full.this, Trait5_page.class);
				if (App_constants.trait_four == 1) {
					img_start.setImageResource(R.drawable.check_two);

				} else if (App_constants.trait_four == 2) {
					img_start.setImageResource(R.drawable.check_newfour);

				} else {
					img_start.setImageResource(R.drawable.check_newten);

				}

				break;
			case 5:
				i.setClass(Image_full.this, Trait5_page.class);
				i_new.setClass(Image_full.this, Trait6_page.class);
				if (App_constants.trait_five == 1) {
					img_start.setImageResource(R.drawable.check_newten);

				} else if (App_constants.trait_five == 2) {
					img_start.setImageResource(R.drawable.check_seven);

				} else {
					img_start.setImageResource(R.drawable.check_two);

				}
				break;
			case 6:
				i.setClass(Image_full.this, Trait6_page.class);
				i_new.setClass(Image_full.this, Trait7_page.class);
				if (App_constants.trait_six == 1) {
					img_start.setImageResource(R.drawable.check_six);

				} else if (App_constants.trait_six == 2) {
					img_start.setImageResource(R.drawable.check_newthree);

				} else {
					img_start.setImageResource(R.drawable.check_two);

				}
				break;
			case 7:
				i.setClass(Image_full.this, Trait7_page.class);
				i_new.setClass(Image_full.this, Weldone_Page.class);
				if (App_constants.trait_seven == 1) {
					img_start.setImageResource(R.drawable.checkfemale_lighter);

				} else if (App_constants.trait_seven == 2) {
					img_start.setImageResource(R.drawable.checkfemale_darker);

				}

				break;

			default:
				break;
			}
		} else {
			switch (App_constants.trait_selected) {
			case 1:
				i.setClass(Image_full.this, Trait1_page.class);
				i_new.setClass(Image_full.this, Trait2_page.class);
				if (App_constants.trait_one == 1) {
					img_start.setImageResource(R.drawable.check_one);

				} else if (App_constants.trait_one == 2) {
					img_start.setImageResource(R.drawable.check_nine);

				} else {
					img_start.setImageResource(R.drawable.check_newtwo);

				}
				break;
			case 2:
				i.setClass(Image_full.this, Trait2_page.class);
				i_new.setClass(Image_full.this, Trait3_page.class);
				if (App_constants.trait_two == 1) {
					img_start.setImageResource(R.drawable.check_four);

				} else if (App_constants.trait_two == 2) {
					img_start.setImageResource(R.drawable.check_ten);

				} else {
					img_start.setImageResource(R.drawable.check_newfive);

				}
				break;
			case 3:
				i.setClass(Image_full.this, Trait3_page.class);
				i_new.setClass(Image_full.this, Trait4_page.class);

				if (App_constants.trait_three == 1) {
					img_start.setImageResource(R.drawable.check_five);

				} else if (App_constants.trait_three == 2) {
					img_start.setImageResource(R.drawable.check_newone);

				} else {
					img_start.setImageResource(R.drawable.check_eight);

				}
				break;
			case 4:
				i.setClass(Image_full.this, Trait4_page.class);
				i_new.setClass(Image_full.this, Trait5_page.class);
				if (App_constants.trait_four == 1) {
					img_start.setImageResource(R.drawable.check_newtwo);

				} else if (App_constants.trait_four == 2) {
					img_start.setImageResource(R.drawable.check_nine);

				} else {
					img_start.setImageResource(R.drawable.check_one);

				}

				break;
			case 5:
				i.setClass(Image_full.this, Trait5_page.class);
				i_new.setClass(Image_full.this, Trait6_page.class);
				if (App_constants.trait_five == 1) {
					img_start.setImageResource(R.drawable.check_ten);

				} else if (App_constants.trait_five == 2) {
					img_start.setImageResource(R.drawable.check_newone);

				} else {
					img_start.setImageResource(R.drawable.check_one);

				}
				break;
			case 6:
				i.setClass(Image_full.this, Trait6_page.class);
				i_new.setClass(Image_full.this, Trait7_page.class);
				if (App_constants.trait_six == 1) {
					img_start.setImageResource(R.drawable.check_newone);

				} else if (App_constants.trait_six == 2) {
					img_start.setImageResource(R.drawable.check_five);

				} else {
					img_start.setImageResource(R.drawable.check_eight);

				}
				break;
			case 7:
				i.setClass(Image_full.this, Trait7_page.class);
				i_new.setClass(Image_full.this, Weldone_Page.class);
				if (App_constants.trait_seven == 1) {
					img_start.setImageResource(R.drawable.check_lighter);

				} else if (App_constants.trait_seven == 2) {
					img_start.setImageResource(R.drawable.check_darker);

				}

				break;

			default:
				break;
		}
		}

	}

	
	
	
/*	
	private Camera openFrontFacingCamera() {
	    Camera camera = null;
	     
	    // Look for front-facing camera, using the Gingerbread API.
	    // Java reflection is used for backwards compatibility with pre-Gingerbread APIs.
	    try {
	        Class<?> cameraClass = Class.forName("android.hardware.Camera");
	        Object cameraInfo = null;
	        Field field = null;
	        int cameraCount = 0;
	        Method getNumberOfCamerasMethod = cameraClass.getMethod( "getNumberOfCameras" );
	        if ( getNumberOfCamerasMethod != null ) {
	            cameraCount = (Integer) getNumberOfCamerasMethod.invoke( null, (Object[]) null );
	        }
	        Class<?> cameraInfoClass = Class.forName("android.hardware.Camera$CameraInfo");
	        if ( cameraInfoClass != null ) {
	            cameraInfo = cameraInfoClass.newInstance();
	        }
	        if ( cameraInfo != null ) {
	            field = cameraInfo.getClass().getField( "facing" );
	        }
	        Method getCameraInfoMethod = cameraClass.getMethod( "getCameraInfo", Integer.TYPE, cameraInfoClass );
	        if ( getCameraInfoMethod != null && cameraInfoClass != null && field != null ) {
	            for ( int camIdx = 0; camIdx < cameraCount; camIdx++ ) {
	                getCameraInfoMethod.invoke( null, camIdx, cameraInfo );
	                int facing = field.getInt( cameraInfo );
	                if ( facing == 1 ) { // Camera.CameraInfo.CAMERA_FACING_FRONT 
	                    try {
	                        Method cameraOpenMethod = cameraClass.getMethod( "open", Integer.TYPE );
	                        if ( cameraOpenMethod != null ) {
	                            camera = (Camera) cameraOpenMethod.invoke( null, camIdx );
	                        }
	                    } catch (RuntimeException e) {
	                        Log.e(TAG, "Camera failed to open: " + e.getLocalizedMessage());
	                    }
	                }
	            }
	        }
	    }
	    // Ignore the bevy of checked exceptions the Java Reflection API throws - if it fails, who cares.
	    catch ( ClassNotFoundException e        ) {Log.e(TAG, "ClassNotFoundException" + e.getLocalizedMessage());}
	    catch ( NoSuchMethodException e         ) {Log.e(TAG, "NoSuchMethodException" + e.getLocalizedMessage());}
	    catch ( NoSuchFieldException e          ) {Log.e(TAG, "NoSuchFieldException" + e.getLocalizedMessage());}
	    catch ( IllegalAccessException e        ) {Log.e(TAG, "IllegalAccessException" + e.getLocalizedMessage());}
	    catch ( InvocationTargetException e     ) {Log.e(TAG, "InvocationTargetException" + e.getLocalizedMessage());}
	    catch ( InstantiationException e        ) {Log.e(TAG, "InstantiationException" + e.getLocalizedMessage());}
	    catch ( SecurityException e             ) {Log.e(TAG, "SecurityException" + e.getLocalizedMessage());}
	 
	    if ( camera == null ) {
	        // Try using the pre-Gingerbread APIs to open the camera.
	        try {
	            camera = Camera.open();
	        } catch (RuntimeException e) {
	            Log.e(TAG, "Camera failed to open: " + e.getLocalizedMessage());
	        }
	    }
	 
	    return camera;
	}*/


	
	
	
	
	
/*	
	
	   PictureCallback myPictureCallback_JPG = new PictureCallback(){

		    @Override

		     public void onPictureTaken(byte[] data, Camera arg1) {
		         // new SavePhotoTask().execute(data);
		 
		        Bitmap bitmap = BitmapFactory.decodeByteArray(data , 0, data .length);
		        App_constants.front_image=bitmap;
		     
		      

		     }};*/


	
		  
	
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
   
    
			if (requestCode == 0 && resultCode == RESULT_OK && null != data) {
			
				ll_two.setVisibility(View.VISIBLE);
				
				Uri selectedImage=data.getData();
			
				Bitmap yourSelectedImage = (Bitmap) data.getExtras().get("data");
				 System.out.println("pictureeeeeeeeee"+yourSelectedImage);
				 App_constants.front_image=yourSelectedImage;
				 img_front.setImageBitmap(App_constants.front_image);
				
			
				
			
		
	 }
			
			
			if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
				Uri selectedImage=data.getData();
			
				Bitmap yourSelectedImage = (Bitmap) data.getExtras().get("data");
				 System.out.println("pictureeeeeeeeee"+yourSelectedImage);
				 App_constants.front_image=yourSelectedImage;
				 img_front.setImageBitmap(App_constants.front_image);
				
			
				
			
		
	 }
    }
	  
	


	
	

	
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_can:
			startActivity(i);
			Image_full.this.finish();

			break;
		case R.id.btn_choose:
			startActivity(i_new);
			Image_full.this.finish();

			break;
			
		case R.id.btn_small:
			
			ll_one.setVisibility(View.INVISIBLE);
			ll_two.setVisibility(View.VISIBLE);
			break;
		case R.id.btn_large:
			
			ll_one.setVisibility(View.VISIBLE);
			//ll_two.setVisibility(View.INVISIBLE);

			img_enlarge.setImageBitmap(App_constants.front_image);
			break;
	
			
		case R.id.btn_cam:
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

	
			intent.putExtra("android.intent.extras.CAMERA_FACING", 1);
			startActivityForResult(intent, CAMERA_PHOTO_REQUEST_CODE);
			Toast.makeText(Image_full.this, "Please choose front camera option if not chosen  already", Toast.LENGTH_LONG).show();

			App_constants.front_cam=true;
		
	

			break;

		default:
			break;
		}

	}
}


