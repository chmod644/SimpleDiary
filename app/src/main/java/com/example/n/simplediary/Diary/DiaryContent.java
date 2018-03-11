package com.example.n.simplediary.Diary;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DiaryContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DiaryItem> ITEMS = new ArrayList<DiaryItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DiaryItem> ITEM_MAP = new HashMap<String, DiaryItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(DiaryItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(String.valueOf(item.id), item);
    }

    private static DiaryItem createDummyItem(int position) {
        return new DiaryItem(position, 1980, 1, 1, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DiaryItem {
        public long id;
        public int year;
        public int month;
        public int day;
        public String content;

        public DiaryItem() { };

        public DiaryItem(long id, int year, int month, int day, String content) {
            this.id = id;
            this.year = year;
            this.month = month;
            this.day = day;
            this.content = content;
        }

        public String getStringId(){ return String.valueOf(this.id); }

        public String getStringDate(){
            Calendar calendar = new GregorianCalendar(this.year, this.month, this.day);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
            return dateFormat.format(calendar.getTime());
            }

        @Override
        public String toString() {
            return content;
        }
    }
}
