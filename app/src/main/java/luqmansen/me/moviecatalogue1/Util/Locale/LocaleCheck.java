package luqmansen.me.moviecatalogue1.Util.Locale;

import android.content.Context;

import java.util.Locale;

public class LocaleCheck {
    Context context;
    String activeLanguage = null;
    String language = null;

    public LocaleCheck(Context context, String activeLanguage) {
        this.context = context;
        this.activeLanguage = activeLanguage;
    }

    public String languageChecker(String activeLanguage){

        if(activeLanguage != Locale.getDefault().getDisplayLanguage()){
            String language = Locale.getDefault().getDisplayLanguage();
            activeLanguage = language;
        }
        return activeLanguage ;

    }

}
