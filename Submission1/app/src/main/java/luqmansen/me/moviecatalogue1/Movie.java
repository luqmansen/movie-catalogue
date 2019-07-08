package luqmansen.me.moviecatalogue1;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    private String title;
    private String release;
    private String desc;
    private Integer movieBg;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getMovieBg() {
        return movieBg;
    }

    public void setMovieBg(Integer movieBg) {
        this.movieBg = movieBg;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    //write object value to parcel for storage
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString( this.title );
        dest.writeString( this.release );
        dest.writeString( this.desc );
        dest.writeValue( this.movieBg );
    }

    public Movie() {
    }
    // constructor for parcel
    protected Movie(Parcel in) {
        this.title = in.readString();
        this.release = in.readString();
        this.desc = in.readString();
        this.movieBg = (Integer) in.readValue( Integer.class.getClassLoader() );
    }
    // creator - used when un-parceling the parcel
    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie( source );
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
