package selise.exam.practicaltest.util;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Slf4j
public class DateUtil {

    private DateUtil() {
    }

    public static final DateTimeFormatter STANDARD_DATE_FORMAT_WITH_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String convertDateToString(LocalDateTime date) {
        return Objects.nonNull(date) ? date.format(STANDARD_DATE_FORMAT_WITH_TIME) : "";
    }

    public static LocalDateTime convertStringToDateTime(String date) {
        try {
            return Objects.nonNull(date) ? LocalDateTime.parse(date, STANDARD_DATE_FORMAT_WITH_TIME) : null;
        } catch (Exception exception) {
            log.info("Exception occurred while parsing date");
            exception.printStackTrace();
            return null;
        }
    }

    public static Instant getStandardCurrentTimeStamp() {
        return Instant.now();
    }
}
