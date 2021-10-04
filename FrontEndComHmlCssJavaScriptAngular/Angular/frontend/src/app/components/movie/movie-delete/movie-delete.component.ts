import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SharedService } from '../../shared/shared.service';
import { MovieService } from '../movie.service';
import { Movie } from '../movie-model';

@Component({
  selector: 'app-movie-delete',
  templateUrl: './movie-delete.component.html',
  styleUrls: ['./movie-delete.component.css']
})
export class MovieDeleteComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private movieService: MovieService,
    private sharedService: SharedService
  ) {}

  //Mock
  movie: Movie = {
    title: "",
    director: "",
    year: "",
    genres: ""
  }

  ngOnInit(): void {
    const id = this.route.snapshot.params.id

    this.movieService.getById(id).subscribe(movie => {
      this.movie = movie
    })
  }

  deleteMovie(): void{
        
    let id:number = this.movie.id as number;
    this.movieService.delete(id).subscribe(() => {
      this.sharedService.showMessage("Filme Removido com Sucesso");
      this.router.navigate(["/movies"]);
    });
  }

  cancel(){
    this.router.navigate(["/movies"]);
  }

}
