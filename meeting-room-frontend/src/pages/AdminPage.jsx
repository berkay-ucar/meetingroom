import { useState } from "react"
import axios from "axios"

function AdminPage(){

    const [roomId,setRoomId] = useState("")
    const [start,setStart] = useState("")
    const [end,setEnd] = useState("")

    const submit = async () => {

        await axios.post("http://localhost:8080/api/reservations",{

            roomId,
            organizerId:1,
            start,
            end

        })

        alert("Meeting created")

    }

    return(

        <div>

            <h2>Toplantı Oluştur</h2>

            <input
                placeholder="Oda Id"
                onChange={e=>setRoomId(e.target.value)}
            />

            <input
                type="datetime-local"
                onChange={e=>setStart(e.target.value)}
            />

            <input
                type="datetime-local"
                onChange={e=>setEnd(e.target.value)}
            />

            <button onClick={submit}>Oluştur</button>

        </div>

    )

}

export default AdminPage