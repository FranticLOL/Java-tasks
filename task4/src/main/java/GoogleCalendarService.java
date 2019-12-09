import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import com.google.api.services.calendar.model.Events;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GoogleCalendarService {
    private Calendar calendar;

    GoogleCalendarService(Calendar googleCalendar) {
        calendar = googleCalendar;
    }

    void start() throws IOException {
        System.out.println("Choose option number:");
        System.out.println("1. Get events");
        System.out.println("2. Create an event");
        System.out.println("3. Edit an event");
        System.out.println("4. Delete an event");
        System.out.println("5. Exit");
        Scanner input = new Scanner(System.in);

        String option = input.nextLine();

        if (option.equals("1")) {
            System.out.println("Enter count of events: ");
            input = new Scanner(System.in);
            String counts = input.nextLine();
            if (counts.matches("[1-9]+")) {
                getEvents(Integer.parseInt(counts));
                return;
            } else {
                throw new IOException("Incorrect input. Please enter positive number.");
            }
        }
        if (option.equals("2")) {
            create();
            return;
        }

        if (option.equals("3")) {
            System.out.println("Enter event number for editing: ");
            input = new Scanner(System.in);
            String counts = input.nextLine();
            if (counts.matches("[1-9]+")) {
                edit(Integer.parseInt(counts));
                return;
            } else {
                throw new IOException("Incorrect input. Please enter positive number.");
            }
        }

        if (option.equals("4")) {
            System.out.println("Enter event number for deleting: ");
            input = new Scanner(System.in);
            String counts = input.nextLine();
            if (counts.matches("[1-9]+")) {
                delete(Integer.parseInt(counts));
                return;
            } else {
                throw new IOException("Incorrect input. Please enter positive number.");
            }
        }

        if (option.equals("5")) {
            System.exit(0);
        } else {
            System.out.println("Incorrect option. Try again.");
        }
    }

    public List<Event> getEvents(int counts) throws IOException {
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = calendar.events().list("primary")
                .setMaxResults(counts)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        List<Event> items = events.getItems();
        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            System.out.println("Upcoming events");
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                System.out.println(event.getSummary() + " " + start);
            }
        }
        return items;
    }

    public void create() throws IOException {
        Scanner input = new Scanner(System.in);

        Event event = new Event();


        initializeEvent(event);

        System.out.println("Do you need reminder? y/n");
        if (input.nextLine().equals("y")) {
            EventReminder reminder = new EventReminder();
            reminder.setMethod("email");
            System.out.println("Enter time of reminder executes before event: ");
            int reminderTime = input.nextInt();
            reminder.setMinutes(reminderTime);

            List<EventReminder> remindersList = new ArrayList<>();
            remindersList.add(reminder);
            Event.Reminders reminders = new Event.Reminders();
            reminders.setUseDefault(false);
            reminders.setOverrides(remindersList);
            event.setReminders(reminders);
        }

        String calendarId = "primary";
        event = calendar.events().insert(calendarId, event).execute();
        System.out.printf("Event created: %s\n", event.getHtmlLink());
    }

    private void edit(int id) throws IOException {
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = calendar.events().list("primary")
                .setMaxResults(id + 1)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        List<Event> items = events.getItems();

        Event currentEvent = items.get(id - 1);
        initializeEvent(currentEvent);
        String calendarId = "primary";
        calendar.events().update(calendarId, currentEvent.getId(), currentEvent).execute();
    }

    private void initializeEvent(Event currentEvent) {
        Scanner input = new Scanner(System.in);
        String inputString;
        System.out.println("Enter summary: ");
        if (!(inputString = input.nextLine()).equals("")) {
            currentEvent.setSummary(inputString);
        }
        System.out.println("Enter location: ");
        if (!(inputString = input.nextLine()).equals("")) {
            currentEvent.setLocation(inputString);
        }
        System.out.println("Enter description: ");
        if (!(inputString = input.nextLine()).equals("")) {
            currentEvent.setDescription(inputString);
        }

        System.out.println("Enter star date time according to YY-MM-DDThh:mm:ss+3:00 : "); //"2019-11-12T02:02:02+03:00"
        DateTime startDateTime = new DateTime(input.nextLine());
        EventDateTime startTime = new EventDateTime()
                .setTimeZone("Europe/Moscow")
                .setDateTime(startDateTime);

        System.out.println("Enter end date time according to YY-MM-DDThh:mm:ss+3:00 : ");
        DateTime endDateTime = new DateTime(input.nextLine());
        EventDateTime endTime = new EventDateTime()
                .setTimeZone("Europe/Moscow")
                .setDateTime(endDateTime);
        currentEvent.setStart(startTime)
                .setEnd(endTime);

    }

    private void delete(int id) throws IOException {
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = calendar.events().list("primary")
                .setMaxResults(id + 1)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        List<Event> items = events.getItems();
        calendar.events().delete("primary", items.get(id - 1).getId()).execute();
    }
}