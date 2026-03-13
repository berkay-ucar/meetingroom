import FullCalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import interactionPlugin from "@fullcalendar/interaction";
import { useEffect, useState } from "react";
import api from "../api/api";

function CalendarPage() {
    const [events, setEvents] = useState([]);
    const [selectedEvent, setSelectedEvent] = useState(null);

    useEffect(() => {
        api.get("/reservations/calendar/me").then(res => setEvents(res.data));
    }, []);

    const handleEventDrop = (info) => {
        api.put(`/reservations/${info.event.id}`, {
            start: info.event.start,
            end: info.event.end
        });
        alert(`Meeting moved to ${info.event.start}`);
    };

    const handleEventClick = (info) => {
        setSelectedEvent(info.event);
        alert(`Title: ${info.event.title}\nStart: ${info.event.start}\nEnd: ${info.event.end}`);
    };

    return (
        <FullCalendar
            plugins={[dayGridPlugin, timeGridPlugin, interactionPlugin]}
            initialView="timeGridWeek"
            editable={true}
            events={events}
            eventDrop={handleEventDrop}
            eventClick={handleEventClick}
        />
    );
}

export default CalendarPage;