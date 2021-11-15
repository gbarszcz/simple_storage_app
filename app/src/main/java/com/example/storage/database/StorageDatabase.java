package com.example.storage.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.storage.database.dao.ProductDao;
import com.example.storage.database.entity.Product;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Product.class}, version = 2)
public abstract class StorageDatabase extends RoomDatabase {

    public abstract ProductDao productDao();

    private static volatile StorageDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static StorageDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (StorageDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            StorageDatabase.class, "storage_database")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                }

                                @Override
                                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                                    super.onOpen(db);

                                    // If you want to keep data through app restarts,
                                    // comment out the following block
                                    // Populate the database in the background.
                                    databaseWriteExecutor.execute(() -> loadData(getDatabase(context)));
                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static void loadData(StorageDatabase db) {
        db.productDao().insert(new Product(1, "Mleko", 2.0, 10.5));
        db.productDao().insert(new Product(2, "Chleb", 1.5, 1.0));
        db.productDao().insert(new Product(3, "Filet z kurczaka", 15.99, 0.75));
        db.productDao().insert(new Product(4, "Kalendarz", 20.0, 1.0));
        db.productDao().insert(new Product(5, "Pepsi", 20.0, 2.5));
        db.productDao().insert(new Product(6, "Ryż", 3.5, 2.5));
        db.productDao().insert(new Product(7, "Orzechy arachidowe", 15.0, 1.5));
    }

}
