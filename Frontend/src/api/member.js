import { client, clientWithToken } from "./client";

const MemberURL = 'member/';

export const getUserInfo = (memberId) => {
    return clientWithToken().get(MemberURL + 'check', { params: { memberId } });
  };

export const getMyUserInfo = () => {
  return clientWithToken().get(MemberURL + 'me/check');
};
