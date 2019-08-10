package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.model.Option;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.CompletableSource;

@Dao
public interface OptionDao {

  @Query("SELECT * FROM __Options__")
  List<Option> getAllOptions();


  /*@Query("SELECT * FROM __Options__ WHERE _id = :optionId AND UserId = :userId")
  Option getOptionById(int optionId, int userId);*/

  // Insert

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Long[] insertOptions(List<Option> options);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  CompletableSource insertRXOptions(List<Option> options);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insertOption(Option option);

  // Update

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateOptions(Option... options);

  @Update
  int updateOption(Option option);

  // Delete

  @Delete
  int deleteOptions(Option... options);

  @Query("DELETE FROM __Options__")
  int deleteAllOptions();

  @Query("DELETE FROM __Options__ WHERE _id = :optionId")
  int deleteOptionById(int optionId);

  // Other Methods

  @Query("SELECT OptionVal FROM __Options__ WHERE _id = :optionId" )
  String getOptionValueById(int optionId);

  @Query("SELECT OptionVal FROM __Options__ WHERE _id = :optionId AND UserId = :userId" )
  String getOptionValueByIdAndUserId(int optionId, int userId);

  @Query("SELECT COUNT(*) FROM __Options__")
  int getCountOption();
}
