import {Errordetails} from "./errordetails";

export class ErrorWrapper {

  public status: number;
  public statusText: string;
  public details: Errordetails[] = [];

  constructor(obj?: any) {
    this.status              = obj && obj.status             || null;
    this.statusText           = obj && obj.statusText          || null;
  }

}
