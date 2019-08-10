package com.sppcco.core.data.local.db.repository;


import com.sppcco.core.data.model.UserServiceLogin;

import java.util.List;

import androidx.annotation.NonNull;

public interface UserServiceLoginRepository {


    // ________________________________________ CRUD ________________________________________ //

    interface GetUserServiceLoginsCallback {
        void onUserServiceLoginsLoaded(List<UserServiceLogin> userServiceLogins);
        void onDataNotAvailable();
    }
    void getUserServiceLogins(@NonNull GetUserServiceLoginsCallback callback);


    interface GetUserServiceLoginCallback {
        void onUserServiceLoginLoaded(UserServiceLogin userServiceLogin);
        void onDataNotAvailable();
    }
    void getUserServiceLogin(int userServiceLoginId, @NonNull GetUserServiceLoginCallback callback);

    // Insert

    interface InsertUserServiceLoginsCallback {
        void onUserServiceLoginsInserted(Long[] longs);
        void onDataNotAvailable();
    }
    void insertUserServiceLogins(List<UserServiceLogin> userServiceLogins, @NonNull InsertUserServiceLoginsCallback callback);


    interface InsertUserServiceLoginCallback {
        void onUserServiceLoginInserted(long userServiceLoginId);
        void onDataNotAvailable();
    }
    void insertUserServiceLogin(UserServiceLogin userServiceLogin, @NonNull InsertUserServiceLoginCallback callback);

    // Update

    interface UpdateUserServiceLoginsCallback {
        void onUserServiceLoginsUpdated(int i);
        void onDataNotAvailable();
    }
    void updateUserServiceLogins(@NonNull UpdateUserServiceLoginsCallback callback, UserServiceLogin... userServiceLogins);


    interface UpdateUserServiceLoginCallback {
        void onUserServiceLoginUpdated(int i);
        void onDataNotAvailable();
    }
    void updateUserServiceLogin(UserServiceLogin userServiceLogin, @NonNull UpdateUserServiceLoginCallback callback);

    // Delete

    interface DeleteUserServiceLoginsCallback {
        void onUserServiceLoginsDeleted(int i);
        void onDataNotAvailable();
    }
    void deleteUserServiceLogins(@NonNull DeleteUserServiceLoginsCallback callback, UserServiceLogin... userServiceLogins);


    interface DeleteAllUserServiceLoginCallback {
        void onUserServiceLoginsDeleted(int i);
        void onDataNotAvailable();
    }
    void deleteAllUserServiceLogin(@NonNull DeleteAllUserServiceLoginCallback callback);


    interface DeleteUserServiceLoginCallback {
        void onUserServiceLoginDeleted(int i);
        void onDataNotAvailable();
    }
    void deleteUserServiceLoginById(int userServiceLoginId, @NonNull DeleteUserServiceLoginCallback callback);

    // ________________________________________ CRUD ________________________________________ //

}
