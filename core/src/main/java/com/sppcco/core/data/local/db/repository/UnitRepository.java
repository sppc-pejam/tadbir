package com.sppcco.core.data.local.db.repository;


import com.sppcco.core.data.model.Unit;

import java.util.List;

import androidx.annotation.NonNull;

public interface UnitRepository {


    // ________________________________________ CRUD ________________________________________ //

    interface GetUnitsCallback {
        void onUnitsLoaded(List<Unit> units);
        void onDataNotAvailable();
    }

    void getUnits(@NonNull GetUnitsCallback callback);


    interface GetUnitCallback {
        void onUnitLoaded(Unit unit);
        void onDataNotAvailable();
    }

    void getUnit(int unitId, @NonNull GetUnitCallback callback);


    // Insert
    interface InsertUnitsCallback {
        void onUnitsInserted(Long[] longs);
        void onDataNotAvailable();
    }

    void insertUnits(List<Unit> units, @NonNull InsertUnitsCallback callback);

    interface InsertUnitCallback {
        void onUnitInserted(long unitId);
        void onDataNotAvailable();
    }

    void insertUnit(Unit unit, @NonNull InsertUnitCallback callback);


    // Update

    interface UpdateUnitsCallback {
        void onUnitsUpdated(int i);
        void onDataNotAvailable();
    }

    void updateUnits(@NonNull UpdateUnitsCallback callback, Unit... units);


    interface UpdateUnitCallback {
        void onUnitUpdated(int i);
        void onDataNotAvailable();
    }

    void updateUnit(Unit unit, @NonNull UpdateUnitCallback callback);

    // Delete


    interface DeleteUnitsCallback {
        void onUnitsDeleted(int i);
        void onDataNotAvailable();
    }
    void deleteUnits(@NonNull DeleteUnitsCallback callback, Unit... units);


    interface DeleteAllUnitCallback {
        void onUnitsDeleted(int i);
        void onDataNotAvailable();
    }
    void deleteAllUnit(@NonNull DeleteAllUnitCallback callback);


    interface DeleteUnitCallback {
        void onUnitDeleted(int i);
        void onDataNotAvailable();
    }
    void deleteUnitById(int unitId, @NonNull DeleteUnitCallback callback);

    // ________________________________________ CRUD ________________________________________ //

    // Other Method


    interface GetUnitIdsCallback {
        void onUnitIdsLoaded(int[] ints);
        void onDataNotAvailable();
    }
     void getUnitIds(@NonNull GetUnitIdsCallback callback);

    interface GetUnitNamesCallback {
        void onUnitNamesLoaded(String[] strings);
        void onDataNotAvailable();
    }
    void getAllUnitName(@NonNull GetUnitNamesCallback callback);

    interface GetUnitIdCallback {
        void onUnitIdLoaded(int i);
        void onDataNotAvailable();
    }
    void getUnitIdFromUnitName(String unitName, @NonNull GetUnitIdCallback callback);

    interface GetUnitNameCallback {
        void onUnitNameLoaded(String string);
        void onDataNotAvailable();
    }
    void getUnitNameFromUnitId(int unitId, @NonNull GetUnitNameCallback callback);

    // Count(*)
    interface GetCountUnitCallback {
        void onUnitCounted(int count);
        void onDataNotAvailable();
    }
    void getCountUnit(@NonNull GetCountUnitCallback callback);
}
