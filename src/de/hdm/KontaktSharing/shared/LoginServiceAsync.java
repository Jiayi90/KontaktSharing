package de.hdm.KontaktSharing.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.KontaktSharing.client.LoginInfo;

public interface LoginServiceAsync {
  public void login(String requestUri, AsyncCallback<LoginInfo> async);
}
