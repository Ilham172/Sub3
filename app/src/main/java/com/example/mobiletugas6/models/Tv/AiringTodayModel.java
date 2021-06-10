package com.example.mobiletugas6.models.Tv;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AiringTodayModel implements Parcelable {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    private String title;

    private String overview;

    public AiringTodayModel() {
    }

    @SerializedName("first_air_date")
    private String firstAirDate;

    protected AiringTodayModel(Parcel in) {
        id = in.readString();
        title = in.readString();
        overview = in.readString();
        firstAirDate = in.readString();
        imgUrl = in.readString();
    }

    public static final Creator<AiringTodayModel> CREATOR = new Creator<AiringTodayModel>() {
        @Override
        public AiringTodayModel createFromParcel(Parcel in) {
            return new AiringTodayModel(in);
        }

        @Override
        public AiringTodayModel[] newArray(int size) {
            return new AiringTodayModel[size];
        }
    };

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    @SerializedName("poster_path")
    private String imgUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(overview);
        dest.writeString(firstAirDate);
        dest.writeString(imgUrl);
    }
}
