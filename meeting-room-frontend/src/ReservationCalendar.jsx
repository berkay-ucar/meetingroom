import React from "react";
import FullCalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import { users, meetings } from "./components/data.jsx";
import './assets/css/fullcalendar.css';

// Renkler: kullanıcı başına
const colors = ['#3788d8', '#28a745', '#ffc107', '#dc3545'];

export default function ReservationCalendar() {
    // Meetings → FullCalendar events
    const events = meetings.map(meeting => {
        const participantNames = meeting.participants.map(id => users.find(u => u.id === id).name);
        const bgColor = colors[meeting.participants[0] - 1]; // ilk katılımcıya göre renk

        return {
            title: meeting.title,
            date: meeting.date,
            backgroundColor: bgColor,
            extendedProps: { participants: participantNames }
        };
    });

    return (
        <FullCalendar
            plugins={[dayGridPlugin, timeGridPlugin]}
            initialView="dayGridMonth"
            headerToolbar={{
                left: "prev,next today",
                center: "title",
                right: "dayGridMonth,timeGridWeek,timeGridDay"
            }}
            events={events}
            eventContent={arg => (
                <div>
                    <b>{arg.event.title}</b>
                    <div style={{ fontSize: '0.7rem' }}>
                        {arg.event.extendedProps.participants.join(', ')}
                    </div>
                </div>
            )}
        />
    );
}