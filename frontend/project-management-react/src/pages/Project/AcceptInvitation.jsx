import { acceptInvatation } from "@/Redux/Project/Action";
import { Button } from "@/components/ui/button";
import { useDispatch } from "react-redux";
import { useLocation, useNavigate } from "react-router-dom";

const AcceptInvitation = () => {
  const dispatch = useDispatch();
  const location = useLocation();  
  const navigate = useNavigate();

  const handleAcceptInvitation = () => {
    const urlParams = new URLSearchParams(location.search);
    const token = urlParams.get("token");
    console.log(token);
    console.log(urlParams)
    dispatch(acceptInvatation({ token, navigate }));
  };

  return (
    <div className="h-[85vh] flex flex-col justify-center items-center">
      <h1 className="py-5 font-semibold text-xl">
        You are invited to join the project
      </h1>
      <Button onClick={handleAcceptInvitation}>Accept Invitation</Button>
    </div>
  );
};

export default AcceptInvitation;
