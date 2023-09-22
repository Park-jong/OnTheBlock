import { client, clientWithToken } from "./client";

const VideoURL = "/videos";

export const getVideo = (videoId) => {
  return clientWithToken().get( VideoURL + "/" + videoId +"/detail");
};

//To-Do : 필요한 갯수만큼 가져오도록 백엔드 수정요망.
export const getLatestVideoList = () => {
  return clientWithToken().get(VideoURL + "/getLatestVideos");
};

export const postComment = (videoId, data) => {
  
}