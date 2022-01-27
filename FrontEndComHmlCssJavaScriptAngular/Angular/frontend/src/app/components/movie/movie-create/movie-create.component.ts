import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SharedService } from '../../shared/shared.service';
import { Movie } from '../movie-model';
import { MovieService } from '../movie.service';

@Component({
  selector: 'app-movie-create',
  templateUrl: './movie-create.component.html',
  styleUrls: ['./movie-create.component.css']
})
export class MovieCreateComponent implements OnInit {

years = [
  2010,
  2011,
  2012,
  2013,
  2014,
  2015,
  2016,
  2017,
  2018,
  2019,
  2020,
  2021
];

//Mock
movie: Movie = {
  title: "",
  director: "",
  year: "",
  genres: ""
}

  constructor(
    private router: Router, 
    private movieService: MovieService, 
    private sharedService: SharedService,
    private fb: FormBuilder //Para validação de form vazio ao incluir    
  ) {}
  
  createForm!: FormGroup;

  ngOnInit(): void {
    this.createForm = this.fb.group({ // Validação de form vazio
      title: ["", Validators.required],
      director: ["", Validators.required],
      year: ["", Validators.required],
      genres: ["", Validators.required],
    })    
  }

  createMovie(): void{
    if(this.createForm.valid){
        this.movieService.create(this.movie).subscribe(() => {
          this.sharedService.showMessage("Filme Adicionado com Sucesso");
          this.router.navigate(["/movies"]);
        });
    }    
  }

  cancel(){
    this.router.navigate(["/movies"]);
  }
}
