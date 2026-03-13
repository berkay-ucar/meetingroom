import FullCalendar from "@fullcalendar/react"
import dayGridPlugin from "@fullcalendar/daygrid"
import timeGridPlugin from "@fullcalendar/timegrid"
import axios from "axios"
import { useEffect, useState } from "react"

function CalendarPage(){

    const [events,setEvents] = useState([])

    useEffect(()=>{

        axios.get("http://localhost:8080/api/calendar")
            .then(res=>{

                setEvents(res.data)

            })

    },[])

    return(

        <div style={{padding:"40px"}}>

            <h2>Meeting Calendar</h2>

            <FullCalendar
                plugins={[dayGridPlugin,timeGridPlugin]}
                initialView="timeGridWeek"
                events={events}
            />

        </div>

    )

}

export default CalendarPage