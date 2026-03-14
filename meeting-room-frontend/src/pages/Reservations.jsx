import { useEffect, useState } from "react";
import API from "../api/api";
import ReservationCalendar from "../ReservationCalendar.jsx";

export default function Reservations() {

    const [events,setEvents] = useState([]);

    useEffect(() => {
        API.get("/reservations").then(res => {
            const mapped = res.data.map(r => ({
                title: "Meeting Room",
                start: r.startTime,
                end: r.endTime
            }));
            setEvents(mapped);
        });
    }, []);

    return (
        <div>
            <h2>Toplantılar</h2>
            <ReservationCalendar events={events}/>
        </div>
    );
}