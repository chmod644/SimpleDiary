package com.example.n.simplediary.dummy;

import java.util.ArrayList;
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
        ITEM_MAP.put(item.id, item);
    }

    private static DiaryItem createDummyItem(int position) {
        return new DiaryItem(String.valueOf(position), "Item " + position, makeDetails(position));
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
        public final String id;
        public final String content;
        public final String details;

        public DiaryItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
