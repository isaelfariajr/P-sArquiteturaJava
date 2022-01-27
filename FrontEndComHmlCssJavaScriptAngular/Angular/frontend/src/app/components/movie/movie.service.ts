import { SharedService } from '../shared/shared.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EMPTY, Observable } from 'rxjs';
import { Movie } from './movie-model';
import { catchError, map } from 'rxjs/operators';

import { API } from '../../app.api'; 

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  constructor(private http: HttpClient, private sharedService: SharedService) { }

  //Tratando erro
  handlerError(error: any): Observable<any>{
    let errorMessage
    if(error.error instanceof ErrorEvent){
      errorMessage = error.error.message
    }else{
      errorMessage = `Error: ${error.status}\message: ${error.message}`; 
    }

    this.sharedService.showMessage(errorMessage, true)
    return EMPTY;
  }

  create(movie: Movie): Observable<Movie>{ //Chama adição de filmes
    //console.log("Filme cadastrado com sucesso")
    const uri = `${API}/movies`;
    return this.http.post<Movie>(uri, movie).pipe( 
      map(obj => obj),
      catchError((e) => this.handlerError(e)) //Faz o pipe para tratar o erro e mandar para o metodo de erro
    );
  }
 
  index():Observable<Movie[]> { //Mostra já cadastrados
    const uri = `${API}/movies`;
    return this.http.get<Movie[]>(uri).pipe( 
      map(obj => obj),
      catchError((e) => this.handlerError(e)) //Faz o pipe para tratar o erro e mandar para o metodo de erro
    );
  }

  getById(id: string): Observable<Movie>{
    const uri = `${API}/movies/${id}`;
    return this.http.get<Movie>(uri).pipe( 
      map(obj => obj),
      catchError((e) => this.handlerError(e)) //Faz o pipe para tratar o erro e mandar para o metodo de erro
    );
  }

  update(movie: Movie): Observable<Movie> {
    const uri = `${API}/movies/${movie.id}`;
    return this.http.put<Movie>(uri, movie).pipe( 
      map(obj => obj),
      catchError((e) => this.handlerError(e)) //Faz o pipe para tratar o erro e mandar para o metodo de erro
    );
  }

  delete(id: number): Observable<Movie> {
    const uri = `${API}/movies/${id}`;
    return this.http.delete<Movie>(uri).pipe( 
      map(obj => obj),
      catchError((e) => this.handlerError(e)) //Faz o pipe para tratar o erro e mandar para o metodo de erro
    );
  }

}
