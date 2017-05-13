package com.example.lluismasdeu.pprog2_p_final.repositories.implementations;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.example.lluismasdeu.pprog2_p_final.model.User;
import com.example.lluismasdeu.pprog2_p_final.repositories.DatabaseManagementInterface;
import com.example.lluismasdeu.pprog2_p_final.utils.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de conectar con la base de datos.
 * @author Eloy Alberto López
 * @author Lluís Masdeu
 */
public class DatabaseManagement implements DatabaseManagementInterface {
    private static Context context;

    // Nombres de las tablas
    private static final String SEARCHES_TABLE = "searches";
    private static final String USERS_TABLE = "users";

    // Nombres de las columnas.
    private static final String DESCRIPTION_COLUMN = "description";
    private static final String EMAIL_COLUMN = "email";
    private static final String GENDER_COLUMN = "gender";
    private static final String ID_COLUMN = "_id";
    private static final String IMAGE_FILE_COLUMN = "profile_picture";
    private static final String NAME_COLUMN = "name";
    private static final String PASSWORD_COLUMN = "password";
    private static final String SEARCH_COLUMN = "search";
    private static final String SURNAME_COLUMN = "surname";

    /**
     * Constructor de la clase.
     * @param context Context.
     */
    public DatabaseManagement(Context context) {
        this.context = context;
    }

    /**
     * Método encargado de registrar un usuario en la base de datos.
     * @param u Información del usuario a registrar.
     */
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
        values.put(IMAGE_FILE_COLUMN, u.getImageFile());

        // Llevamos a cabo la inserción en la base de datos.
        helper.getWritableDatabase().insert(USERS_TABLE, null, values);
    }

    /**
     * Método encargado de borrar un usuario de la base de datos.
     * @param u Información del usuario a borrar.
     */
    @Override
    public void removeUser(User u) {
        // Obtenemos la instancia de la base de datos.
        DatabaseHelper helper = DatabaseHelper.getInstance(context);

        // Configuramos la petición.
        String whereClause = EMAIL_COLUMN + " =? ";
        String[] whereArgs = {u.getEmail()};

        // Llevamos a cabo el borrado en la base de datos.
        helper.getWritableDatabase().delete(USERS_TABLE, whereClause, whereArgs);
    }

    /**
     * Método encargado de actualizar un usuario en la base de datos.
     * @param u Información del usuario a actualizar.
     */
    @Override
    public void updateUser(User u) {
        // TODO
    }

    /**
     * Método encargado de consultar si el usuario existe en la base de datos.
     * @param u Usuario a consultar.
     * @param mode Modo de búsqueda.
     *             1 - Consulta de login.
     *             2 - Consulta de registro.
     * @return
     */
    @Override
    public boolean existsUser(User u, int mode) {
        // Obtenemos la instancia de la base de datos.
        DatabaseHelper helper = DatabaseHelper.getInstance(context);

        // Configuramos la petición.
        String whereClause = "";
        String[] whereArgs = new String[1];

        switch (mode) {
            case 1:
                whereClause = EMAIL_COLUMN + " =? AND " + PASSWORD_COLUMN + " =? ";
                whereArgs = new String[2];
                whereArgs[0] = u.getEmail();
                whereArgs[1] = u.getPassword();
                break;

            case 2:
                whereClause = EMAIL_COLUMN + "=?";
                whereArgs[0] = u.getEmail();
                break;
        }

        // Llevamos a cabo la consulta en la base de datos.
        SQLiteDatabase db = helper.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, USERS_TABLE, whereClause, whereArgs);

        // Comprovamos si existe alguno.
        return count > 0;
    }

    /**
     * Método encargado de obtener un usuario de la base de datos.
     * @param u Información del usuario a buscar.
     * @return Información completa del usuario.
     */
    @Override
    public User getUser(User u) {
        User retrievedUser = null;

        // Obtenemos la instancia de la base de datos.
        DatabaseHelper helper = DatabaseHelper.getInstance(context);

        // Configuramos la petición.
        String[] selectColumns = null;
        String whereClause = EMAIL_COLUMN + "=? and " + PASSWORD_COLUMN + "=?";
        String[] whereArgs = {u.getEmail(), u.getPassword()};

        // Llevamos a cabo la consulta en la base de datos.
        Cursor cursor = helper.getReadableDatabase()
                .query(USERS_TABLE, selectColumns, whereClause, whereArgs, null, null, null);

        // Hacemos la lectura de los resultados.
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int id = 0;
                String name = "", surname = "", email = "", password = "", gender = "",
                        description = "", imageFile = "";

                do {
                    id = cursor.getInt(cursor.getColumnIndex(ID_COLUMN));
                    name = cursor.getString(cursor.getColumnIndex(NAME_COLUMN));
                    surname = cursor.getString(cursor.getColumnIndex(SURNAME_COLUMN));
                    email = cursor.getString(cursor.getColumnIndex(EMAIL_COLUMN));
                    password = cursor.getString(cursor.getColumnIndex(PASSWORD_COLUMN));
                    gender = cursor.getString(cursor.getColumnIndex(GENDER_COLUMN));
                    description = cursor.getString(cursor.getColumnIndex(DESCRIPTION_COLUMN));
                    imageFile = cursor.getString(cursor.getColumnIndex(IMAGE_FILE_COLUMN));
                } while (cursor.moveToNext());

                retrievedUser = new User(id, name, surname, email, password, gender, description,
                        imageFile);
            }
        }

        return retrievedUser;
    }

    /**
     * Método encargado de obtener todos los usuarios registrados en la base de datos.
     * @return Usuarios registrados en la base de datos.
     */
    @Override
    public List<User> getAllUsers() {
        // Obtenemos la instancia de la base de datos.
        DatabaseHelper helper = DatabaseHelper.getInstance(context);

        // Llevamos a cabo la consulta en la base de datos.
        Cursor cursor = helper.getReadableDatabase().query(USERS_TABLE, null, null, null, null,
                null, null);

        // Creamos la lista donde guardaremos los usuarios registrados.
        List<User> users = new ArrayList<User>();

        // Hacemos la lectura de los resultados.
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int id = 0;
                String name = "", surname = "", email = "", password = "", gender = "",
                        description = "", imageFile = "";

                do {
                    id = cursor.getInt(cursor.getColumnIndex(ID_COLUMN));
                    name = cursor.getString(cursor.getColumnIndex(NAME_COLUMN));
                    surname = cursor.getString(cursor.getColumnIndex(SURNAME_COLUMN));
                    email = cursor.getString(cursor.getColumnIndex(EMAIL_COLUMN));
                    password = cursor.getString(cursor.getColumnIndex(PASSWORD_COLUMN));
                    gender = cursor.getString(cursor.getColumnIndex(GENDER_COLUMN));
                    description = cursor.getString(cursor.getColumnIndex(DESCRIPTION_COLUMN));
                    imageFile = cursor.getString(cursor.getColumnIndex(IMAGE_FILE_COLUMN));

                    // Añadimos el usuario en el ArrayList.
                    users.add(new User(id, name, surname, email, password, gender, description,
                            imageFile));
                } while (cursor.moveToNext());
            }
        }

        return users;
    }

    /**
     * Método encargado de registrar una búsqueda reciente en la base de datos.
     * @param recentSearch Búsqueda a registrar.
     */
    @Override
    public void addRecentSearch(String recentSearch) {
        // Obtenemos la instancia de la base de datos.
        DatabaseHelper helper = DatabaseHelper.getInstance(context);

        // Configuramos el valor de la columna.
        ContentValues values = new ContentValues();
        values.put(SEARCH_COLUMN, recentSearch);

        // Llevamos a cabo la inserción en la base de datos.
        helper.getWritableDatabase().insert(SEARCHES_TABLE, null, values);
    }

    /**
     * Método encargado de obtener todas las búsquedas de la base de datos.
     * @return Búsquedas registradas en la base de datos.
     */
    @Override
    public List<String> getAllRecentSearches() {
        // Obtenemos la instancia de la base de datos.
        DatabaseHelper helper = DatabaseHelper.getInstance(context);

        // Llevamos a cabo la consulta en la base de datos.
        Cursor cursor = helper.getReadableDatabase().query(SEARCHES_TABLE, null, null, null, null,
                null, null);

        // Creamos la lista donde guardaremos las búsquedas recientes.
        List<String> recentSearches = new ArrayList<String>();

        // Hacemos la lectura de los resultados.
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String search = "";

                do {
                    search = cursor.getString(cursor.getColumnIndex(SEARCH_COLUMN));

                    // Añadimos la búsqueda en el ArrayList.
                    recentSearches.add(search);
                } while (cursor.moveToNext());
            }
        }

        return recentSearches;
    }
}
