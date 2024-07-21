import * as actionTypes from "./ActionTypes";

import api from "@/config/api";

export const getUserSubscription = (jwt) => {
  return async (dispatch) => {
    dispatch({ type: actionTypes.GET_USER_SUBSCRIPTION_REQUEST });
    try {
      const response = await api.get("/api/subscription/user", {
        headers: {
          Authorization: `Bearer ${jwt}`,
        },
      });
      dispatch({
        type: actionTypes.GET_USER_SUBSCRIPTION_SUCCESS,
        payload: response.data,
      });
      console.log("subscription", response.data);
    } catch (error) {
      console.log(error);
      dispatch({
        type: actionTypes.GET_USER_SUBSCRIPTION_FAILURE,
        payload: error.message,
      });
    }
  };
};

export const upgradeSubscription = ({ planType }) => {
  return async (dispatch) => {
    dispatch({ type: actionTypes.UPGRADE_SUBSCRIPTION_REQUEST });
    try {
      const response = await api.put("/api/subscription/upgrade", null, {
        params: {
            planType: planType,
        }
      });
      dispatch({
        type: actionTypes.UPGRADE_SUBSCRIPTION_SUCCESS,
        payload: response.data,
      });
      console.log("upgradeSubscription", response.data);
    } catch (error) {
      console.log(error.response.data);
      dispatch({
        type: actionTypes.UPGRADE_SUBSCRIPTION_FAILURE,
        payload: error.message,
      });
    }
  };
};
