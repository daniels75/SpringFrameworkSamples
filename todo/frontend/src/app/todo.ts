export class Todo {
  id: number;
  title: string = '';
  complete: boolean;
  order: number = 0;

  constructor(values: Object = {}) {
    Object.assign(this, values)
  }


}
