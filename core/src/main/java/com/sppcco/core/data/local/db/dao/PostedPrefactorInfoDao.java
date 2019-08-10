package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.sub_model.PostedPrefactorInfo;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface PostedPrefactorInfoDao {

  @Query("SELECT spf._id as SPId, spf.FactorNo as FactorNo, spf.EDate as SPDate, \n" +
    "    sps.SPReference as SPReference, \n" +
    "    spf.CustomerId as CustomerId, spf.CustomerName as CustomerName, spf.Total as Total, \n" +
    "    sps.Posted as Posted, sps.PostedDate as PostDate \n" +
    "    FROM __SPFactor__ spf INNER JOIN __SPStatus__ sps ON spf._id = sps.SPId \n" +
    "    WHERE sps.Approved = 1 AND sps.Posted = 1 \n" +
    "    ORDER BY \n" +
    "    CASE :sortType WHEN 6 THEN spf._id ELSE '' END ASC, \n" +
    "    CASE :sortType WHEN 7 THEN spf._id ELSE '' END DESC ")
  LiveData<List<PostedPrefactorInfo>> getAllPostedPrefactorInfo(int sortType);

  @Query("SELECT spf._id as SPId, spf.FactorNo as FactorNo, spf.EDate as SPDate, \n" +
    "    sps.SPReference as SPReference, \n" +
    "    spf.CustomerId as CustomerId, spf.CustomerName as CustomerName, spf.Total as Total, \n" +
    "    sps.Posted as Posted, sps.PostedDate as PostDate \n" +
    "    FROM __SPFactor__ spf INNER JOIN __SPStatus__ sps ON spf._id = sps.SPId \n" +
    "    WHERE sps.Approved = 1 AND sps.Posted = 1 AND  " +
    "    (spf.CustomerName LIKE '%'|| :filter ||'%' OR ((SELECT 1 WHERE :filter GLOB '*[0-9]*') = 1 AND spf.CustomerId = CAST(:filter AS INT))) \n" +
    "    ORDER BY spf.CustomerName  ASC ")
  LiveData<List<PostedPrefactorInfo>> getAllFilteredPostedPrefactorInfo(String filter);

}
