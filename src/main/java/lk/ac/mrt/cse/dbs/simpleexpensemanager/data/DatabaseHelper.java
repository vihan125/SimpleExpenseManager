package lk.ac.mrt.cse.dbs.simpleexpensemanager.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "170387F";
    private static final int DATABASE_VERSION = 3;
//    create tables
    public static final String TABLE_ACCOUNTS = "accounts";
    public static final String TABLE_TRANSACTIONS = "transactions";
//    public static final String TABLE_EXPENSE_TYPES = "expenseTypes";

//    create keys
    public static final String ACCOUNT_NO = "accountNo";
    public static final String BANK_NAME = "bankName";
    public static final String ACCOUNT_HOLDER_NAME = "accountHolderName";
    public static final String BALANCE = "balance";
    private static final String TRANSACTION_ID = "id";
    public static final String EXPENSE_TYPE = "expenseType";
    public static final String AMOUNT = "amount";
    public static final String DATE = "date";

    public static DatabaseHelper instance;

    public static DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context);
        }
        return instance;
    };

    private static final String CREATE_ACCOUNTS_TABLE = "CREATE TABLE " + TABLE_ACCOUNTS + "("
            + ACCOUNT_NO + " TEXT PRIMARY KEY," + BANK_NAME + " TEXT,"
            + ACCOUNT_HOLDER_NAME + " TEXT," + BALANCE + " REAL" + ")";

    private static final String CREATE_TRANSACTIONS_TABLE = "CREATE TABLE " + TABLE_TRANSACTIONS + "("
            + TRANSACTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + DATE + " TEXT," + ACCOUNT_NO + " TEXT,"
            + EXPENSE_TYPE + " TEXT," + AMOUNT + " REAL," + "FOREIGN KEY(" + ACCOUNT_NO +
            ") REFERENCES "+ TABLE_ACCOUNTS +"(" + ACCOUNT_NO + ") )";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ACCOUNTS_TABLE);
        db.execSQL(CREATE_TRANSACTIONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_ACCOUNTS + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_TRANSACTIONS + "'");

        // Create tables again
        onCreate(db);
    }
}
