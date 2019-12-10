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
            List<String> eventProperties = new ArrayList<>();
            System.out.println("Enter summary: ");
            eventProperties.add(input.nextLine());
            System.out.println("Enter location: ");
            eventProperties.add(input.nextLine());
            System.out.println("Enter description: ");
            eventProperties.add(input.nextLine());
            System.out.println("Enter start date time according to YY-MM-DDThh:mm:ss+3:00 : "); //"2019-11-12T02:02:02+03:00"
            eventProperties.add(input.nextLine());
            System.out.println("Enter end date time according to YY-MM-DDThh:mm:ss+3:00 : ");
            eventProperties.add(input.nextLine());
            System.out.println("Do you need reminder? y/n");
            eventProperties.add(input.nextLine());
            if (eventProperties.get(5).equals("y")){
                System.out.println("Enter time of reminder executes before event: ");
                eventProperties.add(input.nextLine());
            }

            create(eventProperties);
            return;
        }

        if (option.equals("3")) {
            System.out.println("Enter event number for editing: ");
            input = new Scanner(System.in);
            String counts = input.nextLine();
            if (counts.matches("[1-9]+")) {
                List<String> eventProperties = new ArrayList<>();
                System.out.println("Enter summary: ");
                eventProperties.add(input.nextLine());
                System.out.println("Enter location: ");
                eventProperties.add(input.nextLine());
                System.out.println("Enter description: ");
                eventProperties.add(input.nextLine());
                System.out.println("Enter start date time according to YY-MM-DDThh:mm:ss+3:00 : "); //"2019-11-12T02:02:02+03:00"
                eventProperties.add(input.nextLine());
                System.out.println("Enter end date time according to YY-MM-DDThh:mm:ss+3:00 : ");
                eventProperties.add(input.nextLine());
                edit(Integer.parseInt(counts),eventProperties);
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

    public Event create(List<String> eventProperties) throws IOException {

        Event event = new Event();

        initializeEvent(event, eventProperties);

        if (eventProperties.get(5).equals("y")) {
            EventReminder reminder = new EventReminder();
            reminder.setMethod("email");
            reminder.setMinutes(Integer.parseInt(eventProperties.get(6)));

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
        return event;
    }

    private Event edit(int id, List<String> eventProperties) throws IOException {
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = calendar.events().list("primary")
                .setMaxResults(id + 1)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        List<Event> items = events.getItems();

        Scanner input = new Scanner(System.in);

        Event currentEvent = items.get(id - 1);

        initializeEvent(currentEvent, eventProperties);

        String calendarId = "primary";
        calendar.events().update(calendarId, currentEvent.getId(), currentEvent).execute();
        return currentEvent;
    }

    private void initializeEvent(Event currentEvent, List<String> eventProperties) {
        if (!(eventProperties.get(0).equals(""))) {
            currentEvent.setSummary(eventProperties.get(0));
        }

        if (!(eventProperties.get(1).equals(""))) {
            currentEvent.setLocation(eventProperties.get(1));
        }

        if (!(eventProperties.get(2).equals(""))) {
            currentEvent.setDescription(eventProperties.get(2));
        }

        DateTime startDateTime = new DateTime(eventProperties.get(3));
        EventDateTime startTime = new EventDateTime()
                .setTimeZone("Europe/Moscow")
                .setDateTime(startDateTime);

        DateTime endDateTime = new DateTime(eventProperties.get(4));
        EventDateTime endTime = new EventDateTime()
                .setTimeZone("Europe/Moscow")
                .setDateTime(endDateTime);
        currentEvent.setStart(startTime)
                .setEnd(endTime);

    }

    public Calendar getCalendar() {
        return calendar;
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