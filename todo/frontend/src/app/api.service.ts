import { Injectable } from '@angular/core';

import { Todo } from './todo';
import {Observable, of} from 'rxjs';
import {HttpClient} from "@angular/common/http";
import {environment} from "../environments/environment";
import { catchError, map, tap } from 'rxjs/operators';


const API_URL = environment.apiUrl;

@Injectable()
export class ApiService {

  constructor(private http: HttpClient) {
  }

  getAllTodos (): Observable<Todo[]> {
    return this.http.get<Todo[]>(API_URL + '/todos')
      .pipe(
        tap(_ => {
          const titles = _.map(function(item) {
            return item['title'];
          });
          console.log('Fetched todos: ' + titles)
        }),
        catchError(this.handleError<Todo[]>('getAllTodos', []))
      );
  }

  public createTodo(todo: Todo): Observable<Todo> {
    return this.http
      .post<Todo>(API_URL + '/todos', todo)
      .pipe(
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

   public deleteTodoById(todoId: number): Observable<Todo> {
    return this.http
      .delete<Todo>(API_URL + '/todos/' + todoId)
      .pipe(
        catchError(this.handleError<Todo>('deleteTodoById'))
      );
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    }
  };


}
