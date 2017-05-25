package com.example.lluismasdeu.pprog2_p_final.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lluismasdeu on 6/5/17.
 */
public class GeneralUtilities {
    private static final String PROFILE_PICTURES_FOLDER = "profile_images/";
    private static final String DEFAULT_PHOTO = "default_photo.jpg";
    private static final String TAG = "GeneralUtilities";

    private static GeneralUtilities instance = null;
    private static Context context;

    /**
     * Constructor de la clase.
     * @param context Context
     */
    private GeneralUtilities(Context context) {
        this.context = context;
    }

    /**
     * Método encargado de obtener la instancia de la clase.
     * @param context Context
     * @return Instancia de la clase.
     */
    public static GeneralUtilities getInstance(Context context) {
        if (instance == null)
            instance = new GeneralUtilities(context);

        instance.context = context;

        return instance;
    }

    /**
     * Método encargado de obtener la imagen de perfil por defecto decodificada.
     * @return Imagen de perfil por defecto decodificada.
     */
    public static Bitmap getDefaultProfilePhoto() {
        Bitmap defaultImage = null;
        InputStream inputStream = null;

        try {
            // Cargamos el recurso.
            AssetManager assetManager = context.getAssets();
            inputStream = assetManager.open(DEFAULT_PHOTO);

            // Decodificamos la imagen, y la colocamos en el componente.
            defaultImage = BitmapFactory.decodeStream(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                inputStream = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return defaultImage;
    }

    /**
     * Método encargado de obtener el número de ficheros en la carpeta de imágenes.
     * @return Número de ficheros en la carpeta de imágenes.
     */
    public static int getNumberPictures() {
        String directoryPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                + PROFILE_PICTURES_FOLDER;
        File directory = new File(directoryPath);
        int numFiles = 0;

        if (!directory.exists())
            directory.mkdirs();

        File[] files = directory.listFiles();

        if (files != null)
            numFiles = files.length;

        return numFiles;
    }

    /**
     * Método encargado de guardar la imagen en el dispositivo.
     * @param image Imagen a guardar.
     * @param fileName Nombre del fichero.
     */
    public static void saveProfilePicture(Bitmap image, String fileName) {
        Log.d(TAG, "saveProfilePicture: saving image...");
        String directoryPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                + PROFILE_PICTURES_FOLDER;
        File directory = new File(directoryPath);
        File file = new File(directory, fileName);
        FileOutputStream out = null;

        if (!directory.exists()) {
            Log.d(TAG, "saveProfilePicture: directory didn't exist...");
            directory.mkdirs();

            if (directory.exists())
                Log.d(TAG, "saveProfilePicture: ...but now exists");
        }

        try {
            Log.d(TAG, "saveProfilePicture: try 0");
            if (file.exists())
                file.delete();

            Log.d(TAG, "saveProfilePicture: try 1");
            out = new FileOutputStream(file);
            Log.d(TAG, "saveProfilePicture: try 2");
            image.compress(Bitmap.CompressFormat.JPEG, 100, out);
            Log.d(TAG, "saveProfilePicture: try 3");

            Log.d(TAG, "saveProfilePicture: " + getNumberPictures());
        } catch (Exception e) {
            Log.d(TAG, "saveProfilePicture: image could not be saved");
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Bitmap getImage(String imageFile) {
        String directoryPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                + PROFILE_PICTURES_FOLDER;
        FileInputStream inputStream = null;
        Bitmap image = null;

        try {
            // Cargamos el recurso.
            File directory = new File(directoryPath);
            File file = new File(directory, imageFile);
            inputStream = new FileInputStream(file);

            // Decodificamos la imagen, y la colocamos en el componente.
            image = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                    inputStream = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return image;
    }
}
