package com.sidiq.toko.DAO

import androidx.room.*
import com.sidiq.toko.Model.TokoModel

@Dao
interface DaoToko {

    @Query("SELECT * FROM toko ")
    fun getAll(): List<TokoModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(toko: List<TokoModel>)


    @Update
    fun update(toko: TokoModel)


    @Delete
    fun delete(toko: TokoModel)
}