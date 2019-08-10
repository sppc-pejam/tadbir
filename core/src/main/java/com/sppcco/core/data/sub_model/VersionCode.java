package com.sppcco.core.data.sub_model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VersionCode implements Serializable {

	@SerializedName("major")
	private int major;

	@SerializedName("minor")
	private int minor;

	@SerializedName("patch")
	private int patch;

	public VersionCode(int major, int minor, int patch) {
		this.major = major;
		this.minor = minor;
		this.patch = patch;
	}

	public int getMajor() {
		return major;
	}

	public void setMajor(int major) {
		this.major = major;
	}

	public int getMinor() {
		return minor;
	}

	public void setMinor(int minor) {
		this.minor = minor;
	}

	public int getPatch() {
		return patch;
	}

	public void setPatch(int patch) {
		this.patch = patch;
	}

	@Override
 	public String toString(){
		return
			"VersionCode{" +
			"major = '" + major + '\'' +
			",minor = '" + minor + '\'' +
			",patch = '" + patch + '\'' +
			"}";
		}
}