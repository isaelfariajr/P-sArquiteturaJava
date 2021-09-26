import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-series-create',
  templateUrl: './series-create.component.html',
  styleUrls: ['./series-create.component.css']
})
export class SeriesCreateComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  createSerie(){
    console.log('Salvar');
  }

  cancel(){
    this.router.navigate(["/series"]);
  }

}
