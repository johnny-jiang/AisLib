package dk.dma.ais.json_decoder_helpers.decoded_objects;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@SuppressWarnings("unused")
public class DecodedAisDate {

    private long dateInMillis;
    private String textDate;

    public DecodedAisDate(long dateInMillis, String textDate) {
        this.dateInMillis = dateInMillis;
        this.textDate = textDate;
    }

    public DecodedAisDate(Date date) {
        this.dateInMillis = date.getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.setTime(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE YYYY MMM dd HH:mm:ss zzz ", Locale.US);
        this.textDate = simpleDateFormat.format(calendar.getTime());
    }

    public long getDateInMillis() {
        return dateInMillis;
    }

    public void setDateInMillis(long dateInMillis) {
        this.dateInMillis = dateInMillis;
    }

    public String getTextDate() {
        return textDate;
    }

    public void setTextDate(String textDate) {
        this.textDate = textDate;
    }
}
