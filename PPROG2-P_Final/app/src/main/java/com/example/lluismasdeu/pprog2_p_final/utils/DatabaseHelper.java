package com.example.lluismasdeu.pprog2_p_final.utils;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lluismasdeu.pprog2_p_final.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lluismasdeu on 26/4/17.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // Constantes
    private static final String DATABASE_NAME = "app_db";
    private static final int version = 1;
    private static final int BUFFER_LENGTH = 1024;

    private static DatabaseHelper instance;
    private Context context;
    private static SQLiteDatabase.CursorFactory factory;

    private DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);

        this.context = context;
    }

    public static DatabaseHelper getInstance(Context context) {
        if (instance == null)
            instance = new DatabaseHelper(context, DATABASE_NAME, factory, version);

        instance.context = context;

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        executeSQLScript(db, R.raw.db_creation);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        executeSQLScript(db, R.raw.db_removal);
        executeSQLScript(db, R.raw.db_creation);
    }

    private void executeSQLScript(SQLiteDatabase database, int scriptFile) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte buf[] = new byte[BUFFER_LENGTH];
        int len;
        InputStream inputStream = null;

        try {
            inputStream = context.getResources().openRawResource(scriptFile);

            while ((len = inputStream.read(buf)) != -1)
                outputStream.write(buf, 0, len);

            outputStream.close();
            inputStream.close();

            String[] createScript = outputStream.toString().split(";");

            for (int i = 0; i < createScript.length; i++) {
                String sqlStatement = createScript[i].trim();

                // TODO You may want to parse out comments here
                if (sqlStatement.length() > 0)
                    database.execSQL(sqlStatement + ";");
            }
        } catch (IOException e) {
            // TODO Handle Script Failed to Load
        } catch (SQLException e) {
            // TODO Handle Script Failed to Execute
        }
    }
}
