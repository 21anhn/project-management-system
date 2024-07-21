import * as actionsTypes from "./ActionTypes";
import api from "@/config/api";

export const createComment = (commentData) => {
  return async (dispatch) => {
    dispatch({ type: actionsTypes.CREATE_COMMENT_REQUEST });
    try {
      const response = await api.post(`/api/comments`, commentData);
      console.log("comment created", response.data);
      dispatch({
        type: actionsTypes.CREATE_COMMENT_SUCCESS,
        comment: response.data,
      });
    } catch (error) {
      console.log("error", error);
      dispatch({
        type: actionsTypes.CREATE_COMMENT_FAILURE,
        error: error.message,
      });
    }
  };
};

export const deleteComment = (commentId) => {
  return async (dispatch) => {
    dispatch({ type: actionsTypes.DELETE_COMMENT_REQUEST });
    try {
      const response = await api.delete(`/api/comments/${commentId}`);
      console.log("comment deleted", response.data);
      dispatch({
        type: actionsTypes.DELETE_COMMENT_SUCCESS,
        commentId,
      });
    } catch (error) {
      console.log("error", error);
      dispatch({
        type: actionsTypes.DELETE_COMMENT_FAILURE,
        error: error.message,
      });
    }
  };
};

export const fetchComments = (issueId) => {
    return async (dispatch) => {
      dispatch({ type: actionsTypes.FETCH_COMMENTS_REQUEST });
      try {
        const response = await api.get(`/api/comments/${issueId}`);
        
        dispatch({
          type: actionsTypes.FETCH_COMMENTS_SUCCESS,
          comments: response.data,
        });
      } catch (error) {
        console.log("error", error);
        dispatch({
          type: actionsTypes.FETCH_COMMENTS_FAILURE,
          error: error.message,
        });
      }
    };
  };