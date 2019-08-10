package com.sppcco.core.data.model;

import android.provider.BaseColumns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "__ApiServiceInfo__")
public class ApiServiceInfo implements Serializable,BaseColumns {

	@SerializedName("Id")
	@Expose
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "_id")
	private int Id;

	@SerializedName("BaseUrl")
	@Expose
	private String IpAddress;

	@SerializedName("Port")
	@Expose
	private int Port;

	@SerializedName("ApiKey")
	@Expose
	private int CallIdService;

	public ApiServiceInfo() {
		super();
	}

	public ApiServiceInfo(int id, String ipAddress, int port, int callIdService) {
		Id = id;
		IpAddress = ipAddress;
		Port = port;
		CallIdService = callIdService;
	}

	public ApiServiceInfo(String ipAddress, int port, int callIdService) {
		IpAddress = ipAddress;
		Port = port;
		CallIdService = callIdService;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getIpAddress() {
		return IpAddress;
	}

	public void setIpAddress(String ipAddress) {
		IpAddress = ipAddress;
	}

	public int getPort() {
		return Port;
	}

	public void setPort(int port) {
		Port = port;
	}

	public int getCallIdService() {
		return CallIdService;
	}

	public void setCallIdService(int callIdService) {
		CallIdService = callIdService;
	}
}