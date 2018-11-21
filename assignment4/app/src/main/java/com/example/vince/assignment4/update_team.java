package com.example.vince.assignment4;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;


public class update_team extends AppCompatActivity {
    byte[] decodedImage = null;
    private DisplayMetrics dm = new DisplayMetrics();
    private ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_team);
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        final Team team = getIntent().getParcelableExtra("team");

        ArrayAdapter aa = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, MainActivity.sports);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ((Spinner) findViewById(R.id.sportSpinner)).setAdapter(aa);


        ((TextView) findViewById(R.id.nameField)).setText(team.getName());
        ((TextView) findViewById(R.id.cityField)).setText(team.getCity());
        ((Spinner) findViewById(R.id.sportSpinner)).setSelection(Arrays.asList(MainActivity.sports).lastIndexOf(team.getSport()));
        ((TextView) findViewById(R.id.mvpField)).setText(team.getMvp());
        decodedImage = Base64.decode(team.getImage(), 0);
        imageview = findViewById(R.id.teamImageView);
        imageview.setImageBitmap(BitmapFactory.decodeByteArray(decodedImage, 0, decodedImage.length));


        findViewById(R.id.uploadImageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(update_team.this, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 1);
                startActivityForResult(new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI), 100);
            }
        });

        findViewById(R.id.updateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView city = findViewById(R.id.cityField);
                TextView name = findViewById(R.id.nameField);
                Spinner sport = findViewById(R.id.sportSpinner);
                TextView mvp = findViewById(R.id.mvpField);
                Bitmap imageMap = getResizedBitmap(((BitmapDrawable) imageview.getDrawable()).getBitmap(), dm.widthPixels / 2);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                imageMap.compress(Bitmap.CompressFormat.PNG, 20, bos);
                String savableImage = Base64.encodeToString(bos.toByteArray(), 0);


                if (city.getText().length() > 0 && name.getText().length() > 0) {
                    Bundle bundle = new Bundle();

                    try {
                        bundle.putParcelable("updatedTeam", new Team(
                                team.getId(),
                                city.getText().toString(),
                                name.getText().toString(),
                                sport.getSelectedItem().toString(),
                                mvp.getText().toString(),
                                savableImage
                        ));
                    } catch (Exception e) {
                        Log.d("inspect", e.getMessage());
                    }
                    bundle.putInt("index", getIntent().getIntExtra("index", -1));

                    setResult(RESULT_OK, new Intent().putExtra("updatedTeam", bundle));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "City and Name are required", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.deleteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("team", team);

                setResult(2, new Intent().putExtra("team", bundle));
                finish();
            }
        });

        findViewById(R.id.exitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            String[] filePaths = new String[]{"_data"};
            Cursor cursor = getContentResolver().query(data.getData(), filePaths, null, null, null);
            cursor.moveToFirst();
            String filePath = cursor.getString(cursor.getColumnIndex(filePaths[0]));
            cursor.close();
            imageview.setImageBitmap(getResizedBitmap(BitmapFactory.decodeFile(filePath), dm.widthPixels / 2));
        } catch (Exception e) {

        }
    }

    private Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width;
        int height;
        float bitmapRatio = ((float) image.getWidth()) / ((float) image.getHeight());
        if (bitmapRatio > 1.0f) {
            width = maxSize;
            height = (int) (((float) width) / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (((float) height) * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }
}
