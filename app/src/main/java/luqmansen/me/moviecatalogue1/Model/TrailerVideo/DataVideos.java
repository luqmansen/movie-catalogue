package luqmansen.me.moviecatalogue1.Model.TrailerVideo;

import com.google.gson.annotations.SerializedName;

public class DataVideos {

    @SerializedName("key")
    private String youtubeID;

//    public DataVideos(String youtubeID) {
//        this.youtubeID = youtubeID;
//    }
        public DataVideos() {

    }

    public String getYoutubeID() {
        return youtubeID;
    }

    public void setYoutubeID(String youtubeID) {
        this.youtubeID = youtubeID;
    }


}
