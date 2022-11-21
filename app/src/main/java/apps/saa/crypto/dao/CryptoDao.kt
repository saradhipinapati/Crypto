package apps.saa.crypto.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import apps.saa.crypto.data.Data

@Dao
interface CryptoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(data: List<Data>)
}