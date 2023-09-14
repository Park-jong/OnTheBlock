import { client, clientWithToken } from "./client";

const testURL = 'instrument/';

export const getMemberInfo = () => {
    return client().get(testURL + 'findAll');

};