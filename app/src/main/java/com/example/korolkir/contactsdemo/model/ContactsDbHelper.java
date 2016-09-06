package com.example.korolkir.contactsdemo.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by korolkir on 06.09.16.
 */
public class ContactsDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contactsDatabase";
    private static final String TABLE_CONTACTS = "contacts";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_WEBSITE = "website";
    private static final String KEY_CITY = "city";
    private static final String KEY_STREET = "street";
    private static final String KEY_SUITE = "suit";
    private static final String KEY_ZIPCODE = "zipcode";

    public ContactsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "(" + KEY_ID +
                " INTEGER PRIMARY KEY," + KEY_NAME +
                " TEXT," +  KEY_USERNAME + " TEXT," + KEY_EMAIL + " TEXT," + KEY_PHONE + " TEXT," +
                KEY_WEBSITE + " TEXT," + KEY_CITY + " TEXT," + KEY_STREET + " TEXT," + KEY_SUITE + " TEXT," +
                KEY_ZIPCODE + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    public boolean addContact(Contact contact) {
        if(!ifContactExist(contact)) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(KEY_ID, contact.getId());
            values.put(KEY_NAME, contact.getName());
            values.put(KEY_USERNAME, contact.getUsername());
            values.put(KEY_EMAIL, contact.getEmail());
            values.put(KEY_PHONE, contact.getPhone());
            values.put(KEY_WEBSITE, contact.getWebsite());
            values.put(KEY_CITY, contact.getAddress().getCity());
            values.put(KEY_STREET, contact.getAddress().getStreet());
            values.put(KEY_SUITE, contact.getAddress().getSuite());
            values.put(KEY_ZIPCODE, contact.getAddress().getZipcode());

            try {
                db.insert(TABLE_CONTACTS, null, values);
            } catch (Exception e) {
                Log.i("Database", e.toString());
            }
            db.close();
            return true;
        }
        return false;
    }

    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))));
                contact.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                contact.setUsername(cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
                contact.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
                contact.setPhone(cursor.getString(cursor.getColumnIndex(KEY_PHONE)));
                contact.setWebsite(cursor.getString(cursor.getColumnIndex(KEY_WEBSITE)));
                Address address = new Address();
                address.setCity(cursor.getString(cursor.getColumnIndex(KEY_CITY)));
                address.setStreet(cursor.getString(cursor.getColumnIndex(KEY_STREET)));
                address.setSuite(cursor.getString(cursor.getColumnIndex(KEY_SUITE)));
                address.setZipcode(cursor.getString(cursor.getColumnIndex(KEY_ZIPCODE)));
                contact.setAddress(address);
                contacts.add(contact);
            } while (cursor.moveToNext());
        }
        return contacts;
    }

    private boolean ifContactExist(Contact contact) {
        List<Contact> contacts = getAllContacts();
        for(Contact savedContact: contacts) {
            if (savedContact.getId().equals(contact.getId()))
            return true;
        }
        return false;
    }
}
