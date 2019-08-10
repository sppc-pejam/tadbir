package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.model.UserServiceLogin;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Created by m_pejam on 01/04/18.
 */
@Dao
public interface UserServiceLoginDao {

    // Get

    @Query("SELECT * FROM __UserServiceLogin__")
    List<UserServiceLogin> getAllUserServiceLogin();

    @Query("SELECT * FROM __UserServiceLogin__ WHERE _id = :userServiceLoginId")
    UserServiceLogin getUserServiceLoginById(int userServiceLoginId);

    // Insert

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertUserServiceLogins(List<UserServiceLogin> userServiceLogins);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertUserServiceLogin(UserServiceLogin userServiceLogin);

    // Update

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int updateUserServiceLogins(UserServiceLogin... userServiceLogins);

    @Update
    int updateUserServiceLogins(UserServiceLogin userServiceLogin);

    // Delete

    @Delete
    int deleteUserServiceLogins(UserServiceLogin... userServiceLogins);

    @Query("DELETE FROM __UserServiceLogin__")
    int deleteAllUserServiceLogin();

    @Query("DELETE FROM __UserServiceLogin__ WHERE _id = :userServiceLoginId")
    int deleteUserServiceLoginById(int userServiceLoginId);

    // Other Method
}
