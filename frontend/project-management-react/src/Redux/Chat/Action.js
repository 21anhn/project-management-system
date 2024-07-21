import api from "@/config/api";
import * as actionTypes from "./ActionType";

export const sendMessage = (messageData) => {
  return async (dispatch) => {
    dispatch({ type: actionTypes.SEND_MESSAGE_REQUEST });
    try {
      const response = await api.post("/api/messages/send", messageData);
      dispatch({
        type: actionTypes.SEND_MESSAGE_SUCCESS,
        message: response.data,
      });
      console.log("ms", response.data)
    } catch (error) {
      console.log(error);
      dispatch({
        type: actionTypes.SEND_MESSAGE_FAILURE,
        error: error.message,
      });
    }
  };
};

export const fetchChatByProject = (projectId) => {
  return async (dispatch) => {
    dispatch({ type: actionTypes.FETCH_CHAT_BY_PROJECT_REQUEST });
    try {
      const response = await api.get(`/api/projects/${projectId}/chat`);
      dispatch({
        type: actionTypes.FETCH_CHAT_BY_PROJECT_SUCCESS,
        chat: response.data,
      });
    } catch (error) {
      console.log(error);
      dispatch({
        type: actionTypes.FETCH_CHAT_BY_PROJECT_FAILURE,
        error: error.message,
      });
    }
  };
};

export const fetchChatMessages = (chatId) => {
  return async (dispatch) => {
    dispatch({ type: actionTypes.FETCH_MESSAGES_REQUEST });
    try {
      const response = await api.get(`/api/messages/chat/${chatId}`);
      dispatch({
        type: actionTypes.FETCH_CHAT_MESSAGES_SUCCESS,
        chatId,
        messages: response.data,
      });
    } catch (error) {
      console.log(error);
      dispatch({
        type: actionTypes.FETCH_CHAT_MESSAGES_FAILURE,
        error: error.message,
      });
    }
  };
};
