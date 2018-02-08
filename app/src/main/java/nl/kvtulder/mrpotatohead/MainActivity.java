package nl.kvtulder.mrpotatohead;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkClicked(View v)
    {
        CheckBox checkbox = (CheckBox) v;
        String text = ((CheckBox) v).getText().toString();
        int ID;

        switch (checkbox.getId())
        {
            case R.id.checkEars:
                ID = R.id.imageEars;
                break;
            case R.id.checkEyes:
                ID = R.id.imageEyes;
                break;
            case R.id.checkEyebrows:
                ID = R.id.imageEyebrows;
                break;
            case R.id.checkGlasses:
                ID = R.id.imageGlasses;
                break;
            case R.id.checkHat:
                ID = R.id.imageHat;
                break;
            case R.id.checkMouth:
                ID = R.id.imageMouth;
                break;
            case R.id.checkMustache:
                ID = R.id.imageMustache;
                break;
            case R.id.checkNose:
                ID = R.id.imageNose;
                break;
            case R.id.checkShoes:
                ID = R.id.imageShoes;
                break;
            case R.id.checkArms:
                ID = R.id.imageArms ;
                break;
            // something unexpected happened: there is a unknown checkbox. Stop the callback
            default:
                return;
        }

        ImageView image = (ImageView) findViewById(ID);
        boolean checked = checkbox.isChecked();
        image.setVisibility(checked ? View.VISIBLE : View.INVISIBLE);
        Log.d("potato","checkClicked " + checkbox.getId());

    }
}
