import { client,clientWithToken } from "./client";

const InstrumentURL = 'instrument/';

export const getAllInstruments = () => {
    return client().get(InstrumentURL + 'findAll');
  };