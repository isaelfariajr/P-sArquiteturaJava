import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class SharedService {

  constructor(private snackBar: MatSnackBar) {}//import
    
  showMessage(msg: string, isError: boolean = false): void {  //Criação de msg
      this.snackBar.open(msg, 'X', {
        duration: 3000,
        horizontalPosition: "right",
        verticalPosition: "top",
        panelClass: isError ? ['snack-message-error'] : ['snack-message-sucess']
      })
  } 
}
