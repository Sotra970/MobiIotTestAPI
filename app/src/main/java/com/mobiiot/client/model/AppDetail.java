package com.mobiiot.client.model;

import android.graphics.drawable.Drawable;

public class AppDetail {

	String label;
	String name;
	Drawable icon;

	public AppDetail(String label, String name, Drawable icon) {
		this.label = label;
		this.name = name;
		this.icon = icon;
	}

	public String getLabel() {
		return label;
	}

	public String getName() {
		return name;
	}

	public Drawable getIcon() {
		return icon;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIcon(Drawable icon) {
		this.icon = icon;
	}
}
