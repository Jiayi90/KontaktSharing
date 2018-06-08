package de.hdm.KontaktSharing.client.widget;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;

public class SmallButton extends PushButton {
	
	public SmallButton(String imgUrl) {
		super(new Image(imgUrl));
		this.getElement().setClassName("smallButton");
	}

}
