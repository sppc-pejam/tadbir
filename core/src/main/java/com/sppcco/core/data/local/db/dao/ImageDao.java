package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.model.Image;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.CompletableSource;

@Dao
public interface ImageDao {

  @Query("SELECT * FROM __Image__")
  List<Image> getAllImage();


  @Query("SELECT * FROM __Image__ WHERE ObjectId = :merchId Limit 1")
  Image getImageByMerchId(int merchId);

  @Query("SELECT * FROM __Image__ WHERE ObjectId = :merchId AND SysId = :sysId")
  Image getImageByMerchIdSysId(int merchId, int sysId);

  @Query("SELECT * FROM __Image__ WHERE ObjectId = :merchId AND SysId = :formId")
  Image getImageByMerchIdFormId(int merchId, int formId);

  @Query("SELECT * FROM __Image__ WHERE SysId = :sysId")
  Image getImageBySysId(int sysId);

  @Query("SELECT * FROM __Image__ WHERE FormId = :formId")
  Image getImageByFormId(int formId);

  @Query("SELECT * FROM __Image__ WHERE SysId = :sysId AND SysId = :formId")
  Image getImageBySysIdFormId(int sysId, int formId);

  /*@Query("SELECT ObjectId, Thumbnail FROM __Image__ WHERE SysId = :sysId AND SysId = :formId AND ObjectFPId = :fpId")
  ArrayMap<Integer, String> getImageCodeMap(int sysId, int formId, int fpId);*/

  // Insert

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Long[] insertImages(List<Image> images);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  CompletableSource insertRXImages(List<Image> images);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insertImage(Image image);

  // Update

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateImages(Image... images);

  @Update
  int updateImage(Image image);

  // Delete

  @Delete
  int deleteImages(Image... images);

  @Query("DELETE FROM __Image__")
  int deleteAllImage();

  @Query("DELETE FROM __Image__ WHERE ObjectId = :merchId")
  int deleteImageByMerchId(int merchId);

  // Other Methods

  @Query("SELECT img.ImageData FROM __Image__ img INNER JOIN __binappendix__ apx ON img._id = apx.ImageId AND img.ImageGuid = apx.ImageGuid" +
    " WHERE apx.ObjectId = :merchId AND apx.ObjectFPId = :fpId AND apx.SysId = :sysId AND apx.FormId = :formId AND apx.Id = 1")
  String getHQImage(int merchId, int fpId, int sysId, int formId);

  @Query("SELECT img.Thumbnail FROM __Image__ img INNER JOIN __binappendix__ apx ON img._id = apx.ImageId AND img.ImageGuid = apx.ImageGuid" +
    " WHERE apx.ObjectId = :merchId AND apx.ObjectFPId = :fpId AND apx.SysId = :sysId AND apx.FormId = :formId AND apx.Id = 1")
  String getLQImage(int merchId, int fpId, int sysId, int formId);

  @Query("SELECT COUNT(*) FROM __Image__ WHERE ObjectId = :merchId")
  int getCountImageByMerchId(int merchId);

  @Query("SELECT COUNT(*) FROM __Image__")
  int getCountImage();

  @Query("SELECT ObjectId FROM __Image__")
  int[] getAllObjectId();

  @Query("SELECT Thumbnail FROM __Image__ WHERE ObjectId = :merchId AND ObjectFPId = :fpId ")
  List<String> getThumbnailFormMerchandiseId(int merchId, int fpId);
}
