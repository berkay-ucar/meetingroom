import axios from "axios"
import { useState } from "react"

function CreateMeeting(){

    const [title,setTitle] = useState("")
    const [roomId,setRoomId] = useState()
    const [participants,setParticipants] = useState([])

    const submit = async () => {

        await axios.post("/api/reservations",{
            roomId,
            organizerId:1,
            participants,
            start:"2026-03-12T10:00",
            end:"2026-03-12T11:00"
        })

    }

}

export default CreateMeeting