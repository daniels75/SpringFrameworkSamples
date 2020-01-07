import { Injectable } from '@angular/core';

import { Todo } from './todo';
import {forkJoin, from, Observable, of, throwError} from 'rxjs';
import {HttpClient} from "@angular/common/http";
import {environment} from "../environments/environment";
import { catchError, map, tap } from 'rxjs/operators';
import {ErrorWrapper} from "./errorwrapper";
import {Errordetails} from "./errordetails";


const API_URL = environment.apiUrl;

@Injectable()
export class ApiService {

  constructor(private http: HttpClient) {
  }

  public createTodo(todo: Todo): Observable<Todo> {
    return this.http
      .post<Todo>(API_URL + '/todos', todo)
      .pipe(
        map((result: Todo) => {
          console.log("Created todo: " + JSON.stringify(result));
          return result;
        }),
        catchError(this.handleError<Todo>('createTodo'))
      );
  }

  public getTodoById(todoId: number): Observable<Todo> {
    return this.http
      .get<Todo>(API_URL + '/todos/' + todoId)
      .pipe(
        catchError(this.handleError<Todo>('getTodoById'))
      );
  }

  public updateTodo(todo: Todo): Observable<Todo> {
    return this.http
      .put<Todo>(API_URL + '/todos/' + todo.id, todo)
      .pipe(
        catchError(this.handleError<Todo>('updateTodo'))
      );
  }

  public updateTodos(todos: Todo[]): Observable<any> {

    let multiUpd: Observable<any>[] = [];

    from(todos).subscribe((todo: Todo) => {
      console.log("From: " + todo.id);
      multiUpd.push(this.http.put<Todo>(API_URL + '/todos/' + todo.id, todo).pipe(
        catchError(this.handleError<Todo>('updateTodo'))
      ));
    });

    todos.forEach((todo: Todo) => {
      //console.log(todo.id);
      // multi.push(this.http.put<Todo>(API_URL + '/todos/' + todo.id, todo))
    });

    /*
    let todo1: Todo = todos[0];
    let todo2: Todo = todos[1];
    let todo3: Todo = todos[2];
    return forkJoin([
      this.http.put<Todo>(API_URL + '/todos/' + todo1.id, todo1),
      this.http.put<Todo>(API_URL + '/todos/' + todo2.id, todo2),
      this.http.put<Todo>(API_URL + '/todos/' + todo3.id, todo3)
    ]);
     */

   //return of([]);

    return forkJoin(multiUpd);
  }

  public updateTodos2(todos: Todo[]): Observable<any> {
    return this.http
      .put<Todo[]>(API_URL + '/todos/', todos)
      .pipe(
        catchError(this.handleError<Todo>('updateTodo'))
      );
  }

   public deleteTodoById(todoId: number): Observable<Todo> {
    return this.http
      .delete<Todo>(API_URL + '/todos/' + todoId)
      .pipe(
        catchError(this.handleError<Todo>('deleteTodoById'))
      );
  }

  public getAllTodos (): Observable<Todo[]> {
    return this.http.get<Todo[]>(API_URL + '/todos')
      .pipe(
        tap(_ => {
          const titles = _.map(function(item) {
            return item['title'];
          });
          console.log('Fetched todos: ' + titles)
        }),
        map((todoList: Todo[]) =>  {
          const simpleTodo:Todo[] = [
            new Todo({id: 1, title: "first  title"}),
            new Todo({id: 2, title: "second title"})
          ]

          const todoListExt = todoList.map((item) => item);

          const orderedTodo:Todo[] = todoList.sort((item1, item2) => {
            return item1.priority - item2.priority;
          });

          const todoFiltered = todoList.filter((item:Todo) => !item.title.includes('aaa'));


          return orderedTodo;

          //return todoList;

        }),
        catchError(this.handleError<Todo[]>('getAllTodos', []))
      );
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error("Error in the response: " + JSON.stringify(error.error)); // log to console instead

      // Let the app keep running by returning an empty result.

      let obj: ErrorWrapper = JSON.parse(JSON.stringify(error));

      let errorsDetails: Errordetails[] = [];
      for (let detail of error.error.errors) {
        errorsDetails.push(new Errordetails({
          message: detail.message,
          details: detail.details
        }));
      }

      let errorWrapper: any = new ErrorWrapper({
        status: error.status,
        statusText: error.statusText
      });

      errorWrapper.details = errorsDetails;

      return throwError(errorWrapper);
    }
  };


}
