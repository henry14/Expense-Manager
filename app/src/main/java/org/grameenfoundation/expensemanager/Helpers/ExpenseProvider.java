package org.grameenfoundation.expensemanager.Helpers;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.UserDictionary;

import org.grameenfoundation.expensemanager.Models.Expense;

import java.sql.SQLException;

/**
 * Created by henry on 3/22/16.
 */
public class ExpenseProvider extends ContentProvider {

    public static final String mProviderName = "org.grameenfoundation.provider.expensemanager";
    public static final String mUri = "content://"+mProviderName+"/expenses";
    public static final Uri mContentUri = Uri.parse(mUri);
    public static final String mExpenseTable = "expense_entry";

    private DatabaseHelper mDatabaseHelper;
    private static final String mDatabase = "expensedb";
    private SQLiteDatabase db;

    static final int mExpenses = 1;
    static final int mExpense_id = 2;

    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(mProviderName, "expenses", mExpenses);
        uriMatcher.addURI(mProviderName, "expenses/#", mExpense_id);
    }

    @Override
    public boolean onCreate() {

        mDatabaseHelper = new DatabaseHelper(getContext());
        db = mDatabaseHelper.getWritableDatabase();
        mDatabaseHelper.close();

        return (db == null) ? false : true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(mExpenseTable);

        if (sortOrder == null || sortOrder == ""){
            sortOrder = Expense.LABEL.toLowerCase();
        }

        Cursor mCursor = qb.query(mDatabaseHelper.getReadableDatabase(), projection, selection, selectionArgs, null, null, sortOrder);
        mCursor.setNotificationUri(getContext().getContentResolver(), uri);


        return mCursor;
    }

    @Override
    public String getType(Uri uri) {

        switch (uriMatcher.match(uri)){
            case mExpenses:
                return "vnd.android.cursor.dir/vnd.example.expenses";

            case mExpense_id:
                return "vnd.android.cursor.item/vnd.example.expenses";

            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        db = mDatabaseHelper.getWritableDatabase();

        long mRowId = db.insert(mExpenseTable, "", values);

        if (mRowId > 0) {
            Uri _uri = ContentUris.withAppendedId(mContentUri, mRowId);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        try {
            throw new SQLException("Failed to add a record into "+uri);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        mDatabaseHelper.close();

        return null;

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    protected static final class DatabaseHelper extends SQLiteOpenHelper {

        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE expense_entry ( _ID INTEGER PRIMARY KEY AUTOINCREMENT, ITEM TEXT, COST INTEGER, DATE TEXT )";

        DatabaseHelper(Context context) {
            super(context, mDatabase, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(SQL_CREATE_ENTRIES);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+mDatabase);
            onCreate(db);
        }
    }


}
