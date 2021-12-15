package com.inusufforn.cinecom.entity;

import java.time.LocalDateTime;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.SequenceGenerator;
import org.seasar.doma.Table;

/**
 * ユーザ情報
 */
@Entity(listener = ReviewUserListener.class)
@Table(name = "review_user")
public class ReviewUser {

    /** ユーザID */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "review_user_id_seq")
    @Column(name = "id")
    Integer id;

    /** ユーザ名 */
    @Column(name = "username")
    String username;

    /** パスワード */
    @Column(name = "password")
    String password;

    /** ユーザ権限 */
    @Column(name = "authority")
    String authority;

    /** 作成日 */
    @Column(name = "created")
    LocalDateTime created;

    /** 更新日 */
    @Column(name = "updated")
    LocalDateTime updated;

    /** 作成者 */
    @Column(name = "created_by")
    String createdBy;

    /** 更新者 */
    @Column(name = "updated_by")
    String updatedBy;

    /** 
     * Returns the id.
     * 
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /** 
     * Sets the id.
     * 
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

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
     * Returns the password.
     * 
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /** 
     * Sets the password.
     * 
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /** 
     * Returns the authority.
     * 
     * @return the authority
     */
    public String getAuthority() {
        return authority;
    }

    /** 
     * Sets the authority.
     * 
     * @param authority the authority
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    /** 
     * Returns the created.
     * 
     * @return the created
     */
    public LocalDateTime getCreated() {
        return created;
    }

    /** 
     * Sets the created.
     * 
     * @param created the created
     */
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    /** 
     * Returns the updated.
     * 
     * @return the updated
     */
    public LocalDateTime getUpdated() {
        return updated;
    }

    /** 
     * Sets the updated.
     * 
     * @param updated the updated
     */
    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    /** 
     * Returns the createdBy.
     * 
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /** 
     * Sets the createdBy.
     * 
     * @param createdBy the createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /** 
     * Returns the updatedBy.
     * 
     * @return the updatedBy
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /** 
     * Sets the updatedBy.
     * 
     * @param updatedBy the updatedBy
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}