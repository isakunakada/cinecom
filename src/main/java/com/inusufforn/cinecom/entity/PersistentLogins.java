package com.inusufforn.cinecom.entity;

import java.time.LocalDateTime;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

/**
 */
@Entity(listener = PersistentLoginsListener.class)
@Table(name = "persistent_logins")
public class PersistentLogins {

    /** */
    @Column(name = "username")
    String username;

    /** */
    @Id
    @Column(name = "series")
    String series;

    /** */
    @Column(name = "token")
    String token;

    /** */
    @Column(name = "last_used")
    LocalDateTime lastUsed;

    /** 
     * Returns the username.
     * 
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /** 
     * Sets the username.
     * 
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /** 
     * Returns the series.
     * 
     * @return the series
     */
    public String getSeries() {
        return series;
    }

    /** 
     * Sets the series.
     * 
     * @param series the series
     */
    public void setSeries(String series) {
        this.series = series;
    }

    /** 
     * Returns the token.
     * 
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /** 
     * Sets the token.
     * 
     * @param token the token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /** 
     * Returns the lastUsed.
     * 
     * @return the lastUsed
     */
    public LocalDateTime getLastUsed() {
        return lastUsed;
    }

    /** 
     * Sets the lastUsed.
     * 
     * @param lastUsed the lastUsed
     */
    public void setLastUsed(LocalDateTime lastUsed) {
        this.lastUsed = lastUsed;
    }
}