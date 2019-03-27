package foxsay.ru.loftcoin.screens.welcome;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

public class WelcomePage implements Parcelable {

    @DrawableRes
    private int icon;

    @StringRes
    private int title;

    @StringRes
    private int subtitle;

    public WelcomePage(int icon, int title, int subtitle) {
        this.icon = icon;
        this.title = title;
        this.subtitle = subtitle;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(int subtitle) {
        this.subtitle = subtitle;
    }


    protected WelcomePage(Parcel in) {
        icon = in.readInt();
        title = in.readInt();
        subtitle = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(icon);
        parcel.writeInt(title);
        parcel.writeInt(subtitle);
    }

    public static final Creator<WelcomePage> CREATOR = new Creator<WelcomePage>() {
        @Override
        public WelcomePage createFromParcel(Parcel in) {
            return new WelcomePage(in);
        }

        @Override
        public WelcomePage[] newArray(int size) {
            return new WelcomePage[size];
        }
    };
}
