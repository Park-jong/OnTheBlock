import { client, clientWithToken } from "./client";

const MemberURL = 'member/';

export const getProfileInfo = () => {
    return client().get(MemberURL + 'check');
  };

