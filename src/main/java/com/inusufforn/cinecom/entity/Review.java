package com.inusufforn.cinecom.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.SequenceGenerator;
import org.seasar.doma.Table;

/**
 *  作品レビュー
 */
@Entity(listener = ReviewListener.class)
@Table(name = "review")
public class Review {

    /**  作品ID */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "review_id_seq")
    @Column(name = "id")
    Integer id;

    /**  作品タイトル */
    @Column(name = "title")
    String title;

    /**  見出しコメント */
    @Column(name = "short_comment")
    String shortComment;

    /**  鑑賞日 */
    @Column(name = "watched_date")
    LocalDate watchedDate;

    /**  上映時刻 */
    @Column(name = "watched_time")
    LocalTime watchedTime;

    /**  劇場名 */
    @Column(name = "theater")
    String theater;

    /**  スクリーン */
    @Column(name = "screen")
    String screen;

    /**  コメント本文 */
    @Column(name = "main_comment")
    String mainComment;

    /**  評価 */
    @Column(name = "rating")
    Short rating;

    /**  有効フラグ */
    @Column(name = "enable")
    Short enable;

    /**  作成日 */
    @Column(name = "created")
    LocalDateTime created;

    /**  更新日 */
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
     * Returns the title.
     * 
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /** 
     * Sets the title.
     * 
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /** 
     * Returns the shortComment.
     * 
     * @return the shortComment
     */
    public String getShortComment() {
        return shortComment;
    }

    /** 
     * Sets the shortComment.
     * 
     * @param shortComment the shortComment
     */
    public void setShortComment(String shortComment) {
        this.shortComment = shortComment;
    }

    /** 
     * Returns the watchedDate.
     * 
     * @return the watchedDate
     */
    public LocalDate getWatchedDate() {
        return watchedDate;
    }

    /** 
     * Sets the watchedDate.
     * 
     * @param watchedDate the watchedDate
     */
    public void setWatchedDate(LocalDate watchedDate) {
        this.watchedDate = watchedDate;
    }

    /** 
     * Returns the watchedTime.
     * 
     * @return the watchedTime
     */
    public LocalTime getWatchedTime() {
        return watchedTime;
    }

    /** 
     * Sets the watchedTime.
     * 
     * @param watchedTime the watchedTime
     */
    public void setWatchedTime(LocalTime watchedTime) {
        this.watchedTime = watchedTime;
    }

    /** 
     * Returns the theater.
     * 
     * @return the theater
     */
    public String getTheater() {
        return theater;
    }

    /** 
     * Sets the theater.
     * 
     * @param theater the theater
     */
    public void setTheater(String theater) {
        this.theater = theater;
    }

    /** 
     * Returns the screen.
     * 
     * @return the screen
     */
    public String getScreen() {
        return screen;
    }

    /** 
     * Sets the screen.
     * 
     * @param screen the screen
     */
    public void setScreen(String screen) {
        this.screen = screen;
    }

    /** 
     * Returns the mainComment.
     * 
     * @return the mainComment
     */
    public String getMainComment() {
        return mainComment;
    }

    /** 
     * Sets the mainComment.
     * 
     * @param mainComment the mainComment
     */
    public void setMainComment(String mainComment) {
        this.mainComment = mainComment;
    }

    /** 
     * Returns the rating.
     * 
     * @return the rating
     */
    public Short getRating() {
        return rating;
    }

    /** 
     * Sets the rating.
     * 
     * @param rating the rating
     */
    public void setRating(Short rating) {
        this.rating = rating;
    }

    /** 
     * Returns the enable.
     * 
     * @return the enable
     */
    public Short getEnable() {
        return enable;
    }

    /** 
     * Sets the enable.
     * 
     * @param enable the enable
     */
    public void setEnable(Short enable) {
        this.enable = enable;
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