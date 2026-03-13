import MeetingForm from "../pages/MeetingForm";
import RoomForm from "../pages/RoomForm";
import GroupForm from "../pages/GroupForm";

function AdminPage() {
    return (
        <div>
            <h2>Admin Dashboard</h2>
            <MeetingForm />
            <RoomForm />
            <GroupForm />
        </div>
    );
}

export default AdminPage;