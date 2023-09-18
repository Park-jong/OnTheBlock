import { client, clientWithToken } from "./client";

const SessionURL = "session/";

export const registSession = (session, file) => {
  const formData = new FormData();
  formData.append("session", JSON.stringify(session));
  formData.append("file", file);

  return clientWithToken().post(SessionURL, formData, {
    headers: {
      "Content-Type": "multipart/form-data", // Set the content type to multipart/form-data
    },
  });
};
