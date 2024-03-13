package kr.co.neteacher.eclass.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

@Data
@Accessors(chain = true)
public class QueryParams {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @JsonProperty("date_from")
    String dateFrom;
    @JsonProperty("date_to")
    String dateTo;

    Integer page;
    Integer size;

    boolean pageable = true;

    public Pageable getPageable() {
        if (pageable) {
            final int currentPage = Optional.ofNullable(page).orElse(1);
            final int pageSize = Optional.ofNullable(size).orElse(10);

            return PageRequest.of(currentPage - 1, pageSize);
        } else {
            return Pageable.unpaged();
        }
    }

    public Optional<Calendar> getCalendarDateFrom() {
        return getCalendar(dateFrom);
    }

    public Optional<Calendar> getCalendarDateTo() {
        return getCalendar(dateTo);
    }

    private Optional<Calendar> getCalendar(String dateString) {
        if (!Optional.ofNullable(dateString).isPresent()) {
            return Optional.empty();
        }

        Calendar dateToParam = Calendar.getInstance();
        try {
            dateToParam.setTime(dateFormat.parse(Optional.of(dateString).get()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return Optional.of(dateToParam);
    }
}
