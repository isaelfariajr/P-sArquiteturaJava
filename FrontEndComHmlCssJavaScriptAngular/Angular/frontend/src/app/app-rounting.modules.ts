import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

import { HomeComponent } from "./views/home/home.component";
import { MoviesComponent } from "./views/movies/movies.component";
import { SeriesComponent } from "./views/series/series.component";
import { MovieCreateComponent } from "./components/movie/movie-create/movie-create.component"
import { SeriesCreateComponent } from "./components/serie/series-create/series-create.component";

const routes: Routes = [{
    path: "",
    component: HomeComponent,
},
{
    path: "movies",
    component: MoviesComponent,
},
{
    path: "movies/create",
    component: MovieCreateComponent,
},
{
    path: "series",
    component: SeriesComponent,
},
{
    path: "series/create",
    component: SeriesCreateComponent,
}];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {}