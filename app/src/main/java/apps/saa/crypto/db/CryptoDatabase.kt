package apps.saa.crypto.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import apps.saa.crypto.dao.CryptoDao
import apps.saa.crypto.data.Data

@Database(entities = [Data::class], version = 1)
abstract class CryptoDatabase : RoomDatabase() {

    abstract fun cryptoDao(): CryptoDao

    companion object {
        @Volatile
        private var INSTANCE: CryptoDatabase? = null

        fun getDatabase(context: Context): CryptoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, CryptoDatabase::class.java, "crypto_database").build()
                INSTANCE = instance
                instance
            }
        }
    }
}
