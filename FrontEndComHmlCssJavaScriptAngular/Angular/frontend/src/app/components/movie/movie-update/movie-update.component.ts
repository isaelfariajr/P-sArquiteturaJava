import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SharedService } from '../../shared/shared.service';
import { Movie } from '../movie-model';
import { MovieService } from '../movie.service';

@Component({
  selector: 'app-movie-update',
  templateUrl: './movie-update.component.html',
  styleUrls: ['./movie-update.component.css']
})
export class MovieUpdateComponent implements OnInit {

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
  //Pegando o id da url
  const id = this.route.snapshot.params.id
  //Passando o id para o metodo
  this.movieService.getById(id).subscribe(movie => {
    this.movie = movie
  })
}

  updateMovie(): void{
    this.movieService.update(this.movie).subscribe(() => {
      this.sharedService.showMessage("Filme Atualizado com Sucesso");
      this.router.navigate(["/movies"]);
    });
  }

  cancel(){
    this.router.navigate(["/movies"]);
  }
}
