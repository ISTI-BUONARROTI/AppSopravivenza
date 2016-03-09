package liceobuonarroti.appsopravivenza;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TorciaActivity extends AppCompatActivity {

    private boolean onoff = false;
    private Camera camera;
    private boolean isFlashOn = false;
    private boolean hasFlash;
    Camera.Parameters params;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torcia);
        getCamera();

        hasFlash = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if (!hasFlash) {
            // device doesn't support flash
            // Show alert message and close the application
            AlertDialog alert = new AlertDialog.Builder(TorciaActivity.this)
                    .create();
            alert.setTitle("Error");
            alert.setMessage("Sorry, your device doesn't support flash light!");
            alert.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // closing the application
                    finish();
                }
            });
            alert.show();
            return;
        }


    }


    private void getCamera() {
        if (camera == null) {
            try {
                camera = Camera.open();
                params = camera.getParameters();
            } catch (RuntimeException e) {

            }
        }
    }

    public void callTorcia(View view){
        if(!isFlashOn){
            turnOnFlash();
        }else{
            turnOffFlash();
        }
    }

    public void sendSOS(View view){
        if(!isFlashOn){
            turnOnFlashSOS();
        }else{
            turnOffFlash();
        }
    }
    /*
     * Turning Off flash
     */
    private void turnOffFlash() {
        if (isFlashOn) {
            if (camera == null || params == null) {
                return;
            }
            // play sound


            params = camera.getParameters();
            params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(params);
            camera.stopPreview();
            isFlashOn = false;


        }
    }

    /*
 * Turning On flash
 */
    private void turnOnFlash() {
        if (!isFlashOn) {
            if (camera == null || params == null) {
                return;
            }


            params = camera.getParameters();
            params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(params);
            camera.startPreview();
            isFlashOn = true;


        }

    }

    /*
* Turning On flash
*/
    private void turnOnFlashSOS() {
        if (!isFlashOn) {
            if (camera == null || params == null) {
                return;
            }


            new Thread () {
                public void run() {
                    int sleepTime =0;
                    String myMorseString = "111000111";
                    if(myMorseString != null){
                        for (int x = 0; x < myMorseString.length(); x++) {
                            if (myMorseString.charAt(x) == '2') {
                                //camera = Camera.open();
                                sleepTime = 500;
                                Camera.Parameters p = camera.getParameters();
                                p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                                camera.setParameters(p);
                                camera.startPreview();
                                try {
                                    Thread.sleep(sleepTime);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                // power off after signal
                                params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                                camera.setParameters(params);
                                camera.stopPreview();

                               // camera = null;
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                            if (myMorseString.charAt(x) == '1') {
                               // camera = Camera.open();
                                sleepTime = 250;
                                Camera.Parameters p = camera.getParameters();
                                p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                                camera.setParameters(p);
                                camera.startPreview();
                                try {
                                    Thread.sleep(sleepTime);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                // power off after signal
                                params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                                camera.setParameters(params);
                                camera.stopPreview();

                                //camera = null;
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                            if (myMorseString.charAt(x) == '0') {
                              //  camera = Camera.open();
                                sleepTime = 250;
                                Camera.Parameters p = camera.getParameters();
                                camera.setParameters(p);
                                //camera.startPreview();
                                params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                                camera.setParameters(params);
                                camera.stopPreview();

                               // camera = null;

                                try {
                                    Thread.sleep(sleepTime);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }}}
            }.start();


        }

    }

}
