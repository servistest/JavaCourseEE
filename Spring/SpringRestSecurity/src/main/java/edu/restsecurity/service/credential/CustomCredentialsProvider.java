package edu.restsecurity.service.credential;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.impl.client.BasicCredentialsProvider;

/**
 * Created by Admin on 25.10.2016.
 */
public class CustomCredentialsProvider extends BasicCredentialsProvider {
    public void setCredentials(Credentials credentials){
        this.setCredentials(AuthScope.ANY,credentials);
    }
}
