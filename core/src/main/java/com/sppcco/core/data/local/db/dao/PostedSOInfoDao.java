package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.sub_model.PostedSOInfo;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface PostedSOInfoDao {

  @Query("SELECT so._id as SOId, so.SONo as SONo, so.EDate as SODate, \n" +
    "    so.SOReference as SOReference, \n" +
    "    so.CustomerId as CustomerId, so.CustomerName as CustomerName, \n" +
    "    sos.Posted as Posted, sos.PostedDate as PostDate \n" +
    "    FROM __SalesOrder__ so INNER JOIN __SOStatus__ sos ON so._id = sos.SOId \n" +
    "    WHERE sos.Approved = 1 AND sos.Posted = 1 \n" +
    "    ORDER BY \n" +
    "    CASE :sortType WHEN 6 THEN so.SONo ELSE '' END ASC, \n" +
    "    CASE :sortType WHEN 7 THEN so.SONo ELSE '' END DESC ")
  LiveData<List<PostedSOInfo>> getAllPostedSOInfo(int sortType);

  @Query("SELECT so._id as SOId, so.SONo as SONo, so.EDate as SODate, \n" +
    "    so.SOReference as SOReference, \n" +
    "    so.CustomerId as CustomerId, so.CustomerName as CustomerName, \n" +
    "    sos.Posted as Posted, sos.PostedDate as PostDate \n" +
    "    FROM __SalesOrder__ so INNER JOIN __SOStatus__ sos ON so._id = sos.SOId \n" +
    "    WHERE sos.Approved = 1 AND sos.Posted = 1  AND  " +
    "    (so.CustomerName LIKE '%'|| :filter ||'%' OR ((SELECT 1 WHERE :filter GLOB '*[0-9]*') = 1 AND so.CustomerId = CAST(:filter AS INT))) \n" +
    "    ORDER BY so.CustomerName  ASC ")
  LiveData<List<PostedSOInfo>> getAllFilteredPostedSOInfo(String filter);

}
