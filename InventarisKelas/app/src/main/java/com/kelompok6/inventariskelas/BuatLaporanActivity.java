package com.kelompok6.inventariskelas;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.bumptech.glide.BuildConfig;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.kelompok6.inventariskelas.utils.FileCompressor;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class BuatLaporanActivity extends AppCompatActivity {

    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_GALLERY_PHOTO = 2;
    File mPhotoFile;
    Bitmap bmp;
    FileCompressor mCompressor;
    ImageView fotoKerusakan;
    OutputStream outputStream;
    LinearLayout btnPilih;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_laporan);

        fotoKerusakan = (ImageView) findViewById(R.id.foto_kerusakan);
        ButterKnife.bind(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            //upload.setEnabled(false);
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 0);
        }else{
            //upload.setEnabled(true);
        }
    }

    /**
     * Alert dialog for capture or select from galley
     */
    private void selectImage() {
        final CharSequence[] items = {"Ambil Gambar", "Pilih dari Library",
                "Hapus Gambar","Cancel"};
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(BuatLaporanActivity.this);
        builder.setItems(items, (dialog, item) -> {
            if (items[item].equals("Ambil Gambar")) {
                setRequestCamera();
            } else if (items[item].equals("Pilih dari Library")) {
                setRequestLibrary();
            } else if (items[item].equals("Hapus Gambar")) {
                fotoKerusakan.setImageDrawable(null);
                dialog.dismiss();
            } else if (items[item].equals("Cancel")) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void setRequestCamera(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null){
            File photoFile = null;
            try {
                photoFile = createImageFile();
            }catch (Exception e){
                e.printStackTrace();
            }
            if (photoFile != null){
                try {
                    Uri photoUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", photoFile);
                    mPhotoFile = photoFile;
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                }catch (Exception e){
                    Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "gagal", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setRequestLibrary(){
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickPhoto.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityForResult(pickPhoto, REQUEST_GALLERY_PHOTO);
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String mFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File mFile = File.createTempFile(mFileName, ".jpg", storageDir);

        return mFile;
    }

    public File createTempFile(Bitmap bitmap) {
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                , "InventarisKelas_" + System.currentTimeMillis() +"_image.jpeg");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG,100, bos);
        byte[] bitmapdata = bos.toByteArray();
        //write the bytes in file

        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    protected void onActivityResult(final int requestCode, int resultCode, final Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode == REQUEST_TAKE_PHOTO){
                Glide.with(BuatLaporanActivity.this)
                        .load(mPhotoFile)
                        .apply(new RequestOptions().centerCrop()
                                .placeholder(R.drawable.ic_image_blue_24dp))
                        .into(fotoKerusakan);
            }else if(requestCode == REQUEST_GALLERY_PHOTO){
                Uri selectedImage = data.getData();
                mPhotoFile = new File(getRealPathFromUri(selectedImage));
                Glide.with(BuatLaporanActivity.this)
                        .load(mPhotoFile)
                        .apply(new RequestOptions().centerCrop()
                                .placeholder(R.drawable.ic_image_blue_24dp))
                        .into(fotoKerusakan);

            }
        }
    }

    /**
     * Get real file path from URI
     */
    public String getRealPathFromUri(Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = getContentResolver().query(contentUri, proj, null, null, null);
            assert cursor != null;
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public void addToInternal(){
        try {
            BitmapDrawable drawable = (BitmapDrawable) fotoKerusakan.getDrawable();
            Bitmap bitmap = drawable.getBitmap();

            File filepaths = Environment.getExternalStorageDirectory();
            File dir = new File (filepaths.getAbsolutePath()+"/Inventaris/");
            dir.mkdir();
            File file = new File(dir, System.currentTimeMillis()+".jpg");
            try {
                outputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100,outputStream);
        }catch (Exception e){
            Toast.makeText(BuatLaporanActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.foto_kerusakan)
    public void onViewClicked() {
        selectImage();
    }
}

