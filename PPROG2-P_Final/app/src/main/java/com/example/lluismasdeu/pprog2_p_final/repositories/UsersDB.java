package com.example.lluismasdeu.pprog2_p_final.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.example.lluismasdeu.pprog2_p_final.model.User;
import com.example.lluismasdeu.pprog2_p_final.utils.DatabaseHelper;

import java.util.List;

/**
 * Created by lluismasdeu on 30/4/17.
 */
public class UsersDB implements UsersDBInterface {
    private static Context context;

    // Constantes
    private static final String USERS_TABLE = "users";
    private static final String NAME_COLUMN = "name";
    private static final String SURNAME_COLUMN = "surname";
    private static final String EMAIL_COLUMN = "email";
    private static final String PASSWORD_COLUMN = "password";
    private static final String GENDER_COLUMN = "gender";
    private static final String DESCRIPTION_COLUMN = "description";

    public UsersDB(Context context) {
        this.context = context;
    }

    @Override
    public void addUser(User u) {
        // Obtenemos la instancia de la base de datos.
        DatabaseHelper helper = DatabaseHelper.getInstance(context);

        // Configuramos los valores de las distintas columnas.
        ContentValues values = new ContentValues();
        values.put(NAME_COLUMN, u.getName());
        values.put(SURNAME_COLUMN, u.getSurname());
        values.put(EMAIL_COLUMN, u.getEmail());
        values.put(PASSWORD_COLUMN, u.getPassword());
        values.put(GENDER_COLUMN, u.getGender());
        values.put(DESCRIPTION_COLUMN, u.getDescription());

        // Llevamos a cabo la inserción en la base de datos.
        helper.getWritableDatabase().insert(USERS_TABLE, null, values);
    }

    @Override
    public void removeUser(User u) {
        // Obtenemos la instancia de la base de datos.
        DatabaseHelper helper = DatabaseHelper.getInstance(context);

        // Configuramos la petición.
        String whereClause = EMAIL_COLUMN + "=?";
        String[] whereArgs = {u.getEmail()};

        // Llevamos a cabo el borrado en la base de datos.
        helper.getWritableDatabase().delete(USERS_TABLE, whereClause, whereArgs);
    }

    @Override
    public void updateUser(User u) {
        // TODO
    }

    @Override
    public boolean existsUser(User u) {
        // Obtenemos la instancia de la base de datos.
        DatabaseHelper helper = DatabaseHelper.getInstance(context);

        // Configuramos la petición.
        String whereClause = EMAIL_COLUMN + "=? AND " + PASSWORD_COLUMN + "=?";
        String[] whereArgs = {u.getEmail(), u.getPassword()};

        // Llevamos a cabo la consulta a la base de datos.
        SQLiteDatabase db = helper.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, USERS_TABLE, whereClause, whereArgs);

        // Comprovamos si existe alguno.
        return count > 0;
    }

    @Override
    public List<User> getAllUsers() {
        // TODO
        return null;
    }
}
