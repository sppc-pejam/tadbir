package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.model.AccVsPrj;
import com.sppcco.core.data.sub_model.AccVectorInfo;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by b_nematzadeh on 11/02/18.
 */

public interface AccVsPrjRepository {

  // ________________________________________ CRUD ________________________________________ //


  interface GetAccVsPrjsCallback {
    void onAccVsPrjsLoaded(List<AccVsPrj> accVsPrjs);
    void onDataNotAvailable();
  }

  void getAccVsPrjs(@NonNull GetAccVsPrjsCallback callback);


  interface GetAccVsPrjCallback {
    void onAccVsPrjLoaded(AccVsPrj accVsPrj);
    void onDataNotAvailable();
  }

  void getAccVsPrjByFullId(String fullId, @NonNull GetAccVsPrjCallback callback);



  // Insert
  interface InsertAccVsPrjsCallback {
    void onAccVsPrjsInserted(Long[] longs);
    void onDataNotAvailable();
  }

  void insertAccVsPrjs(List<AccVsPrj> accVsPrjs, @NonNull InsertAccVsPrjsCallback callback);

  interface InsertAccVsPrjCallback {
    void onAccVsPrjInserted(long accVsPrjId);
    void onDataNotAvailable();
  }

  void insertAccVsPrj(AccVsPrj accVsPrj, @NonNull InsertAccVsPrjCallback callback);


  // Update

  interface UpdateAccVsPrjsCallback {
    void onAccVsPrjsUpdated(int i);
    void onDataNotAvailable();
  }

  void updateAccVsPrjs(@NonNull UpdateAccVsPrjsCallback callback, AccVsPrj... accVsPrjs);


  interface UpdateAccVsPrjCallback {
    void onAccVsPrjUpdated(int i);
    void onDataNotAvailable();
  }

  void updateAccVsPrj(AccVsPrj accVsPrj, @NonNull UpdateAccVsPrjCallback callback);

  // Delete
  interface DeleteAccVsPrjsCallback {
    void onAccVsPrjsDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteAccVsPrjs(@NonNull DeleteAccVsPrjsCallback callback, AccVsPrj... accVsPrjs);


  interface DeleteAllAccVsPrjCallback {
    void onAccVsPrjsDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteAllAccVsPrj(@NonNull DeleteAllAccVsPrjCallback callback);
  
  // ________________________________________ CRUD ________________________________________ //

  // Other Method
  
  // Count(*)
  interface GetCountAccVsPrjCallback {
    void onAccVsPrjCounted(int count);
    void onDataNotAvailable();
  }
  void getCountAccVsPrj(@NonNull GetCountAccVsPrjCallback callback);

  interface GetAccVsPrjRelatedProjectCallback {
    void onAccVsPrjRelated(List<AccVectorInfo> accountInfos);
    void onDataNotAvailable();
  }
  void getAccVsPrjRelatedProject(String fullId, @NonNull GetAccVsPrjRelatedProjectCallback callback);
}
