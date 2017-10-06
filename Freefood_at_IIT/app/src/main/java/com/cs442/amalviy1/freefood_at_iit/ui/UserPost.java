package com.cs442.amalviy1.freefood_at_iit.ui;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.cs442.amalviy1.freefood_at_iit.BackWork;
import com.cs442.amalviy1.freefood_at_iit.Pojo.FoodPOJO;
import com.cs442.amalviy1.freefood_at_iit.R;
import com.cs442.amalviy1.freefood_at_iit.asynctask.Database_New_Post;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ankit on 11/26/16.
 */
public class UserPost extends Fragment {

    ImageView imageView;
    EditText TITLE, DESCRIPTION, LOCATION;
    Button POST;
    File file;
    String foodTitle, foodDescription, foodLocation;
    FoodPOJO pojoObj;
    DateFormat dateFormat;
    DateFormat datetime;
    Date date;
    Date time;
    String photoName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View view;
        view = inflater.inflate(R.layout.user_post_fragment, container, false);


        imageView = (ImageView) view.findViewById(R.id.imageView);
        TITLE = (EditText) view.findViewById(R.id.food_name);
        DESCRIPTION = (EditText) view.findViewById(R.id.description);
        LOCATION = (EditText) view.findViewById(R.id.location);
        POST = (Button) view.findViewById(R.id.post_Btn);

        Toolbar toolbar1 = (Toolbar) view.findViewById(R.id.toolbar1);


        POST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodTitle = TITLE.getText().toString();
                foodDescription = DESCRIPTION.getText().toString();
                foodLocation = LOCATION.getText().toString();

                if (foodTitle.length() == 0 || foodDescription.length() == 0 || foodLocation.length() == 0) {

                    Snackbar snackbar = Snackbar.make(v, "Please enter all the fields", Snackbar.LENGTH_LONG);

                    snackbar.show();
                    return;
                } else {

                    Log.d("====" + file, "");
                    datetime = new SimpleDateFormat("H:mm a");
                    time = new Date();
                    Log.d("=datetime" + datetime, "");
                    pojoObj = new FoodPOJO(file, foodTitle, foodDescription, foodLocation, datetime.format(time));


                    post(pojoObj);
                }
            }
        });


        return view;
    }

    public void photo(View view) {

        Intent cam_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = getFile();
        cam_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(cam_intent, 1);
    }

    private File getFile() {
        dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        date = new Date();
        photoName = "cam_image" + dateFormat.format(date) + ".jpg";

        File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/freefood/");
        if (!folder.exists()) {
            folder.mkdir();
        }

        file = new File(folder, photoName);

        return file;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/freefood/" + photoName;
        Bitmap d = BitmapFactory.decodeFile(path);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        d.compress(Bitmap.CompressFormat.JPEG, 100, out);
        int newHeight = (int) (d.getHeight() * (500.0 / d.getHeight()));
        Bitmap putImage = Bitmap.createScaledBitmap(d, 500, newHeight, true);
        imageView.setImageBitmap(putImage);

    }

    protected void post(FoodPOJO pojo) {


        Database_New_Post databaseBackground = new Database_New_Post(getActivity());
        databaseBackground.execute(pojo);
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("NOTIFICATION", "Token: " + token);
        BackWork backwork = new BackWork();
        backwork.execute();

        Intent intent = new Intent(getActivity(), RetriveData.class);
        this.startActivity(intent);


    }

}