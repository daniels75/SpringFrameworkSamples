import {Errordetails} from "./errordetails";

export class ErrorWrapper {

  status: number;
  statusText: string;
  details: Errordetails[] = [];

  constructor(obj?: any) {
    this.status              = obj && obj.status             || null;
    this.statusText           = obj && obj.statusText          || null;
  }

}
