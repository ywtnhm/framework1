package cn.vansky.framework.common.util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Date;

/**
 * A json tools for the gson deal of the google.
 * Author: CK
 * Date: 2015/8/7
 */
public class JsonUtil {
    /**
     * the {@code String} convert the {@code T} class message.
     * @param json the json {@code String}
     * @param clazz Class
     * @param <T> Class
     * @return Class
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        return gson().fromJson(json, clazz);
    }

    public static String toJson(Object obj) {
        return gson().toJson(obj);
    }

    public static Gson gson() {
        String datePattern = DateUtil.yyyy_MM_dd_HH_mm_ss;
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat(datePattern);
        return builder.create();
    }

    public static Gson gson1() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, new DateSerializer());
        builder.setDateFormat(DateFormat.LONG);
        return builder.create();
    }

    /**
     * the time stamp convert the {@link java.util.Date} of java
     */
    public static class DateDeserializer implements JsonDeserializer<Date> {
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return new Date(json.getAsJsonPrimitive().getAsLong());
        }
    }

    /**
     * the {@link java.util.Date} of java convert the time stamp
     */
    public static class DateSerializer implements JsonSerializer<Date> {
        public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.getTime());
        }
    }
}
