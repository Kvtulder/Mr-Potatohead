package nl.kvtulder.mrpotatohead;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check if there is a saved instance and if possible, restore the image visibilities
        if (savedInstanceState != null) {

            Log.d("potato", "onCreate: saved instance found,restoring image visibilities");
            HashMap<Integer, Integer> imageVisibilities =
                    (HashMap<Integer, Integer>) savedInstanceState.getSerializable("imageVisibilities");

            for (int imageID : imageVisibilities.keySet()) {
                // restore the visibilities
                int visibility = imageVisibilities.get(imageID);
                ImageView image = (ImageView) findViewById(imageID);
                image.setVisibility(visibility);
            }
        }
    }

    public void checkClicked(View v)
    {
        CheckBox checkbox = (CheckBox) v;
        int ID;

        int[] imagesIDs = {R.id.imageArms,R.id.imageEars,R.id.imageEyebrows,
                R.id.imageEyes, R.id.imageGlasses,R.id.imageHat,
                R.id.imageMouth,R.id.imageMustache,R.id.imageNose,R.id.imageShoes};

        int[] checkboxIDs = {R.id.checkArms,R.id.checkEars,R.id.checkEyebrows,
                R.id.checkEyes, R.id.checkGlasses,R.id.checkHat,
                R.id.checkMouth,R.id.checkMustache,R.id.checkNose,R.id.checkShoes};

        for(int i = 0 ;i < checkboxIDs.length;i++) {
            if (checkboxIDs[i] == checkbox.getId()) {
                String text = ((CheckBox) v).getText().toString();
                ImageView image = (ImageView) findViewById(imagesIDs[i]);
                boolean checked = checkbox.isChecked();
                image.setVisibility(checked ? View.VISIBLE : View.INVISIBLE);
                Log.d("potato", "checkClicked " + checkbox.getId());
                break;
            }
        }
    }
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        int[] imagesID = {R.id.imageArms,R.id.imageEars,R.id.imageEyebrows,
                R.id.imageEyes, R.id.imageGlasses,R.id.imageHat,
                R.id.imageMouth,R.id.imageMustache,R.id.imageNose,R.id.imageShoes};

        // Create a hashmap storing all the image visibilities
        HashMap<Integer,Integer> imageVisibilities = new HashMap<Integer,Integer>();
        for(int imageID : imagesID)
            imageVisibilities.put(imageID,findViewById(imageID).getVisibility());

        // Hashmap implements Serializable, so we can use putSerializable
        // https://stackoverflow.com/questions/3422388/android-get-value-from-hashmap
        outState.putSerializable("imageVisibilities", imageVisibilities);
    }
}


