/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.linkedin.camus.etl.kafka.jsons.beans;

import java.io.Serializable;

public class WearableBean implements Serializable {
    
    private int latitude;
    private int longitude;
    private String user;
    private String eventDate;
    private String activity;

    public WearableBean() {
    
    }

    public WearableBean(int latitude, int longitude, String user, String eventDate, String activity) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.user = user;
        this.eventDate = eventDate;
        this.activity = activity;
    }

    /**
     * @return the latitude
     */
    public int getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public int getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the eventDate
     */
    public String getEventDate() {
        return eventDate;
    }

    /**
     * @param eventDate the eventDate to set
     */
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * @return the activity
     */
    public String getActivity() {
        return activity;
    }

    /**
     * @param activity the activity to set
     */
    public void setActivity(String activity) {
        this.activity = activity;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.latitude;
        hash = 19 * hash + this.longitude;
        hash = 19 * hash + (this.user != null ? this.user.hashCode() : 0);
        hash = 19 * hash + (this.eventDate != null ? this.eventDate.hashCode() : 0);
        hash = 19 * hash + (this.activity != null ? this.activity.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WearableBean other = (WearableBean) obj;
        if (this.latitude != other.latitude) {
            return false;
        }
        if (this.longitude != other.longitude) {
            return false;
        }
        if ((this.user == null) ? (other.user != null) : !this.user.equals(other.user)) {
            return false;
        }
        if ((this.eventDate == null) ? (other.eventDate != null) : !this.eventDate.equals(other.eventDate)) {
            return false;
        }
        return !((this.activity == null) ? (other.activity != null) : !this.activity.equals(other.activity));
    }

    @Override
    public String toString() {
        return "WearableBean{" + "latitude=" + latitude + ", longitude=" + longitude + ", user=" + user + ", eventDate=" + eventDate + ", activity=" + activity + '}';
    }
}