import com.google.api.services.calendar.model.Event;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.util.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GoogleCalendarServiceTest {

    @Mock
    private GoogleCalendarService calendar;

    @Test
    public void get() throws IOException {
        //when(calendar.getEvents(1)).thenReturn();
        //verify(calendar.getEvents(1));
    }

    @Test
    public void delete() {

    }
}
