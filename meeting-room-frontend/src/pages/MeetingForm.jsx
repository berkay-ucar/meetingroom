import { useState } from "react";
import api from "../api/api";

function MeetingForm() {
    const [title, setTitle] = useState("");
    const [roomId, setRoomId] = useState();
    const [start, setStart] = useState();
    const [end, setEnd] = useState();

    const submit = async () => {
        await api.post("/reservations", { title, roomId, start, end });
        alert("Meeting created");
    };

    return (
        <div>
            <input placeholder="Title" onChange={e => setTitle(e.target.value)} />
            <input placeholder="Room Id" onChange={e => setRoomId(e.target.value)} />
            <input type="datetime-local" onChange={e => setStart(e.target.value)} />
            <input type="datetime-local" onChange={e => setEnd(e.target.value)} />
            <button onClick={submit}>Create Meeting</button>
        </div>
    );
}

export default MeetingForm;