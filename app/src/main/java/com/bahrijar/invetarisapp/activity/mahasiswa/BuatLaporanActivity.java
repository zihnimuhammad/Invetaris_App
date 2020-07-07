package com.bahrijar.invetarisapp.activity.mahasiswa;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.bahrijar.invetarisapp.R;


import com.bahrijar.invetarisapp.activity.LoginActivity;
import com.bahrijar.invetarisapp.model.Laporan;
import com.bahrijar.invetarisapp.network.ServiceGenerator;

import com.bahrijar.invetarisapp.network.response.LaporanResponse;
import com.bahrijar.invetarisapp.network.service.ApiInterface;
import com.bahrijar.invetarisapp.utils.SharedPrefManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.jetbrains.annotations.NotNull;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuatLaporanActivity extends AppCompatActivity implements View.OnClickListener {
    public static String KELAS = "KELAS";
    SharedPrefManager sharedPrefManager;
    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_GALLERY_PHOTO = 2;
    File mPhotoFile;
    Bitmap bmp;
    ImageView fotoKerusakan, vBtnBack;
    EditText vDeskripsi;
    OutputStream outputStream;
    ApiInterface apiInterface;
    Button vKirimLaporan;
    //    Context mContext;
    TextView no_induk;
    ProgressDialog dialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_laporan);
        apiInterface = ServiceGenerator.createBaseService(this, ApiInterface.class);
        sharedPrefManager = new SharedPrefManager(this);

        no_induk = findViewById(R.id.tv_noinduk);

        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);


        fotoKerusakan = findViewById(R.id.foto_kerusakan);
        vDeskripsi = findViewById(R.id.deskripsi_laporan);
        vBtnBack = findViewById(R.id.btn_back);

        vKirimLaporan = findViewById(R.id.btn_lapor);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //upload.setEnabled(false);
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 0);
        }
//        else {
//            //upload.setEnabled(true);
//        }

        vDeskripsi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!vDeskripsi.getText().toString().isEmpty()) {
                    vKirimLaporan.setVisibility(View.VISIBLE);
                } else if (vDeskripsi.getText().toString().isEmpty()) {
                    vKirimLaporan.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        validate();

        vBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        fotoKerusakan.setOnClickListener(this);

        vKirimLaporan.setOnClickListener(new View.OnClickListener() {
            Intent i = getIntent();
            String id_ruangan = i.getStringExtra("KELAS");

            //Baru
            @Override
            public void onClick(View view) {
                dialog.setMessage("Mengirim Laporan");
                dialog.show();


//                File photoFile = new File(String.valueOf(mPhotoFile));
//                RequestBody reqB = RequestBody.create(MediaType.parse("multipart/form-file"), photoFile);
//                MultipartBody.Part partImage = MultipartBody.Part.createFormData("foto_kerusakan", photoFile.getName(), reqB);

                RequestBody reqIdPelapor = RequestBody.create(MediaType.parse("multipart/form-data"),
                        sharedPrefManager.getSpNoinduk());
                RequestBody reqIdruangan = RequestBody.create(MediaType.parse("multipart/form-data"),
                        id_ruangan.toLowerCase());
                RequestBody reqDeskripsi = RequestBody.create(MediaType.parse("text/plain"),
                        vDeskripsi.getText().toString());

                apiInterface.postLaporan(
                        sharedPrefManager.getSPToken(),
                        reqIdPelapor,
                        reqIdruangan,
                        reqDeskripsi


                ).enqueue(new Callback<LaporanResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<LaporanResponse> call, @NotNull Response<LaporanResponse> response) {
                        try {
                            if (response.code() == 200) {
                                assert response.body() != null;
                                Laporan laporan = response.body().getData();
                                sharedPrefManager.saveSPString(SharedPrefManager.SP_LAPORAN, laporan.getIdLaporan());
                                startActivity(new Intent(BuatLaporanActivity.this, LaporanBerhasilActivity.class)
                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                                finish();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<LaporanResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });
            }
        });


    }

    private boolean validate() {
        if (vDeskripsi.getText().toString().isEmpty()) {
            vKirimLaporan.setVisibility(View.GONE);
            return false;
        }else if (vDeskripsi.getText().toString().length() < 1) {
            vKirimLaporan.setVisibility(View.GONE);
            return false;

        }
        return true;
    }

    /**
     * Alert dialog for capture or select from galley
     */
    private void selectImage() {
        final CharSequence[] items = {"Ambil Gambar", "Pilih dari galeri",
                "Hapus Gambar", "Cancel"};
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(BuatLaporanActivity.this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Ambil Gambar")) {
                    setRequestCamera();
                } else if (items[item].equals("Pilih dari galeri")) {
                    setRequestLibrary();
                } else if (items[item].equals("Hapus Gambar")) {
                    fotoKerusakan.setImageDrawable(null);
                    dialog.dismiss();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void setRequestCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (photoFile != null) {
                try {
                    Uri photoUri = FileProvider.getUriForFile(this, "com.bahrijar.invetarisapp.fileprovider", photoFile);
                    mPhotoFile = photoFile;
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                } catch (Exception e) {
                    Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "gagal", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setRequestLibrary() {
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
                , "InventarisKelas_" + System.currentTimeMillis() + "_image.jpeg");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
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

    protected void onActivityResult(final int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_TAKE_PHOTO) {
                Glide.with(BuatLaporanActivity.this)
                        .load(mPhotoFile)
                        .apply(new RequestOptions().centerCrop()
                                .placeholder(R.drawable.ic_add_a_photo_24dp))
                        .into(fotoKerusakan);
            } else if (requestCode == REQUEST_GALLERY_PHOTO) {
                Uri selectedImage = data.getData();
                mPhotoFile = new File(getRealPathFromUri(selectedImage));
                Glide.with(BuatLaporanActivity.this)
                        .load(mPhotoFile)
                        .apply(new RequestOptions().centerCrop()
                                .placeholder(R.drawable.ic_add_a_photo_24dp))
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
            String[] proj = {MediaStore.Images.Media.DATA};
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

    public void addToInternal() {
        try {
            BitmapDrawable drawable = (BitmapDrawable) fotoKerusakan.getDrawable();
            Bitmap bitmap = drawable.getBitmap();

            File filepaths = Environment.getExternalStorageDirectory();
            File dir = new File(filepaths.getAbsolutePath() + "/Inventaris/");
            dir.mkdir();
            File file = new File(dir, System.currentTimeMillis() + ".jpg");
            try {
                outputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        } catch (Exception e) {
            Toast.makeText(BuatLaporanActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.foto_kerusakan:
                selectImage();
                break;
        }
    }
}
