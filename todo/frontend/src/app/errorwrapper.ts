export class ErrorWrapper {

  status: number;
  statusText: string;

  constructor(obj?: any) {
    this.status              = obj && obj.status             || null;
    this.statusText           = obj && obj.statusText          || null;
  }

}
