export class Errordetails {
  message: string;
  details: string;

  constructor(obj?: any) {
    this.message              = obj && obj.message             || null;
    this.details           = obj && obj.details          || null;
  }
}
