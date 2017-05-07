package com.example.lluismasdeu.pprog2_p_final.utils;

import android.graphics.Bitmap;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by lluismasdeu on 6/5/17.
 */
public class GeneralUtilities {
    private static final String PICTURES_FOLDER = "app/src/images/";
    private static final String TAG = "GeneralUtilities";

    /**
     * Método encargado de guardar la imagen en el dispositivo.
     * @param image Imagen a guardar.
     * @param fileName Nombre del fichero.
     */
    public static void saveImage(Bitmap image, String fileName) {
        File file = new File(PICTURES_FOLDER, fileName);
        FileOutputStream out = null;

        try {
            out = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.JPEG, 100, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Método encargado de obtener el número de ficheros en la carpeta de imágenes.
     * @return Número de ficheros en la carpeta de imágenes.
     */
    public static int getNumberPictures() {
        File f = new File(PICTURES_FOLDER);
        int numFiles = 0;

        if (f == null)
            f.mkdir();

        File[] files = f.listFiles();

        if (files != null)
            numFiles = files.length;

        return numFiles;
    }
}
