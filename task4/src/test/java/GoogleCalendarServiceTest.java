import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GoogleCalendarServiceTest {

    @Mock
    private GoogleCalendarService calendar;

    @Test
    public void create() throws IOException {
        List<String> eventProperties = new ArrayList<>();
        eventProperties.add("hello");
        eventProperties.add("SPb");
        eventProperties.add("test");
        eventProperties.add("2019-11-12T02:02:02+03:00");
        eventProperties.add("2019-12-12T02:02:02+03:00");
        eventProperties.add("n");

        Event event = new Event();
        event.setSummary(eventProperties.get(0));
        event.setLocation(eventProperties.get(1));
        event.setDescription(eventProperties.get(2));

        DateTime startDateTime = new DateTime(eventProperties.get(3));
        EventDateTime startTime = new EventDateTime()
                .setTimeZone("Europe/Moscow")
                .setDateTime(startDateTime);

        DateTime endDateTime = new DateTime(eventProperties.get(4));
        EventDateTime endTime = new EventDateTime()
                .setTimeZone("Europe/Moscow")
                .setDateTime(endDateTime);
        event.setStart(startTime)
                .setEnd(endTime);

        doReturn(event).when(calendar).create(eventProperties);
        assertEquals(calendar.create(eventProperties),event);
    }
}
