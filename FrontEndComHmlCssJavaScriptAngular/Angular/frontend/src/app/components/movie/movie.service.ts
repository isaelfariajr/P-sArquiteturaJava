import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Movie } from './movie-model';

import { API } from '../../app.api'; 

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  constructor(private http: HttpClient) { }

  create(movie: Movie): Observable<Movie>{ //Chama adição de filmes
    //console.log("Filme cadastrado com sucesso")
    const uri = `${API}/movies`;
    return this.http.post<Movie>(uri, movie);
  }
 
  index():Observable<Movie[]> { //Mostra já cadastrados
    const uri = `${API}/movies`;
    return this.http.get<Movie[]>(uri);
  }

  getById(id: string): Observable<Movie>{
    const uri = `${API}/movies/${id}`;
    return this.http.get<Movie>(uri);
  }

  update(movie: Movie): Observable<Movie> {
    const uri = `${API}/movies/${movie.id}`;
    return this.http.put<Movie>(uri, movie);
  }

  delete(id: number): Observable<Movie> {
    const uri = `${API}/movies/${id}`;
    return this.http.delete<Movie>(uri);
  }

}
