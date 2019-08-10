package com.sppcco.core.data.local.db.repository;


import com.sppcco.core.data.local.db.dao.UserServiceLoginDao;
import com.sppcco.core.data.model.UserServiceLogin;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;


public class UserServiceLoginDataSource implements UserServiceLoginRepository {

    //private static volatile UserServiceLoginDataSource INSTANCE;

    private UserServiceLoginDao userServiceLoginDao;
    private AppExecutors appExecutors;

    @Inject
    public UserServiceLoginDataSource(AppExecutors appExecutors, UserServiceLoginDao userServiceLoginDao) {
        this.userServiceLoginDao = userServiceLoginDao;
        this.appExecutors = appExecutors;
    }

    /*public static UserServiceLoginDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                         @NonNull UserServiceLoginDao userServiceLogin) {
        if (INSTANCE == null) {
            synchronized (UserServiceLoginDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserServiceLoginDataSource(appExecutors, userServiceLogin);
                }
            }
        }
        return INSTANCE;
    }*/

    @Override
    public void getUserServiceLogins(@NonNull final GetUserServiceLoginsCallback callback) {
        Runnable runnable = () -> {
            final List<UserServiceLogin> userInfos = userServiceLoginDao.getAllUserServiceLogin();

            appExecutors.mainThread().execute(() -> {
                if(userInfos != null)
                    callback.onUserServiceLoginsLoaded(userInfos);
                else
                    callback.onDataNotAvailable();
            });
        };

        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void getUserServiceLogin(final int userInfoId, @NonNull final GetUserServiceLoginCallback callback) {
        Runnable runnable = () -> {
            final UserServiceLogin userInfo = userServiceLoginDao.getUserServiceLoginById(userInfoId);

            appExecutors.mainThread().execute(() -> {
                if(userInfo != null)
                    callback.onUserServiceLoginLoaded(userInfo);
                else
                    callback.onDataNotAvailable();
            });
        };

        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void insertUserServiceLogins(final List<UserServiceLogin> userInfos, @NonNull final InsertUserServiceLoginsCallback callback) {
        Runnable runnable = () -> {
            final Long[] longs = userServiceLoginDao.insertUserServiceLogins(userInfos);

            appExecutors.mainThread().execute(() -> {
                if(longs != null)
                    callback.onUserServiceLoginsInserted(longs);
                else
                    callback.onDataNotAvailable();
            });
        };
        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void insertUserServiceLogin(final UserServiceLogin userServiceLogin, @NonNull final InsertUserServiceLoginCallback callback) {
        Runnable runnable = () -> {
            final long lUserServiceLoginId  = userServiceLoginDao.insertUserServiceLogin(userServiceLogin);

            appExecutors.mainThread().execute(() -> {
                if(lUserServiceLoginId != 0)
                    callback.onUserServiceLoginInserted(lUserServiceLoginId);
                else
                    callback.onDataNotAvailable();
            });
        };
        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void updateUserServiceLogins(@NonNull final UpdateUserServiceLoginsCallback callback, final UserServiceLogin... userInfos) {
        Runnable runnable = () -> {
            final int i = userServiceLoginDao.updateUserServiceLogins(userInfos);

            appExecutors.mainThread().execute(() -> {
                if(i != 0)
                    callback.onUserServiceLoginsUpdated(i);
                else
                    callback.onDataNotAvailable();
            });
        };
        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void updateUserServiceLogin(final UserServiceLogin userInfo, @NonNull final UpdateUserServiceLoginCallback callback) {
        Runnable runnable = () -> {
            final int i  = userServiceLoginDao.updateUserServiceLogins(userInfo);

            appExecutors.mainThread().execute(() -> {
                if(i != 0)
                    callback.onUserServiceLoginUpdated(i);
                else
                    callback.onDataNotAvailable();
            });
        };
        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void deleteUserServiceLogins(@NonNull final DeleteUserServiceLoginsCallback callback, final UserServiceLogin... userInfos) {
        Runnable runnable = () -> {
            final int i = userServiceLoginDao.deleteUserServiceLogins(userInfos);

            appExecutors.mainThread().execute(() -> {
                if(i != 0)
                    callback.onUserServiceLoginsDeleted(i);
                else
                    callback.onDataNotAvailable();
            });
        };
        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void deleteAllUserServiceLogin(@NonNull final DeleteAllUserServiceLoginCallback callback) {
        Runnable runnable = () -> {
            final int i = userServiceLoginDao.deleteAllUserServiceLogin();

            appExecutors.mainThread().execute(() -> {
                if(i >= 0)
                    callback.onUserServiceLoginsDeleted(i);
                else
                    callback.onDataNotAvailable();
            });
        };
        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void deleteUserServiceLoginById(final int userInfoId, @NonNull final DeleteUserServiceLoginCallback callback) {
        Runnable runnable = () -> {
            final int i = userServiceLoginDao.deleteUserServiceLoginById(userInfoId);

            appExecutors.mainThread().execute(() -> {
                if(i != 0)
                    callback.onUserServiceLoginDeleted(i);
                else
                    callback.onDataNotAvailable();
            });
        };
        appExecutors.diskIO().execute(runnable);
    }

}
