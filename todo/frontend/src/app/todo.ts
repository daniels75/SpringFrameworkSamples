export class Todo {
  id: number;
  title: string = '';
  complete: boolean;
  priority: number = 0;

  constructor(values: Object = {}) {
    Object.assign(this, values)
  }

}
