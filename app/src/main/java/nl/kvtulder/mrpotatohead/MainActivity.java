package nl.kvtulder.mrpotatohead;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    // store all the checkboxes and corresponding images in a public array
    public int[] imagesIDs = {R.id.imageArms,R.id.imageEars,R.id.imageEyebrows,
            R.id.imageEyes, R.id.imageGlasses,R.id.imageHat,
            R.id.imageMouth,R.id.imageMustache,R.id.imageNose,R.id.imageShoes};

    public int[] checkboxIDs = {R.id.checkArms,R.id.checkEars,R.id.checkEyebrows,
            R.id.checkEyes, R.id.checkGlasses,R.id.checkHat,
            R.id.checkMouth,R.id.checkMustache,R.id.checkNose,R.id.checkShoes};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check if there is a saved instance and if so, restore the image visibilities
        if (savedInstanceState != null) {

            Log.d("potato", "onCreate: saved instance found,restoring image visibilities");
            HashMap<Integer, Integer> imageVisibilities =
                    (HashMap<Integer, Integer>) savedInstanceState.getSerializable("imageVisibilities");

            for (int imageID : imageVisibilities.keySet()) {
                // restore the visibilities
                int visibility = imageVisibilities.get(imageID);
                ((ImageView) findViewById(imageID)).setVisibility(visibility);
            }
        }
    }

    //callback method for the checkboxes, when a box is click enable/disable the corresponding image
    public void checkClicked(View v)
    {
        CheckBox checkbox = (CheckBox) v;
        int ID;
        // check which checkbox is clicked
        for(int i = 0 ;i < checkboxIDs.length;i++) {
            if (checkboxIDs[i] == checkbox.getId()) {
                // match found! Make image visible if checked and invisible if not
                ImageView image = (ImageView) findViewById(imagesIDs[i]);
                image.setVisibility(checkbox.isChecked() ? View.VISIBLE : View.INVISIBLE);

                // Already a match found, so not necessary to continue
                break;
            }
        }
    }

    // Image visibilities are not stored by default when the user closes the app, so store them
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Create a hashmap storing all the image visibilities
        HashMap<Integer,Integer> imageVisibilities = new HashMap<Integer,Integer>();
        for(int imageID : imagesIDs)
            imageVisibilities.put(imageID,findViewById(imageID).getVisibility());

        // Hashmap implements Serializable, so we can use putSerializable
        // https://stackoverflow.com/questions/3422388/android-get-value-from-hashmap
        outState.putSerializable("imageVisibilities", imageVisibilities);
    }
}